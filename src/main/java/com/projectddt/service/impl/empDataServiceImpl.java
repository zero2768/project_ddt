package com.projectddt.service.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.projectddt.model.empDataMaster;
import com.projectddt.repository.empRepositoryCRUD;
import com.projectddt.service.empDataService;

@Service
public class empDataServiceImpl implements empDataService {

	@Autowired
	private empRepositoryCRUD empRepository;

//	@Autowired
//	private empDeptRepository empDeptRepository;

	public void addEmpData(JsonNode empData) {
		ObjectMapper mapper = new ObjectMapper();
		Map<String, Object> jsonMap = new HashMap<String, Object>();

		// convert JSON string to Map
		jsonMap = mapper.convertValue(empData, new TypeReference<Map<String, String>>() {});
		
		// todo jsonMap to empDataMaster and empDept
		empDataMaster empDataMaster = new empDataMaster();
		
		// save
		empRepository.save(empDataMaster);
	}

}
