import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

// --== CS400 File Header Information ==--
// Name: Sreeram Danda
// Email: sdanda@wisc.edu
// Team: AB
// TA: Mu Cai
// Lecturer: Gary Dahl
// Notes to Grader: -N/A-

/**
 * This is a basic dummy backend class
 * @author Sreeram Danda
 *
 */
public class BackendDummy implements BackendInterface {
	List<String> dictionary;
	
	/**
	 * Constructor creates a array list representing a dummy dictionary.
	 */
	public BackendDummy(){
		dictionary = new ArrayList<String>();
	}
	
	/**
	 * Adds a word to the dictionary
	 * @param Three strings representing a word, its defintion, and its orgin
	 */
	public void insert(String word, List<String> definition, String origin) {
		dictionary.add(word);
	}

	/**
	 * Retrieves a word from the dictionary if it exists
	 * @param A String representing the word to look for
	 * @return A String of the word if found in dictionary
	 */
	public Word getWord(String word) {
		ArrayList<String> carDef = new ArrayList<String>();
		carDef.add("A vehicle");
		Word dummyWord = new Word("car", carDef ,"UA");
		return dummyWord;
	}
	
	/**
	 * Method retrieves the list of definitions for a word
	 * @param A string representing a word to get the definition for
	 * @return A list of all the definitions
	 */
	public List<String> getDefinition(String word) {
		List<String> dummyDefinitions = new ArrayList<>();
		dummyDefinitions.add("this is one definiton for one word");
		dummyDefinitions.add("this is another definiton for one word");
		return dummyDefinitions;
	}
	
	/**
	 * Retrieves the origin of a word
	 * @param A string representing a word to get the origin for
	 * @return A string representing the origin
	 */
	public String getOrigin(String word) {
		return "Neverland";
	}
	
	/**
	 * Gets the size of the tree
	 * @return An integer representing the tree's size
	 */
	public int getSize() {
		return dictionary.size();
	}
	
	/**
	 * Checks if the tree is empty
	 * @return True is tree is empty, false otherwise
	 */
	public boolean isEmpty() {
		return dictionary.isEmpty();
	}
	
	/**
	 * Returns a suggestion for a given word
	 * @param A string representing the word to find suggestions for
	 * @return A string array representing words as a suggestions
	 */
	public ArrayList<String> getSuggestions(String word){
		ArrayList<String> dummySuggestions = new ArrayList<String>();
		dummySuggestions.add("car");
		dummySuggestions.add("cat");
		dummySuggestions.add("mar");
		return dummySuggestions;
	}

	@Override
	public Word getWord(int num) throws NoSuchElementException {
		ArrayList<String> carDef = new ArrayList<String>();
		carDef.add("A vehicle");
		Word dummyWord = new Word("car", carDef ,"UA");
		return dummyWord;
	}

	@Override
	public void add(String word, List<String> definition, String origin) {
		//Simply here to allow for compilation 
	}
}
