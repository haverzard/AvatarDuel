package com.avatarduel.phase;

// there is no initial value for this class's member
/**
 * Base class for all Phase classes
 * 
 * @author Kelompok 2
 */
abstract public class Phase {
    protected boolean canAttack;
    protected boolean canSwitchCardMode;
    protected boolean canUseNonLandCard;
    protected boolean canUseLandCard;
    protected boolean canDraw;

    /**
     * Get the value of canAttack attribute
     */
    public boolean getCanAttack() {
        return canAttack;
    }

    /**
     * Get the value of canSwitchCard mode attribute
     */
    public boolean getCanSwitchCardMode() {
        return canSwitchCardMode;
    }

    /**
     * Get the value of canUseNonCardLand attribute
     */
    public boolean getCanUseNonLandCard() {
        return canUseNonLandCard;
    }

    /**
     * Get the value of canUseLandCard attribute
     */
    public boolean getCanUseLandCard() {
        return canUseLandCard;
    }

    /**
     * Get the value of canDraw attribute
     */
    public boolean getCanDraw() {
        return canDraw;
    }

    /**
     * Set the value of canAttack attribute
     * @param canAttack canAttack's new value
     */
    public void setCanAttack(boolean canAttack) {
        this.canAttack = canAttack;
    }

    /**
     * Set the value of canSwitchCardMode attribute
     * @param canSwitchCardMode canSwitchCardMode's new value
     */
    public void setCanSwitchCardMode(boolean canSwitchCardMode) {
        this.canSwitchCardMode = canSwitchCardMode;
    }

    /**
     * Set the value of canUseNonLandCard attribute
     * @param canUseNonLandCard canUseNonLandCard's new value
     */
    public void setCanUseNonLandCard(boolean canUseNonLandCard) {
        this.canUseNonLandCard = canUseNonLandCard;
    }

    /**
     * Set the value of canUseLandCard attribute
     * @param canUseLandCard canUseLandCard's new value
     */
    public void setCanUseLandCard(boolean canUseLandCard) {
        this.canUseLandCard = canUseLandCard;
    }

    /**
     * Set the value of canDraw attribute
     * @param canDraw canDraw's new value
     */
    public void setCanDraw(boolean canDraw) {
        this.canDraw = canDraw;
    }
}
