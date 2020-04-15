package com.avatarduel.card;

/**
 * HasCostAttribute is a contract that given for some classes
 * that has a cost attribute.
 *
 * @author Kelompok 2
 */
public interface HasCostAttribute {
    /**
     * Get the value of cost attribute in the class
     */
    public int getCost();

    /**
     * Set the value of cost attribute in the class
     * @param cost new cost's value
     */
    public void setCost(int cost);
}
