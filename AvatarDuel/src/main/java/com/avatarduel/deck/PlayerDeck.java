package com.avatarduel.deck;

import com.avatarduel.card.GameCard;

import java.util.List;

public class PlayerDeck extends Deck {
    public PlayerDeck(List<GameCard> x) {
        super(x);
        setCapacity(60);
    }
}
