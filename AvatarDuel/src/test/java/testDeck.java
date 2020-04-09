
import com.avatarduel.card.CharacterGameCard;
import com.avatarduel.card.GameCard;
import com.avatarduel.deck.Deck;
import org.junit.Test;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.assertEquals;

public class testDeck {
    @Test
    public void test () {
        List<GameCard> gameCardList = new ArrayList<GameCard>();
        GameCard gamecard = new CharacterGameCard();
        Deck deck = new Deck(gameCardList);
        // getCapacity
        assertEquals("Initial Capacity must 100",100, deck.getCapacity());
        // setCapacity
        deck.setCapacity(10);
        assertEquals("set Capacity must 10", 10,deck.getCapacity());
        // getsize
        assertEquals("initial size must 0", 0, deck.getSize());

        //add
        deck.add(gamecard);
        assertEquals("gamecards must increase to one", 1, deck.getSize());

        // pop
        assertEquals("popping gamecard", gamecard, deck.pop());
        assertEquals("empty pop should return null", null, deck.pop());

    }


}