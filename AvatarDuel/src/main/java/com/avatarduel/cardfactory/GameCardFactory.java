package com.avatarduel.cardfactory;

import com.avatarduel.card.GameCard;

// Abstract Factory
abstract public class GameCardFactory {
    abstract public GameCard getCard(String[] args);
}
