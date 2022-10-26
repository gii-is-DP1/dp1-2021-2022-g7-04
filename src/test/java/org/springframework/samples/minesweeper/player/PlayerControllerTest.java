package org.springframework.samples.minesweeper.player;


import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.assertj.core.util.Lists;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(controllers = PlayerController.class)

public class PlayerControllerTest {


	private static final Integer TEST_PLAYER_ID = 1;
	
	//private static final Integer TEST_AUTHORITY_ID = 2;
	
	@MockBean
	private PlayerService playerService;
	
	/*@MockBean
	private AuthoritiesService authoritiesService;*/

	@Autowired
	private ObjectMapper objectMapper;
	
	@Autowired
	private MockMvc mockMvc;
	
	@BeforeEach
	void setup() {
		Player player = new Player();
				//,"address1","telephone1","email1","username1");
		player.setId(TEST_PLAYER_ID);
		given(this.playerService.findAll()).willReturn(Lists.newArrayList(player));
		
		/*Authorities authorities = new Authorities();
		authorities.setId(TEST_AUTHORITY_ID);
		given(this.authoritiesService.)*/
		
	}
	
	@Test
	void testFindPlayers() throws Exception{
		mockMvc.perform(get("/api/players)", TEST_PLAYER_ID)).andExpect(status().isOk());
	}
}
