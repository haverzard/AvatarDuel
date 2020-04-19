package com.avatarduel.view;

import com.avatarduel.model.Loc;
import com.avatarduel.components.Basic;
import com.avatarduel.components.Space;
import com.avatarduel.model.Player;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

/**
 * PlayerView describes the Player attributes in the current match on the GUI.
 * 
 * @author Kelompok 2
 */
public class PlayerView extends BorderPane {
    private Text healthValue;
    private HBox healthBar;
    private Text playerName;

    /**
     * Create the player attributes on the GUI. 
     * @param p The designated player.
     * @param type The position of the player on the field (TOP/BOTTOM)
     */
    public PlayerView(Player p, Loc type) {
        super();
        healthValue = new Text();
        healthBar = new HBox();
        playerName = new Text(String.format("Player %d - %s", p.getId(),p.getName()));
        healthValue.setText(Integer.toString(p.getHealth()));
        healthBar.setBackground(Basic.getBackground(Color.DARKRED));
        healthBar.setMinWidth(p.getHealth()*(1080.0/80));

        // Health Layout
        HBox healthBox = new HBox();
        healthBox.setMaxWidth(1080);
        healthBox.setMinHeight(30);
        healthBox.setBorder(Basic.getBorder(1));
        healthBox.setBorder(Basic.getBorder(1));

        HBox healthInfo = new HBox();
        healthInfo.getChildren().add(new Text("HP"));
        healthInfo.getChildren().add(new Space(10));

        healthInfo.getChildren().add(healthValue);
        healthBox.getChildren().add(healthBar);
        if (type == Loc.TOP) {
            setTop(healthBox);
            setLeft(playerName);
            setRight(healthInfo);
        } else {
            setBottom(healthBox);
            setRight(playerName);
            setLeft(healthInfo);
        }
    }

    /**
     * Gets the health bar of the player
     * @return The health bar of the player
     */
    public HBox getHealthBar() {
        return healthBar;
    }

    /**
     * Gets the health value of the player
     * @return The health value of the player
     */
    public Text getHealthValue() {
        return healthValue;
    }

    /**
     * Gets the player name
     * @return The name of the player
     */
    public Text getPlayerName() {
        return playerName;
    }
}
