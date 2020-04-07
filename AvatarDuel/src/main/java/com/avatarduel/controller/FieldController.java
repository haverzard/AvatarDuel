package com.avatarduel.controller;

import com.avatarduel.card.AuraSkillGameCard;
import com.avatarduel.card.CharacterGameCard;
import com.avatarduel.card.GameCard;
import com.avatarduel.card.HasCostAttribute;
import com.avatarduel.model.FieldModel;
import com.avatarduel.model.StateModel;
import com.avatarduel.player.Player;
import com.avatarduel.view.CardView;
import com.avatarduel.view.FieldView;
import com.avatarduel.view.HandView;
import com.avatarduel.view.PowerView;
import javafx.scene.layout.Pane;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.List;

public class FieldController {
    public static void setFieldBoxOnClickEvent(int idx, Player p1) {
        int j = idx - 16;
        FieldView.getBox(idx).setOnMouseClicked(e -> {
            int target = StateModel.getTarget();
            if (target >= 0) {
                GameCard card = p1.getHand(target);
                if ((StateController.checkState("Main phase")) && StateController.checkState("Card selected")
                        && (j < 8 && card instanceof CharacterGameCard)
                        && p1.useCard((HasCostAttribute) card, card.getElement())) { // Pre-condition that the card is character
                    if (p1.cardsOnField.get(j) == null && !p1.cardsOnField.containsValue(CardView.cardsBottom.get(target))) {
                        FieldView.getBox(j + 16).getChildren().add(CardView.cardsBottom.get(target));
                        FieldView.getBox(j + 16).setOnMouseClicked(null);
                        FieldView.initFieldCardTop(p1, CardView.cardsBottom.get(target));
                        p1.cardsOnField.put(j, CardView.cardsBottom.get(target));
                        p1.cardsOnFieldInfo.put(j, new Pair<>(p1.getHand(target), false));
                        CardController.showInfoOnHover(CardView.cardsBottom.get(target), p1.cardsOnFieldInfo.get(j).getKey());
                        p1.setHand(target, null);
                        HandView.removeFromHand();
                        PowerView.updatePowerCounters("bottom", p1);
                        StateController.updateState("Release card");
                    }
                }
            }
        });
    }

    public static void addSkillLocToChar(Pane card, int loc){
        List<Integer> x = FieldModel.getCharacterSkillList().get(card);
        if (x == null) x = new ArrayList<>();
        x.add(loc);
        FieldModel.getCharacterSkillList().put(card, x);
    }

    public static void addSkillInfo(Pane card, int loc, int id){
        Pair<Integer,Integer> x = FieldModel.getSkillInfo().get(card);
        if (x == null) x = new Pair<>(loc,id);
        FieldModel.getSkillInfo().put(card, x);
    }

    public static void removeSkill(int loc, Pane card, Player a) {
        Pair<Integer,Integer> info = FieldModel.getSkillInfo().get(card);
        CharacterGameCard charCard = (CharacterGameCard) a.cardsOnFieldInfo.get(info.getKey()).getKey();
        charCard.removeAuraSkill((AuraSkillGameCard) a.cardsOnFieldInfo.get(loc).getKey());
        List<Integer> skills = FieldModel.getCharacterSkillList().get(info.getKey());
        skills.remove(loc);
        FieldModel.getSkillInfo().remove(card);
    }
}
