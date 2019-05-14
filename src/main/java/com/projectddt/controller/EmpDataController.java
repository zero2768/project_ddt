package com.projectddt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
import com.projectddt.model.EmpDataMaster;
import com.projectddt.service.EmpDataService;
import com.projectddt.vo.EmpSearchVo;

@RestController
@RequestMapping("/empData")
public class EmpDataController {
	
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
	
	/**
	 * 	查詢員工資料 ByPage
	 * @param EmpSearchVo
	 * @return
	 * @throws BusinessLogicException 
	 */
	@PostMapping(value = "/find/findEmpDataByPage")
	public HttpEntity<Page<EmpSearchVo>> findEmpDataByPage(@RequestBody EmpSearchVo empSearchVo,
            Pageable pageable) throws BusinessLogicException {
		//當前需求每頁10筆
		Pageable pageableSetting = new PageRequest(pageable.getPageNumber(), 10, Sort.Direction.ASC, "empNo");
		return new ResponseEntity<>(empDataService.findEmpDataByPage(empSearchVo, pageableSetting),HttpStatus.CREATED);
	}

}
