package com.avatarduel.model;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

public class Health {
    private SimpleDoubleProperty value = new SimpleDoubleProperty(this, "value");

    public Health() {
        value.set(80);
    }

    public Health(int h) {
        value.set(h);
    }

    public double getValue(){
        return value.get();
    }

    public void setValue(double value){
        this.value.set(value);
    }

    public DoubleProperty valueProperty(){
        return value;
    }
}
