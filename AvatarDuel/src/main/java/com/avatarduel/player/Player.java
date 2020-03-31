package com.avatarduel.player;

import com.avatarduel.card.GameCard;
import com.avatarduel.deck.*;
import com.avatarduel.element.Element;
import javafx.beans.property.SimpleIntegerProperty;

import java.util.ArrayList;

public class Player {
    private static int counts = 0;
    public static Player player1;
    public static Player player2;
    private PlayerDeck myDeck;
    private int id;
    private ArrayList<GameCard> hand;
    public SimpleIntegerProperty deckCounts; // For binding

    // To limit the instance
    private Player() {
        counts++;
        id = counts;
        myDeck = new PlayerDeck(new ArrayList<GameCard>());
        hand = new ArrayList<GameCard>();
        deckCounts = new SimpleIntegerProperty(0);
    }

    // To limit the instance for only 2
    public static void getPlayers() {
        if (counts < 2) {
            player1 = new Player();
            player2 = new Player();
        }
    }

    public GameCard getHand(int idx) {
        if (idx < hand.size()) return hand.get(idx);
        return null;
    }

    public int getId() {
        return id;
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

    public void addToDeck(GameCard x) {
        myDeck.add(x);
        deckCounts.set(deckCounts.getValue()+1);
    }

    public Element takeCard() {
        GameCard temp = myDeck.pop();
        if (temp != null) {
            hand.add(temp);
            deckCounts.set(myDeck.getSize());
            return temp.getElement();
        }
        return null;
    }
}
