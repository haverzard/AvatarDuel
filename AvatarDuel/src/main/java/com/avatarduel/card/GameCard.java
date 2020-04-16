package com.avatarduel.card;

import com.avatarduel.model.Element;

/**
 * GameCard is a base class for all game cards.
 *
 * @author Kelompok 2
 */
// Prototype
abstract public class GameCard {
    private String type;
    private String name;
    private String desc;
    private Element element;
    private String imgUrl;

    /**
     * Creates a new default game card
     */
    public GameCard() {
        type = "";
        name = "";
        desc = "";
        element = Element.EARTH;
    }

    /**
     * Creates a new game card
     * @param _type card's type
     * @param _name card's name
     * @param _desc card's description
     * @param _element card's element
     * @param _imgUrl card's image url
     */
    public GameCard(String _type, String _name, String _desc, Element _element, String _imgUrl) {
        type = _type;
        name = _name;
        desc = _desc;
        element = _element;
        imgUrl = _imgUrl;
    }

    /**
     * Clone the game card
     */
    abstract public GameCard clone();

    // Getter & Setter
    /**
     * Get card's name

     @return The name of Gamecards.
     */
    public String getName() {
        return name;
    }

    /**
     * Set card's name
     * @param _name new card's name
     */
    public void setName(String _name) {
        name = _name;
    }

    /**
     * Get card's description
     * @return The decription attribute of this card
     */
    public String getDesc() {
        return desc;
    }

    /**
     * Set card's description
     * @param _desc new card's description
     */
    public void setDesc(String _desc) {
        desc = _desc;
    }

    /**
     * Get card's element
     * 
     * @return The type of element attribute of this card
     */
    public Element getElement() {
        return element;
    }

    /**
     * Set card's element
     * @param _element new card's element
     */
    public void setElement(Element _element) {
        element = _element;
    }

    /**
     * Get card's image url
     * 
     * @return The URL or address of this card image.
     */
    public String getImgUrl() {
        return imgUrl;
    }

    /**
     * Set card's image url
     * @param _imgUrl new card's image url
     */
    public void setImgUrl(String _imgUrl) {
        imgUrl = _imgUrl;
    }

    /**
     * Get card's type
     * 
     * @return The type of this Gamecard.
     */
    public String getType() {
        return type; 
    }
}
