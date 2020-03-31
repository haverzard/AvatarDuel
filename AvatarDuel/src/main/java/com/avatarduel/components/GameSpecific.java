package com.avatarduel.components;

import com.avatarduel.AvatarDuel;
import com.avatarduel.element.Element;
import com.avatarduel.player.Player;
import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.effect.DropShadow;
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

    // For updating
    public static int target = -1;
    public static List<HBox> fieldBoxes = new ArrayList<>();
    public static List<Pane> cardsBottom = new ArrayList<>();
    public static Map<Integer,Pane> cardsOnField = new HashMap<>();
    public static Text playerBottomName = new Text("Player 1 - IPSUM");
    public static Text playerTopName = new Text("Player 2 - IPSUM");
    public static Text healthValueTop = new Text();
    public static Text healthValueBottom = new Text();
    public static HBox bottomHand = new HBox();
    public static HBox topHand = new HBox();
    public static HBox deckButton = Card.getClosedCard(60);
    public static HBox deckCounterBottom = new HBox();
    public static HBox deckCounterTop = new HBox();
    public static BorderPane cardInfo = Card.getOpenCard(250);

    // For animation
    private static double perBottom = 1;
    private static double perTop = 1;
    private static double slideBottom = 0;
    private static double slideTop = 0;
    public static HBox healthBarBottom = new HBox();
    public static HBox healthBarTop = new HBox();

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
        healthInfo.getChildren().add(new Text("HP"));
        healthInfo.getChildren().add(Basic.getSpace(10));

        BorderPane healthBox = new BorderPane();
        if (type.equals("top")) {
            healthInfo.getChildren().add(healthValueTop);
            healthBox.setTop(healthBar);
            healthBar.getChildren().add(healthBarTop);
            healthBarTop.setBackground(Basic.getBackground(Color.DARKRED));
            healthBarTop.setMinWidth(Player.player2.getHealth()*(1040.0/80));
            healthValueTop.setText(Integer.toString(Player.player2.getHealth()));
            healthBox.setLeft(playerTopName);
            healthBox.setRight(healthInfo);
        } else {
            healthInfo.getChildren().add(healthValueBottom);
            healthBox.setBottom(healthBar);
            healthBar.getChildren().add(healthBarBottom);
            healthBarBottom.setBackground(Basic.getBackground(Color.DARKRED));
            healthBarBottom.setMinWidth(Player.player1.getHealth()*(1040.0/80));
            healthValueBottom.setText(Integer.toString(Player.player1.getHealth()));
            healthBox.setRight(playerBottomName);
            healthBox.setLeft(healthInfo);
        }

        return healthBox;
    }

    public static void updateDeckCounter(HBox deckCounter, int x) {
        Text deckCounts = new Text(Integer.toString(x));
        deckCounter.getChildren().clear();
        deckCounter.getChildren().add(deckCounts);
        deckCounter.getChildren().add(new Text(" / 60"));
    }

    public static void updateHealthValue(Text healthValue, int x) {
        healthValue.setText(Integer.toString(x));
    }

    public static VBox genSideBox(String type) {
        VBox sideBox = new VBox();
        sideBox.setMinHeight(350);
        if (type.equals("top")) {
            updateDeckCounter(deckCounterTop, Player.player2.countCardsInDeck());
            sideBox.setAlignment(Pos.BOTTOM_CENTER);
            sideBox.getChildren().add(deckCounterTop);
            sideBox.getChildren().add(Card.getClosedCard(60));
            sideBox.getChildren().add(Basic.getSpace(10));
            sideBox.getChildren().add(Attribute.getElementBox("hehe"));
            sideBox.getChildren().add(Attribute.getElementBox("hehe"));
            sideBox.getChildren().add(Attribute.getElementBox("hehe"));
            sideBox.getChildren().add(Attribute.getElementBox("hehe"));
        } else {
            updateDeckCounter(deckCounterBottom, Player.player1.countCardsInDeck());
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
            String temp = playerBottomName.getText();
            playerBottomName.setText(playerTopName.getText());
            playerTopName.setText(temp);
            bottomHand.getChildren().clear();
            topHand.getChildren().clear();
            Card.update(cardInfo,250,null);
            if (turn == 1) {
                switchPlayer(Player.player2, Player.player1);
            } else {
                switchPlayer(Player.player1, Player.player2);
            }

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

    public static void switchPlayer(Player p1, Player p2) {
        animateHP(healthBarBottom, p1.getHealth(), p2.getHealth());

        TranslateTransition animation = animateHP(healthBarTop, p2.getHealth(), p1.getHealth());
        animation.setOnFinished(e2 -> {
            updateHealthValue(healthValueTop, p1.getHealth());
            updateHealthValue(healthValueBottom, p2.getHealth());
            Basic.scr.setOnMouseClicked(e3 -> AvatarDuel.screen.getChildren().remove(1));
        });

        for (int i=0; i<p2.countCardsInHand(); i++) {
            addCardToHand(p2,bottomHand,p2.getHand(i).getElement(),i);
        }
        for (int i=0; i<p1.countCardsInHand(); i++) {
            addCardToHand(p1,topHand,p1.getHand(i).getElement(),i);
        }
        updateDeckCounter(deckCounterBottom, p2.countCardsInDeck());
        updateDeckCounter(deckCounterTop, p1.countCardsInDeck());
    }

    public static void initDeckButton() {
        deckButton.setOnMouseClicked(e-> {
            if (draw > 0) {
                if (turn == 1) {
                    Element x = Player.player1.takeCard();
                    if (x != null) {
                        updateDeckCounter(deckCounterBottom, Player.player1.countCardsInDeck());
                        addCardToHand(Player.player1,bottomHand,x,Player.player1.countCardsInHand()-1);
                    }
                } else {
                    Element x = Player.player2.takeCard();
                    if (x != null) {
                        updateDeckCounter(deckCounterBottom, Player.player2.countCardsInDeck());
                        addCardToHand(Player.player2,bottomHand,x,Player.player2.countCardsInHand()-1);
                    }
                }
            }
        });
    }

    public static TranslateTransition animateHP(HBox healthBar, double init, double goal) {
        ScaleTransition a = new ScaleTransition(Duration.seconds(1), healthBar);
        TranslateTransition b = new TranslateTransition(Duration.seconds(1), healthBar);

        // Because double is not mutable
        if (healthBar == healthBarBottom) { // Reference equality
            a.setFromX(perBottom);
            b.setFromX(slideBottom);
            perBottom = goal/init*perBottom;
            slideBottom = (goal-init)*1040/80/2+slideBottom;
            a.setToX(perBottom);
            b.setToX(slideBottom);
        } else {
            a.setFromX(perTop);
            b.setFromX(slideTop);
            perTop = goal/init*perTop;
            slideTop = (goal-init)*1040/80/2+slideTop;
            a.setToX(perTop);
            b.setToX(slideTop);
        }
        a.play();
        b.play();

        return b;
    }
}
