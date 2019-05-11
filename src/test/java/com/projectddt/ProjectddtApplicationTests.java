package com.projectddt;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.projectddt.repository.empRepositoryCRUD;
import com.projectddt.service.empDataService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProjectddtApplicationTests {

	@Mock
	private empDataService aaa;
	
	@Mock
	private empRepositoryCRUD bbb;
	
	@Test
	public void contextLoads() {
		
		//when(bbb.findById(1)).thenReturn(arg0)

	}

}
