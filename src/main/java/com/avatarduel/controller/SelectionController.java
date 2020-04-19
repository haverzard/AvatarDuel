package com.avatarduel.controller;

import com.avatarduel.model.Selection;

/**
 * Represent the Selection Controller for MVC pattern in AvatarDuel
 */
public class SelectionController {
    private Selection hand;
    private Selection attack;
    private Selection skill;

    /**
     * Create new selection controller
     */
    public SelectionController() {
        hand = new Selection();
        attack = new Selection();
        skill = new Selection();
    }

    /**
     * Get selection from hand
     * @return selection from hand
     */
    public Selection getHand() {
        return hand;
    }

    /**
     * Get selection from attack
     * @return selection from attack
     */
    public Selection getAttack() {
        return attack;
    }

    /**
     * Get selection from skill
     * @return selection from skill
     */
    public Selection getSkill() {
        return skill;
    }

    /**
     * Release all target
     */
    public void releaseAll() {
        hand.setTarget(-1);
        attack.setTarget(-1);
        skill.setTarget(-1);
    }

    /**
     * Release skill target
     */
    public void releaseSkill() {
        skill.setTarget(-1);
    }

    /**
     * Release attack target
     */
    public void releaseAttack() {
        attack.setTarget(-1);
    }

    /**
     * Release hand target
     */
    public void releaseHand() {
        hand.setTarget(-1);
    }
}
