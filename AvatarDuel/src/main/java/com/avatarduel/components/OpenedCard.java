package com.avatarduel.components;

import com.avatarduel.card.*;
import com.avatarduel.model.Element;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class OpenedCard extends BorderPane {

    /**
     * Create a new Opened Card
     * @param width Minimum width value
     */
    private OpenedCard(double width) {
        super();
        double height = width/5*8;

        // Card Layout
        setMinWidth(width);
        setMaxHeight(height);
        setBorder(Basic.getBorder(1));
        update(width, null);
    }

    /**
     * Create a default new Opened Card
     */
    public OpenedCard() {
        super();
    }

    /**
     * Create a new Opened Card
     * @param width Minimum width value
     * @param x Element of the Card
     */
    public OpenedCard(double width, Element x) {
        this(width);
        setBackground(Basic.getBackground(x.getCardTemplateURL(), width, width/5*8));
    }

    /**
     * Get the top part of the Card
     * @param width width raw value
     * @param height height raw value
     * 
     * @return The top part of the Card with width width raw value and height height raw value.
     */
    private HBox getCardTop(double width, double height) {
        return getCardTop(width, height, "", null);
    }

    /**
     * Get the top part of the card
     * @param width width raw value
     * @param height height raw value
     * @param title title value
     * @param x element value
     */
    private HBox getCardTop(double width, double height, String title, Element x) {
        BorderPane titleInside = new BorderPane();
        titleInside.setMinWidth(width*0.8);
        titleInside.setLeft(new Text(title));
        if (x != null)  {
            HBox element = new HBox();
            element.setMinSize(30,30);
            element.setBackground(x.getBackground());
            titleInside.setRight(element);
        }

        HBox titleBar = new HBox();
        titleBar.setMinWidth(width*22/25);
        titleBar.setMaxHeight(height*35/400);
        titleBar.setAlignment(Pos.CENTER);
        titleBar.setBorder(Basic.getBorder(1));
        if (!title.isEmpty())
            titleBar.getChildren().add(titleInside);

        HBox cardTop = new HBox();
        cardTop.setMinHeight(height*65/400);
        cardTop.setAlignment(Pos.CENTER);
        cardTop.getChildren().add(titleBar);

        return cardTop;
    }

    /**
     * Get a simple middle-part of the card
     * @param width raw width value
     * @param height raw height value
     */
    private HBox getCardMiddle(double width, double height) {
        return getCardMiddle(width, height, null);
    }

    /**
     * Get a simple middle-part of the card
     * @param width raw width value
     * @param height raw height value
     * @param x value of card
     */
    private HBox getCardMiddle(double width, double height, GameCard x) {

        VBox imageBox = new VBox();
        imageBox.setAlignment(Pos.CENTER_RIGHT);
        imageBox.setMinWidth(width*0.8);
        try {
            if (x != null) {
                ImageView image = new ImageView();
                image.setFitWidth(width * 0.8);
                image.setFitHeight(height / 2);
                image.setImage(new Image(x.getImgUrl()));
                imageBox.getChildren().add(new Label(String.format("[%s]",x.getType())));
                imageBox.getChildren().add(image);
            } else {
                imageBox.setBorder(Basic.getBorder(1));
            }
            //System.out.println(imgUrl);
        } catch (Exception e) {
            assert(false);
        }

        HBox cardMiddle = new HBox();
        cardMiddle.setMinHeight(height/2);
        cardMiddle.setAlignment(Pos.CENTER);
        cardMiddle.getChildren().add(imageBox);

        return cardMiddle;
    }

    /**
     * Get bottom-part of the card 
     * @param width raw width value
     * @param height raw height value
     */
    private HBox getCardBottom(double width, double height) {
        return getCardBottom(width, height, "", null);
    }

    /**
     * Get bottom-part of the card
     * @param width raw width value
     * @param height raw height value  
     * @param desc description value of the card
     * @param card card value
     */
    private HBox getCardBottom(double width, double height, String desc, GameCard card) {
        HBox description = new HBox();
        description.setMaxWidth(width*21/25);
        description.setMinHeight(height*7/40);
        Label label = new Label(desc);
        description.getChildren().add(label);
        label.setWrapText(true);
        label.setFont(new Font(10));

        BorderPane bottomBar = new BorderPane();
        bottomBar.setMinWidth(width*21/25);
        bottomBar.setMaxWidth(width*21/25);
        bottomBar.setMaxHeight(height/4);
        if (!desc.isEmpty())
            bottomBar.setTop(description);
        bottomBar.setBorder(Basic.getBorder(1));

        if (card instanceof HasCostAttribute) {
            HBox attr = new HBox();
            attr.setMinHeight(height * 3 / 40);
            attr.setBorder(Basic.getBorder(1,0, 0,0));
            bottomBar.setBottom(attr);
            BorderPane attrBox = new BorderPane();
            attrBox.setMinWidth(width*21/25);
            attr.getChildren().add(attrBox);
            HBox store = new HBox();
            store.setAlignment(Pos.CENTER_RIGHT);
            HBox store2 = new HBox();
            store2.setAlignment(Pos.CENTER_LEFT);
            attrBox.setLeft(store2);
            attrBox.setRight(store);
            if (card instanceof CharacterGameCard) {
                CharacterGameCard temp = (CharacterGameCard) card;
                Label attack = new Label("ATT/"+temp.getAttack());
                Label defense = new Label("DEF/"+temp.getDefense());
                Label cost = new Label("POW/"+temp.getCost());
                attack.setMinWidth(50);
                defense.setMinWidth(50);
                defense.setBorder(Basic.getBorder(0,0,0,1));
                cost.setMinWidth(50);
                cost.setBorder(Basic.getBorder(0,0,0,1));
                store.getChildren().add(attack);
                store.getChildren().add(defense);
                store.getChildren().add(cost);
            } else {
                if (card instanceof AuraSkillGameCard) {
                    AuraSkillGameCard temp = (AuraSkillGameCard) card;
                    Label attack = new Label(((temp.getAttackAura() >= 0) ? "+" : "")+temp.getAttackAura()+" ATT");
                    Label defense = new Label(((temp.getDefenseAura() >= 0) ? "+" : "")+temp.getDefenseAura()+" DEF");
                    Label cost = new Label("POW/"+temp.getCost());
                    attack.setMinWidth(50);
                    attack.setAlignment(Pos.CENTER_LEFT);
                    defense.setMinWidth(50);
                    defense.setAlignment(Pos.CENTER_LEFT);
                    defense.setBorder(Basic.getBorder(0,0,0,1));
                    cost.setMinWidth(50);
                    store2.getChildren().add(attack);
                    store2.getChildren().add(defense);
                    store.getChildren().add(cost);
                } else {
                    HasCostAttribute temp = (HasCostAttribute) card;
                    Label cost = new Label("POW/"+temp.getCost());
                    cost.setMinWidth(50);
                    store.getChildren().add(cost);
                }
            }
        }

        HBox cardBottom = new HBox();
        cardBottom.setMinHeight(height*135/400);
        cardBottom.setAlignment(Pos.CENTER);
        cardBottom.getChildren().add(bottomBar);

        return cardBottom;
    }

    /**
     * Update a card
     * @param width value of width
     * @param height value of height
     * @param x Card's element
     */
    public void update(double width, double height, Element x) {
        update(width, null);
        setBackground(Basic.getBackground(x.getCardTemplateURL(), width, height));
    }

    /**
     * Update a card
     * @param width raw width value
     * @param x Card's value
     */
    public void update(double width, GameCard x) {
        double height = width/5*8;
        HBox cardTop;
        HBox cardMiddle;
        HBox cardBottom;
        if (x != null) {
            // Card Top
            cardTop = getCardTop(width, height, x.getName(), x.getElement());
            // Card Middle
            cardMiddle = getCardMiddle(width, height, x);
            // Card Bottom
            cardBottom = getCardBottom(width, height, x.getDesc(), x);

            setBackground(Basic.getBackground(x.getElement().getCardTemplateURL(), width, height));
        } else {
            // Card Top
            cardTop = getCardTop(width, height);
            // Card Middle
            cardMiddle = getCardMiddle(width, height);
            // Card Bottom
            cardBottom = getCardBottom(width, height);
            setBackground(Basic.getBackground(Color.WHITE));
        }
        // Card Layout
        setTop(cardTop);
        setCenter(cardMiddle);
        setBottom(cardBottom);
    }
}
