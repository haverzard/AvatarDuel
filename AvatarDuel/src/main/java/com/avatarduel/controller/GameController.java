package com.avatarduel.controller;

import com.avatarduel.model.Loc;
import com.avatarduel.components.Basic;
import com.avatarduel.model.Element;
import com.avatarduel.phase.DrawPhase;
import com.avatarduel.model.Player;
import com.avatarduel.view.MainView;
import javafx.animation.TranslateTransition;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class GameController {
    private static int fieldBoxCounts = 12;
    private PhaseController phaseController;
    private SelectionController selectionController;
    private ButtonController buttonController;
    private CardController cardController;
    private FieldController fieldController;
    private HandController handController;
    private PlayerController playerController;
    private SideController sideController;

    public GameController() {
        Player.getPlayers();
        phaseController = new PhaseController();
        selectionController = new SelectionController();
        playerController = new PlayerController();
        cardController = new CardController();
        buttonController = new ButtonController(phaseController, selectionController);
        sideController = new SideController(buttonController);
        handController = new HandController(phaseController, selectionController, cardController, buttonController, sideController);
        fieldController = new FieldController(phaseController, selectionController, cardController,
                buttonController, playerController, handController, sideController);

        buttonController.getPhaseBtnView().getEnd().setOnAction(e -> {
            endTurn();
            phaseController.setGamePhase(new DrawPhase());
        });
        setDeckButtonEvent();
        setDeleteEvent();
    }

    public PhaseController getPhaseController() {
        return phaseController;
    }

    public SelectionController getSelectionController() {
        return selectionController;
    }

    public ButtonController getButtonController() {
        return buttonController;
    }

    public CardController getCardController() {
        return cardController;
    }

    public FieldController getFieldController() {
        return fieldController;
    }

    public HandController getHandController() {
        return handController;
    }

    public PlayerController getPlayerController() {
        return playerController;
    }

    public SideController getSideController() {
        return sideController;
    }

    private void setDeckButtonEvent() {
        buttonController.getDeckBtnView().setOnMouseClicked(e-> {
            if (phaseController.getGamePhase().getCanDraw()) {
                if (phaseController.getTurn().checkTurn(Player.player1)) {
                    Element x = Player.player1.takeCard();
                    if (x != null) {
                        sideController.getSideV(Loc.BOTTOM).updateDeckCounter(Player.player1.countCardsInDeck());
                        handController.addCardToHand(Player.player1,Loc.BOTTOM,x,Player.player1.countCardsInHand()-1);
                    }
                } else {
                    Element x = Player.player2.takeCard();
                    if (x != null) {
                        sideController.getSideV(Loc.BOTTOM).updateDeckCounter(Player.player2.countCardsInDeck());
                        handController.addCardToHand(Player.player2,Loc.BOTTOM,x,Player.player2.countCardsInHand()-1);
                    }
                }
                buttonController.getDeckBtnView().setEffect(null);
                phaseController.getGamePhase().setCanDraw(false);
            }
        });
    }

    private void setDeleteEvent() {
        buttonController.getPhaseBtnView().getDelete().setOnAction(e-> {
            if (selectionController.getSkill().isSelected()) {
                int targetSkill = selectionController.getSkill().getTarget();
                Player target = (phaseController.getTurn().checkTurn(Player.player1)) ? Player.player1 : Player.player2;
                Player invoker = target;
                Pane skillCard;
                if (targetSkill >= fieldBoxCounts) {
                    target = (phaseController.getTurn().checkTurn(Player.player1)) ? Player.player2 : Player.player1;
                    targetSkill -= fieldBoxCounts;
                }
                skillCard = target.cardsOnField.get(targetSkill);
                skillCard.setEffect(null);
                fieldController.removeSkill(targetSkill, skillCard, invoker, target);
                buttonController.setDisableDelete(true);
                selectionController.releaseSkill();
            }
        });
    }

    private void endTurn() {
        phaseController.getTurn().nextTurn();
        Player.player1.refreshHand();
        Player.player2.refreshHand();
        handController.clearHands();
        cardController.resetCardsBottom();
        cardController.clearInfo();
        cardController.updateCardDesc(null);
        playerController.switchName();
        buttonController.resetAllEffects();
        buttonController.getPhaseBtnView().resetPhaseButtons();
        buttonController.getDeckBtnView().setEffect(Basic.getShadow(Color.BLUE, 30));
        Player.player1.resetPower();
        Player.player2.resetPower();
        Player possibleLoser;
        Player possibleWinner;
        if (phaseController.getTurn().checkTurn(Player.player1)) {
            possibleLoser = Player.player1;
            possibleWinner = Player.player2;
        } else {
            possibleLoser = Player.player2;
            possibleWinner = Player.player1;
        }
        if (possibleLoser.countCardsInDeck() == 0) {
            MainView.getInstance().loadLoseScreen(possibleWinner);
        } else {
            MainView.screen.getChildren().add(Basic.getScreen("End Turn"));
            switchPlayer(possibleWinner, possibleLoser);
        }
        buttonController.getPhaseBtnView().getMain().setDisable(false);
    }


    public void switchPlayer(Player p1, Player p2) {
        fieldController.initFieldBoxes();
        playerController.animateHP(Loc.BOTTOM, p1.getHealth(), p2.getHealth());
        sideController.getSideV(Loc.BOTTOM).updatePowerCounters(p2);
        sideController.getSideV(Loc.TOP).updatePowerCounters(p1);

        TranslateTransition animation = playerController.animateHP(Loc.TOP, p2.getHealth(), p1.getHealth());
        animation.setOnFinished(e -> {
            playerController.updateHealthValue(Loc.TOP, p1.getHealth());
            playerController.updateHealthValue(Loc.BOTTOM, p2.getHealth());
            Basic.scr.setOnMouseClicked(e2 -> MainView.screen.getChildren().remove(1));
        });
        handController.updateHand(p1,p2);
        sideController.getSideV(Loc.BOTTOM).updateDeckCounter(p2.countCardsInDeck());
        sideController.getSideV(Loc.TOP).updateDeckCounter(p1.countCardsInDeck());
    }
}
