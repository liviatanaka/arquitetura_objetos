package com.insper.partida.aposta;

import com.insper.partida.equipe.Team;
import com.insper.partida.game.Game;
import com.insper.partida.game.GameService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class BetServiceTests {

    @InjectMocks
    BetService betService;

    @Mock
    BetRespository betRespository;


    @Mock
    GameService gameService;


    @Test
    void test_saveBet() {

        Game game = new Game();
        game.setIdentifier("game show");

        Bet bet = new Bet();
        bet.setId(1);
        bet.setResult(BetResult.HOME);
        bet.setStatus(BetStatus.WON);
        bet.setGame(game);

        Mockito.when(gameService.getGame("game show")).thenReturn(game);
        Mockito.when(betRespository.save(bet)).thenReturn(bet);
        Bet resp = betService.saveBet(bet);

        Assertions.assertEquals(1, resp.getId());
    }


    @Test
    void test_listBets() {

        Bet bet = new Bet();
        bet.setResult(BetResult.HOME);
        bet.setStatus(BetStatus.WON);

        List<Bet> bets = new ArrayList<>();
        bets.add(bet);

        Mockito.when(betRespository.findAll()).thenReturn(bets);

        List<Bet> resp = betService.listBets();

        Assertions.assertEquals(1, resp.size());
        Assertions.assertEquals(BetResult.HOME, resp.get(0).getResult());
    }

    @Test
    void test_verifyBet() {
        Game game = new Game();
        game.setScoreAway(2);
        game.setScoreHome(3);

        Bet bet = new Bet();
        bet.setResult(BetResult.HOME);
        bet.setStatus(BetStatus.WON);
        bet.setGame(game);


        Mockito.when(betRespository.findById(1)).thenReturn(Optional.of(bet));

        Bet resp = betService.verifyBet(1);

        Assertions.assertEquals(BetStatus.WON, resp.getStatus());
    }


}
