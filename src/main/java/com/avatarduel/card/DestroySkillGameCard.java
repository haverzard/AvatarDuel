package com.avatarduel.card;
import com.avatarduel.model.Element;


/**
 * DestroySkillGameCard is a Skill Gamecard that can be used to destroy the enemy character card
 * with a certain cost.
 * 
 * @author Kelompok 2
 */
public class DestroySkillGameCard extends SkillGameCard{
    /**
     * Creates a new destroy skill game card
     * @param _name card's name
     * @param _desc card's description
     * @param _element card's element
     * @param _imgUrl card's image url
     * @param _cost card's cost
     */
    public DestroySkillGameCard(String _name, String _desc, Element _element, String _imgUrl, int _cost) {
        super(_name,_desc,_element, _imgUrl, _cost);
    }

    /**
     * Clone the destroy skill game card
     * 
     * @return the new Destroy Skill Gamecard with same instance of this destroy card object.
     */
    public GameCard clone() {
        return new DestroySkillGameCard(getName(), getDesc(), getElement(), getImgUrl(), getCost());
    }
}