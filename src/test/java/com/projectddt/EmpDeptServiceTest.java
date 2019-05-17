package com.projectddt;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.InjectMocks;

import com.projectddt.model.EmpDept;
import com.projectddt.repository.EmpDeptRepository;
import com.projectddt.service.EmpDeptService;
import com.projectddt.service.impl.EmpDeptServiceImpl;
import com.projectddt.exception.BusinessLogicException;

public class EmpDeptServiceTest {

	@Mock
	private EmpDeptRepository empDeptRepo;

	@InjectMocks
	private EmpDeptService empDeptService = new EmpDeptServiceImpl();

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}

	@Test  //新增員工部門 - 成功
	public void addEmpDeptTestSuccess() throws BusinessLogicException {

		EmpDept empDept = new EmpDept();
		
		empDept.setEmpDeptId("SSS");
		empDept.setEmpDeptName("TEST008");
		
		when(empDeptService.deptExists(empDept.getEmpDeptId())).thenReturn(false);
		empDeptService.addEmpDept(empDept);
		verify(empDeptRepo, times(1)).save(Mockito.any(EmpDept.class));
	}
	
	@Test(expected = BusinessLogicException.class)  //新增員工部門 - 已存在的員工部門
	public void addEmpDeptTestIsExist() throws BusinessLogicException {

		EmpDept empDept = new EmpDept();
		
		empDept.setEmpDeptId("IT");
		empDept.setEmpDeptName("TEST001");
		
		when(empDeptService.deptExists(empDept.getEmpDeptId())).thenReturn(true);
		empDeptService.addEmpDept(empDept);
	}

	@Test  //更新員工部門 - 成功
	public void updateEmpDeptTestSuccess() throws BusinessLogicException {

		EmpDept empDept = new EmpDept();
		
		empDept.setEmpDeptId("IT");
		empDept.setEmpDeptName("TEST001");
		
		when(empDeptService.deptExists(empDept.getEmpDeptId())).thenReturn(true);
		empDeptService.updateEmpDept(empDept);
		verify(empDeptRepo, times(1)).existsById(empDept.getEmpDeptId());
		verify(empDeptRepo, times(1)).save(Mockito.any(EmpDept.class));
	}

	@Test(expected = BusinessLogicException.class)  //更新員工部門 - 不存在的員工部門
	public void updateEmpDeptTestNotExist() throws BusinessLogicException {

		EmpDept empDept = new EmpDept();
		
		empDept.setEmpDeptId("XXX");
		empDept.setEmpDeptName("TEST004");
		
		when(empDeptService.deptExists(empDept.getEmpDeptId())).thenReturn(false);
		empDeptService.updateEmpDept(empDept);
	}

	@Test  //刪除員工部門 - 成功
	public void deleteEmpDeptTestSuccess() throws BusinessLogicException {

		String dept = "IT";
		
		when(empDeptService.deptExists(dept)).thenReturn(true);
		empDeptService.deleteEmpDept(dept);
		verify(empDeptRepo, times(1)).existsById(dept);
		verify(empDeptRepo, times(1)).deleteById(dept);
	}

	@Test(expected = BusinessLogicException.class)  //刪除員工部門 - 不存在的員工部門
	public void deleteEmpDeptTestNotExist() throws BusinessLogicException {
		
		String dept = "XXX";
		
		when(empDeptService.deptExists(dept)).thenReturn(false);
		empDeptService.deleteEmpDept(dept);
	}
	
}
