package com.avatarduel.view;

import com.avatarduel.card.*;
import com.avatarduel.components.Basic;
import com.avatarduel.components.Space;
import com.avatarduel.model.Element;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * DescCardView describes the card description view that extends BorderPane class.
 * 
 * @author Kelompok 2
 */
public class DescCardView extends BorderPane {
    private static double cardWidth = 250;

    /**
     * Create a description box on card view.
     */
    public DescCardView() {
        super();
        setMinWidth(cardWidth);
        setMaxHeight(cardWidth/5*8);
        setBorder(Basic.getBorder(1));
        setBackground(Basic.getBackground(Element.AIR.getCardTemplateURL(),cardWidth,cardWidth/5*8));
    }

    /**
     * Fill the description box based on the GameCard used.
     * 
     * @param x the GameCard that is used to make the description.
     */
    public void updateCardDesc(GameCard x) {
        getChildren().clear();
        setBackground(Basic.getBackground(Element.AIR.getCardTemplateURL()));
        if (x != null) {
            setBackground(Basic.getBackground(x.getElement().getCardTemplateURL()));
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
            setCenter(store);
        }
    }
}
