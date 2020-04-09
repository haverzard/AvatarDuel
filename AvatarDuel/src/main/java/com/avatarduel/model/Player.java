package com.avatarduel.model;

import com.avatarduel.card.GameCard;
import com.avatarduel.card.HasCostAttribute;
import com.avatarduel.card.LandGameCard;
import com.avatarduel.deck.*;
import javafx.scene.layout.Pane;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Player {
    private static int counts = 0;
    public static Player player1;
    public static Player player2;

    // Class attribute
    private PlayerDeck myDeck;
    private int id;
    private String name;
    private ArrayList<GameCard> hand;
    public Map<Integer, Pane> cardsOnField;
    public Map<Integer, Pair<GameCard,Boolean>> cardsOnFieldInfo;
    public Map<Element, Pair<Integer,Integer>> power; // Using pair to store available power and their maximum.
    private int health;

    // To limit the instance
    private Player() {
        counts++;
        id = counts;
        name = "";
        myDeck = new PlayerDeck(new ArrayList<>());
        cardsOnField = new HashMap<>();
        cardsOnFieldInfo = new HashMap<>();
        hand = new ArrayList<>();
        power = new HashMap<>();
        // Just add new element and it will handle that automatically
        Arrays.asList(Element.values()).forEach(v->{
            power.put(v, new Pair<>(0, 0));
        });
        health = 80;
    }

    // To limit the instance for only 2
    public static void getPlayers() {
        if (counts < 2) {
            player1 = new Player();
            player2 = new Player();
        }
    }

    public static void resetPlayers() {
        counts = 0;
        player1 = new Player();
        player2 = new Player();
    }

    // Getter & Setter
    public int getHealth() {
        return health;
    }

    public void setHealth(int _health) {
        health = _health;
    }

    public int getId() {
        return id;
    }

    public String getName() { return name; }

    public void setName(String _name) { name = _name; }

    public GameCard getHand(int idx) {
        if (idx < hand.size()) return hand.get(idx);
        return null;
    }

    public void setHand(int idx, GameCard x) {
        if (hand.size() > idx) {
            hand.set(idx, null);
        }
    }

    public void removeHand(int idx) {
        if (hand.size() > idx) {
            hand.remove(idx);
        }
    }

    public void removeHand(GameCard card) {
        if (hand.contains(card)) {
            hand.remove(card);
        }
    }

    public void addToDeck(GameCard x) {
        myDeck.add(x);
    }

    public int countCardsInDeck() {
        return myDeck.getSize();
    }

    public int countCardsInHand() {
        return hand.size();
    }
    
    public void shuffleDeck() {
        myDeck.shuffle();
    }

    public void resetPower() {
        power.forEach((e,p)->{
            power.put(e, new Pair<>(p.getValue(),p.getValue()));
        });
    }

    public void addPower(Element x) {
        Pair<Integer,Integer> temp = power.get(x);
        power.put(x,new Pair<>(temp.getValue()+1,temp.getValue()+1));
    }

    public void useLand(int idx) {
        if (hand.get(idx) instanceof LandGameCard){
            addPower(hand.get(idx).getElement());
            hand.set(idx,null);
        }
    }

    public Element takeCard() {
        GameCard temp = myDeck.pop();
        if (temp != null) {
            hand.add(temp);
            return temp.getElement();
        }
        return null;
    }

    // Use Skill or Character Card
    public boolean useCard(HasCostAttribute card, Element e) {
        Pair<Integer,Integer> t = power.get(e);
        if (t.getKey() > card.getCost() && hand.contains(card)) {
            power.put(e,new Pair<>(t.getKey()-card.getCost(),t.getValue()));
            return true;
        }
        return false;
    }

    public void switchCardMode(int idx) {
        if (cardsOnFieldInfo.get(idx) != null) {
            if (cardsOnFieldInfo.get(idx).getValue()) {
                cardsOnField.get(idx).setRotate(0);
            } else {
                cardsOnField.get(idx).setRotate(90);
            }
            cardsOnFieldInfo.put(idx, new Pair<>(cardsOnFieldInfo.get(idx).getKey(), !cardsOnFieldInfo.get(idx).getValue()));
        }
    }

    public void refreshHand() {
        while (hand.contains(null)) hand.remove(null);
    }
}
