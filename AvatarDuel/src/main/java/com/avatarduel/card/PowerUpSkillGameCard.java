package com.avatarduel.card;

import com.avatarduel.model.Element;


/**
 * PowerUpSkillGameCard is Skill GameCard that can ubed to make attack damage effect in 
 * defense mode, this Class is to describe the PowerUpSkillGameCard.
 * 
 * @author Kelompok 2
 */
public class PowerUpSkillGameCard extends SkillGameCard implements AppliableEffect{
    /**
     * Creates a new powerup skill game card
     * @param _name card's name
     * @param _desc card's description
     * @param _element card's element
     * @param _imgUrl card's image url
     * @param _cost card's cost
     */
    public PowerUpSkillGameCard(String _name, String _desc, Element _element, String _imgUrl, int _cost) {
        super(_name,_desc,_element, _imgUrl, _cost);
    }

    /**
     * Clone the powerup skill game card
     * 
     * @return The new power up skill card that have same instance with this card object.
     */
    public GameCard clone() {
        return new PowerUpSkillGameCard(getName(), getDesc(), getElement(), getImgUrl(), getCost());
    }

    public void addEffect(CharacterGameCard card){
        card.setPowerUpinField(this);
    }

    public void removeEffect(CharacterGameCard card){
        card.detachedPowerUpinField();
    }
}