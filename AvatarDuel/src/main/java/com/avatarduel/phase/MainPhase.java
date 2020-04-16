package com.avatarduel.phase;

public class MainPhase extends Phase {
    
    /**
     * Create a new default Main Phase object
     */
    public MainPhase() {
        canAttack = false;
        canSwitchCardMode = false;
        canUseNonLandCard = true;
        canUseLandCard = true;
        canDraw = false;
    }
}
