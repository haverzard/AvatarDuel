package com.avatarduel.model;

/**
 * Represents the health in AvatarDuel application
 */
public class HealthModel {
    private double hpPercentage = 1;
    private double slide = 0;

    /**
     * Creates a new default health model.
     */
    public HealthModel() {
        hpPercentage = 1;
        slide = 0;
    }

    /**
     * Get the value of Hp percentage in the class.
     */
    public double getHpPercentage() {
        return hpPercentage;
    }

    /**
     * Set the value of hpPercentage attribute in the class
     * @param hpPercentage new hpPercentage's value
     */
    public void setHpPercentage(double hpPercentage) {
        this.hpPercentage = hpPercentage;
    }

    /**
     * Get the value of slide attribute in the class
     */
    public double getSlide() {
        return slide;
    }

    /**
     * Set the value of slide attribute in the class
     * @param slide new slide's value
     */
    public void setSlide(double slide) {
        this.slide = slide;
    }
}
