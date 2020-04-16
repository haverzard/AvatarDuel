package com.avatarduel.deck;

import com.avatarduel.card.GameCard;

import java.util.Collections;
import java.util.List;

/**
 * Deck is a base class for all decks.
 * 
 * @author Kelompok 2
 */

public class Deck implements HasCapacity{
    private int capacity;
    protected List<GameCard> gameCards; // Depend on Card, a higher abstraction, instead of CharacterCard, etc.

    /**
     * Create a new deck that contains cards
     * @param x deck's cards
     */
    public Deck(List<GameCard> x) { // Doesn't depend on a single collection class, could use ArrayList or etc.
        gameCards = x;
        capacity = 100;
    }

    // Instead of making a constructor to create a Deck in certain capacity,
    // we let the subclass to decide so Liskov Sub is not violated.

    // Getter & Setter

    /**
     * Get deck's capacity.
     *
     * @return The capacity attribute.
     */
    public int getCapacity() {
        return capacity;
    }

    /**
     * Set deck's capacity
     * @param _capacity new deck's capacity
     */
    public void setCapacity(int _capacity) {
        capacity = _capacity;
    }

    /**
     * Get deck's amount of cards
     * @return the number of cards found on the deck.
     */
    public int getSize() {
        return gameCards.size();
    }

    /**
     * Add 1 card to deck
     * @param x to be inserted card
     */
    public void add(GameCard x) {
        if(gameCards.size()+1 <= capacity) gameCards.add(x);
    }

    /**
     * Take the top-most card (if card(s) exist)
     * @return a Card in the head of gamecards deck (the card in the gameCard list that index is zero)
     */
    public GameCard pop() {
        if (!gameCards.isEmpty()) return gameCards.remove(0);
        return null;
    }

    /**
     * Shuffle cards in deck
     */
    public void shuffle() {
        Collections.shuffle(gameCards);
    }
}
