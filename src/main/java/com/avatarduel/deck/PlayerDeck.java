package com.avatarduel.deck;

import com.avatarduel.card.GameCard;

import java.util.List;

public class PlayerDeck extends Deck {

    /**
     * Creates a new player deck with fixed capacity of 60.
     * @param x to be inserted cards
     */
    public PlayerDeck(List<GameCard> x) {
        super(x);
        setCapacity(60);
    }
}
