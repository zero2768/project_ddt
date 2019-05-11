package com.projectddt.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projectddt.model.empDept;

public interface empDeptRepository extends JpaRepository<empDept, String>, empRepository {

}
