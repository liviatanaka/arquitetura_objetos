package com.insper.partida.aposta;

import com.insper.partida.game.Game;
import com.insper.partida.game.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BetService {

    @Autowired
    private BetRespository betRespository;

    @Autowired
    private GameService gameService;


    public Bet saveBet(Bet bet) {
        Game game = gameService.getGame(bet.getGame().getIdentifier());
        if (game == null) {
            throw new RuntimeException("Game not found");
        }

        bet.setGame(game);
        return betRespository.save(bet);

    }

    public List<Bet> listBets() {

        return betRespository.findAll();
    }

    public Bet verifyBet(Integer betId) {
        Bet bet = null;
        Optional<Bet> bet_optional = betRespository.findById(betId);
        if (bet_optional.isPresent()){
            bet = bet_optional.get();
        } else {
            throw new RuntimeException("Bet not found");
        }

        Game game = bet.getGame();

        Integer scoreAway = game.getScoreAway();
        Integer scoreHome = game.getScoreHome();
        BetResult aposta = bet.getResult();

        if (scoreAway > scoreHome){
            if(aposta == BetResult.AWAY){
                bet.setStatus(BetStatus.WON);
            } else {
                bet.setStatus(BetStatus.LOST);
            }
        } else if (scoreAway < scoreHome){
            if(aposta == BetResult.HOME){
                bet.setStatus(BetStatus.WON);
            } else {
                bet.setStatus(BetStatus.LOST);
            }
        } else {
            if(aposta == BetResult.DRAW){
                bet.setStatus(BetStatus.WON);
            } else {
                bet.setStatus(BetStatus.LOST);
            }
        }

        return bet;

    }

}
