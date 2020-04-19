package com.avatarduel.controller;

import com.avatarduel.model.Loc;
import com.avatarduel.model.Player;
import com.avatarduel.model.HealthModel;
import com.avatarduel.view.PlayerView;
import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.scene.layout.HBox;
import javafx.util.Duration;

/**
 * Represent the Player Controller for MVC pattern in AvatarDuel
 */
public class PlayerController {
    private PlayerView topHealthV;
    private PlayerView bottomHealthV;
    private HealthModel topHealthM;
    private HealthModel bottomHealthM;

    /**
     * Create new player controller
     */
    public PlayerController() {
        Player.getPlayers();
        topHealthV = new PlayerView(Player.player2, Loc.TOP);
        bottomHealthV = new PlayerView(Player.player1, Loc.BOTTOM);
        topHealthM = new HealthModel();
        bottomHealthM = new HealthModel();
    }

    /**
     * Get top player view
     * @return top player view
     */
    public PlayerView getTopPlayerV() {
        return topHealthV;
    }

    /**
     * Get bottom player view
     * @return bottom player view
     */
    public PlayerView getBottomPlayerV() {
        return bottomHealthV;
    }

    /**
     * Get player view
     * @param type player's location
     * @return player view
     */
    public PlayerView getPlayerV(Loc type) {
        if (type == Loc.TOP) return topHealthV;
        else return bottomHealthV;
    }

    /**
     * Get top health model
     * @return top health model
     */
    public HealthModel getTopHealthM() {
        return topHealthM;
    }

    /**
     * Get bottom health model
     * @return bottom health model
     */
    public HealthModel getBottomHealthM() {
        return bottomHealthM;
    }

    /**
     * Get health model
     * @param type player's location
     * @return health model
     */
    public HealthModel getHealthM(Loc type) {
        if (type == Loc.TOP) return topHealthM;
        else return bottomHealthM;
    }

    /**
     * Update helath point
     * @param p for player
     * @param hp new health point
     */
    public void updateHealth(Player p, int hp) {
        p.setHealth(hp);
    }

    /**
     * Switch name player postion
     */
    public void switchName() {
        String temp = getPlayerV(Loc.BOTTOM).getPlayerName().getText();
        getPlayerV(Loc.BOTTOM).getPlayerName().setText(getPlayerV(Loc.TOP).getPlayerName().getText());
        getPlayerV(Loc.TOP).getPlayerName().setText(temp);
    }

    /**
     * Update hp with animation
     * @param type player's location
     * @param init initial point
     * @param goal new point
     * @return the animation
     */
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

    /**
     * Update healt value text
     * @param type player's location
     * @param x new point
     */
    public void updateHealthValue(Loc type, int x) {
        getPlayerV(type).getHealthValue().setText(Integer.toString(x));
    }
}
