package com.avatarduel.card;

public class SkillCard extends Card implements HasCostAttribute {
    private int cost;

    // Getter & Setter
    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }
}
