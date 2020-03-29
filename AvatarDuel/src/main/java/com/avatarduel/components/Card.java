package com.avatarduel.components;

import javafx.geometry.Pos;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

public class Card {
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

        HBox closedCard = new HBox();
        closedCard.setMinWidth(width);
        closedCard.setMinHeight(height);
        closedCard.setAlignment(Pos.CENTER);
        closedCard.setBackground(Basic.getBackground(Color.ORANGE));
        StackPane stack = new StackPane();
        stack.getChildren().add(inside3);
        stack.getChildren().add(inside2);
        stack.getChildren().add(inside);
        closedCard.getChildren().add(stack);

        return closedCard;
    }

    public static BorderPane getOpenCard(double width, boolean isEmpty) {
        double height = width/5*8;

        // Card Top
        BorderPane titleInside = new BorderPane();
        titleInside.setMinWidth(width*0.8);

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

        // Card Middle
        HBox image = new HBox();
        image.setMinWidth(width*0.8);
        image.setBorder(Basic.getBorder(1));

        HBox cardMiddle = new HBox();
        cardMiddle.setMinHeight(height/2);
        cardMiddle.setAlignment(Pos.CENTER);
        cardMiddle.getChildren().add(image);

        // Card Bottom
        HBox description = new HBox();
        description.setMinHeight(height*7/40);
        description.setBorder(Basic.getBorder(1,1,0,1));

        HBox attr = new HBox();
        attr.setMinHeight(height*3/40);
        attr.setBorder(Basic.getBorder(1));

        BorderPane bottomBar = new BorderPane();
        bottomBar.setMinWidth(width*21/25);
        bottomBar.setMaxHeight(height/4);
        bottomBar.setTop(description);
        bottomBar.setBottom(attr);

        HBox cardBottom = new HBox();
        cardBottom.setMinHeight(height*135/400);
        cardBottom.setAlignment(Pos.CENTER);
        cardBottom.getChildren().add(bottomBar);

        // Card Layout
        BorderPane openCard = new BorderPane();
        openCard.setMinWidth(width);
        openCard.setMaxHeight(height);
        openCard.setBorder(Basic.getBorder(1));
        openCard.setTop(cardTop);
        openCard.setCenter(cardMiddle);
        openCard.setBottom(cardBottom);

        if (!isEmpty) {
            Text title = new Text("hehe");
            title.setTextAlignment(TextAlignment.CENTER);
            Text element = new Text("hehe");
            element.setTextAlignment(TextAlignment.CENTER);
            titleInside.setLeft(title);
            titleInside.setRight(element);
        }

        return openCard;
    }
}
