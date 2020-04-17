import com.avatarduel.model.*;
import com.avatarduel.deck.*;
import com.avatarduel.card.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.junit.Test;
import static org.junit.Assert.*;


public class ModelTest {

	@Test
	public void elementTest() {
		for(Element theElement : Element.values()) {
			switch(theElement) {
				case WATER :
					assertEquals("WATER", theElement.toString());
					assertEquals(theElement.getCardTemplateURL(), "com/avatarduel/assets/image/waterTemp.png");
					break;
				case FIRE :
					assertEquals("FIRE", theElement.toString());
					assertEquals(theElement.getCardTemplateURL(), "com/avatarduel/assets/image/fireTemp.png");
					break;
				case AIR :
					assertEquals("AIR", theElement.toString());
					assertEquals(theElement.getCardTemplateURL(), "com/avatarduel/assets/image/airTemp.png");
					break;
				case EARTH :
					assertEquals("EARTH", theElement.toString());
					assertEquals(theElement.getCardTemplateURL(), "com/avatarduel/assets/image/earthTemp.png");
					break;
				case ENERGY :
					assertEquals("ENERGY", theElement.toString());
					assertEquals(theElement.getCardTemplateURL(), "com/avatarduel/assets/image/energyTemp.png");
					break;
				default :
					assertTrue(false);
			}
		}

	}

	@Test
	public void healthModelTest() {
		HealthModel theHealth = new HealthModel();

		double epsilon = 0.0000000001;

		assertEquals(0.00, theHealth.getSlide(), epsilon);
		assertEquals(1.00, theHealth.getHpPercentage(), epsilon);

		theHealth.setSlide(0.2);
		theHealth.setHpPercentage(0.8);

		assertEquals(0.2, theHealth.getSlide(), epsilon);
		assertEquals(0.8, theHealth.getHpPercentage(), epsilon);
	}

	@Test
	public void locTest() {
		for(Loc theLoc : Loc.values()) {
			switch(theLoc) {
				case TOP :
					assertEquals("TOP", theLoc.toString());
					break;
				case BOTTOM :
					assertEquals("BOTTOM", theLoc.toString());
			}
		}
	}

	@Test
	public void playerTest() {

		Player.getPlayers();

		assertEquals(80, Player.player1.getHealth());
		assertEquals(80, Player.player2.getHealth());

		assertEquals(1, Player.player1.getId());
		assertEquals(2, Player.player2.getId());

		assertEquals("", Player.player1.getName());
		assertEquals("", Player.player2.getName());

		Player.player1.setName("Kelompok");
		Player.player2.setName("Dua");

		assertEquals("Kelompok", Player.player1.getName());
		assertEquals("Dua", Player.player2.getName());

		GameCard theGameCardCreator;

		// Add 3 Card to Deck
		theGameCardCreator = new CharacterGameCard("Batman","The Dark Knight", Element.AIR, "com/avatarduel/card/image/character/Iroh.png",1,7,5, new ArrayList<>());
		Player.player1.addToDeck(theGameCardCreator);


		theGameCardCreator = new AuraSkillGameCard("Batman","The Dark Knight", Element.AIR, "Batman", 1, 2, 3);
		Player.player1.addToDeck(theGameCardCreator);

		theGameCardCreator = new LandGameCard("Batman","The Dark Knight", Element.AIR, "com/avatarduel/card/image/character/Iroh.png");
		Player.player1.addToDeck(theGameCardCreator);

		Player.player1.shuffleDeck();

		assertEquals(0, Player.player1.countCardsInHand());
		assertEquals(3, Player.player1.countCardsInDeck());

		Player.player1.takeCard();

		assertEquals(1, Player.player1.countCardsInHand());
		assertEquals(2, Player.player1.countCardsInDeck());

		assertEquals("Batman", Player.player1.getHand(0).getName());

		Player.player1.takeCard();
		Player.player1.takeCard();

		assertEquals(3, Player.player1.countCardsInHand());

		Player.player1.removeHand(theGameCardCreator);

		assertEquals(2, Player.player1.countCardsInHand());
		assertEquals(0, Player.player1.countCardsInDeck());

		theGameCardCreator = new LandGameCard("Batman","The Dark Knight", Element.AIR, "com/avatarduel/card/image/character/Iroh.png");
		Player.player1.addToDeck(theGameCardCreator);
		Player.player1.takeCard();

		assertEquals(3, Player.player1.countCardsInHand());

		Player.resetPlayers();

		assertEquals("", Player.player1.getName());
		assertEquals("", Player.player2.getName());

		assertEquals(0, Player.player1.countCardsInHand());
		assertEquals(0, Player.player1.countCardsInDeck());

		assertEquals(0, Player.player2.countCardsInHand());
		assertEquals(0, Player.player2.countCardsInDeck());

	}
	

	@Test
	public void selectionTest() {
		Selection theSelection = new Selection();

		assertEquals(-1, theSelection.getTarget());
		assertTrue(!theSelection.isSelected());

		theSelection.setTarget(1);

		assertEquals(1, theSelection.getTarget());
		assertTrue(theSelection.isSelected());

	}

	@Test
	public void turnTest() {
		Turn theTurn = new Turn();

		//assertEquals(-1, theTurn.getTurn());
	}
}