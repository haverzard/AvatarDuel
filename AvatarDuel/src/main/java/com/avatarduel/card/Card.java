package com.avatarduel.card;

import com.avatarduel.element.Element;

// Prototype
abstract public class Card {
    private String name;
    private String desc;
    private Element element;
    private String imgUrl;

    public Card() {
        name = "";
        desc = "";
        element = Element.EARTH;
    }

    public Card(String _name, String _desc, Element _element, String _imgUrl) {
        name = _name;
        desc = _desc;
        element = _element;
        imgUrl = _imgUrl;
    }

    abstract public Card clone();

    // Getter & Setter
    public String getName() {
        return name;
    }
    public void setName(String _name) {
        name = name;
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
}
