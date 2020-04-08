package com.avatarduel.view;

import com.avatarduel.controller.CardController;
import com.avatarduel.controller.FieldController;
import com.avatarduel.controller.StateController;
import com.avatarduel.model.StateModel;
import com.avatarduel.player.Player;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

import java.util.ArrayList;
import java.util.List;

public class FieldView {
    public static List<HBox> fieldBoxes;

    public static void init() {
        fieldBoxes = new ArrayList<>();
    }

    public static void initFieldBoxes() {
        Player p1, p2;
        if (StateController.checkState("Player 1 turn")) {
            p1 = Player.player1;
            p2 = Player.player2;
        } else {
            p1 = Player.player2;
            p2 = Player.player1;
        }

        // Top fields
        initTopField(p2);

        // Bottom fields
        initBottomField(p1);
    }

    private static void initTopField(Player p2) {

        // Skill row
        for (int i=0; i<8; i++) {
            int j = (15-i)%16;
            fieldBoxes.get(i).getChildren().clear();
            Pane card = p2.cardsOnField.get(j);
            if (card != null) {
                fieldBoxes.get(i).getChildren().add(card);
                initFieldCardSkill(p2, card, "top");
            }
        }
        // Character row
        for (int i=8; i<16; i++) {
            int j = (15-i)%16;
            fieldBoxes.get(i).getChildren().clear();
            Pane card = p2.cardsOnField.get(j);
            if (card != null) {
                fieldBoxes.get(i).getChildren().add(card);
                initFieldCardChar(p2, card);
            }
        }
    }

    private static void initBottomField(Player p1) {
        // Character row
        for (int i=16; i<24; i++) {
            int j = i-16;
            fieldBoxes.get(i).getChildren().clear();
            Pane card = p1.cardsOnField.get(j);
            if (card != null) {
                fieldBoxes.get(i).getChildren().add(card);
                fieldBoxes.get(i).setOnMouseClicked(null);
                initFieldCardChar(p1, card);
            } else {
                FieldController.setFieldBoxOnClickEvent(i, p1);
            }
        }
        // Skill row
        for (int i=24; i<32; i++) {
            int j = i-16;
            fieldBoxes.get(i).getChildren().clear();
            Pane card = p1.cardsOnField.get(j);
            if (card != null) {
                fieldBoxes.get(i).getChildren().add(card);
                fieldBoxes.get(i).setOnMouseClicked(null);
                initFieldCardSkill(p1, card, "bottom");
            }
        }
    }

    public static void initFieldCardChar(Player a, Pane card) {
        Player b = (a == Player.player1) ? Player.player2 : Player.player1;
        // Battle Phase Action
        if (a.getId() == StateModel.getTurn()) {
            CardController.setBottomCardBehaviour(card, a, b);
        } else {
            CardController.setTopCardBehaviour(card, a, b);
        }
    }

    public static void initFieldCardSkill(Player a, Pane card, String type) {
        Player b = (a == Player.player1) ? Player.player2 : Player.player1;
        // Battle Phase Action
        CardController.setSkillCardBehaviour(card, a, b, type);
    }

    public static void clearBox(int idx) {
        fieldBoxes.get(idx).getChildren().clear();
    }

    public static HBox getLastBox() {
        return fieldBoxes.get(FieldView.fieldBoxes.size()-1);
    }

    // Pre-condition idx is valid
    public static HBox getBox(int idx) {
        return fieldBoxes.get(idx);
    }

    public static void addBox(HBox box) {
        fieldBoxes.add(box);
    }
}
