package com.avatarduel.controller;

import com.avatarduel.card.*;
import com.avatarduel.model.Loc;
import com.avatarduel.components.Basic;
import com.avatarduel.model.Player;
import com.avatarduel.model.FieldModel;
import com.avatarduel.view.FieldView;
import com.avatarduel.view.MainView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.List;

public class FieldController {
    private static int fieldBoxCounts = 12;
    private List<Integer> usedAttackTargets;
    private FieldView topField;
    private FieldView bottomField;
    private FieldModel fieldModel;
    private PhaseController phaseController;
    private SelectionController selectionController;
    private CardController cardController;
    private ButtonController buttonController;
    private PlayerController playerController;
    private HandController handController;
    private SideController sideController;

    public FieldController() {
        this(new PhaseController(), new SelectionController(), new CardController(), new ButtonController(), new PlayerController(),
                new HandController(), new SideController());
    }

    public FieldController(PhaseController phaseController, SelectionController selectionController,
                           CardController cardController, ButtonController buttonController,
                           PlayerController playerController, HandController handController,
                           SideController sideController) {
        Player.getPlayers();
        topField = new FieldView();
        bottomField = new FieldView();
        fieldModel = new FieldModel();
        this.phaseController = phaseController;
        this.selectionController = selectionController;
        this.cardController = cardController;
        this.buttonController = buttonController;
        this.playerController = playerController;
        this.handController = handController;
        this.sideController = sideController;
    }

    public FieldView getTopField() {
        return topField;
    }

    public FieldView getBottomField() {
        return bottomField;
    }

    public FieldView getField(Loc type) {
        if (type == Loc.TOP) return topField;
        else return bottomField;
    }

    public HBox getBox(int idx) {
        assert (idx >= 0 && idx < fieldBoxCounts*2);
        if (idx >= fieldBoxCounts) {
            return bottomField.getBox(idx-fieldBoxCounts);
        } else {
            return topField.getBox(idx);
        }
    }

    public void clearBox(int idx) {
        assert (idx >= 0 && idx < fieldBoxCounts*2);
        if (idx >= fieldBoxCounts) {
            bottomField.clearBox(idx-fieldBoxCounts);
        } else {
            topField.clearBox(idx);
        }
    }


    public void initFieldBoxes() {
        Player p1, p2;
        p1 = phaseController.getTurn().getPlayerInTurn();
        p2 = phaseController.getTurn().getPlayerNotInTurn();

        if (usedAttackTargets != null) {
            usedAttackTargets.forEach(v->{
                p2.switchCardMode(v);
            });
        }

        // Top fields
        initTopField(p2);

        // Bottom fields
        initBottomField(p1);

        usedAttackTargets = new ArrayList<>();
    }

    private void initTopField(Player p2) {
        // Skill row
        for (int i=0; i<fieldBoxCounts/2; i++) {
            int j = (fieldBoxCounts-1-i)%fieldBoxCounts;
            getBox(i).getChildren().clear();
            Pane card = p2.cardsOnField.get(j);
            if (card != null) {
                getBox(i).getChildren().add(card);
                initFieldCardSkill(p2, card, Loc.TOP);
            }
        }
        // Character row
        for (int i=fieldBoxCounts/2; i<fieldBoxCounts; i++) {
            int j = (fieldBoxCounts-1-i)%fieldBoxCounts;
            getBox(i).getChildren().clear();
            Pane card = p2.cardsOnField.get(j);
            if (card != null) {
                getBox(i).getChildren().add(card);
                initFieldCardChar(p2, card);
            }
        }
    }

    private void initBottomField(Player p1) {
        // Character row
        for (int i=fieldBoxCounts; i<fieldBoxCounts/2*3; i++) {
            int j = i-fieldBoxCounts;
            getBox(i).getChildren().clear();
            Pane card = p1.cardsOnField.get(j);
            if (card != null) {
                getBox(i).getChildren().add(card);
                getBox(i).setOnMouseClicked(null);
                initFieldCardChar(p1, card);
            } else {
                setFieldBoxOnClickEvent(i, p1);
            }
        }
        // Skill row
        for (int i=fieldBoxCounts/2*3; i<fieldBoxCounts*2; i++) {
            int j = i-fieldBoxCounts;
            getBox(i).getChildren().clear();
            Pane card = p1.cardsOnField.get(j);
            if (card != null) {
                getBox(i).getChildren().add(card);
                getBox(i).setOnMouseClicked(null);
                initFieldCardSkill(p1, card, Loc.BOTTOM);
            }
        }
    }

    public void initFieldCardChar(Player a, Pane card) {
        Player b = (a == Player.player1) ? Player.player2 : Player.player1;
        // Battle Phase Action
        if (phaseController.getTurn().checkTurn(a)) {
            setBottomCardBehaviour(card, a, b);
        } else {
            setTopCardBehaviour(card, a, b);
        }
    }

    public void initFieldCardSkill(Player a, Pane card, Loc type) {
        // Battle Phase Action
        setSkillCardBehaviour(card, a, type);
    }

    public void setBottomCardBehaviour(Pane card, Player a, Player b) {
        // Pre-condition card is a character card
        card.setOnMouseClicked(e -> {
            a.cardsOnField.forEach((K, V) -> V.setEffect(null));
            if (phaseController.getGamePhase().getCanAttack()) {
                bottomCardBehaviourCall(card,a,b);
            } else if (phaseController.getGamePhase().getCanUseNonLandCard()) {
                bottomCardSkillBehaviourCall(card, a);
            }
        });
    }
    public void setSkillCardBehaviour(Pane card, Player a, Loc type) {
        card.setOnMouseClicked(e -> {
            a.cardsOnField.forEach((K, V) -> V.setEffect(null));
            for (Pane pane : cardController.getCardsBottom()) {
                pane.setEffect(null);
            }
            selectionController.releaseHand();
            if (phaseController.getGamePhase().getCanUseNonLandCard()) {
                for(Integer K : a.cardsOnField.keySet()) {
                    Pane V = a.cardsOnField.get(K);
                    if (V == card) {
                        int i = 0;
                        if (type == Loc.TOP) {
                            i += fieldBoxCounts;
                        }
                        if (selectionController.getSkill().getTarget() != K+i) {
                            selectionController.getSkill().setTarget(K+i);
                            card.setEffect(Basic.getShadow(Color.RED, 30));
                            buttonController.setDisableDelete(false);
                        } else {
                            selectionController.releaseSkill();
                            buttonController.setDisableDelete(true);
                        }
                        break;
                    }
                }
            }
        });
    }

    private void bottomCardBehaviourCall(Pane card, Player a, Player b) {
        for (Integer K : a.cardsOnField.keySet()) {
            Pane V = a.cardsOnField.get(K);
            if (V == card && !a.cardsOnFieldInfo.get(K).getValue()) {
                if (b.cardsOnField.isEmpty()) {
                    CharacterGameCard c = (CharacterGameCard) a.cardsOnFieldInfo.get(K).getKey();
                    selectionController.getAttack().setTarget(K);
                    updateAttack(a, b, 0, c.getAttack(), false);
                } else if (selectionController.getAttack().getTarget() != K) {
                    selectionController.getAttack().setTarget(K);
                    card.setEffect(Basic.getShadow(Color.BLUE, 30));
                } else {
                    selectionController.releaseAttack();
                }
                break;
            }
        }
    }

    private void bottomCardSkillBehaviourCall(Pane card, Player a) {
        for(Integer K : a.cardsOnField.keySet()) {
            Pane V = a.cardsOnField.get(K);
            if (V == card) {
                // Try to add skill to character
                if (selectionController.getHand().isSelected()
                        && a.getHand(selectionController.getHand().getTarget()) instanceof SkillGameCard) {
                    // Pre-condition target is skill card (check by condition above)
                    int target = selectionController.getHand().getTarget();
                    GameCard skillCard = a.getHand(target);
                    if (skillCard instanceof DestroySkillGameCard && a.useCard((HasCostAttribute) skillCard, skillCard.getElement())) {
                        deleteCard(a,K);
                        setFieldBoxOnClickEvent(K+fieldBoxCounts,a);
                        a.setHand(target, null);
                        handController.removeFromHand();
                        sideController.getSideV(Loc.BOTTOM).updatePowerCounters(a);
                        selectionController.releaseHand();
                    } else {
                        int i = fieldBoxCounts/2;
                        while (i < fieldBoxCounts && a.cardsOnFieldInfo.get(i) != null) i++;
                        if (i < fieldBoxCounts) {
                            if ((skillCard instanceof PowerUpSkillGameCard
                                    && !((CharacterGameCard) a.cardsOnFieldInfo.get(K).getKey()).isAttachedPowerUpinField())
                                    || skillCard instanceof AuraSkillGameCard) {
                                if (a.useCard((HasCostAttribute) skillCard, skillCard.getElement())) {
                                    Pane skill = cardController.getCardsBottom().get(target);
                                    getBox(i + fieldBoxCounts).getChildren().add(skill);
                                    getBox(i + fieldBoxCounts).setOnMouseClicked(null);
                                    initFieldCardSkill(a, skill, Loc.BOTTOM);
                                    a.cardsOnField.put(i, skill);
                                    a.cardsOnFieldInfo.put(i, new Pair<>(skillCard, false));
                                    addSkillLocToChar(card, i);
                                    addSkillInfo(skill, K, a.getId());
                                    cardController.showInfoOnHover(cardController.getCardsBottom().get(target), a.cardsOnFieldInfo.get(i).getKey());
                                    a.setHand(target, null);
                                    ((AppliableEffect) skillCard).addEffect((CharacterGameCard) a.cardsOnFieldInfo.get(K).getKey());
                                    handController.removeFromHand();
                                    sideController.getSideV(Loc.BOTTOM).updatePowerCounters(a);
                                    selectionController.releaseHand();
                                }
                            }
                        }
                    }
                } else {
                    a.switchCardMode(K);
                }
                break;
            }
        }
    }

    public void setTopCardBehaviour(Pane card, Player a, Player b) {
        card.setOnMouseClicked(e -> {
            if (phaseController.getGamePhase().getCanAttack()) {
                topCardBehaviourCall(card,a,b);
            } else if (phaseController.getGamePhase().getCanUseNonLandCard()) {
                topCardSkillBehaviourCall(card,a,b);
            }
        });
    }

    private void topCardBehaviourCall(Pane card, Player a, Player b) {
        for (Integer K: a.cardsOnField.keySet()) {
            Pane V = a.cardsOnField.get(K);
            if (selectionController.getAttack().isSelected() && V == card) {
                CharacterGameCard attacker = (CharacterGameCard) b.cardsOnFieldInfo.get(selectionController.getAttack().getTarget()).getKey();
                CharacterGameCard enemy = (CharacterGameCard) a.cardsOnFieldInfo.get(K).getKey();
                if ((!a.cardsOnFieldInfo.get(K).getValue() || attacker.isAttachedPowerUpinField())
                        && attacker.getAttack() > enemy.getAttack()) {
                    updateAttack(b,a,K,attacker.getAttack()-enemy.getAttack(),true);
                } else if (a.cardsOnFieldInfo.get(K).getValue() && attacker.getAttack() > enemy.getDefense()) {
                    updateAttack(b,a,K,0,true);
                }
                break;
            }
        }
    }

    private void topCardSkillBehaviourCall(Pane card, Player a, Player invoker) {
        for(Integer K : a.cardsOnField.keySet()) {
            Pane V = a.cardsOnField.get(K);
            if (V == card) {
                // Try to add skill to character
                if (selectionController.getHand().isSelected()
                        && invoker.getHand(selectionController.getHand().getTarget()) instanceof SkillGameCard) {
                    // Pre-condition target is skill card (check by condition above)
                    int target = selectionController.getHand().getTarget();
                    GameCard skillCard = invoker.getHand(target);
                    if (skillCard instanceof DestroySkillGameCard && invoker.useCard((HasCostAttribute) skillCard, skillCard.getElement())) {
                        deleteCard(a,K);
                        invoker.setHand(target, null);
                        handController.removeFromHand();
                        sideController.getSideV(Loc.BOTTOM).updatePowerCounters(invoker);
                        selectionController.releaseHand();
                    } else {
                        int i = fieldBoxCounts/2;
                        while (i < fieldBoxCounts && a.cardsOnFieldInfo.get(i) != null) i++;
                        if (i < fieldBoxCounts) {
                            if ((skillCard instanceof PowerUpSkillGameCard
                                    && !((CharacterGameCard) a.cardsOnFieldInfo.get(K).getKey()).isAttachedPowerUpinField())
                                    || skillCard instanceof AuraSkillGameCard) {
                                if (invoker.useCard((HasCostAttribute) skillCard, skillCard.getElement())) {
                                    Pane skill = cardController.getCardsBottom().get(target);
                                    int j = (fieldBoxCounts-1-i) % fieldBoxCounts;
                                    getBox(j).getChildren().add(skill);
                                    getBox(j).setOnMouseClicked(null);
                                    initFieldCardSkill(a, skill, Loc.TOP);
                                    a.cardsOnField.put(i, skill);
                                    a.cardsOnFieldInfo.put(i, new Pair<>(skillCard, false));
                                    addSkillLocToChar(card, i);
                                    addSkillInfo(skill, K, invoker.getId());
                                    cardController.showInfoOnHover(cardController.getCardsBottom().get(target), a.cardsOnFieldInfo.get(i).getKey());
                                    invoker.setHand(target, null);
                                    ((AppliableEffect) skillCard).addEffect((CharacterGameCard) a.cardsOnFieldInfo.get(K).getKey());
                                    handController.removeFromHand();
                                    sideController.getSideV(Loc.BOTTOM).updatePowerCounters(invoker);
                                    selectionController.releaseHand();
                                }
                            }
                        }
                    }
                }
                break;
            }
        }
    }


    public void deleteCard(Player a, int idx) {
        if (phaseController.getTurn().checkTurn(a))
            clearBox(idx+fieldBoxCounts);
        else
            clearBox((fieldBoxCounts-1-idx) % fieldBoxCounts);
        Pane card = a.cardsOnField.remove(idx);
        a.cardsOnFieldInfo.remove(idx);
        List<Integer> x = fieldModel.getCharacterSkillList().remove(card);
        if (x != null) {
            x.forEach(K -> {
                a.cardsOnFieldInfo.remove(K);
                Pane temp = a.cardsOnField.remove(K);
                fieldModel.getSkillInfo().remove(temp);
                if (phaseController.getTurn().checkTurn(a))
                    K += fieldBoxCounts;
                else
                    K = (fieldBoxCounts-1-K) % fieldBoxCounts;
                clearBox(K);
            });
        }
    }


    public void setFieldBoxOnClickEvent(int idx, Player p1) {
        int j = idx - fieldBoxCounts;
        getBox(idx).setOnMouseClicked(e -> {
            int target = selectionController.getHand().getTarget();
            if (target >= 0) {
                GameCard card = p1.getHand(target);
                if (phaseController.getGamePhase().getCanUseNonLandCard()
                        && (j < fieldBoxCounts/2 && card instanceof CharacterGameCard)
                        && p1.useCard((HasCostAttribute) card, card.getElement())) { // Pre-condition that the card is character
                    if (p1.cardsOnField.get(j) == null && !p1.cardsOnField.containsValue(cardController.getCardsBottom().get(target))) {
                        Pane temp = cardController.getCardsBottom().get(target);
                        getBox(j + fieldBoxCounts).getChildren().add(temp);
                        getBox(j + fieldBoxCounts).setOnMouseClicked(null);
                        temp.setOnMouseClicked(e2 -> {
                            if (phaseController.getGamePhase().getCanUseNonLandCard()) {
                                bottomCardSkillBehaviourCall(temp, p1);
                            }
                        });
                        p1.cardsOnField.put(j, temp);
                        p1.cardsOnFieldInfo.put(j, new Pair<>(p1.getHand(target), false));
                        cardController.showInfoOnHover(temp, p1.cardsOnFieldInfo.get(j).getKey());
                        p1.setHand(target, null);
                        handController.removeFromHand();
                        sideController.getSideV(Loc.BOTTOM).updatePowerCounters(p1);
                        selectionController.releaseHand();
                    }
                }
            }
        });
    }

    public void addSkillLocToChar(Pane card, int loc){
        List<Integer> x = fieldModel.getCharacterSkillList().get(card);
        if (x == null) x = new ArrayList<>();
        x.add(loc);
        fieldModel.getCharacterSkillList().put(card, x);
    }

    public void addSkillInfo(Pane card, int loc, int id){
        Pair<Integer,Integer> x = fieldModel.getSkillInfo().get(card);
        if (x == null) x = new Pair<>(loc,id);
        fieldModel.getSkillInfo().put(card, x);
    }

    public void removeSkill(int loc, Pane card, Player invoker, Player target) {
        Pair<Integer,Integer> info = fieldModel.getSkillInfo().get(card);
        if (info.getValue() == invoker.getId()) {
            CharacterGameCard charCard = (CharacterGameCard) target.cardsOnFieldInfo.get(info.getKey()).getKey();
            List<Integer> skills = fieldModel.getCharacterSkillList().get(target.cardsOnField.get(info.getKey()));
            skills.remove((Integer) loc);
            ((AppliableEffect) target.cardsOnFieldInfo.get(loc).getKey()).removeEffect(charCard);
            fieldModel.getSkillInfo().remove(card);
            target.cardsOnField.remove(loc);
            target.cardsOnFieldInfo.remove(loc);
            if (invoker.getId() == target.getId())
                clearBox(loc + fieldBoxCounts);
            else
                clearBox((fieldBoxCounts-1-loc)%fieldBoxCounts);
        }
    }

    public void updateAttack(Player attacker, Player enemy, int idx, int damage, boolean isHitOnEnemy) {
        if (damage > 0) {
            int hp = enemy.getHealth();
            int newhp = hp - damage;
            if (newhp < 0) newhp = 0;
            playerController.animateHP(Loc.TOP, hp, newhp);
            playerController.updateHealth(enemy, newhp);
            playerController.updateHealthValue(Loc.TOP, newhp);
            if (newhp <= 0) {
                MainView.getInstance().loadLoseScreen(attacker);
            }
        }
        attacker.switchCardMode(selectionController.getAttack().getTarget());
        if (isHitOnEnemy) {
            deleteCard(enemy, idx);
        }
        usedAttackTargets.add(selectionController.getAttack().getTarget());
        selectionController.releaseAttack();
        attacker.cardsOnField.forEach((K, V) -> V.setEffect(null));

    }
}
