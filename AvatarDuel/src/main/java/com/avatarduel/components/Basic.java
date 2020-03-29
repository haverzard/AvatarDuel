package com.avatarduel.components;

import com.avatarduel.AvatarDuel;
import javafx.animation.ScaleTransition;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.Text;
import javafx.util.Duration;

public class Basic {
    public static HBox scr;

    public static HBox getSpace(double space) {
        HBox spaceBox = new HBox();
        spaceBox.setMinSize(space,space);
        return spaceBox;
    }

    public static Background getBackground(Color x) {
        return new Background(new BackgroundFill(x, CornerRadii.EMPTY, Insets.EMPTY));
    }

    public static Border getBorder(double all) {
        return new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, null, new BorderWidths(all)));
    }

    public static Border getBorder(double all, Color x) {
        return new Border(new BorderStroke(x, BorderStrokeStyle.SOLID, null, new BorderWidths(all)));
    }

    public static Border getBorder(double top, double right, double bottom, double left) {
        return new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, null, new BorderWidths(top,right,bottom,left)));
    }

    public static Border getBorder(double top, double right, double bottom, double left, Color x) {
        return new Border(new BorderStroke(x, BorderStrokeStyle.SOLID, null, new BorderWidths(top,right,bottom,left)));
    }

    public static HBox getScreen(String text) {
        scr = new HBox();
        Text main = new Text(text);
        main.setFont(Font.font("Courier New", FontPosture.ITALIC, 100));
        main.setFill(Color.WHITE);
        scr.setMinSize(1380,880);
        scr.setAlignment(Pos.CENTER);
        scr.setBackground(getBackground(new Color(0,0,0,0.3)));
        scr.getChildren().add(main);

        return scr;
    }
}
