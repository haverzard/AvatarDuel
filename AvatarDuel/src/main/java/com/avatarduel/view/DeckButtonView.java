package com.avatarduel.view;

import com.avatarduel.components.Basic;
import com.avatarduel.components.ClosedCard;
import javafx.scene.paint.Color;

public class DeckButtonView extends ClosedCard {
    public DeckButtonView() {
        super(60);
        setEffect(Basic.getShadow(Color.BLUE, 30));
    }
}
