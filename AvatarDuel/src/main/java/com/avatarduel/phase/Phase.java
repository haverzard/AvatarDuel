package com.avatarduel.phase;

// there is no initial value for this class's member
abstract public class Phase {
    protected boolean canAttack;
    protected boolean canSwitchCardMode;
    protected boolean canUseNonLandCard;
    protected boolean canUseLandCard;
    protected boolean canDraw;

    public boolean getCanAttack() {
        return canAttack;
    }

    public boolean getCanSwitchCardMode() {
        return canSwitchCardMode;
    }

    public boolean getCanUseNonLandCard() {
        return canUseNonLandCard;
    }

    public boolean getCanUseLandCard() {
        return canUseLandCard;
    }

    public boolean getCanDraw() {
        return canDraw;
    }

    public void setCanAttack(boolean canAttack) {
        this.canAttack = canAttack;
    }

    public void setCanSwitchCardMode(boolean canSwitchCardMode) {
        this.canSwitchCardMode = canSwitchCardMode;
    }

    public void setCanUseNonLandCard(boolean canUseNonLandCard) {
        this.canUseNonLandCard = canUseNonLandCard;
    }

    public void setCanUseLandCard(boolean canUseLandCard) {
        this.canUseLandCard = canUseLandCard;
    }

    public void setCanDraw(boolean canDraw) {
        this.canDraw = canDraw;
    }
}
