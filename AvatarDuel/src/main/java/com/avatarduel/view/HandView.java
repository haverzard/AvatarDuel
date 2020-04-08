package com.avatarduel.view;

import com.avatarduel.components.ClosedCard;
import com.avatarduel.components.OpenedCard;
import com.avatarduel.components.Space;
import com.avatarduel.controller.CardController;
import com.avatarduel.element.Element;
import com.avatarduel.model.StateModel;
import com.avatarduel.player.Player;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

public class HandView {
    private static HBox bottomHand;
    private static HBox topHand;

    public static void init() {
        bottomHand = new HBox();
        topHand = new HBox();
        initHand(topHand);
        initHand(bottomHand);
    }

    private static void initHand(HBox hand) {
        hand.setMinWidth(800);
        hand.setMinHeight(130);
        hand.setAlignment(Pos.CENTER);
    }

    public static HBox getHand(String type) {
        if (type.equals("top")) {
            return topHand;
        } else {
            return bottomHand;
        }
    }

    public static void updateHand(Player p1, Player p2) {
        for (int i=0; i<p2.countCardsInHand(); i++) {
            addCardToHand(p2,"bottom",p2.getHand(i).getElement(),i);
        }
        for (int i=0; i<p1.countCardsInHand(); i++) {
            addCardToHand(p1,"top",p1.getHand(i).getElement(),i);
        }
    }

    public static void removeFromHand() {
        Player.player1.refreshHand();
        Player.player2.refreshHand();
        CardView.resetCardsBottom();
        HandView.clearHands();
        if (Player.player1.getId() == StateModel.getTurn()) updateHand(Player.player2,Player.player1);
        else updateHand(Player.player1,Player.player2);
    }

    public static void clearHands() {
        topHand.getChildren().clear();
        bottomHand.getChildren().clear();
    }

    public static void addCardToHand(Player a, String type, Element x, int idx) {
        HBox hand = getHand(type);
        Pane card;
        if (a.getId() == StateModel.getTurn()) {
            card = new OpenedCard(62.5, x);
            CardView.cardsBottom.add(card);
            CardController.showInfoOnHover(card, a, idx);
            CardController.setCardEventOnHand(card, a);
        } else {
            card = new ClosedCard(62.5);
        }
        hand.getChildren().add(card);
        hand.getChildren().add(new Space(10));
    }
}
