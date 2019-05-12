package com.projectddt.model;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "EMP_DATA_MASTER")
public class EmpDataMaster implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "emp_no", unique = true)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer empNo;
	@Column(name = "emp_name")
	private String empName;
	@Column(name = "emp_dept_id")
	private String empDeptId;
	@Column(name = "emp_gender")
	private String empGender;
	@Column(name = "emp_phone_no")
	private String empPhoneNo;
	@Column(name = "emp_address")
	private String empAddress;
	@Column(name = "emp_age")
	private Integer empAge;
	@Column(name = "create_time")
	private LocalDateTime createTime;
	@Column(name = "update_time")
	private LocalDateTime updateTime;
	
	@ManyToOne
	@JoinColumn(name = "emp_dept_id", insertable = false, updatable = false, referencedColumnName = "emp_dept_id")
	private EmpDept empDept;
}
