package com.projectddt.service;

import com.projectddt.exception.BusinessLogicException;
import com.projectddt.model.EmpDept;

public interface EmpDeptService {
	
	/**
	 * insert部門資料
	 * @param EmpDept
	 * @throws BusinessLogicException 
	 */
	void addEmpDept(EmpDept empDept) throws BusinessLogicException;
	
	/**
	 * update部門資料
	 * @param EmpDept
	 * @throws BusinessLogicException 
	 */
	void updateEmpDept(EmpDept empDept) throws BusinessLogicException;

	/**
	 * delete部門資料
	 * @param empDeptId
	 * @throws BusinessLogicException
	 */
	void deleteEmpDept(String empDeptId) throws BusinessLogicException;
	
	/**
	 * 	判斷部門是否存在
	 * @param empDeptId
	 * @return
	 */
	boolean deptExists(String empDeptId);

}
