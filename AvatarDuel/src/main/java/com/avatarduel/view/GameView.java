package com.avatarduel.view;

import com.avatarduel.components.Basic;
import com.avatarduel.components.Card;
import com.avatarduel.components.CustomBox;
import com.avatarduel.element.Element;
import com.avatarduel.player.Player;
import javafx.geometry.Pos;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class GameView {

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
            healthInfo.getChildren().add(HealthView.getHealthValue("top"));
            healthBox.setTop(healthBar);
            healthBar.getChildren().add(HealthView.getHealthBar("top"));

            healthBox.setLeft(PlayerView.getPlayerName("top"));
            healthBox.setRight(healthInfo);
        } else {
            healthInfo.getChildren().add(HealthView.getHealthValue("bottom"));
            healthBox.setBottom(healthBar);
            healthBar.getChildren().add(HealthView.getHealthBar("bottom"));
            healthBox.setRight(PlayerView.getPlayerName(type));
            healthBox.setLeft(healthInfo);
        }

        return healthBox;
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
            PowerView.addToCounters("top",x,powerCounter);
        } else {
            PowerView.addToCounters("bottom",x,powerCounter);
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
            DeckView.updateDeckCounter("top", Player.player2.countCardsInDeck());
            sideBox.setAlignment(Pos.BOTTOM_CENTER);
            sideBox.getChildren().add(DeckView.getDeckCounter("top"));
            sideBox.getChildren().add(Card.getClosedCard(60));
            sideBox.getChildren().add(Basic.getSpace(10));
            sideBox.getChildren().add(genElementBox(type,Element.WATER));
            sideBox.getChildren().add(genElementBox(type,Element.AIR));
            sideBox.getChildren().add(genElementBox(type,Element.EARTH));
            sideBox.getChildren().add(genElementBox(type,Element.FIRE));
        } else {
            DeckView.updateDeckCounter("bottom", Player.player1.countCardsInDeck());
            sideBox.setAlignment(Pos.TOP_CENTER);
            sideBox.getChildren().add(genElementBox(type,Element.FIRE));
            sideBox.getChildren().add(genElementBox(type,Element.EARTH));
            sideBox.getChildren().add(genElementBox(type,Element.AIR));
            sideBox.getChildren().add(genElementBox(type,Element.WATER));
            sideBox.getChildren().add(Basic.getSpace(10));
            sideBox.getChildren().add(ButtonView.deckButton);
            sideBox.getChildren().add(DeckView.getDeckCounter("bottom"));
        }
        return sideBox;
    }

    public static HBox genBoxField(int counts, double size, double pad) {
        HBox boxField = new HBox();
        boxField.setMinWidth((size+pad)*counts+pad);
        boxField.setAlignment(Pos.CENTER);
        for (int i=0; i<counts-1; i++) {
            FieldView.addBox(CustomBox.getBox(size));
            boxField.getChildren().add(FieldView.getLastBox());
            boxField.getChildren().add(Basic.getSpace(10));
        }
        FieldView.addBox(CustomBox.getBox(size));
        boxField.getChildren().add(FieldView.getLastBox());
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
        scrollPane.setContent(HandView.getHand(type));
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

    public static void loadGameLayout() {
        HBox store = new HBox();
        store.setMinHeight(440);
        store.setAlignment(Pos.CENTER);
        store.getChildren().add(CardView.getCardInfo());

        HBox store2 = new HBox();
        store2.setMinHeight(440);
        store2.setAlignment(Pos.CENTER);
        store2.getChildren().add(CardView.getCardDesc());

        // Left Layout
        BorderPane sidebar = new BorderPane();
        sidebar.setMinWidth(290);
        sidebar.setMinHeight(880);
        sidebar.setTop(store);
        sidebar.setBottom(store2);

        // Right Layout
        BorderPane plane = new BorderPane();
        plane.setMinWidth(900);
        plane.setMaxHeight(840);
        plane.setTop(GameView.genPlane("top"));
        plane.setCenter(ButtonView.getPhaseButtons());
        plane.setBottom(GameView.genPlane("bottom"));

        HBox planeBox = new HBox();
        planeBox.setMinWidth(1000);
        planeBox.setAlignment(Pos.CENTER);
        planeBox.getChildren().add(plane);

        // Another init after design is loaded
        FieldView.initFieldBoxes();

        // Main Layout
        BorderPane mainLayout = new BorderPane();
        mainLayout.setLeft(sidebar);
        mainLayout.setCenter(planeBox);

        MainView.screen.getChildren().add(mainLayout);
    }
}
