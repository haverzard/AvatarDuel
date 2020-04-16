package com.avatarduel.cardfactory;

import com.avatarduel.card.AuraSkillGameCard;
import com.avatarduel.card.GameCard;
import com.avatarduel.model.Element;

// Concrete Factory

/**
 * Factory class to create a Aura Skill Gamecard
 */
public class AuraSkillGameCardFactory  extends GameCardFactory {
 
    /**
     * Gets the aura skill card.
     * @param      args  A formatted string from skill card data csv that store all skill card data 
     * @return     The aura skill gamecard.
     */
    @Override
    public GameCard getCard(String[] args) {
        return new AuraSkillGameCard(args[1], args[3], Element.valueOf(args[2]), args[4],
                Integer.parseInt(args[5]), Integer.parseInt(args[6]), Integer.parseInt(args[7]));
    }
}
