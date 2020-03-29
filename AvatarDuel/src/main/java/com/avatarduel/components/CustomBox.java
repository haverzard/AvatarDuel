package com.avatarduel.components;

import javafx.geometry.Pos;
import javafx.scene.layout.*;

public class CustomBox {
    public static HBox getBox(double size) {
        HBox box = new HBox();
        box.setMinSize(size,size);
        box.setBorder(Basic.getBorder(1));
        return box;
    }

    public static HBox getBoxField(int counts, double size, double pad) {
        HBox boxField = new HBox();
        boxField.setMinWidth((size+pad)*counts+pad);
        boxField.setAlignment(Pos.CENTER);
        for (int i=0; i<counts-1; i++) {
            boxField.getChildren().add(getBox(size));
            boxField.getChildren().add(Basic.getSpace(10));
        }
        boxField.getChildren().add(getBox(size));
        return boxField;
    }
}
