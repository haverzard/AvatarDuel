package com.avatarduel.card;

import com.avatarduel.element.Element;

// Concrete Factory
public class AuraSkillGameCardFactory  extends GameCardFactory {
    @Override
    public GameCard getCard(String[] args) {
        return new AuraSkillGameCard(args[1], args[3], Element.valueOf(args[2]), args[4],
                Integer.parseInt(args[6]), Integer.parseInt(args[7]), Integer.parseInt(args[5]));
    }
}
