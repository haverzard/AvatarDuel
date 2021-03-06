package com.avatarduel.card;

/**
 * HasBattleAttribute is a contract that given for some classes
 * that has a battle attribute.
 *
 * @author Kelompok 2
 */
public interface HasBattleAttribute {
    /**
     * Get the value of attack attribute in the class
     * @return the attack value of object from the class that implements this inteface.
     */
    public int getAttack();

    /**
     * Set the value of attack attribute in the class
     * @param attack new attack's value
     */
    public void setAttack(int attack);

    /**
     * Get the value of defense attribute in the class
     * @return the defense value of object from the class that implements this inteface.
     */
    public int getDefense();

    /**
     * Set the value of defense attribute in the class
     * @param defense new defense's value
     */
    public void setDefense(int defense);
}
