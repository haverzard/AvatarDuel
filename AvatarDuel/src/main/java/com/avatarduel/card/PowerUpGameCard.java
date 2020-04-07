package com.avatarduel.card;
import com.avatarduel.element.Element;

public class PowerUpGameCard extends SkillGameCard{
    public PowerUpGameCard(String _name, String _desc, Element _element, String _imgUrl, int _cost) {
        super(_name,_desc,_element, _imgUrl, _cost);
    }
    public GameCard clone() {
        return new PowerUpGameCard(getName(), getDesc(), getElement(), getImgUrl(), getCost());
    }
}