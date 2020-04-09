package com.avatarduel.view;

import com.avatarduel.model.Loc;
import com.avatarduel.components.Basic;
import com.avatarduel.model.Player;
import com.avatarduel.controller.GameController;
import javafx.geometry.Pos;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

public class GameView extends BorderPane {
    private static String BACKGROUND_URL = "com/avatarduel/assets/image/background.png";
    private GameController gameController;

    public GameView() {
        super();
        Player.getPlayers();
        gameController = new GameController();
        gameController.getFieldController().initFieldBoxes();
        gameController.getHandController().updateHand(Player.player2, Player.player1);

        HBox store = new HBox();
        store.setMinHeight(440);
        store.setAlignment(Pos.CENTER);
        store.getChildren().add(gameController.getCardController().getCardInfo());

        HBox store2 = new HBox();
        store2.setMinHeight(440);
        store2.setAlignment(Pos.CENTER);
        store2.getChildren().add(gameController.getCardController().getCardDesc());

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
        plane.setTop(genPlane(Loc.TOP));
        plane.setCenter(gameController.getButtonController().getPhaseBtnView());
        plane.setBottom(genPlane(Loc.BOTTOM));

        HBox planeBox = new HBox();
        planeBox.setMinWidth(1000);
        planeBox.setAlignment(Pos.CENTER);
        planeBox.getChildren().add(plane);

        // Main Layout
        setLeft(sidebar);
        setCenter(planeBox);
        setBackground(Basic.getBackground(BACKGROUND_URL));
    }
    private BorderPane genPlane(Loc type) {
        BorderPane deckField = new BorderPane();
        BorderPane deck = new BorderPane();
        BorderPane plane = new BorderPane();

        HBox wrapper = new HBox();
        wrapper.setMinWidth(800);
        wrapper.setAlignment(Pos.CENTER);
        ScrollPane scrollPane = new ScrollPane();
        wrapper.getChildren().add(scrollPane);
        scrollPane.setContent(gameController.getHandController().getHandV(type));
        scrollPane.setMinWidth(800);
        scrollPane.setMaxWidth(800);
        scrollPane.setMinHeight(150);
        scrollPane.setStyle("-fx-background: transparent;\n -fx-background-color: transparent");
        if (type == Loc.TOP) {
            deckField.setBottom(gameController.getFieldController().getField(type));
            deckField.setTop(wrapper);
            deck.setLeft(gameController.getSideController().getSideV(type));
            deck.setCenter(deckField);

            plane.setTop(gameController.getPlayerController().getPlayerV(type));
            plane.setBottom(deck);
        } else {
            deckField.setTop(gameController.getFieldController().getField(type));
            deckField.setBottom(wrapper);
            deck.setRight(gameController.getSideController().getSideV(type));
            deck.setCenter(deckField);

            plane.setBottom(gameController.getPlayerController().getPlayerV(type));
            plane.setTop(deck);
        }

        return plane;
    }
}
