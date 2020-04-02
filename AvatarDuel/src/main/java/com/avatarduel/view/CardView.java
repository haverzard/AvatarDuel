package com.avatarduel.view;

import com.avatarduel.components.Card;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

import java.util.ArrayList;
import java.util.List;

public class CardView {
    public static List<Pane> cardsBottom = new ArrayList<>();
    private static BorderPane cardInfo = Card.getOpenCard(250);

    public static void clearInfo() {
        Card.update(cardInfo,250,null);
    }

    public static void resetCardsBottom() {
        for (Pane pane : cardsBottom) {
            pane.setEffect(null);
        }
        cardsBottom.clear();
    }

    public static BorderPane getCardInfo() {
        return cardInfo;
    }
}
