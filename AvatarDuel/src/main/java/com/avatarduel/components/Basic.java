package com.avatarduel.components;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.Text;

/**
 * Basic getter for GUI framework
 */
public class Basic {
    public static HBox scr;

    /**
     * Get the Background value
     * @param x JavaFX's color
     */
    public static Background getBackground(Color x) {
        return new Background(new BackgroundFill(x, CornerRadii.EMPTY, Insets.EMPTY));
    }

    /**
     * Get the Background value
     * @param url JavaFX's URL
     */
    public static Background getBackground(String url) {
        return new Background(new BackgroundImage(new Image(url), BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT));
    }

    /**
     * Get the Background value
     * @param url JavaFX's color
     * @param x horizontal size
     * @param y vertical size
     */
    public static Background getBackground(String url, double x, double y) {
        return new Background(new BackgroundImage(new Image(url), BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                new BackgroundSize(x,y,false,false,false,false)));
    }

    /**
     * Get the Border value
     * @param all border width's value
     */
    public static Border getBorder(double all) {
        return new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, null, new BorderWidths(all)));
    }

    /**
     * Get the border value
     * @param all border width's value
     * @param x Border's color
     */
    public static Border getBorder(double all, Color x) {
        return new Border(new BorderStroke(x, BorderStrokeStyle.SOLID, null, new BorderWidths(all)));
    }

    /**
     * Get the border value
     * @param top top border width value
     * @param right right border width value
     * @param bottom bottom border width value
     * @param left left border width value
     */
    public static Border getBorder(double top, double right, double bottom, double left) {
        return new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, null, new BorderWidths(top,right,bottom,left)));
    }

    /**
     * Get the border value
     * @param top top border width value
     * @param right right border width value
     * @param bottom bottom border width value
     * @param left left border width value
     * @param x border color
     */
    public static Border getBorder(double top, double right, double bottom, double left, Color x) {
        return new Border(new BorderStroke(x, BorderStrokeStyle.SOLID, null, new BorderWidths(top,right,bottom,left)));
    }

    /**
     * Get HBox value
     * @param text Text for the label
     */
    public static HBox getScreen(String text) {
        scr = new HBox();
        Label main = new Label(text);
        main.setFont(Font.font("Courier New", FontPosture.ITALIC, 100));
        main.setTextFill(Color.WHITE);
        main.setWrapText(true);
        scr.setMinSize(1380,880);
        scr.setAlignment(Pos.CENTER);
        scr.setBackground(getBackground(new Color(0,0,0,0.3)));
        scr.getChildren().add(main);

        return scr;
    }

    /**
     * Get the DropShadow Value
     * @param x border color
     * @param spread border width value
     */
    public static DropShadow getShadow(Color x, double spread) {
        DropShadow border = new DropShadow();
        border.setColor(x);
        border.setWidth(spread);
        border.setHeight(spread);

        return border;
    }
}
