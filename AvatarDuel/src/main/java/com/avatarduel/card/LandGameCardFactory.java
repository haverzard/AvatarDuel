package com.avatarduel.card;

import com.avatarduel.element.Element;

public class LandGameCardFactory extends GameCardFactory {
    @Override
    public GameCard getCard(String[] args) {
        return new LandGameCard(args[1], args[3], Element.valueOf(args[2]), args[4]);
    }
}
