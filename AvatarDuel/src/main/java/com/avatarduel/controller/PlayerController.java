package com.avatarduel.controller;

import com.avatarduel.model.Loc;
import com.avatarduel.model.Player;
import com.avatarduel.model.HealthModel;
import com.avatarduel.view.PlayerView;
import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.scene.layout.HBox;
import javafx.util.Duration;

public class PlayerController {
    private PlayerView topHealthV;
    private PlayerView bottomHealthV;
    private HealthModel topHealthM;
    private HealthModel bottomHealthM;

    public PlayerController() {
        Player.getPlayers();
        topHealthV = new PlayerView(Player.player2, Loc.TOP);
        bottomHealthV = new PlayerView(Player.player1, Loc.BOTTOM);
        topHealthM = new HealthModel();
        bottomHealthM = new HealthModel();
    }

    public PlayerView getTopPlayerV() {
        return topHealthV;
    }

    public PlayerView getBottomPlayerV() {
        return bottomHealthV;
    }

    public PlayerView getPlayerV(Loc type) {
        if (type == Loc.TOP) return topHealthV;
        else return bottomHealthV;
    }

    public HealthModel getTopHealthM() {
        return topHealthM;
    }

    public HealthModel getBottomHealthM() {
        return bottomHealthM;
    }

    public HealthModel getHealthM(Loc type) {
        if (type == Loc.TOP) return topHealthM;
        else return bottomHealthM;
    }

    public void updateHealth(Player p, int hp) {
        p.setHealth(hp);
    }

    public void switchName() {
        String temp = getPlayerV(Loc.BOTTOM).getPlayerName().getText();
        getPlayerV(Loc.BOTTOM).getPlayerName().setText(getPlayerV(Loc.TOP).getPlayerName().getText());
        getPlayerV(Loc.TOP).getPlayerName().setText(temp);
    }

    public TranslateTransition animateHP(Loc type, double init, double goal) {
        HBox healthBar = getPlayerV(type).getHealthBar();
        ScaleTransition a = new ScaleTransition(Duration.seconds(0.5), healthBar);
        TranslateTransition b = new TranslateTransition(Duration.seconds(0.5), healthBar);

        // Because double is not mutable
        double hpPercentage = getHealthM(type).getHpPercentage();
        double slide = getHealthM(type).getSlide();
        a.setFromX(hpPercentage);
        b.setFromX(slide);
        getHealthM(type).setHpPercentage(goal/init*hpPercentage);
        getHealthM(type).setSlide((goal-init)*1080/80/2+slide);
        hpPercentage = getHealthM(type).getHpPercentage();
        slide = getHealthM(type).getSlide();
        a.setToX(hpPercentage);
        b.setToX(slide);
        a.play();
        b.play();

        return b;
    }

    public void updateHealthValue(Loc type, int x) {
        getPlayerV(type).getHealthValue().setText(Integer.toString(x));
    }
}
