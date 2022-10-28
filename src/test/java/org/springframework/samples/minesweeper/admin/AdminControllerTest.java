package org.springframework.samples.minesweeper.admin;


import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.samples.minesweeper.configuration.SecurityConfiguration;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@WebMvcTest(controllers = AdminController.class,
excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = WebSecurityConfigurer.class),
excludeAutoConfiguration = SecurityConfiguration.class)
public class AdminControllerTest {
	
	private static final int TEST_ADMIN_ID = 5;

	@MockBean
	private AdminService adminService;
	
	@Autowired
	private WebApplicationContext context;
	private MockMvc mockMvc;
	
	@Mock
	private Admin admin;
	
	@BeforeEach
	void setup() {
		mockMvc = MockMvcBuilders
		          .webAppContextSetup(context)
		          .apply(SecurityMockMvcConfigurers.springSecurity())
		          .build();
	}
	
	@Test
	@WithMockUser(username="admin",authorities={"admin"})
	void testGetAdminProfile() throws Exception {
		when(this.adminService.findAdmin()).thenReturn(admin);
		mockMvc.perform(get("/admin")).andExpect(status().isOk());
	}
	
	@Test
	@WithMockUser(username="admin",authorities={"admin"})
	void testUpdateAdmin() throws Exception {
		when(this.adminService.findAdmin()).thenReturn(admin);
		mockMvc.perform(get("/admin/{adminId}/edit",TEST_ADMIN_ID)).andExpect(status().isOk());
	}
	
}
