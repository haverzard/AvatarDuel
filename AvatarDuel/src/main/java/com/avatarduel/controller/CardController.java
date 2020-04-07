package com.avatarduel.controller;

import com.avatarduel.card.AuraSkillGameCard;
import com.avatarduel.card.CharacterGameCard;
import com.avatarduel.card.DestroySkillGameCard;
import com.avatarduel.card.GameCard;
import com.avatarduel.card.LandGameCard;
import com.avatarduel.card.SkillGameCard;
import com.avatarduel.components.Basic;
import com.avatarduel.components.Card;
import com.avatarduel.model.HealthModel;
import com.avatarduel.model.StateModel;
import com.avatarduel.player.Player;
import com.avatarduel.view.CardView;
import com.avatarduel.view.HandView;
import com.avatarduel.view.PowerView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class CardController {
    public static void showInfoOnHover(Pane card, Player p, int handIndex) {
        card.setOnMouseEntered(e2 -> Card.update(CardView.getCardInfo(), 250, p.getHand(handIndex)));
    }

    public static void showInfoOnHover(Pane card, GameCard x) {
        card.setOnMouseEntered(e2 -> Card.update(CardView.getCardInfo(), 250, x));
    }

    public static void setBottomCardBehaviour(Pane card, Player a, Player b) {
        card.setOnMouseClicked(e -> {
            a.cardsOnField.forEach((K, V) -> V.setEffect(null));
            if (StateController.checkState("Battle phase")) {
                bottomCardBehaviourCall(card,a,b);
            } else if (StateController.checkState("Main phase")) {
                for(Integer K : a.cardsOnField.keySet()) {
                    Pane V = a.cardsOnField.get(K);
                    GameCard tempCard = (GameCard) a.cardsOnFieldInfo.get(K).getKey();
                    if (V == card && !(tempCard instanceof SkillGameCard)) {
                        a.switchCardMode(K);
                    }
                }
            }
        });
    }

    private static void bottomCardBehaviourCall(Pane card, Player a, Player b) {
        for(Integer K : a.cardsOnField.keySet()) {
            Pane V = a.cardsOnField.get(K);
            
            if (V == card && !a.cardsOnFieldInfo.get(K).getValue()) {
                if (b.cardsOnField.isEmpty()) {
                    CharacterGameCard c = (CharacterGameCard) a.cardsOnFieldInfo.get(K).getKey();
                    StateController.updateTargetAttack(K);
                    System.out.println(K);
                    HealthModel.updateAttack(a,b,0,c.getAttack(),false);
                } else if (K != StateModel.getTargetAttack()) {
                    StateController.updateTargetAttack(K);
                    card.setEffect(Basic.getShadow(Color.RED, 30));
                } else if (K != StateModel.getTargetSkill() && K != StateModel.getTargetAttack()) {
                    StateController.updateTargetSkill(K);
                    card.setEffect(Basic.getShadow(Color.BLUE, 30));
                } else if (K == StateModel.getTargetAttack()){
                    StateController.updateState("Release attack card");
                }  else if (K == StateModel.getTargetSkill()){
                    StateController.updateState("Release skill card");
                }
                break;
            }
        }
    }

    public static void setTopCardBehaviour(Pane card, Player a, Player b) {
        card.setOnMouseClicked(e -> {
            if (StateController.checkState("Battle phase")) {
                topCardBehaviourCall(card,a,b);
            }
        });
    }

    private static void topCardBehaviourCall(Pane card, Player a, Player b) {
        for (Integer K: a.cardsOnField.keySet()) {
            Pane V = a.cardsOnField.get(K);
            if (StateController.checkState("Attack card selected") && V == card) {
                CharacterGameCard attacker = (CharacterGameCard) b.cardsOnFieldInfo.get(StateModel.getTargetAttack()).getKey();
                CharacterGameCard enemy = (CharacterGameCard) a.cardsOnFieldInfo.get(K).getKey();
                if (!a.cardsOnFieldInfo.get(K).getValue() && attacker.getAttack() >= enemy.getAttack()) {
                    HealthModel.updateAttack(b,a,K,attacker.getAttack()-enemy.getAttack(),true);
                } else if (a.cardsOnFieldInfo.get(K).getValue() && attacker.getAttack() > enemy.getDefense()) {
                    HealthModel.updateAttack(b,a,K,0,true);
                }
                break;
            } else if (StateController.checkState("Skill card selected") && V == card) {
                SkillGameCard skill = (SkillGameCard) b.cardsOnFieldInfo.get(StateModel.getTargetSkill()).getKey();
                CharacterGameCard target;
                if (a.cardsOnFieldInfo.get(K) != null) target = (CharacterGameCard) (a.cardsOnFieldInfo.get(K).getKey());
                else target = (CharacterGameCard) (b.cardsOnFieldInfo.get(K).getKey());
                if (skill instanceof AuraSkillGameCard) target.addAuraSkill((AuraSkillGameCard)skill);
                else if (skill instanceof DestroySkillGameCard) {
                    HealthModel.updateAttack(b,a,K,0,true);
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
}
