package com.avatarduel.model;

import com.avatarduel.AvatarDuel;
import com.avatarduel.components.Basic;
import com.avatarduel.controller.CardController;
import com.avatarduel.controller.PlayerController;
import com.avatarduel.controller.StateController;
import com.avatarduel.player.Player;
import com.avatarduel.view.FieldView;
import com.avatarduel.view.HealthView;
import com.avatarduel.view.MainView;
import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

public class HealthModel {
    //private static double oldHealth;
    private static double hpPercentageBottom = 1;
    private static double hpPercentageTop = 1;
    private static double slideBottom = 0;
    private static double slideTop = 0;

    public static void init() {
        hpPercentageBottom = 1;
        hpPercentageTop = 1;
        slideBottom = 0;
        slideTop = 0;
    }

    public static TranslateTransition animateHP(String type, double init, double goal) {
        HBox healthBar = HealthView.getHealthBar(type);
        ScaleTransition a = new ScaleTransition(Duration.seconds(1), healthBar);
        TranslateTransition b = new TranslateTransition(Duration.seconds(1), healthBar);

        // Because double is not mutable
        if (type.equals("bottom")) { // Reference equality
            a.setFromX(hpPercentageBottom);
            b.setFromX(slideBottom);
            hpPercentageBottom = goal/init*hpPercentageBottom;
            slideBottom = (goal-init)*1040/80/2+slideBottom;
            a.setToX(hpPercentageBottom);
            b.setToX(slideBottom);
        } else {
            a.setFromX(hpPercentageTop);
            b.setFromX(slideTop);
            hpPercentageTop = goal/init*hpPercentageTop;
            slideTop = (goal-init)*1040/80/2+slideTop;
            a.setToX(hpPercentageTop);
            b.setToX(slideTop);
        }
        a.play();
        b.play();

        return b;
    }

    public static void updateHealthValue(String type, int x) {
        HealthView.getHealthValue(type).setText(Integer.toString(x));
    }

    public static void updateAttack(Player attacker, Player enemy, int idx, int damage, boolean isHitOnEnemy) {
        if (damage > 0) {
            int hp = enemy.getHealth();
            int newhp = hp - damage;
            if (newhp < 0) newhp = 0;
            animateHP("top", hp, newhp);
            PlayerController.updateHealth(enemy, newhp);
            updateHealthValue("top", newhp);
            if (newhp <= 0) {
                MainView.loadLoseScreen(attacker);
            }
        }
        attacker.switchCardMode(StateModel.getTargetAttack());
        if (isHitOnEnemy) {
            CardController.deleteCard(enemy, idx);
        }
        StateController.updateState("Release attack card");
        attacker.cardsOnField.forEach((K, V) -> V.setEffect(null));

    }
}
