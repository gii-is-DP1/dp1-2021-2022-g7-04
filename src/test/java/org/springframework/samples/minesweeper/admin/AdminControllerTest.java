package org.springframework.samples.minesweeper.admin;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers=AdminController.class)
public class AdminControllerTest {
	
	@Autowired
	private WebApplicationContext context;
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private AdminService adminService;
	
	@BeforeEach
	public void setup() {
		mockMvc = MockMvcBuilders.webAppContextSetup(context).apply(SecurityMockMvcConfigurers.springSecurity()).build();
	}

	@Test
	void shouldFindAdmin() throws Exception{
		mockMvc.perform(get("/admin")).andExpect(status().isOk());
	}

	@Test
	void shouldInitUpdateAdminForm() throws Exception{
		mockMvc.perform(get("/admin/{adminId}/edit")).andExpect(status().isOk());
	}

	@Test
	void shouldProcessUpdateAdminForm() throws Exception{
		mockMvc.perform(post("/admin/{adminId}/edit").param("username","admin"));
	}
}
