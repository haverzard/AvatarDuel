package com.avatarduel.components;

import com.avatarduel.card.*;
import com.avatarduel.element.Element;
import com.avatarduel.player.Player;
import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.util.Duration;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GameSpecific {
    // For phase
    private static int phase = 0;
    private static int turn = 1;
    private static int draw = 1;
    private static int land = 1;

    // For updating
    public static StackPane screen = new StackPane();
    public static int target = -1;
    public static int targetAttack = -1;
    public static List<HBox> fieldBoxes = new ArrayList<>();
    public static List<Pane> cardsBottom = new ArrayList<>();
    public static Text playerBottomName = new Text("Player 1 - IPSUM");
    public static Text playerTopName = new Text("Player 2 - IPSUM");
    public static Text healthValueTop = new Text();
    public static Text healthValueBottom = new Text();
    public static HBox bottomHand = new HBox();
    public static HBox topHand = new HBox();
    public static Map<Element,HBox> powerCountersTop = new HashMap<>();
    public static Map<Element,HBox> powerCountersBottom = new HashMap<>();
    public static HBox deckCounterBottom = new HBox();
    public static HBox deckCounterTop = new HBox();
    public static HBox deckButton = Card.getClosedCard(60);
    public static BorderPane cardInfo = Card.getOpenCard(250);

    // For animation
    private static double perBottom = 1;
    private static double perTop = 1;
    private static double slideBottom = 0;
    private static double slideTop = 0;
    public static HBox healthBarBottom = new HBox();
    public static HBox healthBarTop = new HBox();

    public static void initFieldBoxes() {
        Player p1, p2;
        if (turn == 1) {
            p1 = Player.player1;
            p2 = Player.player2;
        } else {
            p1 = Player.player2;
            p2 = Player.player1;
        }
        // Top fields
        for (int i=0; i<16; i++) {
            int j = (15-i)%16;
            fieldBoxes.get(i).getChildren().clear();
            Pane card = p2.cardsOnField.get(j);
            if (card != null) {
                fieldBoxes.get(i).getChildren().add(card);
                initFieldCard(p2, card);
            }
        }

        // Bottom fields
        // Bottom Top fields
        for (int i=16; i<32; i++) {
            int j = i-16;
            fieldBoxes.get(i).getChildren().clear();
            Pane card = p1.cardsOnField.get(j);
            if (card != null) {
                fieldBoxes.get(i).getChildren().add(card);
                fieldBoxes.get(i).setOnMouseClicked(null);
                initFieldCard(p1, card);
            } else {
                fieldBoxes.get(i).setOnMouseClicked(e -> {
                    if ((phase == 1 || phase == 2) && target != -1 && (j < 8 && p1.getHand(target) instanceof CharacterGameCard || j > 8)
                            && p1.useCard((HasCostAttribute) p1.getHand(target), p1.getHand(target).getElement())) { // Pre-condition that the card is character/skill
                        if (p1.cardsOnField.get(j) == null && !p1.cardsOnField.containsValue(cardsBottom.get(target))) {
                            int cardLoc = bottomHand.getChildren().indexOf(cardsBottom.get(target));
                            bottomHand.getChildren().remove(cardLoc+1);
                            fieldBoxes.get(j + 16).getChildren().add(cardsBottom.get(target));
                            fieldBoxes.get(j + 16).setOnMouseClicked(null);
                            initFieldCard(p1, cardsBottom.get(target));
                            p1.cardsOnField.put(j, cardsBottom.get(target));
                            p1.cardsOnFieldInfo.put(j, new Pair<>(p1.getHand(target), false));
                            cardsBottom.get(target).setOnMouseEntered(e2 -> Card.update(cardInfo, 250, p1.cardsOnFieldInfo.get(j).getKey()));
                            p1.setHand(target, null);
                            updatePowerCounters(powerCountersBottom, p1);
                        }
                    }
                });
            }
        }
    }

    public static void initFieldCard(Player a, Pane card) {
        Player b = (a == Player.player1) ? Player.player2 : Player.player1;
        // Battle Phase Action
        if (a.getId() == turn) {
            card.setOnMouseClicked(e -> {
                if (phase == 2) {
                    for(Integer K : a.cardsOnField.keySet()) {
                        Pane V = a.cardsOnField.get(K);
                        if (V == card && !a.cardsOnFieldInfo.get(K).getValue()) {
                            if (b.cardsOnField.isEmpty()) {
                                CharacterGameCard c = (CharacterGameCard) a.cardsOnFieldInfo.get(K).getKey();
                                targetAttack = K;
                                updateAttack(a,b,0,c.getAttack(),false);
                            } else if (targetAttack == -1) {
                                targetAttack = K;
                                card.setEffect(Basic.getShadow(Color.BLUE, 30));
                            } else {
                                targetAttack = -1;
                            }
                            break;
                        }
                    }
                } else if (phase == 1 || phase == 3) {
                    for(Integer K : a.cardsOnField.keySet()) {
                        Pane V = a.cardsOnField.get(K);
                        if (V == card) {
                            a.switchCardMode(K);
                        }
                    }
                }
            });
        } else {
            card.setOnMouseClicked(e -> {
                if (phase == 2) {
                    for (Integer K: a.cardsOnField.keySet()) {
                        Pane V = a.cardsOnField.get(K);
                        if (targetAttack != -1 && V == card) {
                            CharacterGameCard attacker = (CharacterGameCard) b.cardsOnFieldInfo.get(targetAttack).getKey();
                            CharacterGameCard enemy = (CharacterGameCard) a.cardsOnFieldInfo.get(K).getKey();
                            if (!a.cardsOnFieldInfo.get(K).getValue() && attacker.getAttack() >= enemy.getAttack()) {
                                updateAttack(b,a,K,attacker.getAttack()-enemy.getAttack(),true);
                            } else if (a.cardsOnFieldInfo.get(K).getValue() && attacker.getAttack() > enemy.getDefense()) {
                                updateAttack(b,a,K,0,true);
                            }
                            break;
                        }
                    }
                }
            });
        }
    }

    public static void updateAttack(Player attacker, Player enemy, int idx, int damage, boolean isHitOnEnemy) {
        if (damage > 0) {
            int hp = enemy.getHealth();
            int newhp = hp - damage;
            if (newhp < 0) newhp = 0;
            animateHP(healthBarTop, hp, newhp);
            enemy.setHealth(newhp);
            updateHealthValue(healthValueTop, newhp);
            if (hp <= 0) {
                screen.getChildren().add(Basic.getScreen("You Lose!"));
            }
        }
        attacker.switchCardMode(targetAttack);
        if (isHitOnEnemy) {
            fieldBoxes.get((15 - idx) % 16).getChildren().clear();
            enemy.cardsOnField.remove(idx);
            enemy.cardsOnFieldInfo.remove(idx);
        }
        targetAttack = -1;
    }

    public static BorderPane genHealthBox(String type) {
        // Health Layout
        HBox healthBar = new HBox();
        healthBar.setMaxWidth(1040);
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

    public static void updateHand(Player p1, Player p2) {
        for (int i=0; i<p2.countCardsInHand(); i++) {
            addCardToHand(p2,bottomHand,p2.getHand(i).getElement(),i);
        }
        for (int i=0; i<p1.countCardsInHand(); i++) {
            addCardToHand(p1,topHand,p1.getHand(i).getElement(),i);
        }
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

    public static void updatePowerCounters(Map<Element,HBox> powerCounters, Player p) {
        powerCounters.forEach((e,b)->{
            Pair<Integer,Integer> x = p.power.get(e);
            Text t1 = new Text(Integer.toString(x.getKey()));
            Text t2 = new Text("/");
            Text t3 = new Text(Integer.toString(x.getValue()));

            b.getChildren().clear();
            b.getChildren().add(t1);
            b.getChildren().add(t2);
            b.getChildren().add(t3);
        });
    }

    public static BorderPane genElementBox(String type, Element x) {
        Text t1 = new Text("0");
        Text t2 = new Text("/");
        Text t3 = new Text("0");

        HBox powerCounter = new HBox();
        powerCounter.setMinHeight(30);
        powerCounter.setAlignment(Pos.CENTER);
        powerCounter.getChildren().add(t1);
        powerCounter.getChildren().add(t2);
        powerCounter.getChildren().add(t3);
        if (type.equals("top")) {
            powerCountersTop.put(x,powerCounter);
        } else {
            powerCountersBottom.put(x,powerCounter);
        }

        HBox element = new HBox();
        element.setMinSize(30,30);
        element.setBorder(Basic.getBorder(1));

        BorderPane elementBox = new BorderPane();
        elementBox.setLeft(powerCounter);
        elementBox.setRight(element);

        return elementBox;
    }

    public static VBox genSideBox(String type) {
        VBox sideBox = new VBox();
        sideBox.setMinWidth(75);
        sideBox.setMinHeight(350);
        if (type.equals("top")) {
            deckCounterTop.setAlignment(Pos.CENTER);
            updateDeckCounter(deckCounterTop, Player.player2.countCardsInDeck());
            sideBox.setAlignment(Pos.BOTTOM_CENTER);
            sideBox.getChildren().add(deckCounterTop);
            sideBox.getChildren().add(Card.getClosedCard(60));
            sideBox.getChildren().add(Basic.getSpace(10));
            sideBox.getChildren().add(genElementBox(type,Element.WATER));
            sideBox.getChildren().add(genElementBox(type,Element.AIR));
            sideBox.getChildren().add(genElementBox(type,Element.EARTH));
            sideBox.getChildren().add(genElementBox(type,Element.FIRE));
        } else {
            deckCounterBottom.setAlignment(Pos.CENTER);
            updateDeckCounter(deckCounterBottom, Player.player1.countCardsInDeck());
            sideBox.setAlignment(Pos.TOP_CENTER);
            sideBox.getChildren().add(genElementBox(type,Element.FIRE));
            sideBox.getChildren().add(genElementBox(type,Element.EARTH));
            sideBox.getChildren().add(genElementBox(type,Element.AIR));
            sideBox.getChildren().add(genElementBox(type,Element.WATER));
            sideBox.getChildren().add(Basic.getSpace(10));
            sideBox.getChildren().add(deckButton);
            sideBox.getChildren().add(deckCounterBottom);
        }
        return sideBox;
    }

    public static HBox genHand(String type) {
        if (type.equals("top")) {
            topHand.setMinWidth(800);
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
            if (a.getId() == turn) {
                card.setOnMouseClicked(e2 -> {
                    for (Pane pane : cardsBottom) {
                        pane.setEffect(null);
                    }
                    a.cardsOnField.forEach((K, V) -> V.setEffect(null));
                    // Main Phase Action
                    if (phase == 1 || phase == 3) {
                        int temp = cardsBottom.indexOf(card); // Could be not found
                        // Use land card?
                        if (temp >= 0 && a.getHand(temp) instanceof LandGameCard) {
                            if (land > 0 && (phase == 1 || phase == 3)) {
                                int i = bottomHand.getChildren().indexOf(card);
                                a.useLand(temp);
                                updatePowerCounters(powerCountersBottom, a);
                                bottomHand.getChildren().remove(i + 1);
                                bottomHand.getChildren().remove(i);
                                land--;
                                target = -1;
                            }
                        } else if (temp >= 0 && target != temp) {
                            target = temp;
                            card.setEffect(Basic.getShadow(Color.BLUE, 30));
                        } else {
                            target = -1;
                        }
                    }
                });
            }
        } else {
            card = Card.getClosedCard(62.5);
        }
        hand.getChildren().add(card);
        hand.getChildren().add(Basic.getSpace(10));
    }

    public static HBox genButton() {
        Button delete = new Button("Delete");
        Button main1 = new Button("Main 1");
        Button battle = new Button("Battle");
        Button main2 = new Button("Main 2");
        Button end = new Button("End");
        delete.setDisable(true);
        battle.setDisable(true);
        main2.setDisable(true);
        end.setDisable(true);
        main1.setOnAction(e -> {
            phase++;
            main1.setDisable(true);
            battle.setDisable(false);
            deckButton.setEffect(null);
            screen.getChildren().add(Basic.getScreen("Main Phase 1"));
            Basic.scr.setOnMouseClicked(e3 -> screen.getChildren().remove(1));
        });
        battle.setOnAction(e -> {
            phase++;
            battle.setDisable(true);
            main2.setDisable(false);
            screen.getChildren().add(Basic.getScreen("Battle Phase"));
            Basic.scr.setOnMouseClicked(e3 -> screen.getChildren().remove(1));
        });
        main2.setOnAction(e -> {
            phase++;
            main2.setDisable(true);
            end.setDisable(false);
            screen.getChildren().add(Basic.getScreen("Main Phase 2"));
            Basic.scr.setOnMouseClicked(e3 -> screen.getChildren().remove(1));
        });
        end.setOnAction(e -> {
            turn = turn%2 + 1;
            target = -1;
            draw = 1;
            phase = 0;
            land = 1;
            deckButton.setEffect(Basic.getShadow(Color.BLUE, 30));
            Player.player1.refreshHand();
            Player.player2.refreshHand();
            cardsBottom.clear();
            screen.getChildren().add(Basic.getScreen("End Turn"));
            String temp = playerBottomName.getText();
            playerBottomName.setText(playerTopName.getText());
            playerTopName.setText(temp);
            bottomHand.getChildren().clear();
            topHand.getChildren().clear();
            Card.update(cardInfo,250,null);
            for (Pane pane : cardsBottom) {
                pane.setEffect(null);
            }
            Player.player1.cardsOnField.forEach((K, V) -> V.setEffect(null));
            Player.player2.cardsOnField.forEach((K, V) -> V.setEffect(null));
            Player.player1.resetPower();
            Player.player2.resetPower();
            if (turn == 1) {
                switchPlayer(Player.player2, Player.player1);
            } else {
                switchPlayer(Player.player1, Player.player2);
            }
            end.setDisable(true);
            main1.setDisable(false);
        });

        HBox buttons = new HBox();
        buttons.setMinWidth(900);
        buttons.setAlignment(Pos.CENTER);
        buttons.getChildren().add(delete);
        buttons.getChildren().add(main1);
        buttons.getChildren().add(battle);
        buttons.getChildren().add(main2);
        buttons.getChildren().add(end);

        return buttons;
    }

    public static void switchPlayer(Player p1, Player p2) {
        initFieldBoxes();
        animateHP(healthBarBottom, p1.getHealth(), p2.getHealth());
        updatePowerCounters(powerCountersBottom, p2);
        updatePowerCounters(powerCountersTop, p1);

        TranslateTransition animation = animateHP(healthBarTop, p2.getHealth(), p1.getHealth());
        animation.setOnFinished(e2 -> {
            updateHealthValue(healthValueTop, p1.getHealth());
            updateHealthValue(healthValueBottom, p2.getHealth());
            Basic.scr.setOnMouseClicked(e3 -> screen.getChildren().remove(1));
        });
        updateHand(p1,p2);
        updateDeckCounter(deckCounterBottom, p2.countCardsInDeck());
        updateDeckCounter(deckCounterTop, p1.countCardsInDeck());
    }

    public static void initDeckButton() {
        deckButton.setEffect(Basic.getShadow(Color.BLUE, 30));
        deckButton.setOnMouseClicked(e-> {
            if (draw > 0 && phase == 0) {
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
                deckButton.setEffect(null);
                draw--;
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
