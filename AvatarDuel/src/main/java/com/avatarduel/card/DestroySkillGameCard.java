package com.avatarduel.card;
import com.avatarduel.element.Element;

public class DestroySkillGameCard extends SkillGameCard{
    public DestroySkillGameCard(String _name, String _desc, Element _element, String _imgUrl, int _cost) {
        super(_name,_desc,_element, _imgUrl, _cost);
    }
    public GameCard clone() {
        return new DestroySkillGameCard(getName(), getDesc(), getElement(), getImgUrl(), getCost());
    }
}