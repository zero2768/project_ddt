package com.projectddt.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.projectddt.service.empDataService;
import com.projectddt.repository.empRepositoryCRUD;
import com.projectddt.repository.empDeptRepository;
import com.projectddt.repository.empRepository;
import com.projectddt.model.empDataMaster;

@Service
public class empDataServiceImpl implements empDataService {

	@Autowired
	private empRepositoryCRUD empRepoCRUD;	
	@Autowired
	private empRepository empRepo;
	@Autowired
	private empDeptRepository empDeptRepo;

	public void addEmpData(empDataMaster empData) {

//		// convert JSON string to Map
//		ObjectMapper mapper = new ObjectMapper();
//		Map<String, Object> jsonMap = new HashMap<String, Object>();
//		jsonMap = mapper.convertValue(empData, new TypeReference<Map<String, String>>() {});

		empDataMaster findMaxEmpNo = empRepoCRUD.findTopByOrderByEmpNoDesc();
		
		empData.setEmpNo(findMaxEmpNo.getEmpNo() + 1);
		empData.setCreateTime(new Date());
		empData.setUpdateTime(new Date());
		
		empRepoCRUD.save(empData);
	}

}
