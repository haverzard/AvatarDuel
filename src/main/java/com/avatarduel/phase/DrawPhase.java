package com.avatarduel.phase;

public class DrawPhase extends Phase {

    /**
     * Create a new default DrawPhase object
     */
    public DrawPhase() {
        canAttack = false;
        canSwitchCardMode = false;
        canUseNonLandCard = false;
        canUseLandCard = false;
        canDraw = true;
    }
}
