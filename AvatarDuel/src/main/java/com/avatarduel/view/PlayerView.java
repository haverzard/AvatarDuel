package com.avatarduel.view;

import javafx.scene.text.Text;

public class PlayerView {
    private static Text playerBottomName;
    private static Text playerTopName;

    public static void init() {
        playerBottomName = new Text();
        playerTopName = new Text();
    }

    public static Text getPlayerName(String type) {
        if (type.equals("top")) {
            return playerTopName;
        } else {
            return playerBottomName;
        }
    }
}
