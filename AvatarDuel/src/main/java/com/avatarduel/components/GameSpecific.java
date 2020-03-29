package com.avatarduel.components;

import com.avatarduel.AvatarDuel;
import com.avatarduel.model.Health;
import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.beans.binding.Bindings;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.util.Duration;

public class GameSpecific {
    private static Health player1H = new Health();
    private static Health player2H = new Health(65);
    private static double per1 = 1;
    private static double per2 = 1;
    private static double slide1 = 0;
    private static double slide2 = 0;
    public static Text player1Name = new Text("Player 1 - IPSUM");
    public static Text player2Name = new Text("Player 2 - IPSUM");
    public static HBox healthFillBar1 = new HBox();
    public static HBox healthFillBar2 = new HBox();

    public static BorderPane genHealthBox(String type) {
        // Health Layout
        HBox healthBar = new HBox();
        healthBar.setMinHeight(30);
        healthBar.setBorder(Basic.getBorder(1));
        healthBar.setBorder(Basic.getBorder(1));

        HBox healthInfo = new HBox();
        Text healthValue = new Text();
        healthInfo.getChildren().add(new Text("HP"));
        healthInfo.getChildren().add(Basic.getSpace(10));
        healthInfo.getChildren().add(healthValue);

        BorderPane healthBox = new BorderPane();
        if (type == "top") {
            healthBox.setTop(healthBar);
            healthBar.getChildren().add(healthFillBar2);
            healthFillBar2.setBackground(Basic.getBackground(Color.DARKRED));
            healthFillBar2.setMinWidth(player2H.getValue()*(1040/80));
            healthValue.textProperty().bind(Bindings.convert(player2H.valueProperty()));
            healthBox.setLeft(player2Name);
            healthBox.setRight(healthInfo);
        } else {
            healthBox.setBottom(healthBar);
            healthBar.getChildren().add(healthFillBar1);
            healthFillBar1.setBackground(Basic.getBackground(Color.DARKRED));
            healthFillBar1.setMinWidth(player1H.getValue()*(1040/80));
            healthValue.textProperty().bind(Bindings.convert(player1H.valueProperty()));
            healthBox.setRight(player1Name);
            healthBox.setLeft(healthInfo);
        }

        return healthBox;
    }

    public static VBox genSideBox(String type) {
        VBox sideBox = new VBox();
        sideBox.setMinHeight(350);
        if (type == "top") {
            sideBox.setAlignment(Pos.BOTTOM_CENTER);
            sideBox.getChildren().add(Attribute.getDeckBox());
            sideBox.getChildren().add(Card.getClosedCard(60));
            sideBox.getChildren().add(Basic.getSpace(10));
            sideBox.getChildren().add(Attribute.getElementBox("hehe"));
            sideBox.getChildren().add(Attribute.getElementBox("hehe"));
            sideBox.getChildren().add(Attribute.getElementBox("hehe"));
            sideBox.getChildren().add(Attribute.getElementBox("hehe"));
        } else {
            sideBox.setAlignment(Pos.TOP_CENTER);
            sideBox.getChildren().add(Attribute.getElementBox("hehe"));
            sideBox.getChildren().add(Attribute.getElementBox("hehe"));
            sideBox.getChildren().add(Attribute.getElementBox("hehe"));
            sideBox.getChildren().add(Attribute.getElementBox("hehe"));
            sideBox.getChildren().add(Basic.getSpace(10));
            sideBox.getChildren().add(Card.getClosedCard(60));
            sideBox.getChildren().add(Attribute.getDeckBox());

        }
        return sideBox;
    }

    public static HBox genHand(String type) {
        HBox hand = new HBox();
        hand.setAlignment(Pos.CENTER);
        if (type == "top") {
            hand.getChildren().add(Card.getClosedCard(75));
            hand.getChildren().add(Basic.getSpace(10));
            hand.getChildren().add(Card.getClosedCard(75));
            hand.getChildren().add(Basic.getSpace(10));
            hand.getChildren().add(Card.getClosedCard(75));
        } else {
            hand.getChildren().add(Card.getOpenCard(75, true));
            hand.getChildren().add(Basic.getSpace(10));
            hand.getChildren().add(Card.getOpenCard(75, true));
            hand.getChildren().add(Basic.getSpace(10));
            hand.getChildren().add(Card.getOpenCard(75, true));
        }
        return hand;
    }

    public static HBox genField() {
        VBox fieldInside = new VBox();
        fieldInside.setAlignment(Pos.CENTER);
        fieldInside.setBorder(Basic.getBorder(1));
        fieldInside.getChildren().add(CustomBox.getBoxField(8,100, 10));
        fieldInside.getChildren().add(Basic.getSpace(10));
        fieldInside.getChildren().add(CustomBox.getBoxField(8,100, 10));

        HBox field = new HBox();
        field.setMinWidth(930);
        field.setMinHeight(225);
        field.setAlignment(Pos.CENTER);
        field.getChildren().add(fieldInside);

        return field;
    }

    public static BorderPane genPlane(String type) {
        BorderPane deckField = new BorderPane();
        BorderPane deck = new BorderPane();
        BorderPane plane = new BorderPane();

        if (type == "top") {
            deckField.setTop(genHand(type));
            deckField.setBottom(genField());

            deck.setLeft(genSideBox(type));
            deck.setCenter(deckField);

            plane.setTop(genHealthBox(type));
            plane.setBottom(deck);
        } else {
            deckField.setBottom(genHand(type));
            deckField.setTop(genField());

            deck.setRight(genSideBox(type));
            deck.setCenter(deckField);

            plane.setBottom(genHealthBox(type));
            plane.setTop(deck);
        }

        return plane;
    }

    public static HBox genButton() {
        Button main1 = new Button("Main 1");
        Button battle = new Button("Battle");
        Button main2 = new Button("Main 2");
        Button end = new Button("End");
        end.setOnAction(e -> {
            AvatarDuel.screen.getChildren().add(Basic.getScreen("End Turn"));
            String temp = player1Name.getText();
            player1Name.setText(player2Name.getText());
            player2Name.setText(temp);

            animateHP1(player2H.getValue());
            TranslateTransition animation = animateHP2(player1H.getValue());
            animation.setOnFinished(e2 -> {
                double temp2 = player1H.getValue();
                player1H.setValue(player2H.getValue());
                player2H.setValue(temp2);
                Basic.scr.setOnMouseClicked(e3 -> {
                    AvatarDuel.screen.getChildren().remove(1);
                });
            });
        });

        HBox buttons = new HBox();
        buttons.setMinWidth(900);
        buttons.setAlignment(Pos.CENTER);
        buttons.getChildren().add(main1);
        buttons.getChildren().add(battle);
        buttons.getChildren().add(main2);
        buttons.getChildren().add(end);

        return buttons;
    }

    public static TranslateTransition animateHP1(double target) {
        ScaleTransition a = new ScaleTransition(Duration.seconds(1), healthFillBar1);
        TranslateTransition b = new TranslateTransition(Duration.seconds(1), healthFillBar1);
        a.setFromX(per1);
        b.setFromX(slide1);
        per1 = target/player1H.getValue()*per1;
        slide1 = (target-player1H.getValue())*1040/80/2+slide1;
        a.setToX(per1);
        b.setToX(slide1);
        a.play();
        b.play();

        return b;
    }


    public static TranslateTransition animateHP2(double target) {
        ScaleTransition a = new ScaleTransition(Duration.seconds(1), healthFillBar2);
        TranslateTransition b = new TranslateTransition(Duration.seconds(1), healthFillBar2);
        a.setFromX(per2);
        b.setFromX(slide2);
        per2 = target/player2H.getValue()*per2;
        slide2 = (target-player2H.getValue())*1040/80/2+slide2;
        a.setToX(per2);
        b.setToX(slide2);
        a.play();
        b.play();

        return b;
    }
}
