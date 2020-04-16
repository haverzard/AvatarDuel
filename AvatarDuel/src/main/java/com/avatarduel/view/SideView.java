package com.avatarduel.view;

import com.avatarduel.model.Loc;
import com.avatarduel.components.ClosedCard;
import com.avatarduel.components.Space;
import com.avatarduel.model.Element;
import com.avatarduel.model.Player;
import com.avatarduel.controller.ButtonController;
import javafx.geometry.Pos;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.util.Pair;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * SideView describes the attribute that the player currently have (powerCounters and deckCounter).
 * 
 * @author Kelompok 2
 */
public class SideView extends VBox {
    private Map<Element, HBox> powerCounters;
    private ButtonController buttonController;
    private HBox deckCounter;


    /**
     * Create the side attribute view on the GUI.
     * 
     * @param p The designated player.
     * @param type The location of the player on the field.
     * @param buttonController The ButtonController.
     */
    public SideView(Player p, Loc type, ButtonController buttonController) {
        super();
        powerCounters = new HashMap<>();
        this.buttonController = buttonController;

        deckCounter = new HBox();
        deckCounter.setAlignment(Pos.CENTER);

        setMinWidth(75);
        setMinHeight(350);
        if (type == Loc.TOP) {
            updateDeckCounter(p.countCardsInDeck());
            setAlignment(Pos.BOTTOM_CENTER);
            getChildren().add(deckCounter);
            getChildren().add(new ClosedCard(60));
            getChildren().add(new Space(10));
            Arrays.asList(Element.values()).forEach(v->{
                getChildren().add(genElementBox(v));
            });
        } else {
            updateDeckCounter(p.countCardsInDeck());
            setAlignment(Pos.TOP_CENTER);
            Arrays.asList(Element.values()).forEach(v->{
                getChildren().add(genElementBox(v));
            });
            getChildren().add(new Space(10));
            getChildren().add(buttonController.getDeckBtnView());
            getChildren().add(deckCounter);
        }
        updatePowerCounters(p);
    }

    private BorderPane genElementBox(Element x) {
        Text t1 = new Text("0");
        Text t2 = new Text("/");
        Text t3 = new Text("0");

        HBox powerCounter = new HBox();
        powerCounter.setMinHeight(30);
        powerCounter.setMinWidth(80);
        powerCounter.setAlignment(Pos.CENTER);
        powerCounter.getChildren().add(t1);
        powerCounter.getChildren().add(t2);
        powerCounter.getChildren().add(t3);
        addToCounters(x,powerCounter);

        HBox element = new HBox();
        element.setMinSize(30,30);
        element.setBackground(x.getBackground());

        BorderPane elementBox = new BorderPane();
        elementBox.setLeft(powerCounter);
        elementBox.setRight(element);

        return elementBox;
    }


    public HBox getDeckCounter() {
        return deckCounter;
    }

    public Map<Element, HBox> getPowerCounters() {
        return powerCounters;
    }

    public void updatePowerCounters(Player p) {
        powerCounters.forEach((e,b)->{
            Pair<Integer,Integer> x = p.power.get(e);
            Text t1 = new Text(Integer.toString(x.getKey()));
            Text t2 = new Text("/");
            Text t3 = new Text(Integer.toString(x.getValue()));

            b.getChildren().clear();
            b.getChildren().add(t1);
            b.getChildren().add(t2);
            b.getChildren().add(t3);
        });
    }

    public void addToCounters(Element e, HBox b) {
        powerCounters.put(e,b);
    }

    public void updateDeckCounter(int x) {
        Text deckCounts = new Text(Integer.toString(x));
        deckCounter.getChildren().clear();
        deckCounter.getChildren().add(deckCounts);
        deckCounter.getChildren().add(new Text(" / 60"));
    }
}
