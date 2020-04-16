package com.avatarduel.card;

import com.avatarduel.model.Element;

// Concrete Prototype
public class LandGameCard extends GameCard {
    /**
     * Creates a new land game card
     * @param _name card's name
     * @param _desc card's description
     * @param _element card's element
     * @param _imgUrl card's image url
     */
    public LandGameCard(String _name, String _desc, Element _element, String _imgUrl) {
        super("Land",_name,_desc,_element, _imgUrl);
    }

    /**
     * Clone the land game card
     * 
     * @return the new Land Gamecard with same instance with this Land card.
     */
    public GameCard clone() {
        return new LandGameCard(getName(), getDesc(), getElement(), getImgUrl());
    }
}
