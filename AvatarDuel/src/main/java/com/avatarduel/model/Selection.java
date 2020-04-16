package com.avatarduel.model;

public class Selection {
    private int target;

    /**
     * Create a new default selection.
     */
    public Selection() {
        target = -1;
    }

    /**
     * Get the value of target attribute in the class.
     * @return The value of target attribute in the class.
     */
    public int getTarget() {
        return target;
    }

    /**
     * Set the value of target attribute in the class.
     * @param target new target's value
     */
    public void setTarget(int target) {
        this.target = target;
    }

    /**
     * Show that a card is selected.
     * @return boolean with value that a card is selected.
     */
    public boolean isSelected() { return target != -1; }
}
