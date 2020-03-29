package com.avatarduel.components;

import javafx.geometry.Pos;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class Attribute {
    public static BorderPane getElementBox(String img) {
        Text count = new Text("0/0");
        count.setFont(Font.font(20));

        HBox text = new HBox();
        text.setMinHeight(30);
        text.setAlignment(Pos.CENTER);
        text.getChildren().add(count);

        HBox element = new HBox();
        element.setMinSize(30,30);
        element.setBorder(Basic.getBorder(1));

        BorderPane elementBox = new BorderPane();
        elementBox.setLeft(text);
        elementBox.setRight(element);

        return elementBox;
    }

    public static HBox getDeckBox() {
        Text count = new Text("0/0");
        count.setFont(Font.font(20));

        HBox deckBox = new HBox();
        deckBox.setMinHeight(30);
        deckBox.setAlignment(Pos.CENTER);
        deckBox.getChildren().add(count);

        return deckBox;
    }
}
