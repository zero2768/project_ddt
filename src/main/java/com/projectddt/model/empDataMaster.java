package com.projectddt.model;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "EMP_DATA_MASTER")
public class empDataMaster implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int empNo;
	@Column
	private String empName;
	@Column
	private String empDeptId;
	@Column
	private String empSex;
	@Column
	private String empPhoneNo;
	@Column
	private String empAddress;
	@Column
	private int empAge;
	@Column
	private Date createTime;
	@Column
	private Date updateTime;
	
}
