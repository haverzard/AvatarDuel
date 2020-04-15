package com.avatarduel.card;
import com.avatarduel.model.Element;

public class DestroySkillGameCard extends SkillGameCard{
    /**
     * Creates a new destroy skill game card
     * @param _name card's name
     * @param _desc card's description
     * @param _element card's element
     * @param _imgUrl card's image url
     * @param _cost card's cost
     */
    public DestroySkillGameCard(String _name, String _desc, Element _element, String _imgUrl, int _cost) {
        super(_name,_desc,_element, _imgUrl, _cost);
    }

    /**
     * Clone the destroy skill game card
     */
    public GameCard clone() {
        return new DestroySkillGameCard(getName(), getDesc(), getElement(), getImgUrl(), getCost());
    }
}