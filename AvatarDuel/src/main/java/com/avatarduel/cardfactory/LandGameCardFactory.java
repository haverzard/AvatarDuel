package com.avatarduel.cardfactory;

import com.avatarduel.card.GameCard;
import com.avatarduel.card.LandGameCard;
import com.avatarduel.model.Element;


/**
 * landGameCarsFactory is a Factory of GameCard that create a Land GameCard 
 * corresponding the Land Card Data Stored
 * 
 * @author Kelompok 2
 */
public class LandGameCardFactory extends GameCardFactory {
    
    /**
     * Create a land gamecard.
     * @param args A formatted string from land card data csv that store all land card data 
     * @return The new LandrGameCard corresponding args data.
     */
    @Override
    public GameCard getCard(String[] args) {
        return new LandGameCard(args[1], args[3], Element.valueOf(args[2]), args[4]);
    }
}
