package com.avatarduel.controller;

import com.avatarduel.components.Basic;
import com.avatarduel.phase.BattlePhase;
import com.avatarduel.phase.MainPhase;
import com.avatarduel.model.Player;
import com.avatarduel.view.DeckButtonView;
import com.avatarduel.view.PhaseButtonView;
import com.avatarduel.view.MainView;
import javafx.scene.control.Button;

/**
 * Represent the Button Controller for MVC pattern in AvatarDuel
 */
public class ButtonController {
    private PhaseButtonView phaseBtnView;
    private DeckButtonView deckBtnView;
    private PhaseController phaseController;
    private SelectionController selectionController;

    /**
     * Creates a new button controller
     */
    public ButtonController() {
        this(new PhaseController(), new SelectionController());
    }
    /**
     * Creates a new button controller with user defined
     * @param phaseController new button controller's phase controller
     * @param selectionController new button controller's selection controller
     */
    public ButtonController(PhaseController phaseController, SelectionController selectionController) {
        Player.getPlayers();
        phaseBtnView = new PhaseButtonView();
        deckBtnView = new DeckButtonView();
        phaseBtnView.getMain().setOnAction(e -> {
            enterNextPhase(phaseBtnView.getMain(), phaseBtnView.getBattle(), "Main Phase");
            phaseController.setGamePhase(new MainPhase());
        });
        phaseBtnView.getBattle().setOnAction(e -> {
            enterNextPhase(phaseBtnView.getBattle(), phaseBtnView.getEnd(), "Battle Phase");
            phaseController.setGamePhase(new BattlePhase());
        });
        this.phaseController = phaseController;
        this.selectionController = selectionController;
    }

    /**
     * Get the Phase button's view
     * @return Phase button's view
     */
    public PhaseButtonView getPhaseBtnView() {
        return phaseBtnView;
    }

    /**
     * Get the Deck button's view
     * @return Deck button's view
     */
    public DeckButtonView getDeckBtnView() {
        return deckBtnView;
    }

    /**
     * Reset all cards effect in field
     */
    public void resetAllEffects() {
        deckBtnView.setEffect(null);
        Player.player1.cardsOnField.forEach((K, V) -> V.setEffect(null));
        Player.player2.cardsOnField.forEach((K, V) -> V.setEffect(null));
        setDisableDelete(true);
    }

    /**
     * Set to clicked phase
     * @param now Current phase button
     * @param next Next phase button
     * @param message Phase's message
     */
    private void enterNextPhase(Button now, Button next, String message) {
        now.setDisable(true);
        next.setDisable(false);
        MainView.screen.getChildren().add(Basic.getScreen(message));
        Basic.scr.setOnMouseClicked(e -> MainView.screen.getChildren().remove(1));
        resetAllEffects();
    }

    /**
     * Disable delete 
     * @param x boolean option
     */
    public void setDisableDelete(boolean x) {
        phaseBtnView.getDelete().setDisable(x);
    }
}
