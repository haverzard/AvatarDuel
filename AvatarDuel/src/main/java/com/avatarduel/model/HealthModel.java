package com.avatarduel.model;

public class HealthModel {
    private double hpPercentage = 1;
    private double slide = 0;

    public HealthModel() {
        hpPercentage = 1;
        slide = 0;
    }

    public double getHpPercentage() {
        return hpPercentage;
    }

    public void setHpPercentage(double hpPercentage) {
        this.hpPercentage = hpPercentage;
    }

    public double getSlide() {
        return slide;
    }

    public void setSlide(double slide) {
        this.slide = slide;
    }
}
