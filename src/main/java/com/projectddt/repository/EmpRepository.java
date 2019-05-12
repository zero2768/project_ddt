package com.projectddt.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projectddt.model.EmpDataMaster;

public interface EmpRepository extends JpaRepository<EmpDataMaster, Integer> {
	
	Optional<EmpDataMaster> findByEmpNo(Integer empNo);

}
