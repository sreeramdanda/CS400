// --== CS400 File Header Information ==--
// Name: Nijae King
// Email: nrking@wisc.edu
// Team: Purple
// Role: Frontend
// TA: Mu Cai
// Lecturer: Florian
// Notes to Grader: n/a

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class FrontEndDeveloperTests {

    @Test
    /**
     * Tests command to exit program when 'e' or 'x' is typed
     */
    public void testCommandExit() {
        Frontend.testSwitchModes("e");
        Frontend.testEnterMode();

        if(Frontend.currMode != Frontend.Mode.EXIT || Frontend.runLoop)
            fail("Failed to switch modes");
    }

    @Test
    /**
     * Tests correctly adding word to dictionary while in addition mode
     */
    public void testCommandAddition() {
        Frontend.testSwitchModes("a");

        if(Frontend.currMode != Frontend.Mode.ADDITION)
            fail("Failed to switch modes");
    }

    @Test
    /**
     * Tests ability to give definition of word while in search mode
     */
    public void testCommandSearch() {
        Frontend.testSwitchModes("s");

        if(Frontend.currMode != Frontend.Mode.SEARCH)
            fail("Failed to switch modes");
    }

    @Test
    /**
     * Tests correctly pulling a random word from dictionary
     */
    public void testCommandRandom() {
        Frontend.testSwitchModes("r");

        if(Frontend.currMode != Frontend.Mode.RANDOM)
            fail("Failed to switch modes");
    }

    @Test
    /**
     * Tests correctly adding word to dictionary while in search mode
     */
    public void testAdditionFromSearch() {
        Frontend.testSwitchModes("s");
        Frontend.testPrintWord("abcdef", 'y');
        if(Frontend.currMode != Frontend.Mode.ADDITION)
            fail("Failed to switch modes");
    }
}