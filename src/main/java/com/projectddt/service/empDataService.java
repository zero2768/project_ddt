package com.projectddt.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.projectddt.model.empDataMaster;

public interface empDataService {
	
	/** 增加*/
    public void addEmpData(JsonNode empData);
//    /** 更新*/
//    public int updateEmpData(empDataMaster empData);
//    /** 删除 */
//    public void deleteEmpData(int id);
//    /** 查询单个*/
//    public empDataMaster selectById(int id);

}
