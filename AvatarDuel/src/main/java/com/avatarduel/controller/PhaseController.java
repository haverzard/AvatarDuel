package com.avatarduel.controller;

import com.avatarduel.phase.DrawPhase;
import com.avatarduel.phase.Phase;
import com.avatarduel.model.Player;
import com.avatarduel.model.Turn;

/**
 * Represent the Phase Controller for MVC pattern in AvatarDuel
 */
public class PhaseController {
    private Phase gamePhase;
    private Turn turn;
    private boolean landUsed;

    /**
     * Create new phase controller
     */
    public PhaseController() {
        Player.getPlayers();
        gamePhase = new DrawPhase();
        turn = new Turn();
    }

    /**
     * Get current game phase
     * @return game phase
     */
    public Phase getGamePhase() {
        return gamePhase;
    }

    /**
     * Set new state for game phase
     * @param gamePhase new game phase
     */
    public void setGamePhase(Phase gamePhase) {
        if (gamePhase.getCanAttack()) landUsed = this.gamePhase.getCanUseLandCard();
        if (this.gamePhase.getCanAttack()) gamePhase.setCanUseLandCard(landUsed);
        this.gamePhase = gamePhase;
    }

    /**
     * Get game turn
     * @return game turn
     */
    public Turn getTurn() {
        return turn;
    }
}
