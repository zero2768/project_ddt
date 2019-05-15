package com.projectddt.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.projectddt.model.EmpDataMaster;
import com.projectddt.vo.EmpSearchVo;

public interface EmpDataRepository extends JpaRepository<EmpDataMaster, Integer> {
	
	@Query(value = "SELECT emp FROM EmpDataMaster emp, EmpDept dept WHERE 1 = 1" 
				 + "   AND emp.empDeptId = dept.empDeptId"
				 + "   AND ( :#{#vo.empName} IS NULL OR emp.empName = :#{#vo.empName} )"
				 + "   AND ( :#{#vo.empNo} IS NULL OR emp.empNo = :#{#vo.empNo} )"
				 + "   AND ( :#{#vo.empAge} IS NULL OR emp.empAge = :#{#vo.empAge} )"
				 + "   AND ( :#{#vo.empDeptName} IS NULL OR dept.empDeptName = :#{#vo.empDeptName} )")
	Page<EmpDataMaster> findEmpDataByPage(@Param(value = "vo") EmpSearchVo empSearchVo, Pageable pageableSetting);

	Optional<EmpDataMaster> findByEmpNo(Integer empNo);
	
}
