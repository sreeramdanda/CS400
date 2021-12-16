import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.FileNotFoundException;

import org.junit.Test;

// --== CS400 File Header Information ==--
// Name: Sreeram Danda
// Email sdanda@wisc.edu
// Team: Blue
// Group AB
// TA: Mu Cai
// Lecturer: Gary Dahl
// Notes: N/A

/**
 * This class contains a set of tests for the data reader of project three
 */
public class DataWranglerTests {

	/**
	 * This method tests that the class throws an error if any data is read from a
	 * non-existing file.
	 */
	@Test
	public void testErrorThrownWhenFileNotFound() {
		try {
			// Attempt to read the data in invalid files
			DataReader.readData();

		} catch (FileNotFoundException e) {
			// Test that the two messages are equal
			fail("Invalid files was used.");
		}
	}

	/**
	 * This method ensures that no extra data is being added.
	 */
	@Test
	public void testInvalidIntersectionFromData() {
		try {
			// Ensure that the first intersection is not in output
			String expectedMessage = "#500 Intersection:";
			
			String actualMessage = DataReader.printAllIntersectionsAndConnectionsWithDistance();
			assertEquals(actualMessage.contains(expectedMessage), false);

		} catch (FileNotFoundException e) {
			// Throw exception if the file is not found
			fail("Actual Message did not contain what was expected.");
		}
	}

	/**
	 * This method tests the print method in the DataReader class. It ensures that
	 * the first and last intersection are included. 
	 */
	@Test
	public void testPrintAllIntersections() {
		try {
			// Ensure that the first intersection is in the output
			String expectedMessage = "#1 Intersection: Observatory & Babcock\n";
			String actualMessage = DataReader.printAllIntersectionsAndConnectionsWithDistance();
			assertEquals(actualMessage.contains(expectedMessage), true);

			// Ensure that the last intersection is in the output
			expectedMessage = "#54 Intersection: Regent & Coyne\n";
			assertEquals(actualMessage.contains(expectedMessage), true);

		} catch (FileNotFoundException e) {
			// Throw exception if the file is not found
			fail("Actual Message did not contain what was expected.");
		}
	}
}
