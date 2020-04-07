package com.avatarduel.deck;

import com.avatarduel.card.GameCard;

public interface HasDynamicCapacity extends HasCapacity {
    public void addDynamically(GameCard x);
    public void increaseCapacity();
}