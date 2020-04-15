package com.avatarduel.deck;

import com.avatarduel.card.GameCard;

/**
 * HasDynamicCapacity is sub-interface of HasCapacity that has the ability
 * to extend dynamically.
 */
public interface HasDynamicCapacity extends HasCapacity {

    /**
     * Add a card to class
     * @param x to be inserted card
     */
    public void addDynamically(GameCard x);

    /**
     * Increase capacity attribute by 1
     */
    public void increaseCapacity();
}