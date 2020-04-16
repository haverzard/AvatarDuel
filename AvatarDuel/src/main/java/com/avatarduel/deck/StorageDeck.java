package com.avatarduel.deck;

import com.avatarduel.card.GameCard;

import java.util.List;

public class StorageDeck extends Deck implements HasDynamicCapacity{

    /**
     * Creates a new storage deck with cards.
     * @param x cards to be inserted
     */
    public StorageDeck(List<GameCard> x) {
        super(x);
    }

    /**
     * Creates a new storage deck with it's capacity and cards.
     * @param x cards to be inserted
     * @param _capacity deck's capacity
     */
    public StorageDeck(List<GameCard> x, int _capacity) {
        super(x);
        setCapacity(_capacity);
    }

    public void addDynamically(GameCard x) {
        if (gameCards.size()+1 > 1) {
            increaseCapacity();
        }
        gameCards.add(x);
    }

    public void increaseCapacity() {
        setCapacity(getCapacity()+1);
    }

    /**
     * Access to a specific card by index
     * @param idx Index to be accessed
     * @return the idx-th game card in storage deck, or return null idx if out of range
     */
    public GameCard access(int idx) {
        if (gameCards.size() > idx) return gameCards.get(idx);
        return null; // Could add exception here
    }
}
