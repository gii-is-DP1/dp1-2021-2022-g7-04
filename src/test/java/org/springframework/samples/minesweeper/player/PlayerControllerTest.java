package org.springframework.samples.minesweeper.player;


import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyObject;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.http.MediaType;
import org.springframework.samples.minesweeper.configuration.SecurityConfiguration;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;

@WebMvcTest(controllers = PlayerController.class,
excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = WebSecurityConfigurer.class),
excludeAutoConfiguration = SecurityConfiguration.class)
public class PlayerControllerTest {
	
	private static final int TEST_PLAYER_ID = 5;
    private static final String TEST_PLAYER_NAME = "player";

	@MockBean
	private PlayerService playerService;
	
	@MockBean
	private PlayerStatsService playerStatsService;
	
	@Autowired
	private WebApplicationContext context;
	private MockMvc mockMvc;
	
	@Mock
	private Player player;
	
	@BeforeEach
	void setup() {
		mockMvc = MockMvcBuilders
		          .webAppContextSetup(context)
		          .apply(SecurityMockMvcConfigurers.springSecurity())
		          .build();
	}
	
	@Test
	@WithMockUser(username="admin",authorities={"admin"})
	void testFindPlayers() throws Exception {
		mockMvc.perform(get("/players/find")).andExpect(status().isOk());
	}
	
	@Test
	@WithMockUser(username="admin",authorities={"admin"})
	void testListPlayers() throws Exception {
		when(this.playerService.checkPlayerSearched(anyObject())).thenReturn(player);
		mockMvc.perform(get("/players/list")).andExpect(status().isOk());
	}
	
	@Test
	@WithMockUser(username="player",authorities={"player"})
	void testGetPlayerProfile() throws Exception {
		when(this.playerService.findPlayerByUsername(TEST_PLAYER_NAME)).thenReturn(player);
		mockMvc.perform(get("/players/{username}",TEST_PLAYER_NAME)).andExpect(status().isOk());
	}
	
	@Test
	@WithMockUser(username="player",authorities={"player"})
	void testNewPlayer() throws Exception {
		mockMvc.perform(get("/players/new")).andExpect(status().isOk());
	}
	
	@Test
	@WithMockUser(username="player",authorities={"player"})
	void testUpdatePlayer() throws Exception {
		when(this.playerService.findPlayerById(anyInt())).thenReturn(Optional.of(player));
		mockMvc.perform(get("/players/{playerId}/edit",TEST_PLAYER_ID)).andExpect(status().isOk());
	}
	
	@Test
	@WithMockUser(username="admin",authorities={"admin"})
	void testDeletePlayer() throws Exception {
		mockMvc.perform(get("/{username}/delete",TEST_PLAYER_NAME)).andExpect(status().isOk());
	}

	
}
