package com.avatarduel.controller;

import com.avatarduel.card.*;
import com.avatarduel.components.Basic;
import com.avatarduel.components.OpenedCard;
import com.avatarduel.components.Space;
import com.avatarduel.model.Element;
import com.avatarduel.model.Player;
import com.avatarduel.view.DescCardView;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.List;

/**
 * Represent the Card Controller for MVC pattern in AvatarDuel
 */
public class CardController {
    private static double cardWidth = 250;
    private List<Pane> cardsBottom;
    private OpenedCard cardInfo;
    private DescCardView cardDesc;

    /**
     * Creates a new card controller
     */
    public CardController() {
        Player.getPlayers();
        cardInfo = new OpenedCard(cardWidth, Element.AIR);
        cardsBottom = new ArrayList<>();
        cardDesc = new DescCardView();
    }

    /**
     * Clear card's info
     */
    public void clearInfo() {
        cardInfo.update(cardWidth,cardWidth/5*8, Element.AIR);
    }

    /**
     * Reset all cards in CardsBottom
     */
    public void resetCardsBottom() {
        for (Pane pane : cardsBottom) {
            pane.setEffect(null);
        }
        cardsBottom.clear();
    }

    /**
     * Get a card's info
     * @return card's info
     */
    public OpenedCard getCardInfo() {
        return cardInfo;
    }

    /**
     * Get a card's description
     * @return card's description
     */
    public BorderPane getCardDesc() { return cardDesc; }

    /**
     * Get all cards in CardsBottom
     * @return List of Pane from CardsBottom
     */
    public List<Pane> getCardsBottom() {
        return cardsBottom;
    }


    /**
     * Update Card Description from parameter
     * @param x new GameCard info
     */
    public void updateCardDesc(GameCard x) {
        cardDesc.getChildren().clear();
        cardDesc.setBackground(Basic.getBackground(Element.AIR.getCardTemplateURL()));
        if (x != null) {
            cardDesc.setBackground(Basic.getBackground(x.getElement().getCardTemplateURL()));
            HBox store = new HBox();
            store.setAlignment(Pos.CENTER);
            BorderPane layout = new BorderPane();
            layout.setMinWidth(cardWidth * 0.9);
            layout.setMaxWidth(cardWidth * 0.9);
            layout.setMaxHeight(cardWidth/5*8 * 0.9);

            // Desc field
            VBox desc = new VBox();
            desc.getChildren().add(new Label("Description"));
            Label label = new Label(x.getDesc());
            label.setWrapText(true);
            desc.getChildren().add(label);
            layout.setTop(desc);

            // Skill field
            if (x instanceof CharacterGameCard) {
                VBox skill = new VBox();
                skill.getChildren().add(new Space(50));
                skill.getChildren().add(new Label("Current skill attached"));
                // Iterate skill here plz
                CharacterGameCard card = (CharacterGameCard) x;
                if (!card.getAuraSkillGameCardsList().isEmpty() || card.isAttachedPowerUpinField()) {
                    if (card.isAttachedPowerUpinField()) {
                        PowerUpSkillGameCard temp = card.getPowerUpSkillGameCard();
                        skill.getChildren().add(new Label("- " + temp.getName() + " (Power Up)"));
                    }
                    card.getAuraSkillGameCardsList().forEach(v ->
                            skill.getChildren().add(new Label("- "+v.getName()+" (Aura) - "
                                    +" ATT: "+(v.getAttackAura() >= 0 ? "+" : "")+v.getAttackAura()
                                    +" DEF: "+(v.getDefenseAura() >= 0 ? "+" : "")+v.getDefenseAura())));
                } else {
                    skill.getChildren().add(new Label("None"));
                }
                layout.setCenter(skill);
            }

            // Skill type
            if (x instanceof SkillGameCard) {
                String type;
                if (x instanceof PowerUpSkillGameCard) {
                    type = "power up";
                } else if (x instanceof AuraSkillGameCard) {
                    type = "aura";
                } else {
                    type = "destroy";
                }
                VBox skill = new VBox();
                skill.getChildren().add(new Space(50));
                skill.getChildren().add(new Label("Skill type - " + type));
                layout.setCenter(skill);
            }

            store.getChildren().add(layout);
            cardDesc.setCenter(store);
        }
    }

    /**
     * Showing card info on details when pointer is hovering above the card
     * @param card Card's pane
     * @param p Player 
     * @param handIndex Index of card in hand
     */
    public void showInfoOnHover(Pane card, Player p, int handIndex) {
        card.setOnMouseEntered(e2 -> {
            cardInfo.update(250, p.getHand(handIndex));
            cardDesc.updateCardDesc(p.getHand(handIndex));
        });
    }

    /**
     * Showing card info on details when pointer is hovering above the card
     * @param card Card's pane
     * @param x GameCard on field
     */
    public void showInfoOnHover(Pane card, GameCard x) {
        card.setOnMouseEntered(e2 -> {
            cardInfo.update(250, x);
            cardDesc.updateCardDesc(x);
        });
    }
}
