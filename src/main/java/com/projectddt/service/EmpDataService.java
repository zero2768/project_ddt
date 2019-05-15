package com.projectddt.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.projectddt.exception.BusinessLogicException;
import com.projectddt.model.EmpDataMaster;
import com.projectddt.vo.EmpSearchVo;

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

	/**
	 * query員工ByPage
	 * @param EmpSearchVo
	 * @param pageable
	 * @return
	 * @throws BusinessLogicException
	 */
	Page<EmpSearchVo> findEmpDataByPage(EmpSearchVo empSearchVo, Pageable pageableSetting) throws BusinessLogicException;

	/**
	 * query員工資料
	 * @param empNo
	 * @return
	 */
	Optional<EmpDataMaster> findByEmp(Integer empNo);

}
