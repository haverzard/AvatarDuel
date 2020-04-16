package com.avatarduel.card;

import com.avatarduel.model.Element;

import java.util.ArrayList;
import java.util.List;

// Concrete Prototype

/**
 * CharacterGameCard is Gamecard that have some defined properties
 * like cost to use, attack value, defense value, and some skill attached.S
 * 
 * @author Kelompok 2
 */
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
     * Clone the character game card.
     * NOTE: skill is not included
     */
    public GameCard clone() {
        return new CharacterGameCard(getName(), getDesc(), getElement(), getImgUrl(), cost, attack, defense, new ArrayList<>());
    }

    // Getter & Setter

    /**
     * Gets the cost to add character to arena.
     *
     * @return The cost needed to add character to arena.
     */
    public int getCost() {
        return cost;
    }


    /**
     * Sets the cost to add character to arena.
     *
     * @param _cost  The desired cost needed to add character to arena.
     */
    public void setCost(int _cost) {
        cost = _cost;
    }

    /**
     * Get card's attack
     * with the applied attack effects on it
     * 
     * @return the attack of this character card that has been added with bonus attack.
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
     * 
     * @return the defence attribute of this character card that has been added with defence bonus.
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
     * 
     * @return List of Aura skill gamecard that this character have.
     */
    public List<AuraSkillGameCard> getAuraSkillGameCardsList() {
        return listofAura;
    }

    /**
     * Get card's powerup skill
     * 
     * @return List of power up skill gamecard that this character have.
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
     * 
     * @return logical condition : TRUE if the field not have power up skill and FALSE otherwise.
     */
    public boolean isAttachedPowerUpinField() {
        return powerUpCard != null;
    }
 }
