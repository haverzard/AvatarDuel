package com.avatarduel.view;

import com.avatarduel.element.Element;
import com.avatarduel.player.Player;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.util.Pair;

import java.util.HashMap;
import java.util.Map;

public class PowerView {
    private static Map<Element, HBox> powerCountersTop = new HashMap<>();
    private static Map<Element, HBox> powerCountersBottom = new HashMap<>();

    public static void init() {
        updatePowerCounters("bottom", Player.player1);
    }

    public static void updatePowerCounters(String type, Player p) {
        Map<Element,HBox> powerCounters = getPowerCounters(type);
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

    public static Map<Element, HBox> getPowerCounters(String type) {
        if (type.equals("top")) {
            return powerCountersTop;
        } else {
            return powerCountersBottom;
        }
    }

    public static void addToCounters(String type, Element e, HBox b) {
        Map<Element, HBox> powerCounters = getPowerCounters(type);
        powerCounters.put(e,b);
    }
}
