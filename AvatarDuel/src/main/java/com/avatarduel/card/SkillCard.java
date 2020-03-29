package com.avatarduel.card;

import com.avatarduel.element.Element;

public class SkillCard extends Card implements HasCostAttribute {
    private int cost;

    public SkillCard(String _name, String _desc, Element _element, String _imgUrl, int _cost) {
        super(_name,_desc,_element, _imgUrl);
        cost = _cost;
    }

    public Card clone() {
        return new SkillCard(getName(), getDesc(), getElement(), getImgUrl(), cost);
    }

    // Getter & Setter
    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }
}
