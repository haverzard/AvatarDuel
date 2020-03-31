package com.avatarduel.card;

import com.avatarduel.element.Element;

// Concrete Prototype
public class CharacterGameCard extends GameCard implements HasCostAttribute, HasBattleAttribute {
    private int cost;
    private int attack;
    private int defense;

    public CharacterGameCard() {
        super();
        cost = 0;
        attack = 0;
        defense = 0;
    }

    public CharacterGameCard(String _name, String _desc, Element _element, String _imgUrl, int _cost, int _attack, int _defense) {
        super(_name,_desc,_element, _imgUrl);
        cost = _cost;
        attack = _attack;
        defense = _defense;
    }

    public GameCard clone() {
        return new CharacterGameCard(getName(), getDesc(), getElement(), getImgUrl(), cost, attack, defense);
    }

    // Getter & Setter
    public int getCost() {
        return cost;
    }

    public void setCost(int _cost) {
        cost = _cost;
    }

    public int getAttack() {
        return attack;
    }

    public void setAttack(int _attack) {
        attack = _attack;
    }

    public int getDefense() {
        return defense;
    }

    public void setDefense(int _defense) {
        defense = _defense;
    }
}
