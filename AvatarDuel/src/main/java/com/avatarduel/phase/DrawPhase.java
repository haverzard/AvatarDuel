package com.avatarduel.phase;

public class DrawPhase extends Phase {
    public DrawPhase() {
        canAttack = false;
        canSwitchCardMode = false;
        canUseNonLandCard = false;
        canUseLandCard = false;
        canDraw = true;
    }
}
