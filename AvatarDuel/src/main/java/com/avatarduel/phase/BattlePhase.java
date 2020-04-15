package com.avatarduel.phase;

public class BattlePhase extends Phase {
    public BattlePhase() {
        canAttack = true;
        canSwitchCardMode = false;
        canUseNonLandCard = false;
        canUseLandCard = false;
        canDraw = false;
    }
}
