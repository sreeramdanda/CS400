// --== CS400 File Header Information ==--
// Name: Sreeram Danda
// Email: sdanda@wisc.edu
// Team: AB
// TA: Mu Cai
// Lecturer: Gary Dahl
// Notes to Grader: -N/A-

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.zip.DataFormatException;

/**
 * This class represents the backend to the dictionary project. The backend
 * creates a red black tree by using a list of words gathered from the
 * DictionaryDataReader. The backend can add and get words from the dictionary.
 * Also, the backend is able to offer suggestions based on a given input for
 * words not in dictionary.
 * 
 * @author Sreeram Danda
 *
 */
public class Backend implements BackendInterface {

	List<Word> words = new ArrayList<Word>(); // Array list that is initiated in constructor to have all words to be
												// added to tree
	RedBlackTree<Word> dictionary = new RedBlackTree<Word>();// Red-black tree that represents the dictionary

	/**
	 * Default constructor for backend. Creates the red-black tree dictionary and
	 * throws an given exception if any errors occur during the process.
	 */
	public Backend() {
		try {
			// Update word array to have all words to be added
			words = DictionaryDataReader.readData("Oxford English Dictionary.txt");

			// Add every word from arrayList to the red-black tree
			for (Word x : words) {
				dictionary.insert(x);
			}
		} catch (FileNotFoundException e) {
			System.out.println("ERROR. Could not find the file.");
		} catch (DataFormatException e) {
			System.out.println("ERROR. Could not parse any data from file.");
		} catch (IOException e) {
			System.out.println("ERROR. Problem occured in the data reader.");
		} catch (Exception e) {
			System.out.println("ERROR. Problem occured creating the red-black tree.");
		}
	}

	/**
	 * The insert method adds a word to the red black tree.
	 * 
	 * @param word      A string representing the word
	 * @param definiton A list representing the definitions
	 * @param origin    A string representing the origin
	 */
	@Override
	public void insert(String word, List<String> definition, String origin) {
		// Word object created with given parameters
		Word toAdd = new Word(word, definition, origin);

		// Check for duplicate
		if (dictionary.contains(toAdd))
			throw new IllegalArgumentException("Word already exists in the dictionary.");

		// Add to dictionary
		dictionary.insert(toAdd);
	}

	/**
	 * The getWord method returns a word if the given word exists in the dictionary.
	 * 
	 * @param word A string representing the word to look for.
	 * @return A string representing the word if found
	 * @throws NoSuchElementException Exception thrown if word not found.
	 */
	@Override
	public String getWord(String word) throws NoSuchElementException {
		// Determine if word exists in the dictionary and return it if it exists
		for (Word x : dictionary) {
			if (x.getWord().equals(word))
				return word;
		}

		// Exception thrown if word does not exist in dictionary.
		throw new NoSuchElementException("Word not found in the dictionary.");
	}

	/**
	 * The getWord(int) method returns a word from the arrayList given an index.
	 * 
	 * @param num A int represeting the index to get a word from.
	 * @return A String represeting the word found at a given index.
	 * @throws IllegalArgumentException Exception thrown if int is greater or
	 *                                  smaller than arrayList of words.
	 */
	public String getWord(int num) throws IllegalArgumentException {
		// Check if parameter is within the size of list of words
		if (num > words.size() - 1 || num < 0)
			throw new IllegalArgumentException("Index exceeds size of dictionary.");

		// Word at given index
		Word wordToReturn = words.get(num);

		return wordToReturn.getWord();
	}

	/**
	 * The getDefinition method returns a list of definitions for a given word.
	 * 
	 * @param word A String representing the word to find the definitions for.
	 * @return A List<String> representing the list of definitions for the word.
	 * @throws NoSuchElementException Exception thrown when word not found in
	 *                                dictionary.
	 */
	@Override
	public List<String> getDefinition(String word) throws NoSuchElementException {
		// Check for and return word's definition
		for (Word x : dictionary) {
			if (x.getWord().equals(word))
				return x.getDefinitions();
		}

		// Throw exception if word is not in dictionary
		throw new NoSuchElementException("Word not found in the dictionary.");
	}

	/**
	 * The getDefinition method returns a list of definitions for a given index.
	 * 
	 * @param num Index to find definition
	 * @return A List<String> Representing the definitions of a word at a given
	 *         index.
	 * @throws IllegalArgumentException Exception thrown when index is outside
	 *                                  bounds of arrayList size.
	 */
	public List<String> getDefinition(int num) throws IllegalArgumentException {
		// Ensure index is within bounds
		if (num > words.size() - 1 || num < 0)
			throw new IllegalArgumentException("Index exceeds size of dictionary.");

		// Word at index
		Word wordToReturn = words.get(num);

		// Return definition of word
		return wordToReturn.getDefinitions();
	}

	/**
	 * The getOrigin method returns the origin of a given word.
	 * 
	 * @param word A String representing the word to find origin for
	 * @return A String representing the origin for given word.
	 * @throws NoSuchElementException Exception thrown when word is not in dictionary.
	 */
	@Override
	public String getOrigin(String word) throws NoSuchElementException {
		for (Word x : dictionary) {
			if (x.getWord().equals(word))
				return x.getOrigin();
		}

		throw new NoSuchElementException("Word not found in the dictionary.");
	}

	/**
	 * The getOrigin method returns the origin of a word given an index.
	 * 
	 * @param num The index to find the word
	 * @return A String representing the origin of the word at given index
	 * @throws IllegalArgumentException Exception thrown if index is outside bounds
	 *                                  of arrayList
	 */
	public String getOrigin(int num) throws IllegalArgumentException {
		// Ensure index is within bounds
		if (num > words.size() - 1 || num < 0)
			throw new IllegalArgumentException("Index exceeds size of dictionary.");

		// Word at index
		Word wordToReturn = words.get(num);

		// Return word's origin
		return wordToReturn.getOrigin();
	}

	/**
	 * The getSize method returns the size of the dictionary.
	 * 
	 * @return A int representing the size of the dictionary.
	 */
	@Override
	public int getSize() {
		return dictionary.size();
	}

	/**
	 * The isEmpty method returns a boolean representing the state of emptiness of
	 * the dictionary.
	 * 
	 * @return A boolean representing the state of emptiness of the dictionary.
	 */
	@Override
	public boolean isEmpty() {
		if (getSize() == 0)
			return true;
		return false;
	}

	/**
	 * The getSuggestions method returns a list of strings that represent words that
	 * are similar to a given word. It finds the similar words by replacing one
	 * character at a time with the a character from the alphabet and searching for
	 * the word in the dictionary. It continues until each character has been
	 * replaced with all characters in the alphabet.
	 * 
	 * @param word A string representing the word to find suggestions for.
	 * @return ArrayList<String> Representing the word that are similar to given
	 *         word.
	 */
	@Override
	public ArrayList<String> getSuggestions(String word) {
		int wordLength = word.length();// Length of given word
		ArrayList<String> suggestions = new ArrayList<>(); // ArrayList of suggestions
		String changedWord = word;// Temporary string to represent word after changes

		// Iterate through each character in given word
		for (int x = wordLength - 1; x <= 0; x++) {
			// Reset changedWord after iterating a character through each letter in alphabet
			changedWord = word;
			// Iterate through each letter in alphabet
			for (int y = 97; y <= 122; y++) {
				char letterReplacement = (char) y; // Char representing a letter in alphabet
				char letterToReplace = word.charAt(x); // Char being replaced in given word
				// Replace char with a replacement
				changedWord = word.replace(letterToReplace, letterReplacement);
				// Search dictionary with changed word and add if word found in dictionary
				for (Word z : dictionary) {
					if (z.getWord().equals(changedWord))
						suggestions.add(changedWord);
				}
			}
		}
		// Return suggestions
		return suggestions;
	}
}
