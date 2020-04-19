package com.avatarduel.card;

/**
 * HasAuraEffect is a contract that given for some classes
 * that has an aura effect.
 *
 * @author Kelompok 2
 */
interface HasAuraEffect {
    /**
     * Get the value of attack aura attribute in the class
     * @return the attackAura value of object from the class that implements this inteface.
     */
    public int getAttackAura();

    /**
     * Set the value of attack aura attribute in the class
     * @param _attack new attack aura's value
     */
    public void setAttackAura(int _attack);

    /**
     * Get the value of defense aura attribute in the class
     * @return the defenseAura value of object from the class that implements this inteface.
     */
    public int getDefenseAura();

    /**
     * Set the value of defense aura attribute in the class
     * @param _defense new defense aura's value
     */
    public void setDefenseAura(int _defense);

}