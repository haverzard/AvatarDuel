package com.avatarduel.phase;

public class MainPhase extends Phase {
    public MainPhase() {
        canAttack = false;
        canSwitchCardMode = false;
        canUseNonLandCard = true;
        canUseLandCard = true;
        canDraw = false;
    }
}
