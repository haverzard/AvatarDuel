package com.avatarduel.view;

import javafx.scene.text.Text;

public class PlayerView {
    private static Text playerBottomName = new Text();
    private static Text playerTopName = new Text();

    public static Text getPlayerName(String type) {
        if (type.equals("top")) {
            return playerTopName;
        } else {
            return playerBottomName;
        }
    }
}
