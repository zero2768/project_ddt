package com.projectddt.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projectddt.model.empDataMaster;

public interface empRepositoryCRUD extends JpaRepository<empDataMaster, Integer> , empRepository {

}
