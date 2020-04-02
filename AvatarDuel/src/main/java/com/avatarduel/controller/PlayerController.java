package com.avatarduel.controller;

import com.avatarduel.components.Basic;
import com.avatarduel.model.HealthModel;
import com.avatarduel.player.Player;
import com.avatarduel.view.*;
import javafx.animation.TranslateTransition;

public class PlayerController {

    public static void updateHealth(Player p, int hp) {
        p.setHealth(hp);
    }

    public static void switchName() {
        String temp = PlayerView.getPlayerName("bottom").getText();
        PlayerView.getPlayerName("bottom").setText(PlayerView.getPlayerName("top").getText());
        PlayerView.getPlayerName("top").setText(temp);
    }

    public static void switchPlayer(Player p1, Player p2) {
        FieldView.initFieldBoxes();
        HealthModel.animateHP("bottom", p1.getHealth(), p2.getHealth());
        PowerView.updatePowerCounters("bottom", p2);
        PowerView.updatePowerCounters("top", p1);

        TranslateTransition animation = HealthModel.animateHP("top", p2.getHealth(), p1.getHealth());
        animation.setOnFinished(e2 -> {
            HealthModel.updateHealthValue("top", p1.getHealth());
            HealthModel.updateHealthValue("bottom", p2.getHealth());
            Basic.scr.setOnMouseClicked(e3 -> MainView.screen.getChildren().remove(1));
        });
        HandView.updateHand(p1,p2);
        DeckView.updateDeckCounter("bottom", p2.countCardsInDeck());
        DeckView.updateDeckCounter("top", p1.countCardsInDeck());
    }
}
