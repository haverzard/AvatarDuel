package com.avatarduel.card;

import com.avatarduel.model.Element;

// Concrete Prototype
public class AuraSkillGameCard extends SkillGameCard implements HasAuraEffect, AppliableEffect{
    private int attackAura;
    private int defenseAura;

    /**
     * Creates a new aura skill game card
     * @param _name card's name
     * @param _desc card's description
     * @param _element card's element
     * @param _imgUrl card's image url
     * @param _cost card's cost
     * @param _attack card's attack aura
     * @param _defense card's defense aura
     */
    public AuraSkillGameCard(String _name, String _desc, Element _element, String _imgUrl, int _cost, int _attack, int _defense) {
        super(_name,_desc,_element, _imgUrl, _cost);
        attackAura = _attack;
        defenseAura = _defense;
    }

    /**
     * Clone the aura skill game card
     */
    public GameCard clone() {
        return new AuraSkillGameCard(getName(), getDesc(), getElement(), getImgUrl(), getCost(), getAttackAura(), getDefenseAura());
    }

    // Getter & Setter
    public int getAttackAura() {
        return attackAura;
    }

    public void setAttackAura(int _attack) {
        attackAura = _attack;
    }

    public int getDefenseAura() {
        return defenseAura;
    }

    public void setDefenseAura(int _defense) {
        defenseAura = _defense;
    }

    public void addEffect(CharacterGameCard card){
        card.addAuraSkill(this);
    }

    public void removeEffect(CharacterGameCard card){
        card.removeAuraSkill(this);
    }
}
