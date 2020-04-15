package com.avatarduel.components;

import javafx.scene.layout.HBox;

public class Space extends HBox {
    /**
     * Create a default minimum space
     * @param space Minimum value for the space created
     */
    public Space(double space) {
        super();
        setMinSize(space,space);
    }
}
