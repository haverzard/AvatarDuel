package com.avatarduel.card;

import com.avatarduel.element.Element;

public class LandCard extends Card {
    public LandCard(String _name, String _desc, Element _element, String _imgUrl) {
        super(_name,_desc,_element, _imgUrl);
    }

    public Card clone() {
        return new LandCard(getName(), getDesc(), getElement(), getImgUrl());
    }
}
