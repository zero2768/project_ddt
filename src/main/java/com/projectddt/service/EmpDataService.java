package com.projectddt.service;

import java.util.Optional;

import com.projectddt.exception.BusinessLogicException;
import com.projectddt.model.EmpDataMaster;

public interface EmpDataService {
	
	/**
	 * insert員工資料
	 * @param EmpDataMaster
	 * @throws BusinessLogicException 
	 */
	void addEmpData(EmpDataMaster empData) throws BusinessLogicException;
	/**
	 * update員工資料
	 * @param EmpDataMaster
	 * @throws BusinessLogicException 
	 */
	void updateEmpData(EmpDataMaster empData) throws BusinessLogicException;

	/**
	 * delete員工資料
	 * @param empNo
	 * @throws BusinessLogicException
	 */
	void deleteEmpData(Integer empNo) throws BusinessLogicException;

//	/**
//	 * query員工ByPage
//	 * @param employeeQueryVo
//	 * @param pageable
//	 * @return
//	 * @throws BusinessLogicException
//	 */
//	Page<EmployeeResultVo> getEmployee(EmployeeQueryVo employeeQueryVo,Pageable pageable) throws BusinessLogicException;
//
	/**
	 * 	判斷部門是否存在
	 * @param deptId
	 * @return
	 */
	boolean deptExists(String deptId);
	
	/**
	 * query員工資料
	 * @param empNo
	 * @return
	 */
	Optional<EmpDataMaster> findByEmp(Integer empNo);

}
