package com.avatarduel.view;

import com.avatarduel.components.Basic;
import com.avatarduel.components.ClosedCard;
import javafx.scene.paint.Color;

/**
 * DeckButtonView describes the deck view that extends closed card class.
 * 
 * @author Kelompok 2
 */
public class DeckButtonView extends ClosedCard {

    /**
     * Create a Closed card Object in the GUI to represent deck.
     */
    public DeckButtonView() {
        super(60);
        setEffect(Basic.getShadow(Color.BLUE, 30));
    }
}
