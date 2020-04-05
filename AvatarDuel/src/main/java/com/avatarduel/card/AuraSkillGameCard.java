package com.avatarduel.card;

import com.avatarduel.element.Element;

// Concrete Prototype
public class AuraSkillGameCard extends SkillGameCard {
    private int attackAura;
    private int defenseAura;
    public AuraSkillGameCard(String _name, String _desc, Element _element, String _imgUrl, int _cost, int _attack, int _defense) {
        super(_name,_desc,_element, _imgUrl, _cost);
        attackAura = _attack;
        defenseAura = _defense;
    }

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
