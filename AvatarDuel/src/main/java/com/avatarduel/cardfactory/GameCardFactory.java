package com.avatarduel.cardfactory;

import com.avatarduel.card.GameCard;

// Abstract Factory

/**
 * GameCardFactory is a base class for all GameCard Factory child class.
 * 
 * @author Kelompok 2
 */
abstract public class GameCardFactory implements CardCreation {

    /**
     * Create the card with corresponding features.
     *
     * @param args  The list of string that contain data of creating game card
     *
     * @return The a certain new gamecard.
     */
    abstract public GameCard getCard(String[] args);
}
