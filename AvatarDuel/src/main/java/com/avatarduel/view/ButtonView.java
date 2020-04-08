package com.avatarduel.view;

import com.avatarduel.components.Basic;
import com.avatarduel.components.Card;
import com.avatarduel.controller.ButtonController;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class ButtonView {
    public static HBox deckButton = Card.getClosedCard(60);
    private static Button delete = new Button("Delete");
    private static Button main1 = new Button("Main 1");
    private static Button battle = new Button("Battle");
    private static Button main2 = new Button("Main 2");
    private static Button end = new Button("End");

    public static void init() {
        initDeckButton();
        initPhaseButton();
        ButtonController.setPhaseButtonsEvent(main1, main2, battle, end, deckButton);
        ButtonController.setDeleteEvent(delete);
    }

    private static void initPhaseButton() {
        delete.setDisable(true);
        battle.setDisable(true);
        main2.setDisable(true);
        end.setDisable(false);
    }

    private static void initDeckButton() {
        deckButton.setEffect(Basic.getShadow(Color.BLUE, 30));
        ButtonController.setDeckButtonEvent(deckButton);
    }

    public static HBox getPhaseButtons() {
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

    public static Pane getDeckButton() {
        return deckButton;
    }

    public static Button getDelete() { return delete; }
}
