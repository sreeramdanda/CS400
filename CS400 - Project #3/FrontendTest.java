// --== CS400 File Header Information ==--
// Name: Gautama Manja
// Email: manja@wisc.edu
// Team: Blue
// Role: Frontend Dev
// TA: Mu Cai
// Lecturer: Gary
// Notes to Grader: I know the tests seem simple, but most of the testing was done manually while I was running the program b/c I could not redirect stdin
// or stdout for Junit
//
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class FrontendTest{
    /**
     *Test the mode selection is working correctly by changing mode to exit mode 
     *
     */
    @Test
    public void testModeSelection(){
	Frontend frontend = new Frontend();
	Frontend.modeSelectionHelper('e');
	assertEquals(frontend.currentMode.name(), "EXITPROGRAM");
    }
    
    /**
     *
     *Test that the mode switch property is working by changing from basic to overview
     *
     */
    @Test
    public void testModeSwitch(){
	Frontend frontend = new Frontend();
	Frontend.modeSelectionHelper('b');
	Frontend.switchModes("o");
	assertEquals(frontend.currentMode.name(), "OVERVIEW");
    }

    /**
     *Test that the program will stay in the same mode when switched from a mode to itself
     */
    @Test
    public void testModeSwitchinMode(){
	Frontend frontend = new Frontend();
	Frontend.modeSelectionHelper('b');
	Frontend.switchModes("b");
	assertEquals(frontend.currentMode.name(), "BASIC");
    }
}
