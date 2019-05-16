package com.projectddt.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.projectddt.service.EmpDataService;
import com.projectddt.vo.EmpSearchVo;

import lombok.Synchronized;

import com.projectddt.repository.EmpDataRepository;
import com.projectddt.repository.EmpDeptRepository;
import com.projectddt.exception.BusinessLogicException;
import com.projectddt.model.EmpDataMaster;

@Service
public class EmpDataServiceImpl implements EmpDataService {

	@Autowired
	private EmpDataRepository empRepo;
	
	@Autowired
	private EmpDeptRepository empDeptRepo;

	@Override
	@Transactional(rollbackOn=Exception.class)
	@Synchronized
	public void addEmpData(List<EmpDataMaster> empDataList) throws BusinessLogicException {
		
		for(EmpDataMaster empData : empDataList) {
			// 判斷部門資料是否存在
			if (this.deptExists(empData.getEmpDeptId())) {
				
				empData.setEmpGender(this.genderFilter(empData.getEmpGender()));
				empData.setCreateTime(LocalDateTime.now());
				empData.setUpdateTime(LocalDateTime.now());
				
				empRepo.save(empData);
			} else {
				throw new BusinessLogicException("不存在的部門代碼:" + empData.getEmpDeptId(), HttpStatus.NOT_FOUND);
			}
		}
	}

	@Override
	@Transactional
	@Synchronized
	public void updateEmpData(EmpDataMaster empData) throws BusinessLogicException {
		// 判斷員工是否存在
		Optional<EmpDataMaster> existsEmp = this.findByEmp(empData.getEmpNo());
		
		existsEmp.orElseThrow(() -> new BusinessLogicException("查無此員工資料", HttpStatus.NOT_FOUND));
		
		EmpDataMaster returnEmpData = existsEmp.get();
		// 判斷部門資料是否存在
		if (this.deptExists(empData.getEmpDeptId())) {

			returnEmpData.setEmpDeptId(StringUtils.isEmpty(empData.getEmpDeptId()) ? returnEmpData.getEmpDeptId() : empData.getEmpDeptId());
			returnEmpData.setEmpName(StringUtils.isEmpty(empData.getEmpName()) ? returnEmpData.getEmpName() : empData.getEmpName());
			returnEmpData.setEmpGender(StringUtils.isEmpty(empData.getEmpGender()) ? returnEmpData.getEmpGender() : this.genderFilter(empData.getEmpGender()));
			returnEmpData.setEmpPhoneNo(StringUtils.isEmpty(empData.getEmpPhoneNo()) ? returnEmpData.getEmpPhoneNo() : empData.getEmpPhoneNo());
			returnEmpData.setEmpAddress(StringUtils.isEmpty(empData.getEmpAddress()) ? returnEmpData.getEmpAddress() : empData.getEmpAddress());
			returnEmpData.setEmpAge(null != empData.getEmpAge() ? returnEmpData.getEmpAge() : empData.getEmpAge());
			returnEmpData.setUpdateTime(LocalDateTime.now());
			
			empRepo.save(returnEmpData);
		} else {
			throw new BusinessLogicException("不存在的部門代碼", HttpStatus.NOT_FOUND);
		}		
	}

	@Override
	@Transactional
	@Synchronized
	public void deleteEmpData(Integer empNo) throws BusinessLogicException {
		// 判斷員工是否存在
		Optional<EmpDataMaster> existsEmp = this.findByEmp(empNo);
		
		existsEmp.orElseThrow(() -> new BusinessLogicException("查無此員工資料", HttpStatus.NOT_FOUND));
		
		empRepo.delete(existsEmp.get());
	}

	@Override
	public Page<EmpSearchVo> findEmpDataByPage(EmpSearchVo empSearchVo, Pageable pageableSetting) throws BusinessLogicException {
		
		Page<EmpDataMaster> result = empRepo.findEmpDataByPage(empSearchVo, pageableSetting);
		
		List<EmpSearchVo> resultList = result.getContent().stream().map(model ->{
			return EmpSearchVo.builder()
					.empNo(model.getEmpNo())
					.empName(model.getEmpName())
					.empGender(model.getEmpGender())
					.empPhoneNo(model.getEmpPhoneNo())
					.empAddress(model.getEmpAddress())
					.empAge(model.getEmpAge())
					.createTime(model.getCreateTime().toString())
					.updateTime(model.getUpdateTime().toString())
					.empDeptId(null != model.getEmpDept() ? model.getEmpDept().getEmpDeptId() : null)
					.empDeptName(null != model.getEmpDept() ? model.getEmpDept().getEmpDeptName() : null)
					.build();
		}).collect(Collectors.toList());
		return new PageImpl<>(resultList, pageableSetting, result.getTotalElements());
	}

	private String genderFilter(String empGender) {
		
		switch(empGender.toUpperCase()) {

	        case "M":
	        	return "MALE";
	        case "F":
	        	return "FEMALE";
	        default:
	        	return "OTHER";
	    }
	}
	
	@Override
    public Optional<EmpDataMaster> findByEmp(Integer empNo) {
		return empRepo.findByEmpNo(empNo);
	}
	
	@Override
    public boolean deptExists(String empDeptId) {
		return empDeptRepo.existsById(empDeptId);
	}

}
