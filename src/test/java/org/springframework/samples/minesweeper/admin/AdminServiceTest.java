package org.springframework.samples.minesweeper.admin;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;


import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
public class AdminServiceTest {
	@Autowired
	private AdminService adminService;
	
	@Test
	@Transactional
	void shouldFindAdmin() {
		
		Admin admin1 = this.adminService.findAdmin();

		// There is a unique administrator, whose username and password is a"admin"
		assertNotNull(admin1);
		assertEquals(admin1.getUser().getUsername(), "admin");
		assertEquals(admin1.getUser().getPassword(), "admin");
		
	}
	
}