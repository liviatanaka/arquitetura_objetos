package com.insper.partida.aposta;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.insper.partida.equipe.Team;
import com.insper.partida.equipe.TeamController;
import com.insper.partida.equipe.TeamService;
import com.insper.partida.game.Game;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class BetControllerTests {

    MockMvc mockMvc;

    @InjectMocks
    BetController betController;

    @Mock
    BetService betService;

    @BeforeEach
    void setup() {
        this.mockMvc = MockMvcBuilders
                .standaloneSetup(betController)
                .build();
    }

    @Test
    void test_listBets() throws Exception {
        Bet bet = new Bet();
        bet.setResult(BetResult.HOME);
        bet.setStatus(BetStatus.WON);

        List<Bet> apostas = new ArrayList<>();
        apostas.add(bet);

        Mockito.when(betService.listBets()).thenReturn(apostas);

        MvcResult result = mockMvc
                .perform(MockMvcRequestBuilders.get("/bet"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        ObjectMapper om = new ObjectMapper();
        String resp = result.getResponse().getContentAsString();
        Assertions.assertEquals(om.writeValueAsString(apostas), resp);
    }

    @Test
    void test_createBets() throws Exception {
        Bet bet = new Bet();
        bet.setResult(BetResult.HOME);
        bet.setStatus(BetStatus.WON);

        Mockito.when(betService.saveBet(bet)).thenReturn(bet);

        ObjectMapper mapper = new ObjectMapper();
        String content = mapper.writeValueAsString(bet);

        MvcResult result = mockMvc
                .perform(MockMvcRequestBuilders.post("/bet").content(content).contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        String resp = result.getResponse().getContentAsString();
        Assertions.assertEquals(content, resp);



    }

    @Test
    void test_verifyBets() throws Exception {
        Game game = new Game();
        game.setScoreAway(2);
        game.setScoreHome(3);

        Bet bet = new Bet();
        bet.setResult(BetResult.HOME);
        bet.setStatus(BetStatus.WON);
        bet.setGame(game);
        bet.setId(1);

        Mockito.when(betService.verifyBet(bet.getId())).thenReturn(bet);

        ObjectMapper mapper = new ObjectMapper();
        String content = mapper.writeValueAsString(bet);

        MvcResult result = mockMvc
                .perform(MockMvcRequestBuilders.put("/bet/1/verify"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        String resp = result.getResponse().getContentAsString();
        Assertions.assertEquals(content, resp);
    }



}
