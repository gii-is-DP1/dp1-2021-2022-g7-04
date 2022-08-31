package org.springframework.samples.minesweeper.player;

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
@WebMvcTest(controllers=PlayerController.class)
public class PlayerControllerTest {
	
	@Autowired
	private WebApplicationContext context;
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private PlayerService playerService;
	
	@BeforeEach
	public void setup() {
		mockMvc = MockMvcBuilders.webAppContextSetup(context).apply(SecurityMockMvcConfigurers.springSecurity()).build();
	}
	
	@Test
	void shouldInitFindForm() throws Exception{
		mockMvc.perform(get("/players/find")).andExpect(status().isOk());
	}
	
	@Test
	void shouldFindAllPlayers() throws Exception{
		mockMvc.perform(get("/players/list")).andExpect(status().isOk());
	}
	
	@Test
	void shouldShowPlayers() throws Exception{
		mockMvc.perform(get("/players/{username}")).andExpect(status().isOk());
	}
	
	
	@Test
	void shouldInitCreationForm() throws Exception{
		mockMvc.perform(get("/players/new")).andExpect(status().isOk());
	}
	
	@Test
	void shouldProcessCreationForm() throws Exception{
		mockMvc.perform(post("/players/new").param("username","player"));
	}
	
	@Test
	void shouldInitUpdatePlayerForm() throws Exception{
		mockMvc.perform(get("/players/{playerId}/edit")).andExpect(status().isOk());
	}
	
	@Test
	void shouldProcessUpdatePlayerForm() throws Exception{
		mockMvc.perform(post("/players/{playerId}/edit").param("username","player1"));
	}
	
	@Test
	void shouldDeletePlayer() throws Exception{
		mockMvc.perform(get("/{username}/delete")).andExpect(status().isOk());
		
	}
	
}