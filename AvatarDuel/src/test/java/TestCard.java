import com.avatarduel.card.*;
import com.avatarduel.model.Element;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class TestCard {

    @Test
    public  void testLandGameCard() {
        LandGameCard card = new LandGameCard("Batman","The Dark Knight", Element.AIR, "com/avatarduel/card/image/character/Iroh.png");
        // Getter & Setter test
        assertEquals("Batman", card.getName());
        card.setName("Bat-Man");
        assertEquals("Bat-Man", card.getName());
        assertEquals("The Dark Knight", card.getDesc());
        card.setDesc("The dark Night");
        assertEquals("The dark Night", card.getDesc());
        assertEquals(Element.AIR, card.getElement());
        card.setElement(Element.FIRE);
        assertEquals(Element.FIRE, card.getElement());
        assertEquals("com/avatarduel/card/image/character/Iroh.png", card.getImgUrl());
        card.setImgUrl("Batman");
        assertEquals("Batman", card.getImgUrl());
        // Clone test
        assertNotEquals(card.clone(),card);
        assertEquals(card.clone().getDesc(), card.getDesc());
    }
    @Test
    public void testCharacterGameCard() {
        CharacterGameCard card = new CharacterGameCard("Batman","The Dark Knight", Element.AIR,
                "com/avatarduel/card/image/character/Iroh.png",1,7,5, new ArrayList<>());
        // Getter & Setter test from land
        // Getter & Setter test
        assertEquals(1,card.getCost());
        card.setCost(9);
        assertEquals(9,card.getCost());
        assertEquals(7,card.getAttack());
        card.setAttack(2);
        assertEquals(2,card.getAttack());
        assertEquals(5,card.getDefense());
        card.setDefense(3);
        assertEquals(3,card.getDefense());
        // Card effect test
        card.addAuraSkill(new AuraSkillGameCard("Batman","The Dark Knight", Element.AIR, "Batman", 1, -2, -3));
        assertEquals(1, card.getAuraSkillGameCardsList().size());
        assertEquals(0, card.getAttack());
        assertEquals(0, card.getDefense());
        assertFalse(card.isAttachedPowerUpinField());
        card.setPowerUpinField(new PowerUpSkillGameCard("Batman","The Dark Knight", Element.AIR, "Batman", 1));
        assertTrue(card.isAttachedPowerUpinField());
        card.nullifyAllSkill();
        // Clone test
        assertNotEquals(card.clone(),card);
        assertEquals(card.clone().getDesc(), card.getDesc());
    }

    @Test
    public  void testAuraSkillGameCard() {
        AuraSkillGameCard card = new AuraSkillGameCard("Batman","The Dark Knight", Element.AIR, "Batman", 1, 2, 3);
        // Getter & Setter test from land
        // Getter & Setter test
        assertEquals(2,card.getAttackAura());
        card.setAttackAura(10);
        assertEquals(10,card.getAttackAura());
        assertEquals(3,card.getDefenseAura());
        card.setDefenseAura(11);
        assertEquals(11,card.getDefenseAura());
        // Add/Remove effect test
        CharacterGameCard card2 = new CharacterGameCard("Batman","The Dark Knight", Element.AIR,
                "com/avatarduel/card/image/character/Iroh.png",1,7,5, new ArrayList<>());
        card.addEffect(card2);
        assertTrue(card2.getAuraSkillGameCardsList().contains(card));
        // Clone test
        assertNotEquals(card.clone(),card);
        assertEquals(card.clone().getDesc(), card.getDesc());
    }

    @Test
    public void testDestroySkillGameCard() {
        DestroySkillGameCard card = new DestroySkillGameCard("Batman","The Dark Knight", Element.AIR, "Batman", 1);
        // Getter & Setter test from land
        // Clone test
        assertNotEquals(card.clone(),card);
        assertEquals(card.clone().getDesc(), card.getDesc());
    }

    @Test
    public void testPowerUpSkillGameCard() {
        PowerUpSkillGameCard card = new PowerUpSkillGameCard("Batman","The Dark Knight", Element.AIR, "Batman", 1);
        // Getter & Setter test from land
        // Add/Remove effect test
        CharacterGameCard card2 = new CharacterGameCard("Batman","The Dark Knight", Element.AIR,
                "com/avatarduel/card/image/character/Iroh.png",1,7,5, new ArrayList<>());
        card.addEffect(card2);
        assertEquals(card2.getPowerUpSkillGameCard(), card);
        assertTrue(card2.isAttachedPowerUpinField());
        // Clone test
        assertNotEquals(card.clone(),card);
        assertEquals(card.clone().getDesc(), card.getDesc());
    }
}
