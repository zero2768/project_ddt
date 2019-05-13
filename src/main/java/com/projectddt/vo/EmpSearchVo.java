package com.projectddt.vo;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EmpSearchVo {

	private Integer empNo;	
	private String empName;
	private String empGender;
	private String empPhoneNo;
	private String empAddress;
	private Integer empAge;
	private String createTime;
	private String updateTime;
	
	private String empDeptId;
	private String empDeptName;
}
