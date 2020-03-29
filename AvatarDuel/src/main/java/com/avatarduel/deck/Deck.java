package com.avatarduel.deck;

import com.avatarduel.card.Card;

import java.util.ArrayDeque;
import java.util.Queue;

public class Deck {
    private int capacity;
    private Queue<Card> cards; // Depend on Card, a higher abstraction, instead of CharacterCard, etc.

    public Deck() {
        cards = new ArrayDeque<Card>();
        capacity = 100;
    }

    // Instead of making a constructor to create a Deck in certain capacity,
    // we let the subclass to decide so Liskov Sub is not violated.

    // Getter & Setter
    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int _capacity) {
        capacity = _capacity;
    }

    public void add(Card x) {
        cards.add(x);
    }

    public Card pop() {
        return cards.remove();
    }

}
