package com.avatarduel.deck;

import com.avatarduel.card.GameCard;

import java.util.List;

public class StorageDeck extends Deck implements HasDynamicCapacity{

    public StorageDeck(List<GameCard> x) {
        super(x);
    }

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

    public GameCard access(int idx) {
        if (gameCards.size() > idx) return gameCards.get(idx);
        return null; // Could add exception here
    }
}
