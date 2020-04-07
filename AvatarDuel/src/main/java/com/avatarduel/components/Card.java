package com.avatarduel.components;

import com.avatarduel.card.CharacterGameCard;
import com.avatarduel.card.GameCard;
import com.avatarduel.card.SkillGameCard;
import com.avatarduel.element.Element;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class Card {
    private static Color WATER = Color.AQUA;
    private static Color FIRE = Color.INDIANRED;
    private static Color EARTH = Color.LIME;
    private static Color AIR = Color.YELLOW;

    // Width : Height = 5 : 8
    public static HBox getClosedCard(double width) {
        double height = width/5*8;

        Rectangle inside = new Rectangle();
        inside.setWidth(width*0.75/2);
        inside.setHeight(width*0.75/2);
        inside.setRotate(45);
        inside.setFill(Color.LIGHTGOLDENRODYELLOW);

        Circle inside2 = new Circle();
        inside2.setRadius(width*0.75/2);
        inside2.setFill(Color.INDIANRED);

        Rectangle inside3 = new Rectangle();
        inside3.setWidth(width*0.9);
        inside3.setHeight(height-width*0.1);
        inside3.setFill(Color.LIGHTGOLDENRODYELLOW);

        Rectangle inner = new Rectangle();
        inner.setWidth(width);
        inner.setHeight(height);
        inner.setFill(Color.ORANGE);

        HBox closedCard = new HBox();
        closedCard.setMinWidth(width);
        closedCard.setMinHeight(height);
        closedCard.setAlignment(Pos.CENTER);
        StackPane stack = new StackPane();
        stack.getChildren().add(inner);
        stack.getChildren().add(inside3);
        stack.getChildren().add(inside2);
        stack.getChildren().add(inside);
        closedCard.getChildren().add(stack);

        return closedCard;
    }

    public static HBox getCardTop(double width, double height) {
        return getCardTop(width, height, "", null);
    }


    public static HBox getCardTop(double width, double height, String title, Element x) {
        BorderPane titleInside = new BorderPane();
        titleInside.setMinWidth(width*0.8);
        titleInside.setLeft(new Text(title));
        //if (x != null)  titleInside.setRight(new Text(title));

        HBox titleBar = new HBox();
        titleBar.setMinWidth(width*22/25);
        titleBar.setMaxHeight(height*35/400);
        titleBar.setAlignment(Pos.CENTER);
        titleBar.setBorder(Basic.getBorder(1));
        titleBar.getChildren().add(titleInside);

        HBox cardTop = new HBox();
        cardTop.setMinHeight(height*65/400);
        cardTop.setAlignment(Pos.CENTER);
        cardTop.getChildren().add(titleBar);

        return cardTop;
    }

    public static HBox getCardMiddle(double width, double height) {
        return getCardMiddle(width, height, "");
    }

    public static HBox getCardMiddle(double width, double height, String imgUrl) {

        HBox imageBox = new HBox();
        imageBox.setMinWidth(width*0.8);
        imageBox.setBorder(Basic.getBorder(1));
        try {
            if (!imgUrl.isEmpty()) {
                ImageView image = new ImageView();
                image.setFitWidth(width * 0.8);
                image.setFitHeight(height / 2);
                image.setImage(new Image(imgUrl));
                imageBox.getChildren().add(image);
            }
            //System.out.println(imgUrl);
        } catch(Exception e) {
            //System.out.println(e);
        }

        HBox cardMiddle = new HBox();
        cardMiddle.setMinHeight(height/2);
        cardMiddle.setAlignment(Pos.CENTER);
        cardMiddle.getChildren().add(imageBox);

        return cardMiddle;
    }

    public static HBox getCardBottom(double width, double height) {
        return getCardBottom(width, height, "", null);
    }

    public static HBox getCardBottom(double width, double height, String desc, GameCard card) {
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
        bottomBar.setTop(description);
        bottomBar.setBorder(Basic.getBorder(1));

        if (card instanceof CharacterGameCard || card instanceof SkillGameCard) {
            HBox attr = new HBox();
            attr.setMinHeight(height * 3 / 40);
            attr.setBorder(Basic.getBorder(1,0, 0,0));
            bottomBar.setBottom(attr);
            BorderPane attrBox = new BorderPane();
            attrBox.setMinWidth(width*21/25);
            attr.getChildren().add(attrBox);
            if (card instanceof CharacterGameCard) {
                CharacterGameCard temp = (CharacterGameCard) card;
                HBox store = new HBox();
                store.setAlignment(Pos.CENTER_RIGHT);
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
                attrBox.setLeft(Basic.getSpace(10));
                attrBox.setRight(store);
            }
        }

        HBox cardBottom = new HBox();
        cardBottom.setMinHeight(height*135/400);
        cardBottom.setAlignment(Pos.CENTER);
        cardBottom.getChildren().add(bottomBar);

        return cardBottom;
    }

    public static BorderPane getOpenCard(double width) {
        double height = width/5*8;


        // Card Layout
        BorderPane openCard = new BorderPane();
        openCard.setMinWidth(width);
        openCard.setMaxHeight(height);
        openCard.setBorder(Basic.getBorder(1));
        update(openCard, width, null);

        return openCard;
    }

    public static Color getCardColor(Element x) {
        switch(x) {
            case AIR:
                return AIR;
            case WATER:
                return WATER;
            case FIRE:
                return FIRE;
            case EARTH:
                return EARTH;
            default:
                return Color.WHITE;
        }
    }

    public static BorderPane getOpenCard(double width, Element x) {
        BorderPane openCard = getOpenCard(width);
        openCard.setBackground(Basic.getBackground(getCardColor(x)));
        return openCard;
    }

    public static void update(BorderPane card, double width, GameCard x) {
        double height = width/5*8;
        HBox cardTop;
        HBox cardMiddle;
        HBox cardBottom;
        if (x != null) {
            // Card Top
            cardTop = getCardTop(width, height, x.getName(), x.getElement());
            // Card Middle
            cardMiddle = getCardMiddle(width, height, x.getImgUrl());
            // Card Bottom
            cardBottom = getCardBottom(width, height, x.getDesc(), x);

            card.setBackground(Basic.getBackground(getCardColor(x.getElement())));
        } else {
            // Card Top
            cardTop = getCardTop(width, height);
            // Card Middle
            cardMiddle = getCardMiddle(width, height);
            // Card Bottom
            cardBottom = getCardBottom(width, height);
            card.setBackground(Basic.getBackground(Color.WHITE));
        }
        // Card Layout
        card.setTop(cardTop);
        card.setCenter(cardMiddle);
        card.setBottom(cardBottom);
    }
}
