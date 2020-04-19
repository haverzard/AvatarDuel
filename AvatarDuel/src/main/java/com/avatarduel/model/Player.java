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

/**
 * Represents the data of Players who play the game
 * 
 * @author Kelompok 2
 */
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
    /**
     * Create a new default Player object.
     */
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
    /**
     * Creates only 2 players in the game
     */
    public static void getPlayers() {
        if (counts < 2) {
            player1 = new Player();
            player2 = new Player();
        }
    }

    /**
     * Reset all players to new players
     */
    public static void resetPlayers() {
        counts = 0;
        player1 = new Player();
        player2 = new Player();
    }

    // Getter & Setter


    /**
     * Get the value health attribute in the class.
     *
     * @return The Health value of this Player.
     */
    public int getHealth() {
        return health;
    }

    /**
     * Set the value of health attribute in the class.
     * @param _health new health's value
     */
    public void setHealth(int _health) {
        health = _health;
    }

    /**
     * Get the value of id attribute in the class
     * 
     * @return The ID Number of this Player
     */
    public int getId() {
        return id;
    }

    /**
     * Get the value of name attribute in the class
     * 
     * @return The Name attribute of this Player.
     */
    public String getName() { return name; }

    /**
     * Set the value of name attribute in the class
     * @param _name new name's value
     */
    public void setName(String _name) { name = _name; }

    /**
     * Access the card in player's hand by index
     * @param idx to be accessed index
     * 
     * @return The idx-th Card in the Player hand (the idx card in the hand list of cards).
     */
    public GameCard getHand(int idx) {
        if (idx < hand.size()) return hand.get(idx);
        return null;
    }

    /**
     * Set the value of chosen index in hand with a new card
     * @param idx to be accessed index
     * @param x to be inserted card
     */
    public void setHand(int idx, GameCard x) {
        if (hand.size() > idx) {
            hand.set(idx, null);
        }
    }

    /**
     * Remove the chosen index card in hand
     * @param idx to be accessed index
     */
    public void removeHand(int idx) {
        if (hand.size() > idx) {
            hand.remove(idx);
        }
    }

    /**
     * Remove the chosen card from hand
     * @param card to be removed card
     */
    public void removeHand(GameCard card) {
        if (hand.contains(card)) {
            hand.remove(card);
        }
    }

    /**
     * Add a new card to deck
     * @param x to be inserted card
     */
    public void addToDeck(GameCard x) {
        myDeck.add(x);
    }

    /**
     * Get the amount of cards in deck
     * 
     * @return The number of deck card of this Player
     */
    public int countCardsInDeck() {
        return myDeck.getSize();
    }

    /**
     * Get the amount of cards in player's hand
     * 
     * @return The number of cards in player's hand
     */
    public int countCardsInHand() {
        return hand.size();
    }
    
    /**
     * Shuffles the deck
     */
    public void shuffleDeck() {
        myDeck.shuffle();
    }

    /**
     * Reset player's power stats
     */
    public void resetPower() {
        power.forEach((e,p)->{
            power.put(e, new Pair<>(p.getValue(),p.getValue()));
        });
    }

    /**
     * Add the value of the chosen element maximum stats
     * @param x type of element
     */
    public void addPower(Element x) {
        Pair<Integer,Integer> temp = power.get(x);
        power.put(x,new Pair<>(temp.getValue()+1,temp.getValue()+1));
    }

    /**
     * Add new max stats of the chosen Land element
     * @param idx to be accessed index
     */
    public void useLand(int idx) {
        if (hand.get(idx) instanceof LandGameCard){
            addPower(hand.get(idx).getElement());
            hand.set(idx,null);
        }
    }

    /**
     * Take the card from deck to Player hand and get the element of the card was taken
     * 
     * @return The type of element that was taken
     */
    public Element takeCard() {
        GameCard temp = myDeck.pop();
        if (temp != null) {
            hand.add(temp);
            return temp.getElement();
        }
        return null;
    }

    // Use Skill or Character Card

    /**
     * Put the card at field if power sufficient and chosen card exist in hand
     *
     * @param card  The card that used
     * @param e The type of element that used
     *
     * @return Success value of action
     */
    public boolean useCard(HasCostAttribute card, Element e) {
        Pair<Integer,Integer> t = power.get(e);
        if (t.getKey() >= card.getCost() && hand.contains(card)) {
            power.put(e,new Pair<>(t.getKey()-card.getCost(),t.getValue()));
            return true;
        }
        return false;
    }

    /**
     * Switch between attack and defend mode
     * @param idx to be accessed index
     */
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

    /**
     * Remove all null cards from hand for update
     */
    public void refreshHand() {
        while (hand.contains(null)) hand.remove(null);
    }
}
