package org.springframework.samples.minesweeper.board;


import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.samples.minesweeper.audit.Audit;
import org.springframework.samples.minesweeper.audit.AuditService;
import org.springframework.samples.minesweeper.configuration.AdminStats;
import org.springframework.samples.minesweeper.configuration.AdminStatsService;
import org.springframework.samples.minesweeper.configuration.SecurityConfiguration;
import org.springframework.samples.minesweeper.model.BoardRequestService;
import org.springframework.samples.minesweeper.player.PlayerService;
import org.springframework.samples.minesweeper.player.PlayerStatsService;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@WebMvcTest(controllers = GameController.class,
excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = WebSecurityConfigurer.class),
excludeAutoConfiguration = SecurityConfiguration.class)
public class GameControllerTest {
	
	private static final String TEST_STRING = "TEST";
	private static final long TEST_LONG = 1L;
	
	private static final String TEST_BRONZE_GAMES_TITLE = "bronzeGames";
	private static final String TEST_SILVER_GAMES_TITLE = "silverGames";
	private static final String TEST_GOLD_GAMES_TITLE = "goldGames";
	
	private static final String TEST_WINNER_TITLE = "winner";
	private static final String TEST_TIMER_TITLE = "timer";
	private static final String TEST_FLAGS_TITLE = "flagsInMines";
	
	private static final int TEST_BRONZE_GAMES_VALUE = 5;
	private static final int TEST_SILVER_GAMES_VALUE = 10;
	private static final int TEST_GOLD_GAMES_VALUE = 15;
	
	private static final boolean TEST_WINNER_VALUE = false;
	private static final Integer TEST_TIMER_VALUE = 1;
	private static final int TEST_FLAGS_VALUE = 0;
	
	@MockBean
	private MinesweeperBoardService minesweeperBoardService;
	@MockBean
	private BoardRequestService boardRequestService;
	@MockBean
	private CellService cellService;
	@MockBean
	private AuditService auditService;
	@MockBean
	private PlayerService playerService;
	@MockBean
	private AdminStatsService adminStatsService;
	@MockBean
	private PlayerStatsService playerStatsService;
	
	@Autowired
	private WebApplicationContext context;
	private MockMvc mockMvc;
	
	@Mock
	private AdminStats adminStats;
	
	@Mock
	private Audit audit;
	
	@Mock
	private List<Object[]> mockListArrayObject;
	
	@Mock
	private Date date;
	
	@Mock
	private MinesweeperBoard board;
	
	@Mock
	private Cell cell;
	
	private Object[] mockArrayObject = new Object[2];
	
	@BeforeEach
	void setup() {
		mockMvc = MockMvcBuilders
		          .webAppContextSetup(context)
		          .apply(SecurityMockMvcConfigurers.springSecurity())
		          .build();
		mockArrayObject[0] = TEST_STRING;
		mockArrayObject[1] = TEST_LONG;
	}
	
	@Test
	@WithMockUser(username="admin",authorities={"admin"})
	void testConfigAchievements() throws Exception {
		mockMvc.perform(get("/configAchievements")).andExpect(status().isOk());
	}
	
	@Test
	@WithMockUser(username="admin",authorities={"admin"})
	void testUpdateAchievements() throws Exception {
		when(this.adminStatsService.getStatsByLevel(anyString())).thenReturn(adminStats);
		mockMvc.perform(get("/updateAchievements")
				.param(TEST_BRONZE_GAMES_TITLE, String.valueOf(TEST_BRONZE_GAMES_VALUE))
				.param(TEST_SILVER_GAMES_TITLE, String.valueOf(TEST_SILVER_GAMES_VALUE))
				.param(TEST_GOLD_GAMES_TITLE, String.valueOf(TEST_GOLD_GAMES_VALUE))).andExpect(status().isOk());
	}
	
	@Test
	@WithMockUser(username="player",authorities={"player"})
	void testGameStats() throws Exception {
		when(this.auditService.findAllNotCancelledOrStarted()).thenReturn(List.of(audit));
		when(this.auditService.getHallOfFame()).thenReturn(mockListArrayObject);
		when(mockListArrayObject.get(anyInt())).thenReturn(mockArrayObject);
		when(audit.getEndDate()).thenReturn(date);
		when(audit.getStartDate()).thenReturn(date);
		when(date.getTime()).thenReturn(TEST_LONG);
		mockMvc.perform(get("/gameStats")).andExpect(status().isOk());
	}
	
	@Test
	@WithMockUser(username="player",authorities={"player"})
	void testSelectGame() throws Exception {
		mockMvc.perform(get("/selectGame")).andExpect(status().isOk());
	}
	
	@Test
	@WithMockUser(username="player",authorities={"player"})
	void testFinishGame() throws Exception {
		mockMvc.perform(get("/finishGame")
				.param(TEST_WINNER_TITLE, String.valueOf(TEST_WINNER_VALUE))
				.param(TEST_TIMER_TITLE, String.valueOf(TEST_TIMER_VALUE))
				.param(TEST_FLAGS_TITLE, String.valueOf(TEST_FLAGS_VALUE))
				).andExpect(status().is3xxRedirection());
	}
	
}
