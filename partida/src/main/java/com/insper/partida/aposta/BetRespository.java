package com.insper.partida.aposta;

import com.insper.partida.equipe.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BetRespository extends JpaRepository<Bet, Integer> {

    Bet findByIdentifier(String identifier);
}
