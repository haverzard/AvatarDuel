package com.avatarduel.card;

import com.avatarduel.model.Element;

// Prototype
abstract public class GameCard {
    private String type;
    private String name;
    private String desc;
    private Element element;
    private String imgUrl;

    public GameCard() {
        type = "";
        name = "";
        desc = "";
        element = Element.EARTH;
    }

    public GameCard(String _type, String _name, String _desc, Element _element, String _imgUrl) {
        type = _type;
        name = _name;
        desc = _desc;
        element = _element;
        imgUrl = _imgUrl;
    }

    abstract public GameCard clone();

    // Getter & Setter
    public String getName() {
        return name;
    }
    public void setName(String _name) {
        name = _name;
    }
    public String getDesc() {
        return desc;
    }
    public void setDesc(String _desc) {
        desc = _desc;
    }
    public Element getElement() {
        return element;
    }
    public void setElement(Element _element) {
        element = _element;
    }
    public String getImgUrl() {
        return imgUrl;
    }
    public void setImgUrl(String _imgUrl) {
        imgUrl = _imgUrl;
    }
    public String getType() { return type; }
}
