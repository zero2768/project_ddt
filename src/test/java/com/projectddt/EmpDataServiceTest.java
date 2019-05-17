package com.projectddt;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.projectddt.exception.BusinessLogicException;
import com.projectddt.repository.EmpDataRepository;
import com.projectddt.repository.EmpDeptRepository;
import com.projectddt.service.EmpDataService;
import com.projectddt.service.impl.EmpDataServiceImpl;
import com.projectddt.vo.EmpSearchVo;
import com.projectddt.model.EmpDataMaster;
import com.projectddt.model.EmpDept;

public class EmpDataServiceTest {

	@Mock
	private EmpDataRepository empRepo;
	
	@Mock
	private EmpDeptRepository empDeptRepo;
	
	@InjectMocks
	private EmpDataService empDataService = new EmpDataServiceImpl();
	
	@Before
	public void setUp() {
	    MockitoAnnotations.initMocks(this);
	}
	 
	@Test  //新增員工資料 - 成功
	public void addEmpDataTestSuccess() throws BusinessLogicException {

		List<EmpDataMaster> empDataList = new ArrayList<EmpDataMaster>();
		EmpDataMaster empData = new EmpDataMaster();
		
		empData.setEmpDeptId("IT");
		empData.setEmpName("TEST001");
		empData.setEmpGender("M");
		empData.setEmpAge(66);
		empData.setEmpPhoneNo("0912001002");
		empData.setEmpAddress("TAIWAN");
		empDataList.add(empData);
		
		when(empDataService.deptExists(empData.getEmpDeptId())).thenReturn(true);
		empDataService.addEmpData(empDataList);
		verify(empRepo, times(1)).save(Mockito.any(EmpDataMaster.class));
	}
	
	@Test(expected= BusinessLogicException.class)  //新增員工資料 - 不存在的員工部門
	public void addEmpDataTestDeptIsNotExist() throws BusinessLogicException {

		List<EmpDataMaster> empDataList = new ArrayList<EmpDataMaster>();
		EmpDataMaster empData = new EmpDataMaster();
		
		empData.setEmpDeptId("XXX");
		empData.setEmpName("TEST001");
		empData.setEmpGender("M");
		empData.setEmpAge(66);
		empData.setEmpPhoneNo("0912001002");
		empData.setEmpAddress("TAIWAN");
		empDataList.add(empData);
		
		when(empDataService.deptExists(empData.getEmpDeptId())).thenReturn(false);
		empDataService.addEmpData(empDataList);
		verify(empRepo, times(1)).save(Mockito.any(EmpDataMaster.class));
	}

	@Test  //更新員工資料 - 成功
	public void updateEmpDataTestSuccess() throws BusinessLogicException {
		
		EmpDataMaster empData = new EmpDataMaster();
		
		empData.setEmpNo(1);
		empData.setEmpDeptId("IT");
		empData.setEmpName("TEST001");
		
		when(empDataService.findByEmp(empData.getEmpNo())).thenReturn(Optional.of(new EmpDataMaster()));
		when(empDataService.deptExists(empData.getEmpDeptId())).thenReturn(true);
		empDataService.updateEmpData(empData);
		verify(empRepo, times(1)).findByEmpNo(empData.getEmpNo());
		verify(empDeptRepo, times(1)).existsById(empData.getEmpDeptId());
		verify(empRepo, times(1)).save(Mockito.any(EmpDataMaster.class));
	}
	
	@Test(expected= BusinessLogicException.class)  //更新員工資料 - 不存在的員工編號
	public void updateEmpDataTestEmpNoIsNotExist() throws BusinessLogicException {
		
		EmpDataMaster empData = new EmpDataMaster();
		
		empData.setEmpNo(0);
		empData.setEmpDeptId("IT");
		empData.setEmpName("TEST008");
		
		when(empDataService.findByEmp(empData.getEmpNo())).thenReturn(Optional.empty());
		when(empDataService.deptExists(empData.getEmpDeptId())).thenReturn(true);
		empDataService.updateEmpData(empData);
	}

	@Test(expected= BusinessLogicException.class)  //更新員工資料  - 不存在的員工部門
	public void updateEmpDataTestEmpDeptIsNotExist() throws BusinessLogicException {

		EmpDataMaster empData = new EmpDataMaster();
		
		empData.setEmpNo(1);
		empData.setEmpDeptId("XXX");
		empData.setEmpName("TEST008");
		
		when(empDataService.findByEmp(empData.getEmpNo())).thenReturn(Optional.of(new EmpDataMaster()));
		when(empDataService.deptExists(empData.getEmpDeptId())).thenReturn(false);
		empDataService.updateEmpData(empData);
	}

	@Test  //刪除員工資料 - 成功
	public void deleteEmpDataTestSuccess() throws BusinessLogicException {

		when(empDataService.findByEmp(1)).thenReturn(Optional.of(new EmpDataMaster()));
		empDataService.deleteEmpData(1);
		verify(empRepo, times(1)).findByEmpNo(1);
		verify(empRepo, times(1)).delete(Mockito.any(EmpDataMaster.class));
	}
	
	@Test(expected= BusinessLogicException.class)  //刪除員工資料 - 不存在的員工編號
	public void deleteEmpDataTestEmpNoIsNotExist() throws BusinessLogicException {

		when(empDataService.findByEmp(0)).thenReturn(Optional.empty());
		empDataService.deleteEmpData(0);
	}

	@Test  //查詢員工資料使用分頁 - 成功
	public void findEmpDataByPageTestSuccess() throws BusinessLogicException {

		EmpSearchVo empSearchVo = EmpSearchVo.builder().empNo(1).empName("Zyaire")
				.empAge(32).empDeptName("科技魔法部").build();
		Pageable pageableSetting = PageRequest.of(0, 10, Sort.Direction.ASC, "empNo");
		List<EmpDataMaster> data = new ArrayList<>();
		EmpDataMaster e = EmpDataMaster.builder().empNo(1).empDeptId("IT")
				.empDept(EmpDept.builder().empDeptId("IT").empDeptName("科技魔法部").build())
				.empName("Zyaire").empGender("M").empPhoneNo("0912001002").empAddress("TAIWAN").empAge(31)
				.createTime(LocalDateTime.now()).updateTime(LocalDateTime.now()).build();
		data.add(e);
		when(empRepo.findEmpDataByPage(empSearchVo, pageableSetting)).thenReturn(new PageImpl<>(data, pageableSetting, 1));
		empDataService.findEmpDataByPage(empSearchVo, pageableSetting);
		verify(empRepo, times(1)).findEmpDataByPage(empSearchVo, pageableSetting);
		assertEquals(empRepo.findEmpDataByPage(empSearchVo, pageableSetting).getContent().size(), 1);
	}
	
}
