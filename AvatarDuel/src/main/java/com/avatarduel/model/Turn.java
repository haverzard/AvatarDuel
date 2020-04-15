package com.avatarduel.model;

public class Turn {
    private int num;

    public Turn() {
        num = 1;
        Player.getPlayers();
    }

    public void nextTurn() {
        num = (num%2) + 1;
    }

    public boolean checkTurn(Player p) {
        return p.getId() == num;
    }

    public Player getPlayerInTurn() {
        return (Player.player1.getId() == num) ? Player.player1 : Player.player2;
    }

    public Player getPlayerNotInTurn() {
        return (Player.player1.getId() == num) ? Player.player2 : Player.player1;
    }
}
