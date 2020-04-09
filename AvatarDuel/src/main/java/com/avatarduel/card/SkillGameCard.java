package com.avatarduel.card;

import com.avatarduel.model.Element;

// Prototype
abstract public class SkillGameCard extends GameCard implements HasCostAttribute {
    private int cost;

    public SkillGameCard(String _name, String _desc, Element _element, String _imgUrl, int _cost) {
        super(_name,_desc,_element, _imgUrl);
        cost = _cost;
    }

    // Getter & Setter
    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }
}
