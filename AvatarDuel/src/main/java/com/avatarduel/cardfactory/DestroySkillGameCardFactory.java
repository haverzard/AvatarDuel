package com.avatarduel.cardfactory;

import com.avatarduel.card.DestroySkillGameCard;
import com.avatarduel.card.GameCard;
import com.avatarduel.model.Element;


/**
 * DestroySkillGameCardFactory is a Factory class for create Destroy Skill
 * GameCard with corresponding data.
 * 
 * @author Kelompok 2
 */
public class DestroySkillGameCardFactory extends GameCardFactory {

    /**
     * Create a DestroySkillGameCard from the data stored.
     *
     * @param args A formatted list of string from destroy skill card data csv with that store all skill card data 
     *
     * @return The new DestroySkillGameCard corresponding args data.
     */
    @Override
    public GameCard getCard(String[] args) {
        return new DestroySkillGameCard(args[1], args[3], Element.valueOf(args[2]), args[4],
                Integer.parseInt(args[5]));
    }
}