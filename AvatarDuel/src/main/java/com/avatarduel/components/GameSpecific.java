package com.avatarduel.components;

import com.avatarduel.AvatarDuel;
import com.avatarduel.element.Element;
import com.avatarduel.model.Health;
import com.avatarduel.player.Player;
import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.ScrollPane;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Glow;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GameSpecific {
    // For phase
    private static int turn = 1;
    private static int draw = 1;

    // For binding
    public static int target = -1;
    public static List<HBox> fieldBoxes = new ArrayList<HBox>();
    public static List<Pane> cardsBottom = new ArrayList<Pane>();
    public static Map<Integer,Pane> cardsOnField = new HashMap<Integer,Pane>();
    public static Health player1H = new Health();
    public static Health player2H = new Health(65);
    public static Text player1Name = new Text("Player 1 - IPSUM");
    public static Text player2Name = new Text("Player 2 - IPSUM");
    public static HBox bottomHand = new HBox();
    public static HBox topHand = new HBox();
    public static HBox deckButton = Card.getClosedCard(60);
    public static HBox deckCounterBottom = new HBox();
    public static HBox deckCounterTop = new HBox();
    public static BorderPane cardInfo = Card.getOpenCard(250);

    // For animation
    private static double per1 = 1;
    private static double per2 = 1;
    private static double slide1 = 0;
    private static double slide2 = 0;
    public static HBox healthFillBar1 = new HBox();
    public static HBox healthFillBar2 = new HBox();

    public static void initFieldBoxes() {
        // Bottom fields
        for (int i=16; i<32; i++) {
            int j = i;
            fieldBoxes.get(i).setOnMouseClicked(e-> {
                System.out.println(target);
                if (target != -1) {
                    fieldBoxes.get(j).getChildren().add(cardsBottom.get(target));
                    cardsOnField.put(j,cardsBottom.get(target));
                }
            });
        }
    }

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
        if (type.equals("top")) {
            healthBox.setTop(healthBar);
            healthBar.getChildren().add(healthFillBar2);
            healthFillBar2.setBackground(Basic.getBackground(Color.DARKRED));
            healthFillBar2.setMinWidth(player2H.getValue()*(1040.0/80));
            healthValue.textProperty().bind(Bindings.convert(player2H.valueProperty()));
            healthBox.setLeft(player2Name);
            healthBox.setRight(healthInfo);
        } else {
            healthBox.setBottom(healthBar);
            healthBar.getChildren().add(healthFillBar1);
            healthFillBar1.setBackground(Basic.getBackground(Color.DARKRED));
            healthFillBar1.setMinWidth(player1H.getValue()*(1040.0/80));
            healthValue.textProperty().bind(Bindings.convert(player1H.valueProperty()));
            healthBox.setRight(player1Name);
            healthBox.setLeft(healthInfo);
        }

        return healthBox;
    }

    public static void updateDeckCounter(HBox deckCounter, SimpleIntegerProperty x) {
        Text deckCounts = new Text();
        deckCounter.getChildren().clear();
        deckCounter.getChildren().add(deckCounts);
        deckCounter.getChildren().add(new Text(" / 60"));
        deckCounts.textProperty().bind(Bindings.convert(x));
    }

    public static VBox genSideBox(String type) {
        VBox sideBox = new VBox();
        sideBox.setMinHeight(350);
        if (type.equals("top")) {
            updateDeckCounter(deckCounterTop, Player.player2.deckCounts);
            sideBox.setAlignment(Pos.BOTTOM_CENTER);
            sideBox.getChildren().add(deckCounterTop);
            sideBox.getChildren().add(Card.getClosedCard(60));
            sideBox.getChildren().add(Basic.getSpace(10));
            sideBox.getChildren().add(Attribute.getElementBox("hehe"));
            sideBox.getChildren().add(Attribute.getElementBox("hehe"));
            sideBox.getChildren().add(Attribute.getElementBox("hehe"));
            sideBox.getChildren().add(Attribute.getElementBox("hehe"));
        } else {
            updateDeckCounter(deckCounterBottom, Player.player1.deckCounts);
            sideBox.setAlignment(Pos.TOP_CENTER);
            sideBox.getChildren().add(Attribute.getElementBox("hehe"));
            sideBox.getChildren().add(Attribute.getElementBox("hehe"));
            sideBox.getChildren().add(Attribute.getElementBox("hehe"));
            sideBox.getChildren().add(Attribute.getElementBox("hehe"));
            sideBox.getChildren().add(Basic.getSpace(10));
            sideBox.getChildren().add(deckButton);
            sideBox.getChildren().add(deckCounterBottom);
        }
        return sideBox;
    }

    public static HBox genHand(String type) {
        if (type.equals("top")) {
            topHand.setMinWidth(900);
            topHand.setAlignment(Pos.CENTER);
            return topHand;
        } else {
            bottomHand.setMinWidth(800);
            bottomHand.setAlignment(Pos.CENTER);
            return bottomHand;
        }
    }

    public static HBox genBoxField(int counts, double size, double pad) {
        HBox boxField = new HBox();
        boxField.setMinWidth((size+pad)*counts+pad);
        boxField.setAlignment(Pos.CENTER);
        for (int i=0; i<counts-1; i++) {
            fieldBoxes.add(CustomBox.getBox(size));
            boxField.getChildren().add(fieldBoxes.get(fieldBoxes.size()-1));
            boxField.getChildren().add(Basic.getSpace(10));
        }
        fieldBoxes.add(CustomBox.getBox(size));
        boxField.getChildren().add(fieldBoxes.get(fieldBoxes.size()-1));
        return boxField;
    }

    public static HBox genField() {
        VBox fieldInside = new VBox();
        fieldInside.setAlignment(Pos.CENTER);
        fieldInside.setBorder(Basic.getBorder(1));
        fieldInside.getChildren().add(genBoxField(8,110, 10));
        fieldInside.getChildren().add(Basic.getSpace(10));
        fieldInside.getChildren().add(genBoxField(8,110, 10));

        HBox field = new HBox();
        field.setMinWidth(800);
        field.setMinHeight(225);
        field.setAlignment(Pos.CENTER);
        field.getChildren().add(fieldInside);

        return field;
    }

    public static BorderPane genPlane(String type) {
        BorderPane deckField = new BorderPane();
        BorderPane deck = new BorderPane();
        BorderPane plane = new BorderPane();

        HBox wrapper = new HBox();
        wrapper.setMinWidth(800);
        wrapper.setAlignment(Pos.CENTER);
        ScrollPane scrollPane = new ScrollPane();
        wrapper.getChildren().add(scrollPane);
        scrollPane.setContent(genHand(type));
        scrollPane.setMinWidth(800);
        scrollPane.setMaxWidth(800);
        scrollPane.setMinHeight(150);
        scrollPane.setBackground(Basic.getBackground(Color.TRANSPARENT));
        if (type.equals("top")) {
            deckField.setBottom(genField());
            deckField.setTop(wrapper);
            deck.setLeft(genSideBox(type));
            deck.setCenter(deckField);

            plane.setTop(genHealthBox(type));
            plane.setBottom(deck);
        } else {
            deckField.setTop(genField());
            deckField.setBottom(wrapper);
            deck.setRight(genSideBox(type));
            deck.setCenter(deckField);

            plane.setBottom(genHealthBox(type));
            plane.setTop(deck);
        }

        return plane;
    }

    public static void addCardToHand(Player a, HBox hand, Element x, int idx) {
        Pane card;
        if (a.getId() == turn) {
            card = Card.getOpenCard(62.5, x);
            cardsBottom.add(card);
            card.setOnMouseEntered(e2 -> Card.update(cardInfo, 250, a.getHand(idx)));
            card.setOnMouseClicked(e2 -> {
                int temp = cardsBottom.indexOf(card);
                for (Pane pane : cardsBottom) {
                    pane.setEffect(null);
                }
                if (target != temp) {
                    target = temp;
                    DropShadow border = new DropShadow();
                    border.setOffsetY(0f);
                    border.setOffsetX(0f);
                    border.setColor(Color.BLUE);
                    border.setWidth(30);
                    border.setHeight(30);
                    card.setEffect(border);
                } else {
                    target = -1;
                }
            });
        } else {
            card = Card.getClosedCard(62.5);
        }
        hand.getChildren().add(card);
        hand.getChildren().add(Basic.getSpace(10));
    }

    public static HBox genButton() {
        Button main1 = new Button("Main 1");
        Button battle = new Button("Battle");
        Button main2 = new Button("Main 2");
        Button end = new Button("End");
        end.setOnAction(e -> {
            turn = turn%2 + 1;
            target = -1;
            AvatarDuel.screen.getChildren().add(Basic.getScreen("End Turn"));
            String temp = player1Name.getText();
            player1Name.setText(player2Name.getText());
            player2Name.setText(temp);
            bottomHand.getChildren().clear();
            topHand.getChildren().clear();
            Card.update(cardInfo,250,null);
            if (turn == 1) {
                for (int i=0; i<Player.player1.countCardsInHand(); i++) {
                    addCardToHand(Player.player1,bottomHand,Player.player1.getHand(i).getElement(),i);
                }
                for (int i=0; i<Player.player2.countCardsInHand(); i++) {
                    addCardToHand(Player.player2,topHand,Player.player2.getHand(i).getElement(),i);
                }
                updateDeckCounter(deckCounterBottom, Player.player1.deckCounts);
                updateDeckCounter(deckCounterTop, Player.player2.deckCounts);
            } else {
                for (int i=0; i<Player.player2.countCardsInHand(); i++) {
                    addCardToHand(Player.player2,bottomHand,Player.player2.getHand(i).getElement(),i);
                }
                for (int i=0; i<Player.player1.countCardsInHand(); i++) {
                    addCardToHand(Player.player1,topHand,Player.player1.getHand(i).getElement(),i);
                }
                updateDeckCounter(deckCounterBottom, Player.player2.deckCounts);
                updateDeckCounter(deckCounterTop, Player.player1.deckCounts);
            }

            animateHP1(player2H.getValue());
            TranslateTransition animation = animateHP2(player1H.getValue());
            animation.setOnFinished(e2 -> {
                double temp2 = player1H.getValue();
                player1H.setValue(player2H.getValue());
                player2H.setValue(temp2);
                Basic.scr.setOnMouseClicked(e3 -> AvatarDuel.screen.getChildren().remove(1));
            });
        });

        HBox buttons = new HBox();
        buttons.setMinWidth(900);
        buttons.setAlignment(Pos.CENTER);
        buttons.getChildren().add(main1);
        buttons.getChildren().add(battle);
        buttons.getChildren().add(main2);
        buttons.getChildren().add(end);

        deckButton.setOnMouseClicked(e-> {
            if (draw > 0) {
                if (turn == 1) {
                    Element x = Player.player1.takeCard();
                    if (x != null) {
                        addCardToHand(Player.player1,bottomHand,x,Player.player1.countCardsInHand()-1);
                    }
                } else {
                    Element x = Player.player2.takeCard();
                    if (x != null) {
                        addCardToHand(Player.player2,bottomHand,x,Player.player2.countCardsInHand()-1);
                    }
                }
            }
        });

        return buttons;
    }

    public static void animateHP1(double target) {
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
