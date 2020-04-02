package com.avatarduel.view;

import com.avatarduel.components.Basic;
import com.avatarduel.player.Player;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class HealthView {
    private static Text healthValueTop = new Text();
    private static Text healthValueBottom = new Text();
    private static HBox healthBarBottom = new HBox();
    private static HBox healthBarTop = new HBox();

    public static void init() {
        initHealthBar(healthBarBottom, Player.player1);
        initHealthBar(healthBarTop, Player.player2);
        initHealthValue(healthValueBottom, Player.player1);
        initHealthValue(healthValueTop, Player.player2);
    }

    private static void initHealthBar(Pane healthBar, Player p) {
        healthBar.setBackground(Basic.getBackground(Color.DARKRED));
        healthBar.setMinWidth(p.getHealth()*(1040.0/80));
    }

    private static void initHealthValue(Text healthValue, Player p) {
        healthValue.setText(Integer.toString(p.getHealth()));
    }

    public static HBox getHealthBar(String type) {
        if (type.equals("top")) {
            return healthBarTop;
        } else {
            return healthBarBottom;
        }
    }

    public static Text getHealthValue(String type) {
        if (type.equals("top")) {
            return healthValueTop;
        } else {
            return healthValueBottom;
        }
    }
}
