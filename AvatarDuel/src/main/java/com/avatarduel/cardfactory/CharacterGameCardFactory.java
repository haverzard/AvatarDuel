package com.avatarduel.cardfactory;

import com.avatarduel.card.CharacterGameCard;
import com.avatarduel.card.GameCard;
import com.avatarduel.model.Element;

import java.util.ArrayList;

/**
 * CharacterGameCarsFactory is a Factory of GameCard that create a GameCard 
 * corresponding the Card Data Stored
 * 
 * @author Kelompok 2
 */
public class CharacterGameCardFactory extends GameCardFactory {

    /**
     * Create a character gamecard.
     * @param args A formatted string from character card data csv that store all character card data 
     * @return The new CharacterGameCard corresponding args data.
     */
    @Override
    public GameCard getCard(String[] args) {
        return new CharacterGameCard(args[1], args[3], Element.valueOf(args[2]), args[4],
                Integer.parseInt(args[7]), Integer.parseInt(args[5]), Integer.parseInt(args[6]), new ArrayList<>());
    }
}
