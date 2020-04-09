package com.avatarduel.controller;

import com.avatarduel.card.LandGameCard;
import com.avatarduel.model.Loc;
import com.avatarduel.components.Basic;
import com.avatarduel.components.ClosedCard;
import com.avatarduel.components.OpenedCard;
import com.avatarduel.components.Space;
import com.avatarduel.model.Element;
import com.avatarduel.model.Player;
import com.avatarduel.view.HandView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class HandController {
    private HandView topHandV;
    private HandView bottomHandV;
    private PhaseController phaseController;
    private SelectionController selectionController;
    private CardController cardController;
    private ButtonController buttonController;
    private SideController sideController;

    public HandController() {
        this(new PhaseController(), new SelectionController(), new CardController(), new ButtonController(), new SideController());
    }

    public HandController(PhaseController phaseController, SelectionController selectionController,
                          CardController cardController, ButtonController buttonController,
                          SideController sideController) {
        Player.getPlayers();
        topHandV = new HandView();
        bottomHandV = new HandView();
        this.phaseController = phaseController;
        this.selectionController = selectionController;
        this.cardController = cardController;
        this.buttonController = buttonController;
        this.sideController = sideController;
    }

    public HandView getHandV(Loc type) {
        if (type == Loc.TOP) {
            return topHandV;
        } else {
            return bottomHandV;
        }
    }

    public void clearHands() {
        topHandV.getChildren().clear();
        bottomHandV.getChildren().clear();
    }

    public void addCardToHand(Player a, Loc type, Element x, int idx) {
        HandView hand = getHandV(type);
        Pane card;
        if (phaseController.getTurn().checkTurn(a)) {
            card = new OpenedCard(62.5, x);
            cardController.getCardsBottom().add(card);
            cardController.showInfoOnHover(card, a, idx);
            setCardEventOnHand(card, a);
        } else {
            card = new ClosedCard(62.5);
        }
        hand.getChildren().add(card);
        hand.getChildren().add(new Space(10));
    }

    public void updateHand(Player p1, Player p2) {
        for (int i=0; i<p2.countCardsInHand(); i++) {
            addCardToHand(p2,Loc.BOTTOM,p2.getHand(i).getElement(),i);
        }
        for (int i=0; i<p1.countCardsInHand(); i++) {
            addCardToHand(p1,Loc.TOP,p1.getHand(i).getElement(),i);
        }
    }

    public void removeFromHand() {
        Player.player1.refreshHand();
        Player.player2.refreshHand();
        cardController.resetCardsBottom();
        clearHands();
        updateHand(phaseController.getTurn().getPlayerNotInTurn(),phaseController.getTurn().getPlayerInTurn());
    }


    public void setCardEventOnHand(Pane card, Player a) {
        card.setOnMouseClicked(e2 -> {
            for (Pane pane : cardController.getCardsBottom()) {
                pane.setEffect(null);
            }
            a.cardsOnField.forEach((K, V) -> V.setEffect(null));
            buttonController.setDisableDelete(true);
            selectionController.releaseSkill();
            // Main Phase Action
            if (phaseController.getGamePhase().getCanUseNonLandCard() || phaseController.getGamePhase().getCanUseLandCard()) {
                int temp = cardController.getCardsBottom().indexOf(card); // Could be not found
                // Use land card?
                if (temp >= 0 && a.getHand(temp) instanceof LandGameCard) {
                    if (phaseController.getGamePhase().getCanUseLandCard()) {
                        a.useLand(temp);
                        sideController.getSideV(Loc.BOTTOM).updatePowerCounters(a);
                        removeFromHand();
                        phaseController.getGamePhase().setCanUseLandCard(false);
                        selectionController.releaseHand();
                    }
                } else if (temp >= 0 && selectionController.getHand().getTarget() != temp) {
                    card.setEffect(Basic.getShadow(Color.BLUE, 30));
                    selectionController.getHand().setTarget(temp);
                } else {
                    selectionController.releaseHand();
                }
            }
        });
    }
}
