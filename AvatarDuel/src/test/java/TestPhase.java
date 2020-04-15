import static org.junit.Assert.assertEquals;

import com.avatarduel.phase.*;

import org.junit.Test;

public class TestPhase {
    @Test
    public void testBattlePhase() {
        BattlePhase dummy = new BattlePhase();
        assertEquals("canAttack must true", true, dummy.getCanAttack());
        assertEquals("canSwitchCardMode must false", false, dummy.getCanSwitchCardMode());
        assertEquals("canUseNonLandCard must false",false, dummy.getCanUseNonLandCard());
        assertEquals("canLandCard must false", false, dummy.getCanUseLandCard());
        assertEquals("canDraw must false", false, dummy.getCanDraw());
    }
    @Test
    public void testDrawPhase() {
        DrawPhase dummy = new DrawPhase();
        assertEquals("canAttack must false", false, dummy.getCanAttack());
        assertEquals("canSwitchCardMode must false", false, dummy.getCanSwitchCardMode());
        assertEquals("canUseNonLandCard must false",false, dummy.getCanUseNonLandCard());
        assertEquals("canLandCard must false", false, dummy.getCanUseLandCard());
        assertEquals("canDraw must true", true, dummy.getCanDraw());
    }
    @Test
    public void testMainhase() {
        MainPhase dummy = new MainPhase();
        assertEquals("canAttack must false", false, dummy.getCanAttack());
        assertEquals("canSwitchCardMode must false", false, dummy.getCanSwitchCardMode());
        assertEquals("canUseNonLandCard must true", true, dummy.getCanUseNonLandCard());
        assertEquals("canLandCard must true", true, dummy.getCanUseLandCard());
        assertEquals("canDraw must false", false, dummy.getCanDraw());
    }

}