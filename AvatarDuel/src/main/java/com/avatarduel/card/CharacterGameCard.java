package com.avatarduel.card;

import com.avatarduel.model.Element;

import java.util.ArrayList;
import java.util.List;

// Concrete Prototype
public class CharacterGameCard extends GameCard implements HasCostAttribute, HasBattleAttribute {
    private int cost;
    private int attack;
    private int defense;
    private PowerUpSkillGameCard powerUpCard;
    private List<AuraSkillGameCard> listofAura;

    /**
     * Creates a new default character game card
     */
    public CharacterGameCard() {
        super();
        cost = 0;
        attack = 0;
        defense = 0;
        powerUpCard = null;
        this.listofAura = null;
    }

    /**
     * Creates a new character game card
     * @param _name card's name
     * @param _desc card's description
     * @param _element card's element
     * @param _imgUrl card's image url
     * @param _cost card's cost
     * @param _attack card's base attack
     * @param _defense card's base defense
     * @param list card's list's implementation
     */
    public CharacterGameCard(String _name, String _desc, Element _element, String _imgUrl, int _cost, int _attack, int _defense, List<AuraSkillGameCard> list) {
        super("Character",_name,_desc,_element, _imgUrl);
        cost = _cost;
        attack = _attack;
        defense = _defense;
        powerUpCard = null;
        this.listofAura = list;
    }

    /**
     * Clone the character game card
     */
    public GameCard clone() {
        return new CharacterGameCard(getName(), getDesc(), getElement(), getImgUrl(), cost, attack, defense, new ArrayList<>());
    }

    // Getter & Setter
    public int getCost() {
        return cost;
    }
    public void setCost(int _cost) {
        cost = _cost;
    }
    /**
     * Get card's attack
     * with the applied attack effects on it
     */
    public int getAttack() {
        int bonusAttack = 0;
        for (AuraSkillGameCard x : this.listofAura){
            bonusAttack += x.getAttackAura();
        }
        return attack + bonusAttack;
    }
    /**
     * Set card's base attack
     * @param _attack new card's base attack
     */
    public void setAttack(int _attack) {
        attack = _attack;
    }
    /**
     * Get card's defense
     * with the applied defense effects on it
     */
    public int getDefense() {
        int bonusDefense = 0;
        for (AuraSkillGameCard x : this.listofAura){
            bonusDefense += x.getDefenseAura();
        }
        return defense + bonusDefense;
    }
    /**
     * Set card's base defense
     * @param _defense new card's base defense
     */
    public void setDefense(int _defense) {
        defense = _defense;
    }
    /**
     * Get card's aura skills
     */
    public List<AuraSkillGameCard> getAuraSkillGameCardsList() {
        return listofAura;
    }
    /**
     * Get card's powerup skill
     */
    public PowerUpSkillGameCard getPowerUpSkillGameCard() {
        return powerUpCard;
    }

    /**
     * Add or apply aura skill to the card
     * @param newCard aura skill card to be applied
     */
    public void addAuraSkill (AuraSkillGameCard newCard){
        this.listofAura.add(newCard);
    }

    /**
     * Remove aura skill from the card
     * @param newCard aura skill card to be removed
     */
    public void removeAuraSkill (AuraSkillGameCard newCard){
        this.listofAura.remove(newCard);
    }

    /**
     * Remove all effects or skills
     */
    public void nullifyAllSkill (){
        this.listofAura.clear();
        powerUpCard = null;
    }

    /**
     * Add or apply powerup skill to the card
     * @param card powerup skill card to be applied
     */
    public void setPowerUpinField(PowerUpSkillGameCard card){
        powerUpCard = card;
    }

    /**
     * Remove powerup skill from the card
     */
    public void detachedPowerUpinField(){
        powerUpCard = null;
    }

    /**
     * Return true if there is an attached power up skill on the card
     */
    public boolean isAttachedPowerUpinField() {
        return powerUpCard != null;
    }
 }
