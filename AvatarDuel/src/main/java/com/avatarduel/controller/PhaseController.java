package com.avatarduel.controller;

import com.avatarduel.phase.DrawPhase;
import com.avatarduel.phase.Phase;
import com.avatarduel.model.Player;
import com.avatarduel.model.Turn;

public class PhaseController {
    private Phase gamePhase;
    private Turn turn;
    private boolean landUsed;

    public PhaseController() {
        Player.getPlayers();
        gamePhase = new DrawPhase();
        turn = new Turn();
    }

    public Phase getGamePhase() {
        return gamePhase;
    }

    public void setGamePhase(Phase gamePhase) {
        if (gamePhase.getCanAttack()) landUsed = this.gamePhase.getCanUseLandCard();
        if (this.gamePhase.getCanAttack()) gamePhase.setCanUseLandCard(landUsed);
        this.gamePhase = gamePhase;
    }

    public Turn getTurn() {
        return turn;
    }
}
