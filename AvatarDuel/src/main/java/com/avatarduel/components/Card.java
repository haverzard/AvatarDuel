package com.avatarduel.components;

import com.avatarduel.card.GameCard;
import com.avatarduel.element.Element;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

public class Card {
    private static Color WATER = Color.AQUA;
    private static Color FIRE = Color.RED;
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
        return getCardBottom(width, height, "");
    }

    public static HBox getCardBottom(double width, double height, String desc) {
        HBox description = new HBox();
        description.setMaxWidth(width*21/25);
        description.setMinHeight(height*7/40);
        description.setBorder(Basic.getBorder(1,1,0,1));
        Label label = new Label(desc);
        description.getChildren().add(label);
        label.setWrapText(true);

        HBox attr = new HBox();
        attr.setMinHeight(height*3/40);
        attr.setBorder(Basic.getBorder(1));

        BorderPane bottomBar = new BorderPane();
        bottomBar.setMinWidth(width*21/25);
        bottomBar.setMaxWidth(width*21/25);
        bottomBar.setMaxHeight(height/4);
        bottomBar.setTop(description);
        bottomBar.setBottom(attr);

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

    public static BorderPane getOpenCard(double width, Element x) {
        BorderPane openCard = getOpenCard(width);
        switch(x) {
            case AIR:
                openCard.setBackground(Basic.getBackground(AIR));
                break;
            case WATER:
                openCard.setBackground(Basic.getBackground(WATER));
                break;
            case FIRE:
                openCard.setBackground(Basic.getBackground(FIRE));
                break;
            case EARTH:
                openCard.setBackground(Basic.getBackground(EARTH));
                break;
        }
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
            cardBottom = getCardBottom(width, height, x.getDesc());

            switch(x.getElement()) {
                case AIR:
                    card.setBackground(Basic.getBackground(AIR));
                    break;
                case WATER:
                    card.setBackground(Basic.getBackground(WATER));
                    break;
                case FIRE:
                    card.setBackground(Basic.getBackground(FIRE));
                    break;
                case EARTH:
                    card.setBackground(Basic.getBackground(EARTH));
                    break;
            }
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
