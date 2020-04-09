package com.avatarduel.controller;

import com.avatarduel.components.Basic;
import com.avatarduel.phase.BattlePhase;
import com.avatarduel.phase.MainPhase;
import com.avatarduel.model.Player;
import com.avatarduel.view.DeckButtonView;
import com.avatarduel.view.PhaseButtonView;
import com.avatarduel.view.MainView;
import javafx.scene.control.Button;

public class ButtonController {
    private PhaseButtonView phaseBtnView;
    private DeckButtonView deckBtnView;
    private PhaseController phaseController;
    private SelectionController selectionController;

    public ButtonController() {
        this(new PhaseController(), new SelectionController());
    }

    public ButtonController(PhaseController phaseController, SelectionController selectionController) {
        Player.getPlayers();
        phaseBtnView = new PhaseButtonView();
        deckBtnView = new DeckButtonView();
        phaseBtnView.getMain1().setOnAction(e -> {
            enterNextPhase(phaseBtnView.getMain1(), phaseBtnView.getBattle(), "Main Phase 1");
            phaseController.setGamePhase(new MainPhase());
        });
        phaseBtnView.getBattle().setOnAction(e -> {
            enterNextPhase(phaseBtnView.getBattle(), phaseBtnView.getMain2(), "Battle Phase");
            phaseController.setGamePhase(new BattlePhase());
        });
        phaseBtnView.getMain2().setOnAction(e -> {
            enterNextPhase(phaseBtnView.getMain2(), phaseBtnView.getEnd(), "Main Phase 2");
            phaseController.setGamePhase(new MainPhase());
        });
        this.phaseController = phaseController;
        this.selectionController = selectionController;
    }

    public PhaseButtonView getPhaseBtnView() {
        return phaseBtnView;
    }

    public DeckButtonView getDeckBtnView() {
        return deckBtnView;
    }

    public void resetAllEffects() {
        deckBtnView.setEffect(null);
        Player.player1.cardsOnField.forEach((K, V) -> V.setEffect(null));
        Player.player2.cardsOnField.forEach((K, V) -> V.setEffect(null));
        setDisableDelete(true);
    }

    private void enterNextPhase(Button now, Button next, String message) {
        now.setDisable(true);
        next.setDisable(false);
        MainView.screen.getChildren().add(Basic.getScreen(message));
        Basic.scr.setOnMouseClicked(e -> MainView.screen.getChildren().remove(1));
        resetAllEffects();
    }

    public void setDisableDelete(boolean x) {
        phaseBtnView.getDelete().setDisable(x);
    }
}
