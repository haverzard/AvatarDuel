package com.avatarduel.controller;

import com.avatarduel.card.*;
import com.avatarduel.components.Basic;
import com.avatarduel.components.Card;
import com.avatarduel.model.FieldModel;
import com.avatarduel.model.HealthModel;
import com.avatarduel.model.StateModel;
import com.avatarduel.player.Player;
import com.avatarduel.view.CardView;
import com.avatarduel.view.FieldView;
import com.avatarduel.view.HandView;
import com.avatarduel.view.PowerView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.util.Pair;

import java.util.List;

public class CardController {
    public static void showInfoOnHover(Pane card, Player p, int handIndex) {
        card.setOnMouseEntered(e2 -> {
            Card.update(CardView.getCardInfo(), 250, p.getHand(handIndex));
            CardView.updateCardDesc(p.getHand(handIndex));
        });
    }

    public static void showInfoOnHover(Pane card, GameCard x) {
        card.setOnMouseEntered(e2 -> {
            Card.update(CardView.getCardInfo(), 250, x);
            CardView.updateCardDesc(x);
        });
    }

    public static void setBottomCardBehaviour(Pane card, Player a, Player b) {
        // Pre-condition card is a character card
        card.setOnMouseClicked(e -> {
            a.cardsOnField.forEach((K, V) -> V.setEffect(null));
            if (StateController.checkState("Battle phase")) {
                bottomCardBehaviourCall(card,a,b);
            } else if (StateController.checkState("Main phase")) {
                bottomCardSkillBehaviourCall(card, a);
            }
        });
    }
    public static void setSkillCardBehaviour(Pane card, Player a, String type) {
        card.setOnMouseClicked(e -> {
            a.cardsOnField.forEach((K, V) -> V.setEffect(null));
            for (Pane pane : CardView.cardsBottom) {
                pane.setEffect(null);
            }
            StateController.updateState("Release card");
            if (StateController.checkState("Main phase")) {
                for(Integer K : a.cardsOnField.keySet()) {
                    Pane V = a.cardsOnField.get(K);
                    if (V == card) {
                        int i = 0;
                        if (type.equals("top")) {
                            i += 16;
                        }
                        if (StateController.updateTargetSkill(K+i)) {
                            card.setEffect(Basic.getShadow(Color.RED, 30));
                            ButtonController.setDisableDelete(false);
                        } else {
                            StateController.updateState("Release skill card");
                            ButtonController.setDisableDelete(true);
                        }
                        break;
                    }
                }
            }
        });
    }

    private static void bottomCardBehaviourCall(Pane card, Player a, Player b) {
        for (Integer K : a.cardsOnField.keySet()) {
            Pane V = a.cardsOnField.get(K);
            if (V == card && !a.cardsOnFieldInfo.get(K).getValue()) {
                if (b.cardsOnField.isEmpty()) {
                    CharacterGameCard c = (CharacterGameCard) a.cardsOnFieldInfo.get(K).getKey();
                    StateController.updateTargetAttack(K);
                    HealthModel.updateAttack(a, b, 0, c.getAttack(), false);
                } else if (StateController.updateTargetAttack(K)) {
                    card.setEffect(Basic.getShadow(Color.BLUE, 30));
                } else {
                    StateController.updateState("Release attack card");
                }
                break;
            }
        }
    }

    private static void bottomCardSkillBehaviourCall(Pane card, Player a) {
        for(Integer K : a.cardsOnField.keySet()) {
            Pane V = a.cardsOnField.get(K);
            if (V == card) {
                // Try to add skill to character
                if (StateController.checkState("Card selected")
                        && a.getHand(StateModel.getTarget()) instanceof SkillGameCard) {
                    // Pre-condition target is skill card (check by condition above)
                    GameCard skillCard = a.getHand(StateModel.getTarget());
                    if (skillCard instanceof DestroySkillGameCard) {
                        deleteCard(a,K);
                    } else {
                        int target = StateModel.getTarget();
                        int i = 8;
                        while (i < 16 && a.cardsOnFieldInfo.get(i) != null) i++;
                        if (i < 16) {
                            if ((skillCard instanceof PowerUpSkillGameCard
                                && !((CharacterGameCard) a.cardsOnFieldInfo.get(K).getKey()).isAttachedPowerUpinField())
                                || skillCard instanceof AuraSkillGameCard) {
                                if (a.useCard((HasCostAttribute) skillCard, skillCard.getElement())) {
                                    Pane skill = CardView.cardsBottom.get(target);
                                    FieldView.getBox(i + 16).getChildren().add(skill);
                                    FieldView.getBox(i + 16).setOnMouseClicked(null);
                                    FieldView.initFieldCardSkill(a, skill, "bottom");
                                    a.cardsOnField.put(i, skill);
                                    a.cardsOnFieldInfo.put(i, new Pair<>(skillCard, false));
                                    FieldController.addSkillLocToChar(card, i);
                                    FieldController.addSkillInfo(skill, K, a.getId());
                                    CardController.showInfoOnHover(CardView.cardsBottom.get(target), a.cardsOnFieldInfo.get(i).getKey());
                                    a.setHand(target, null);
                                    ((AppliableEffect) skillCard).addEffect((CharacterGameCard) a.cardsOnFieldInfo.get(K).getKey());
                                    HandView.removeFromHand();
                                    PowerView.updatePowerCounters("bottom", a);
                                    StateController.updateState("Release card");
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

    public static void setTopCardBehaviour(Pane card, Player a, Player b) {
        card.setOnMouseClicked(e -> {
            if (StateController.checkState("Battle phase")) {
                topCardBehaviourCall(card,a,b);
            } else if (StateController.checkState("Main phase")) {
                topCardSkillBehaviourCall(card,a,b);
            }
        });
    }

    private static void topCardBehaviourCall(Pane card, Player a, Player b) {
        for (Integer K: a.cardsOnField.keySet()) {
            Pane V = a.cardsOnField.get(K);
            if (StateController.checkState("Attack card selected") && V == card) {
                CharacterGameCard attacker = (CharacterGameCard) b.cardsOnFieldInfo.get(StateModel.getTargetAttack()).getKey();
                CharacterGameCard enemy = (CharacterGameCard) a.cardsOnFieldInfo.get(K).getKey();
                if ((!a.cardsOnFieldInfo.get(K).getValue() || attacker.isAttachedPowerUpinField())
                        && attacker.getAttack() >= enemy.getAttack()) {
                    HealthModel.updateAttack(b,a,K,attacker.getAttack()-enemy.getAttack(),true);
                } else if (a.cardsOnFieldInfo.get(K).getValue() && attacker.getAttack() > enemy.getDefense()) {
                    HealthModel.updateAttack(b,a,K,0,true);
                }
                break;
            }
        }
    }

    private static void topCardSkillBehaviourCall(Pane card, Player a, Player invoker) {
        for(Integer K : a.cardsOnField.keySet()) {
            Pane V = a.cardsOnField.get(K);
            if (V == card) {
                System.out.println("hello");
                // Try to add skill to character
                if (StateController.checkState("Card selected")
                        && invoker.getHand(StateModel.getTarget()) instanceof SkillGameCard) {
                    // Pre-condition target is skill card (check by condition above)
                    int target = StateModel.getTarget();
                    GameCard skillCard = invoker.getHand(target);
                    if (skillCard instanceof DestroySkillGameCard && invoker.useCard((HasCostAttribute) skillCard, skillCard.getElement())) {
                        deleteCard(a,K);
                        invoker.setHand(target, null);
                        HandView.removeFromHand();
                        PowerView.updatePowerCounters("bottom", invoker);
                        StateController.updateState("Release card");
                    } else {
                        int i = 8;
                        while (i < 16 && a.cardsOnFieldInfo.get(i) != null) i++;
                        if (i < 16) {
                            if ((skillCard instanceof PowerUpSkillGameCard
                                    && !((CharacterGameCard) a.cardsOnFieldInfo.get(K).getKey()).isAttachedPowerUpinField())
                                    || skillCard instanceof AuraSkillGameCard) {
                                if (invoker.useCard((HasCostAttribute) skillCard, skillCard.getElement())) {
                                    Pane skill = CardView.cardsBottom.get(target);
                                    int j = (15 - i) % 16;
                                    FieldView.getBox(j).getChildren().add(skill);
                                    FieldView.getBox(j).setOnMouseClicked(null);
                                    FieldView.initFieldCardSkill(a, skill, "top");
                                    a.cardsOnField.put(i, skill);
                                    a.cardsOnFieldInfo.put(i, new Pair<>(skillCard, false));
                                    FieldController.addSkillLocToChar(card, j);
                                    FieldController.addSkillInfo(skill, K, invoker.getId());
                                    CardController.showInfoOnHover(CardView.cardsBottom.get(target), a.cardsOnFieldInfo.get(i).getKey());
                                    invoker.setHand(target, null);
                                    ((AppliableEffect) skillCard).addEffect((CharacterGameCard) a.cardsOnFieldInfo.get(K).getKey());
                                    HandView.removeFromHand();
                                    PowerView.updatePowerCounters("bottom", invoker);
                                    StateController.updateState("Release card");
                                }
                            }
                        }
                    }
                }
                break;
            }
        }
    }

    public static void setCardEventOnHand(Pane card, Player a) {
        card.setOnMouseClicked(e2 -> {
            for (Pane pane : CardView.cardsBottom) {
                pane.setEffect(null);
            }
            a.cardsOnField.forEach((K, V) -> V.setEffect(null));
            ButtonController.setDisableDelete(true);
            StateController.updateState("Release skill card");
            // Main Phase Action
            if (StateController.checkState("Main phase")) {
                int temp = CardView.cardsBottom.indexOf(card); // Could be not found
                // Use land card?
                if (temp >= 0 && a.getHand(temp) instanceof LandGameCard) {
                    if (StateController.checkState("Use land")) {
                        a.useLand(temp);
                        PowerView.updatePowerCounters("bottom", a);
                        HandView.removeFromHand();
                        StateController.updateState("Use land");
                    }
                    StateController.updateTarget(-1);
                } else if (temp >= 0 && StateController.updateTarget(temp)) {
                    card.setEffect(Basic.getShadow(Color.BLUE, 30));
                } else {
                    StateController.updateTarget(-1);
                }
            }
        });
    }

    public static void deleteCard(Player a, int idx) {
        if (a.getId() == StateModel.getTurn())
            FieldView.clearBox(idx+16);
        else
            FieldView.clearBox((15 - idx) % 16);
        Pane card = a.cardsOnField.remove(idx);
        List<Integer> x = FieldModel.getCharacterSkillList().remove(card);
        if (x != null) {
            x.forEach(K -> {
                if (a.getId() == StateModel.getTurn())
                    K += 16;
                else
                    K = (15 - K) % 16;
                FieldView.clearBox(K);
                FieldModel.getSkillInfo().remove(a.cardsOnField.remove(K));
                a.cardsOnFieldInfo.remove(K);
            });
            System.out.println(x);
        }
    }
}