package com.avatarduel.model;

public class Selection {
    private int target;

    public Selection() {
        target = -1;
    }

    public int getTarget() {
        return target;
    }

    public void setTarget(int target) {
        this.target = target;
    }

    public boolean isSelected() { return target != -1; }
}
