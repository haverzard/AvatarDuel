package com.avatarduel.controller;

import com.avatarduel.card.CharacterGameCard;
import com.avatarduel.card.GameCard;
import com.avatarduel.card.HasCostAttribute;
import com.avatarduel.model.StateModel;
import com.avatarduel.player.Player;
import com.avatarduel.view.CardView;
import com.avatarduel.view.FieldView;
import com.avatarduel.view.HandView;
import com.avatarduel.view.PowerView;
import javafx.util.Pair;

public class FieldController {
    public static void setFieldBoxOnClickEvent(int idx, Player p1) {
        int j = idx - 16;
        FieldView.getBox(idx).setOnMouseClicked(e -> {
            int target = StateModel.getTarget();
            if (target >= 0) {
                GameCard card = p1.getHand(target);
                if ((StateController.checkState("Main phase")) && StateController.checkState("Card selected")
                        && (j < 8 && card instanceof CharacterGameCard || j > 8)
                        && p1.useCard((HasCostAttribute) card, card.getElement())) { // Pre-condition that the card is character/skill
                    if (p1.cardsOnField.get(j) == null && !p1.cardsOnField.containsValue(CardView.cardsBottom.get(target))) {
                        FieldView.getBox(j + 16).getChildren().add(CardView.cardsBottom.get(target));
                        FieldView.getBox(j + 16).setOnMouseClicked(null);
                        FieldView.initFieldCard(p1, CardView.cardsBottom.get(target));
                        p1.cardsOnField.put(j, CardView.cardsBottom.get(target));
                        p1.cardsOnFieldInfo.put(j, new Pair<>(p1.getHand(target), false));
                        CardController.showInfoOnHover(CardView.cardsBottom.get(target), p1.cardsOnFieldInfo.get(j).getKey());
                        p1.setHand(target, null);
                        HandView.removeFromHand();
                        PowerView.updatePowerCounters("bottom", p1);
                    }
                }
            }
        });
    }
}
