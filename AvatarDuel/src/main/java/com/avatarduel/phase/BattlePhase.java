package com.avatarduel.phase;

public class BattlePhase extends Phase {

    /**
     * Create a new default BattlePhase Object
     */
    public BattlePhase() {
        canAttack = true;
        canSwitchCardMode = false;
        canUseNonLandCard = false;
        canUseLandCard = false;
        canDraw = false;
    }
}
