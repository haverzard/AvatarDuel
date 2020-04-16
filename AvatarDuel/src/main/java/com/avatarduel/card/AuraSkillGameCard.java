package com.avatarduel.card;

import com.avatarduel.model.Element;

// Concrete Prototype

/**
 * This class describes an aura skill game card that extends skill card class
 * and have aura effect that can appliable.
 * 
 * @author Kelompok 2
 */
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
     * @return A Aura Skill Card with same instance with this object
     */
    public GameCard clone() {
        return new AuraSkillGameCard(getName(), getDesc(), getElement(), getImgUrl(), getCost(), getAttackAura(), getDefenseAura());
    }

    // Getter & Setter

    /**
     * Gets the attack number of this card
     * @return The attack number of this card.
     */
    public int getAttackAura() {
        return attackAura;
    }


    /**
     * Sets the attack aura.
     * @param  _attack The desired attack number.
     */
    public void setAttackAura(int _attack) {
        attackAura = _attack;
    }


    /**
     * Gets the defenseAura attribute.
     * @return The defense aura attribute of object.
     */
    public int getDefenseAura() {
        return defenseAura;
    }

    /**
     * Sets the defense aura.
     * @param _defense  The defenseAura attribute value desired.
     */
    public void setDefenseAura(int _defense) {
        defenseAura = _defense;
    }


    /**
     * Adds an effect.
     * @param card  The character card that use this aura skill
     */
    public void addEffect(CharacterGameCard card){
        card.addAuraSkill(this);
    }


    /**
     * Removes an aura card effect in a character card.
     * @param card  The character card that use this aura skill
     */
    public void removeEffect(CharacterGameCard card){
        card.removeAuraSkill(this);
    }
}
