package com.avatarduel.deck;

import com.avatarduel.card.GameCard;

import java.util.Collections;
import java.util.List;

public class Deck {
    private int capacity;
    protected List<GameCard> gameCards; // Depend on Card, a higher abstraction, instead of CharacterCard, etc.

    public Deck(List<GameCard> x) { // Doesn't depend on a single collection class, could use ArrayList or etc.
        gameCards = x;
        capacity = 100;
    }

    // Instead of making a constructor to create a Deck in certain capacity,
    // we let the subclass to decide so Liskov Sub is not violated.

    // Getter & Setter
    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int _capacity) {
        capacity = _capacity;
    }

    public int getSize() {
        return gameCards.size();
    }

    public void add(GameCard x) {
        if(gameCards.size()+1 <= capacity) gameCards.add(x);
    }

    public GameCard pop() {
        if (!gameCards.isEmpty()) return gameCards.remove(0);
        return null;
    }

    public void shuffle() {
        Collections.shuffle(gameCards);
    }
}
