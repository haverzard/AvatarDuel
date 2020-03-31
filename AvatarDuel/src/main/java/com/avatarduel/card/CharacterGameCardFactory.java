package com.avatarduel.card;

import com.avatarduel.element.Element;

public class CharacterGameCardFactory extends GameCardFactory {
    @Override
    public GameCard getCard(String[] args) {
        return new CharacterGameCard(args[1], args[3], Element.valueOf(args[2]), args[4],
                Integer.parseInt(args[5]), Integer.parseInt(args[6]), Integer.parseInt(args[7]));
    }
}
