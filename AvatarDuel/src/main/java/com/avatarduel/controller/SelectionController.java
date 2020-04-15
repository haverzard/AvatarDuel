package com.avatarduel.controller;

import com.avatarduel.model.Selection;

public class SelectionController {
    private Selection hand;
    private Selection attack;
    private Selection skill;

    public SelectionController() {
        hand = new Selection();
        attack = new Selection();
        skill = new Selection();
    }

    public Selection getHand() {
        return hand;
    }

    public Selection getAttack() {
        return attack;
    }

    public Selection getSkill() {
        return skill;
    }

    public void releaseAll() {
        hand.setTarget(-1);
        attack.setTarget(-1);
        skill.setTarget(-1);
    }

    public void releaseSkill() {
        skill.setTarget(-1);
    }

    public void releaseAttack() {
        attack.setTarget(-1);
    }

    public void releaseHand() {
        hand.setTarget(-1);
    }
}
