// --== CS400 File Header Information ==--
// Name: Anthony Segedi
// Email: segedi@wisc.edu
// Team: Blue
// Group: AB
// TA: Mu Cai    
// Lecturer: Florian
// Notes to Grader: None
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.io.FileNotFoundException;
import java.util.zip.DataFormatException;
import java.util.List;
import java.io.IOException;

public class DataWranglerTests {

	private static final String FILENAME = "Oxford English Dictionary.txt";
	private final List<Word> list = readDictionary();

	/**
	 * Reads the dictionary
	 * @return A list<Word> containing all words in dictionary
	 */
	private static List<Word> readDictionary() {
		try {
			return DictionaryDataReader.readData(FILENAME);
		} catch (FileNotFoundException e) {
			fail("A FileNotFoundException occured while reading the Dictionary file: " + e.getMessage());
		} catch (IOException e) {
			fail("An IOException occured while reading the Dictionary file: " + e.getMessage());
		} catch (DataFormatException e) {
			fail("A DataFormatException occured while reading the Dictionary file: " + e.getMessage());
		}		
		return null;
	}

	@Test
	/**
	 * Tests correct parsing of the standard dictionary.
	 */
	public void testParse() {
		if (list.size() < 10000)
			fail("Majority of words were unparsed");
		Word word = list.get(0);
		assertEquals("A-", word.getWord());
		assertEquals(1, word.getDefinitions().size());
		assertEquals("prefix (also an- before a vowel sound) not, without (amoral).", word.getDefinitions().get(0));
		assertEquals("greek", word.getOrigin());
	}

	@Test
	/**
	 * Tests incorrect file type handling
	 */
	public void testFile() {
		// Test incorrect file name
		try {
			DictionaryDataReader.readData("IncorrectFilename.X");
			fail("Did not throw FileNotFoundException");
		} catch (FileNotFoundException e) {
			// Should throw
		} catch (IOException e) {
			fail("Threw an IOException while reading incorrect filename file");
		} catch (DataFormatException e) {
			fail("Threw a DataFormatException while reading incorrect filename file");
		}
		
		// Test incorrect file format
		try {
			DictionaryDataReader.readData("IncorrectFormatFile.txt");
			fail("Did not throw DataFormatException");
		} catch (FileNotFoundException e) {
			fail("Threw a FileNotFoundException trying to read from an incorrectly formatted file");
		} catch (DataFormatException e) {
			// Should throw
		} catch (IOException e) {
			fail("Threw an IOException while reading from an incorrectly formatted file");
		}
	}

	@Test
	/**
	 * Tests parsing of words with multiple definitions / types (may be included with testParse)
	 */
	public void testParseMultipleDefinitions() {
		Word word = list.get(1);
		assertEquals("Aa", word.getWord());
		assertEquals(3, word.getDefinitions().size());
		List<String> definitions = word.getDefinitions();
		assertEquals("abbr. automobile association.", definitions.get(0));
		assertEquals("alcoholics anonymous.", definitions.get(1));
		assertEquals("anti-aircraft.", definitions.get(2));
	}

	@Test
	/**
	 * Tests words loaded in properly
	 */
	public void loadAllWords() {
		final int BLUE_INDEX = 2623; // Bob-sleight = 2750 -- index 2684, sub 26
		assertEquals("Blue", list.get(BLUE_INDEX).getWord());
	}

	@Test
	/**
	 * Tests toString method to confirm correct output
	 */
	public void testToString() {
		Word word = list.get(1);
		assertEquals("Aa", word.getWord());
		assertEquals("Aa\n\nDefinitions:\n1 - abbr. automobile association.\n2 - alcoholics anonymous.\n3 - anti-aircraft.\n\nOrigin\nNo listed origin.", word.toString());
	}

	@Test
	/**
	 * Tests getters for DataWrangler objects
	 */
	public void testParseMultipleWordNames() {
		final int multiWordIndex = 24;
		assertEquals("Aberdeen angus", list.get(multiWordIndex).getWord());
	}
}
