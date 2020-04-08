package com.avatarduel.cardfactory;

import com.avatarduel.card.DestroySkillGameCard;
import com.avatarduel.card.GameCard;
import com.avatarduel.element.Element;

public class DestroySkillGameCardFactory extends GameCardFactory {
    @Override
    public GameCard getCard(String[] args) {
        return new DestroySkillGameCard(args[1], args[3], Element.valueOf(args[2]), args[4],
                Integer.parseInt(args[5]));
    }
}