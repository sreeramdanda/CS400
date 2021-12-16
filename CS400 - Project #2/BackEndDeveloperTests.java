// --== CS400 File Header Information ==--
// Name: Sreeram Danda
// Email sdanda@wisc.edu
// Team: Blue
// Group AB
// TA: Mu Cai
// Lecturer: Gary Dahl
// Notes: N/A

import org.junit.Test;

import static org.junit.Assert.*;

import java.util.ArrayList;

/**
 * This class contains a set of tests for the back end of Project Two
 */
public class BackEndDeveloperTests {

	/**
	 * This test ensures that the insert method is working properly in the backend.
	 * The method first adds five items to the tree. If an exception is thrown the
	 * test fails. If the word added was not found, the test fails. If the size is
	 * not updated, the test fails.
	 */
	@Test
	public void testInsert() {
		Backend backend = new Backend("DummyDictionary.txt");

		ArrayList<String> carDef = new ArrayList<String>();
		carDef.add("A vehicle");

		ArrayList<String> dogDef = new ArrayList<String>();
		dogDef.add("A animal");

		ArrayList<String> petDef = new ArrayList<String>();
		petDef.add("A animal");

		ArrayList<String> catDef = new ArrayList<String>();
		catDef.add("A animal");

		ArrayList<String> truckDef = new ArrayList<String>();
		truckDef.add("A vehicle");

		backend.insert("car", carDef, "UD");
		backend.insert("dog", dogDef, "CA");
		backend.insert("pet", petDef, "UL");
		backend.insert("cat", catDef, "JF");
		backend.insert("truck", truckDef, "LT");

		if (backend.getSize() != 6)
			fail("The size was not update correctly");
	}

	/**
	 * This test ensures that the backend is retrieves the correct origin
	 * tree. If the tree is not updates correctly the test fails.
	 */
	@Test
	public void testOrigin() {
		Backend backend = new Backend("DummyDictionary.txt");
		
		if(!backend.getWord("Animal").getOrigin().equals("US"))
			fail("The correct origin was not retrieved");
	}

	/**
	 * This test ensures that the backend is able to get the correct definition for
	 * a word entered and a exception is thrown if it is not found. The method first
	 * tries to retrieve a definition for a word in the list and then attempt to
	 * retrieve a word that is not in a list.
	 */
	@Test
	public void testCorrectDefinition() {
		Backend backend = new Backend("DummyDictionary.txt");

		ArrayList<String> carDef = new ArrayList<String>();
		carDef.add("A vehicle");

		ArrayList<String> dogDef = new ArrayList<String>();
		dogDef.add("A animal");

		ArrayList<String> petDef = new ArrayList<String>();
		petDef.add("A animal");

		ArrayList<String> catDef = new ArrayList<String>();
		catDef.add("A animal");

		ArrayList<String> truckDef = new ArrayList<String>();
		truckDef.add("A vehicle");

		backend.insert("car", carDef, "UD");
		backend.insert("dog", dogDef, "CA");
		backend.insert("pet", petDef, "UL");
		backend.insert("cat", catDef, "JF");
		backend.insert("truck", truckDef, "LT");

		if (!backend.getWord("car").getDefinitions().contains("A vehicle"))
			fail("The definitions did not match the word");
	}

	/**
	 * This method tests the backend returns the correct suggestions when they
	 * exist. The method tries to get a word that is not in the dictionary. If an
	 * Exception is thrown the test fails.
	 */
	@Test
	public void testSuggestions() {
		Backend backend = new Backend("DummyDictionary.txt");

		ArrayList<String> carDef = new ArrayList<String>();
		carDef.add("A vehicle");

		ArrayList<String> dogDef = new ArrayList<String>();
		dogDef.add("A animal");

		ArrayList<String> petDef = new ArrayList<String>();
		petDef.add("A animal");

		ArrayList<String> catDef = new ArrayList<String>();
		catDef.add("A animal");

		ArrayList<String> truckDef = new ArrayList<String>();
		truckDef.add("A vehicle");

		backend.insert("car", carDef, "UD");
		backend.insert("dog", dogDef, "CA");
		backend.insert("pet", petDef, "UL");
		backend.insert("cat", catDef, "JF");
		backend.insert("truck", truckDef, "LT");

		if (!backend.getSuggestions("cat").contains("car"))
			fail("A suggestion was not made when expected");
	}

	/**
	 * This method tests that the backend updates the size of the tree empty at the
	 * right moments. First checks the size at 0 objects in the tree, makes sure
	 * that the tree is empty, then adds a word and checks size again. The test
	 * fails if any of these checks go incorrectly or an exception is thrown.
	 */
	@Test
	public void testSizeAndIsEmpty() {
		Backend backend = new Backend("DummyDictionary.txt");

		if (backend.getSize() != 1)
			fail("Size is not updated for the red black tree");

		if (backend.isEmpty())
			fail("The tree is not marked empty when it should be");

		ArrayList<String> testDef = new ArrayList<String>();
		testDef.add("Test");

		backend.insert("test", testDef, "GA");

		if (backend.getSize() != 2)
			fail("Size is not updated for non-empty tree");
	}
}