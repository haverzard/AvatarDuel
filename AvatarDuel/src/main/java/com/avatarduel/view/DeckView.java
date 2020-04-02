package com.avatarduel.view;

import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

public class DeckView {
    private static HBox deckCounterBottom = new HBox();
    private static HBox deckCounterTop = new HBox();

    public static void init() {
        deckCounterTop.setAlignment(Pos.CENTER);
        deckCounterBottom.setAlignment(Pos.CENTER);
    }

    public static void updateDeckCounter(String type, int x) {
        HBox deckCounter = getDeckCounter(type);
        Text deckCounts = new Text(Integer.toString(x));
        deckCounter.getChildren().clear();
        deckCounter.getChildren().add(deckCounts);
        deckCounter.getChildren().add(new Text(" / 60"));
    }

    public static HBox getDeckCounter(String type) {
        if (type.equals("top")) {
            return deckCounterTop;
        } else {
            return deckCounterBottom;
        }
    }
}
