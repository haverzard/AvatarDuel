package com.avatarduel.card;

import com.avatarduel.model.Element;

// Prototype
abstract public class SkillGameCard extends GameCard implements HasCostAttribute {
    private int cost;

    /**
     * Creates a new skill game card
     * @param _name card's name
     * @param _desc card's description
     * @param _element card's element
     * @param _imgUrl card's image url
     * @param _cost card's cost
     */
    public SkillGameCard(String _name, String _desc, Element _element, String _imgUrl, int _cost) {
        super("Skill",_name,_desc,_element, _imgUrl);
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
