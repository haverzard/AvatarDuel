package com.avatarduel.card;

/**
 * AppliableEffect is a contract that given for some classes
 * that can be applied to CharacterGameCard.
 *
 * @author Kelompok 2
 */
public interface AppliableEffect {
    /**
     * Apply the effect to a CharacterGameCard
     * @param card CharacterGameCard which you want to apply the effect.
     */
    public void addEffect(CharacterGameCard card);

    /**
     * Remove the effect to a CharacterGameCard.
     * PRE-CONDITION: The effect has already been applied to the card.
     * @param card CharacterGameCard which you want to remove the effect.
     */
    public void removeEffect(CharacterGameCard card);

}