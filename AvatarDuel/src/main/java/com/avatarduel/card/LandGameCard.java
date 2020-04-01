package com.avatarduel.card;

import com.avatarduel.element.Element;

// Concrete Prototype
public class LandGameCard extends GameCard {
    public LandGameCard(String _name, String _desc, Element _element, String _imgUrl) {
        super(_name,_desc,_element, _imgUrl);
    }

    public GameCard clone() {
        return new LandGameCard(getName(), getDesc(), getElement(), getImgUrl());
    }
}