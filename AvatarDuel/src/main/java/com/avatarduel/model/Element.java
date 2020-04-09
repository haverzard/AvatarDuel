package com.avatarduel.model;

import com.avatarduel.components.Basic;
import javafx.scene.layout.Background;

public enum Element {
    WATER,
    FIRE,
    AIR,
    EARTH;

    public Background getBackground() {
        switch (this) {
            case WATER:
                return Basic.getBackground("com/avatarduel/assets/image/water.png",30,30);
            case AIR:
                return Basic.getBackground("com/avatarduel/assets/image/air.png",30,30);
            case FIRE:
                return Basic.getBackground("com/avatarduel/assets/image/fire.png",30,30);
            case EARTH:
                return Basic.getBackground("com/avatarduel/assets/image/earth.png",30,30);
            default:
                assert(false);
                return null;
        }
    }

    public String getCardTemplateURL() {
        switch (this) {
            case AIR:
                return "com/avatarduel/assets/image/airTemp.png";
            case WATER:
                return "com/avatarduel/assets/image/waterTemp.png";
            case FIRE:
                return "com/avatarduel/assets/image/fireTemp.png";
            case EARTH:
                return "com/avatarduel/assets/image/earthTemp.png";
            default:
                assert(false);
                return "";
        }
    }
};
