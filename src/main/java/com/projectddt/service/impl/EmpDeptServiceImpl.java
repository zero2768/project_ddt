package com.projectddt.service.impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.projectddt.exception.BusinessLogicException;
import com.projectddt.model.EmpDept;
import com.projectddt.repository.EmpDeptRepository;
import com.projectddt.service.EmpDeptService;

import lombok.Synchronized;

@Service
public class EmpDeptServiceImpl implements EmpDeptService {
	
	@Autowired
	private EmpDeptRepository empDeptRepo;
	
	@Override
	@Transactional
	@Synchronized
	public void addEmpDept(EmpDept empDept) throws BusinessLogicException {
		
		if(!StringUtils.isEmpty(empDept.getEmpDeptId()) && !StringUtils.isEmpty(empDept.getEmpDeptName())) {
			// 判斷是否為存在的部門ID
			// 當前案例允許相同部門Name
			if (!this.deptExists(empDept.getEmpDeptId())) {
				empDeptRepo.save(empDept);
			} else {
				throw new BusinessLogicException("已存在的部門代碼", HttpStatus.NOT_FOUND);
			}
		}else {
			throw new BusinessLogicException("部門代碼&&部門名稱皆為必填", HttpStatus.NOT_FOUND);
		}
	}

	@Override
	@Transactional
	@Synchronized
	public void updateEmpDept(EmpDept empDept) throws BusinessLogicException {
		// 判斷部門資料是否存在
		if (this.deptExists(empDept.getEmpDeptId())) {
			empDeptRepo.save(empDept);
		} else {
			throw new BusinessLogicException("不存在的部門代碼", HttpStatus.NOT_FOUND);
		}
	}

	@Override
	@Transactional
	@Synchronized
	public void deleteEmpDept(String empDeptId) throws BusinessLogicException {
		// 判斷部門資料是否存在
		if (this.deptExists(empDeptId)) {
			empDeptRepo.deleteById(empDeptId);
		} else {
			throw new BusinessLogicException("不存在的部門代碼", HttpStatus.NOT_FOUND);
		}
	}
	
	@Override
    public boolean deptExists(String empDeptId) {
		return empDeptRepo.existsById(empDeptId);
	}

}
