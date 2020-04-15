package com.avatarduel.deck;

/**
 * HasCapacity is a contract that given for some classes
 * that has a capacity attribute.
 * 
 * @author Kelompok 2
 */
public interface HasCapacity {
    /**
     * Get the value of capacity attribute in the class
     */
    public int getCapacity();

    /**
     * Set the value of capacity attribute in the class
     * @param _capacity new capacity's value
     */
    public void setCapacity(int _capacity);
}