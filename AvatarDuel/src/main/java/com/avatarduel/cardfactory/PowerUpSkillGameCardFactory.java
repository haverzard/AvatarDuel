package com.avatarduel.cardfactory;

import com.avatarduel.card.PowerUpSkillGameCard;
import com.avatarduel.card.GameCard;
import com.avatarduel.model.Element;


/**
 * PowerUpSKillGameCarsFactory is a Factory of GameCard that create a PowerUp SKill GameCard 
 * corresponding the Power Up Skill Card Data Stored
 * 
 * @author Kelompok 2
 */
public class PowerUpSkillGameCardFactory extends GameCardFactory {
    
    /**
     * Create a power up gamecard.
     * @param args A formatted string from power up skill card data csv that store all power up skill card data 
     * @return The new powerUpSkillGameCard corresponding args data.
     */
    @Override
    public GameCard getCard(String[] args) {
        return new PowerUpSkillGameCard(args[1], args[3], Element.valueOf(args[2]), args[4],
                Integer.parseInt(args[5]));
    }
}