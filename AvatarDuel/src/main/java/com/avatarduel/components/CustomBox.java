package com.avatarduel.components;

import javafx.geometry.Pos;
import javafx.scene.layout.*;

public class CustomBox extends HBox {
    public CustomBox(double size) {
        super();
        setMinSize(size,size);
        setAlignment(Pos.CENTER);
        setBorder(Basic.getBorder(1));
    }
}
