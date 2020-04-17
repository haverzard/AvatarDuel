package com.avatarduel.cardfactory;

import com.avatarduel.card.GameCard;

/**
 * AppliableEffect is a contract that given for some classes
 * that can create GameCard
 *
 * @author Kelompok 2
 */
public interface CardCreation {
    /**
     * Create GameCard based on csv file's row.
     * @param args row data from csv file.
     */
    public GameCard getCard(String[] args);
}
