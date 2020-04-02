package com.avatarduel.controller;

import com.avatarduel.components.Basic;
import com.avatarduel.view.*;
import com.avatarduel.element.Element;
import com.avatarduel.player.Player;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class ButtonController {
    private static void endTurn(Pane deckButton, Button end, Button main1) {
        StateController.nextTurn();
        Player.player1.refreshHand();
        Player.player2.refreshHand();
        HandView.clearHands();
        CardView.resetCardsBottom();
        MainView.screen.getChildren().add(Basic.getScreen("End Turn"));
        PlayerController.switchName();
        resetAllEffects();
        deckButton.setEffect(Basic.getShadow(Color.BLUE, 30));
        Player.player1.resetPower();
        Player.player2.resetPower();
        if (StateController.checkState("Player 1 turn")) {
            PlayerController.switchPlayer(Player.player2, Player.player1);
        } else {
            PlayerController.switchPlayer(Player.player1, Player.player2);
        }
        end.setDisable(true);
        main1.setDisable(false);
    }

    private static void resetAllEffects() {
        ButtonView.getDeckButton().setEffect(null);
        CardView.clearInfo();
        Player.player1.cardsOnField.forEach((K, V) -> V.setEffect(null));
        Player.player2.cardsOnField.forEach((K, V) -> V.setEffect(null));
    }

    private static void enterNextPhase(Button now, Button next, String message) {
        StateController.nextPhase();
        now.setDisable(true);
        next.setDisable(false);
        MainView.screen.getChildren().add(Basic.getScreen(message));
        Basic.scr.setOnMouseClicked(e3 -> MainView.screen.getChildren().remove(1));
        resetAllEffects();
    }

    public static void setPhaseButtonsEvent(Button main1, Button main2, Button battle, Button end, HBox deckButton) {
        main1.setOnAction(e -> enterNextPhase(main1, battle, "Main Phase 1"));
        battle.setOnAction(e -> enterNextPhase(battle, main2, "Battle Phase"));
        main2.setOnAction(e -> enterNextPhase(main2, end, "Main Phase 2"));
        end.setOnAction(e -> endTurn(deckButton, end, main1));
    }

    public static void setDeckButtonEvent(HBox deckButton) {
        deckButton.setOnMouseClicked(e-> {
            if (StateController.checkState("Draw phase")) {
                if (StateController.checkState("Player 1 turn")) {
                    Element x = Player.player1.takeCard();
                    if (x != null) {
                        DeckView.updateDeckCounter("bottom", Player.player1.countCardsInDeck());
                        HandView.addCardToHand(Player.player1,"bottom",x,Player.player1.countCardsInHand()-1);
                    }
                } else {
                    Element x = Player.player2.takeCard();
                    if (x != null) {
                        DeckView.updateDeckCounter("bottom", Player.player2.countCardsInDeck());
                        HandView.addCardToHand(Player.player2,"bottom",x,Player.player2.countCardsInHand()-1);
                    }
                }
                deckButton.setEffect(null);
                StateController.updateState("Draw card");
            }
        });
    }
}
