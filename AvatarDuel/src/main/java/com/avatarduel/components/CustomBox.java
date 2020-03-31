package com.avatarduel.components;

import javafx.geometry.Pos;
import javafx.scene.layout.*;

import java.util.ArrayList;
import java.util.List;

public class CustomBox {
    public static HBox getBox(double size) {
        HBox box = new HBox();
        box.setMinSize(size,size);
        box.setAlignment(Pos.CENTER);
        box.setBorder(Basic.getBorder(1));
        return box;
    }
}
