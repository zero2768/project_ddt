package com.projectddt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projectddt.exception.BusinessLogicException;
import com.projectddt.model.EmpDept;
import com.projectddt.service.EmpDeptService;

@RestController
@RequestMapping("/empDept")
public class EmpDeptController {
	
	@Autowired
	private EmpDeptService empDeptService;
	
	/**
	 * 	新增員工資料
	 * @param EmpDataMaster
	 * @return
	 * @throws BusinessLogicException 
	 */
	@PostMapping
	public HttpEntity<Void> addEmpDept(@RequestBody EmpDept empDept) throws BusinessLogicException {

		empDeptService.addEmpDept(empDept);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}

	/**
	 * 	更新員工資料
	 * @param EmpDataMaster
	 * @param empNo
	 * @return
	 * @throws BusinessLogicException 
	 */
	@PatchMapping(value = "/{empDeptId}")
	public HttpEntity<Void> updateEmpDept(@RequestBody EmpDept empDept, @PathVariable(value = "empDeptId") String empDeptId) throws BusinessLogicException {

		empDept.setEmpDeptId(empDeptId);
		empDeptService.updateEmpDept(empDept);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	/**
	 * 	刪除員工資料
	 * @param empNo
	 * @return
	 * @throws BusinessLogicException 
	 */
	@DeleteMapping(value = "/{empDeptId}")
	public HttpEntity<Void> deleteEmpDept(@PathVariable(value = "empDeptId") String empDeptId) throws BusinessLogicException {

		empDeptService.deleteEmpDept(empDeptId);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
