package com.avatarduel.components;

import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

public class ClosedCard extends HBox {

    // Width : Height = 5 : 8
    /**
     * Create a Closed card Object
     * @param width height and width raw input
     */
    public ClosedCard(double width) {
        super();
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

        setMinWidth(width);
        setMinHeight(height);
        setAlignment(Pos.CENTER);
        StackPane stack = new StackPane();
        stack.getChildren().add(inner);
        stack.getChildren().add(inside3);
        stack.getChildren().add(inside2);
        stack.getChildren().add(inside);
        getChildren().add(stack);
    }
}
