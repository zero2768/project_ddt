package com.projectddt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projectddt.exception.BusinessLogicException;
import com.projectddt.model.EmpDataMaster;
import com.projectddt.service.EmpDataService;

@RestController
@RequestMapping("/empData")
public class EmpData {
	
	@Autowired
	private EmpDataService empDataService;
	
//	@GetMapping("/hello")
//	public String getHello() {
//        System.out.println("he46llo");
//        return "testNewHello";
//    }
	
	/**
	 * 	新增員工資料
	 * @param EmpDataMaster
	 * @return
	 * @throws BusinessLogicException 
	 */
	@PostMapping
	public HttpEntity<Void> addEmpData(@RequestBody EmpDataMaster empData) throws BusinessLogicException {

		empDataService.addEmpData(empData);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}

	/**
	 * 	更新員工資料
	 * @param EmpDataMaster
	 * @param empNo
	 * @return
	 * @throws BusinessLogicException 
	 */
	@PatchMapping(value = "/{empNo}")
	public HttpEntity<Void> updateEmpData(@RequestBody EmpDataMaster empData, @PathVariable(value = "empNo") int empNo) throws BusinessLogicException {

		empData.setEmpNo(empNo);
		empDataService.updateEmpData(empData);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	/**
	 * 	刪除員工資料
	 * @param empNo
	 * @return
	 * @throws BusinessLogicException 
	 */
	@DeleteMapping(value = "/{empNo}")
	public HttpEntity<Void> deleteEmpData(@PathVariable(value = "empNo") int empNo) throws BusinessLogicException {

		empDataService.deleteEmpData(empNo);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
//	/**
//	 * 	查詢員工資料 ByPage
//	 * @param departmentVo
//	 * @return
//	 * @throws BusinessLogicException 
//	 */
//	@RequestMapping(value = "/query/queryEmpByPage", method = RequestMethod.POST)
//	public HttpEntity<Page<EmployeeResultVo>> searchEmployeeByPage(@RequestBody EmployeeQueryVo employeeQueryVo,
//            Pageable pageable) throws BusinessLogicException {
//		//前端傳入當前頁數(PageNumber,預設首頁),後端寫死每頁頁數10頁(需求) 並利用EmployeeId做排序(ASC)
//		Pageable customPageable = new PageRequest(pageable.getPageNumber(),10, Sort.Direction.ASC,"employeeId");
//		return new ResponseEntity<>(empDataService.getEmployee(employeeQueryVo,customPageable),HttpStatus.CREATED);
//	}

}
