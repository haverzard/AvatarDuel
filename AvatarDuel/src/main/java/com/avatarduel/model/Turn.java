package com.avatarduel.model;

public class Turn {
    private int num;

    /**
     * Create a new default turn
     */
    public Turn() {
        num = 1;
        Player.getPlayers();
    }

    /**
     * Set the turn time for player
     */
    public void nextTurn() {
        num = (num%2) + 1;
    }

    /**
     * Checks whose turn at a moment
     * @param p Player's value
     */
    public boolean checkTurn(Player p) {
        return p.getId() == num;
    }

    /**
     * Show whose turn at a moment
     */
    public Player getPlayerInTurn() {
        return (Player.player1.getId() == num) ? Player.player1 : Player.player2;
    }

    /**
     * Show who is not in turn at a moment
     */
    public Player getPlayerNotInTurn() {
        return (Player.player1.getId() == num) ? Player.player2 : Player.player1;
    }
}
