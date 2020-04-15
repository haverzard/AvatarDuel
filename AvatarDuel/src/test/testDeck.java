import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

import java.api.ArrayList;

import com.avatarduel.card.GameCard;

public class testDeck {
    @Test
    public void testDeckConstructor {
        List<GameCard> dummyDeck = new ArrayList<GameCard>();
        GameCard dummyCard = new GameCard();

        assertEquals(99, dummyDeck.getCapacity(),"Test init capacity");

    }
}