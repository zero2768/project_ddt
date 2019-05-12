package com.projectddt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JsonNode;
import com.projectddt.model.empDataMaster;
import com.projectddt.service.empDataService;

@RestController
public class empData {
	
	@Autowired
	private empDataService empDataService;
	
	@GetMapping("/hello")
	public String getHello() {
        System.out.println("he46llo");
        return "testNewHello";
    }
	
	@RequestMapping(value = "/addEmp", method = RequestMethod.POST)
	public String addEmpData(@RequestBody empDataMaster empData) {
        
		empDataService.addEmpData(empData);
		return null;
	}
}
