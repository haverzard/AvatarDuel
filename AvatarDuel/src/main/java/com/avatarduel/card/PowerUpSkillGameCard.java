package com.avatarduel.card;
import com.avatarduel.element.Element;

public class PowerUpSkillGameCard extends SkillGameCard{
    public PowerUpSkillGameCard(String _name, String _desc, Element _element, String _imgUrl, int _cost) {
        super(_name,_desc,_element, _imgUrl, _cost);
    }
    public GameCard clone() {
        return new PowerUpSkillGameCard(getName(), getDesc(), getElement(), getImgUrl(), getCost());
    }
}