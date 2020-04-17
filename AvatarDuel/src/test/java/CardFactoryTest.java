import com.avatarduel.card.GameCard;
import com.avatarduel.cardfactory.*;
import com.avatarduel.util.CSVReader;
import org.junit.Test;

import java.io.File;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class CardFactoryTest {
    private static final String CHARACTER_CSV_FILE_PATH = "com/avatarduel/card/data/character.csv";
    private static final String SKILL_CSV_FILE_PATH = "com/avatarduel/card/data/skill_aura.csv";
    private static final String LAND_CSV_FILE_PATH = "com/avatarduel/card/data/land.csv";
    private static final String DESTROY_CSV_FILE_PATH = "com/avatarduel/card/data/skill_destroy.csv";
    private static final String POWER_UP_CSV_FILE_PATH = "com/avatarduel/card/data/skill_powerup.csv";
    @Test
    public void testAuraSkillGameCardFactory () {
        try {
            File CSVFile = new File(getClass().getResource(SKILL_CSV_FILE_PATH).toURI());
            CSVReader reader = new CSVReader(CSVFile, ",");
            reader.setSkipHeader(true);
            List<String[]> rows = reader.read();
            GameCardFactory x = new AuraSkillGameCardFactory();
            GameCard card = x.getCard(rows.get(0));
            assertEquals("Skill", card.getType());
        } catch (Exception e) {
            assertTrue("Fail on creating card based on csv file", false);
        }
    }

    @Test
    public void testCharacterGameCardFactory () {
        try {
            File CSVFile = new File(getClass().getResource(CHARACTER_CSV_FILE_PATH).toURI());
            CSVReader reader = new CSVReader(CSVFile, ",");
            reader.setSkipHeader(true);
            List<String[]> rows = reader.read();
            GameCardFactory x = new CharacterGameCardFactory();
            GameCard card = x.getCard(rows.get(0));
            assertEquals("Character", card.getType());
        } catch (Exception e) {
            assertTrue("Fail on creating card based on csv file", false);
        }
    }

    @Test
    public void testDestroyGameCardFactory () {
        try {
            File CSVFile = new File(getClass().getResource(DESTROY_CSV_FILE_PATH).toURI());
            CSVReader reader = new CSVReader(CSVFile, ",");
            reader.setSkipHeader(true);
            List<String[]> rows = reader.read();
            GameCardFactory x = new DestroySkillGameCardFactory();
            GameCard card = x.getCard(rows.get(0));
            assertEquals("Skill", card.getType());
        } catch (Exception e) {
            assertTrue("Fail on creating card based on csv file", false);
        }
    }

    @Test
    public void testLandGameCardFactory () {
        try {
            File CSVFile = new File(getClass().getResource(LAND_CSV_FILE_PATH).toURI());
            CSVReader reader = new CSVReader(CSVFile, ",");
            reader.setSkipHeader(true);
            List<String[]> rows = reader.read();
            GameCardFactory x = new LandGameCardFactory();
            GameCard card = x.getCard(rows.get(0));
            assertEquals("Land", card.getType());
        } catch (Exception e) {
            assertTrue("Fail on creating card based on csv file", false);
        }
    }

    @Test
    public void testPowerUpSkillGameCardFactory () {
        try {
            File CSVFile = new File(getClass().getResource(POWER_UP_CSV_FILE_PATH).toURI());
            CSVReader reader = new CSVReader(CSVFile, ",");
            reader.setSkipHeader(true);
            List<String[]> rows = reader.read();
            GameCardFactory x = new PowerUpSkillGameCardFactory();
            GameCard card = x.getCard(rows.get(0));
            assertEquals("Skill", card.getType());
        } catch (Exception e) {
            assertTrue("Fail on creating card based on csv file", false);
        }
    }
}
