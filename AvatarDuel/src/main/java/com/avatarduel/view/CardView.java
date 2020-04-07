package com.avatarduel.view;

import com.avatarduel.card.CharacterGameCard;
import com.avatarduel.card.GameCard;
import com.avatarduel.components.Basic;
import com.avatarduel.components.Card;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;

public class CardView {
    private static double cardWidth = 250;
    public static List<Pane> cardsBottom = new ArrayList<>();
    private static BorderPane cardInfo = Card.getOpenCard(cardWidth);
    private static BorderPane cardDesc = new BorderPane();

    public static void init() {
        cardDesc.setMinWidth(cardWidth);
        cardDesc.setMaxHeight(cardWidth/5*8);
        cardDesc.setBorder(Basic.getBorder(1));
    }

    public static void clearInfo() {
        Card.update(cardInfo,cardWidth,null);
    }

    public static void resetCardsBottom() {
        for (Pane pane : cardsBottom) {
            pane.setEffect(null);
        }
        cardsBottom.clear();
    }

    public static void updateCardDesc(GameCard x) {
        cardDesc.getChildren().clear();
        cardDesc.setBackground(Basic.getBackground(Color.WHITE));
        if (x != null) {
            cardDesc.setBackground(Basic.getBackground(Card.getCardColor(x.getElement())));
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
                skill.getChildren().add(Basic.getSpace(50));
                skill.getChildren().add(new Label("Current skill attached"));
                // Iterate skill here plz
                skill.getChildren().add(new Label("None"));
                layout.setCenter(skill);
            }

            store.getChildren().add(layout);
            cardDesc.setCenter(store);
        }
    }

    public static BorderPane getCardInfo() {
        return cardInfo;
    }

    public static BorderPane getCardDesc() { return cardDesc; }
}
