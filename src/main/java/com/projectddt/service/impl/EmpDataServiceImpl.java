package com.projectddt.service.impl;

import java.time.LocalDateTime;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.projectddt.service.EmpDataService;

import lombok.Synchronized;

import com.projectddt.repository.EmpRepository;
import com.projectddt.repository.EmpDeptRepository;
import com.projectddt.exception.BusinessLogicException;
import com.projectddt.model.EmpDataMaster;

@Service
public class EmpDataServiceImpl implements EmpDataService {

	@Autowired
	private EmpRepository empRepo;
	@Autowired
	private EmpDeptRepository empDeptRepo;

//    @Autowired
//    public EmployeeServiceImpl(EmployeeRepository employeeRepository,DepartmentRepository departmentRepository) {
//    	this.employeeRepository = employeeRepository;
//        this.departmentRepository = departmentRepository;
//    }

	@Override
	@Transactional
	@Synchronized
	public void addEmpData(EmpDataMaster empData) throws BusinessLogicException {
		// 判斷部門資料是否存在
		if (this.deptExists(empData.getEmpDeptId())) {
			empData.setCreateTime(LocalDateTime.now());
			empData.setUpdateTime(LocalDateTime.now());
			
			empRepo.save(empData);
		} else {
			throw new BusinessLogicException("不存在的部門代碼", HttpStatus.NOT_FOUND);
		}
	}

	@Override
	@Transactional
	@Synchronized
	public void updateEmpData(EmpDataMaster empData) throws BusinessLogicException {
		// 判斷員工是否存在
		Optional<EmpDataMaster> existsEmp = this.findByEmp(empData.getEmpNo());
		
		if(existsEmp.isPresent()) {
			EmpDataMaster returnEmpData = existsEmp.get();
			// 判斷部門資料是否存在
			if (this.deptExists(empData.getEmpDeptId())) {
//				empData = transformEmpData(empData, empDataVo);
				returnEmpData.setEmpDeptId(StringUtils.isEmpty(empData.getEmpDeptId()) ? returnEmpData.getEmpDeptId() : empData.getEmpDeptId());
				returnEmpData.setEmpName(StringUtils.isEmpty(empData.getEmpName()) ? returnEmpData.getEmpName() : empData.getEmpName());
				returnEmpData.setEmpGender(StringUtils.isEmpty(empData.getEmpGender()) ? returnEmpData.getEmpGender() : empData.getEmpGender());
				returnEmpData.setEmpPhoneNo(StringUtils.isEmpty(empData.getEmpPhoneNo()) ? returnEmpData.getEmpPhoneNo() : empData.getEmpPhoneNo());
				returnEmpData.setEmpAddress(StringUtils.isEmpty(empData.getEmpAddress()) ? returnEmpData.getEmpAddress() : empData.getEmpAddress());
				returnEmpData.setEmpAge(null != empData.getEmpAge() ? returnEmpData.getEmpAge() : empData.getEmpAge());
				returnEmpData.setUpdateTime(LocalDateTime.now());
				
				empRepo.save(returnEmpData);
			} else {
				throw new BusinessLogicException("不存在的部門代碼", HttpStatus.NOT_FOUND);
			}
		}else {
			throw new BusinessLogicException("查無此員工資料", HttpStatus.NOT_FOUND);
		}		
	}

	@Override
	@Transactional
	@Synchronized
	public void deleteEmpData(Integer empNo) throws BusinessLogicException {
		// 判斷員工是否存在
		Optional<EmpDataMaster> existsEmp = this.findByEmp(empNo);
		
		if(existsEmp.isPresent()) {
			empRepo.delete(existsEmp.get());
		}else {
			throw new BusinessLogicException("查無此員工資料", HttpStatus.NOT_FOUND);
		}		
	}

//	@Override
//	public Page<EmployeeResultVo> getEmployee(EmployeeQueryVo employeeQueryVo,Pageable pageable) throws BusinessLogicException {
//		Page<Employee> pageRecords = employeeRepository.searchEmployeeByPage(employeeQueryVo, pageable);
//		List<EmployeeResultVo> infos = pageRecords.getContent().stream().map(record ->{
//			return EmployeeResultVo.builder()
//					.employeeId(record.getEmployeeId())
//					.employeeName(record.getEmployeeName())
//					.gender(record.getGender().toString())
//					.phone(record.getPhone())
//					.address(record.getAddress())
//					.age(record.getAge())
//					.createTime(record.getCreateTime().toString())
//					.updateTime(record.getUpdateTime().toString())
//					.departmentId(null != record.getDepartment() ? record.getDepartment().getDepartmentId() : null)
//					.departmentName(null != record.getDepartment() ? record.getDepartment().getDepartmentName() : null)
//					.build();
//		}).collect(Collectors.toList());
//		return new PageImpl<>(infos, pageable, pageRecords.getTotalElements());
//	}
	
	@Override
    public boolean deptExists(String deptId) {
		return empDeptRepo.existsById(deptId);
	}
	
	@Override
    public Optional<EmpDataMaster> findByEmp(Integer empNo) {
		return empRepo.findByEmpNo(empNo);
	}

}
