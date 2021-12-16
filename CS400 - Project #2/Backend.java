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
				try {
					dictionary.insert(x);
				} catch (IllegalArgumentException e) {
					continue;
				}
			}
		} catch (FileNotFoundException e) {
			System.out.println("ERROR. Could not find the file.");
		} catch (DataFormatException e) {
			System.out.println("ERROR. Could not parse any data from file.");
		} catch (IOException e) {
			System.out.println("ERROR. Problem occured in the data reader.");
		} catch (IllegalArgumentException e) {
			System.out.println("ERROR. Adding a duplicate word to dictionary.");
		} catch (Exception e) {
			System.out.println("ERROR. Problem occurred creating the red-black tree.");
		}
	}

	/**
	 * Secondary constructor for backend. This is used for testing different
	 * dictionary text files.
	 * 
	 * @param dictionary A file path to the dictionary text file.
	 */
	public Backend(String dictionary) {
		try {
			// Update word array to have all words to be added
			words = DictionaryDataReader.readData(dictionary);

			// Add every word from arrayList to the red-black tree
			for (Word x : words) {
				this.dictionary.insert(x);
			}

		} catch (FileNotFoundException e) {
			System.out.println("ERROR. Could not find the file.");
		} catch (DataFormatException e) {
			System.out.println("ERROR. Could not parse any data from file.");
		} catch (IOException e) {
			System.out.println("ERROR. Problem occured in the data reader.");
		} catch (IllegalArgumentException e) {
			System.out.println("ERROR. Adding a duplicate word to dictionary.");
		} catch (Exception e) {
			System.out.println("ERROR. Problem occurred creating the red-black tree.");
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
		if (dictionary.contains(toAdd)){
			throw new IllegalArgumentException("Word already exists in the dictionary.");
		} else {
		// Add to dictionary
		dictionary.insert(toAdd);
		}
	}
	
	/**
	 * Method used by front end to add words that are not in dictionary
	 * 
	 * @param word String of word to be added
	 * @param definition List of strings of definitions for the word
	 * @param origin String of the word's origin
	 * @throws IllegalArgumentException Exception thrown if word already exists in dictionary
	 */
	public void add(String word, List<String> definition, String origin) throws IllegalArgumentException{
		Word toAdd = new Word(word, definition, origin);

		for(Word x: words) {
			if(x.getWord().toLowerCase().compareTo(word) == 0) {
				throw new IllegalArgumentException("Word already exists in the dictionary.");
			}
		}

		// Add to the words list
		for(int i = 0; i < words.size() - 1; i++) {
			// Special case if end of for loop is reached
			if(i == words.size() - 1) {
				if(words.get(i+1).getWord().toLowerCase().compareTo(word) > 0) {
					words.add(i+1, toAdd);
				} else {
					words.add(toAdd);
				}
				break;
			// Regular case for in between 0 and end of arrayList
			} else if(words.get(i+1).getWord().toLowerCase().compareTo(word) > 0) {
				words.add(i+1, toAdd);
				break;
			}
		}
		// Update red black tree to add maintaining alphabetical order
		update();
	}

	/**
	 * Recreates the red black tree if the arrayList of words is updated
	 */
	private void update() {
		dictionary = new RedBlackTree<Word>();
		for (Word x : words) {
			try {
				this.dictionary.insert(x);
			} catch (Exception e) {
				continue;
			}
		}
	}
	
	/**
	 * The getWord method returns a word if the given word exists in the dictionary.
	 * 
	 * @param word A string representing the word to look for.
	 * @return A string representing the word if found
	 * @throws NoSuchElementException Exception thrown if word not found.
	 */
	@Override
	public Word getWord(String word) throws NoSuchElementException {
		// Determine if word exists in the dictionary and return it if it exists
		try {
			Word foundWord = this.searchForItem(word.toLowerCase());

			return foundWord;
		} catch (NoSuchElementException e) {
			// Exception thrown if word does not exist in dictionary.
			throw new NoSuchElementException("Word not found in the dictionary.");
		}
	}

	/**
	 * The getWord(int) method returns a word from the arrayList given an index.
	 * 
	 * @param num A int represeting the index to get a word from.
	 * @return A String represeting the word found at a given index.
	 * @throws IllegalArgumentException Exception thrown if int is greater or
	 *                                  smaller than arrayList of words.
	 */
	@Override
	public Word getWord(int num) throws IllegalArgumentException {
		// Check if parameter is within the size of list of words
		if (num > words.size() - 1 || num < 0)
			throw new IllegalArgumentException("Index exceeds size of dictionary.");

		// Word at given index
		Word wordToReturn = words.get(num);

		return wordToReturn;
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
	 * This method searches for a word in the dictionary. If the word exists, it is
	 * returned
	 * 
	 * @param item The string value of the word being searched for
	 * @return A word object representing the word thats found.
	 * @throws NoSuchElementException Thrown if the word is not in the dictionary.
	 */
	private Word searchForItem(String item) throws NoSuchElementException {
		// Root of the dictionary tree
		RedBlackTree.Node<Word> wordToCompare = dictionary.root;

		// While loop to iterate through tree
		while (wordToCompare != null) {
			// String value of word being compared to
			String wordData = wordToCompare.data.getWord().toLowerCase();

			// Finding the word though the tree
			if (wordData.compareTo(item) < 0) {
				wordToCompare = wordToCompare.rightChild;
			} else if (wordData.compareTo(item) > 0) {
				wordToCompare = wordToCompare.leftChild;
			} else {
				// The word returned when found.
				return wordToCompare.data;
			}
		}

		// Exception thrown if word not found.
		throw new NoSuchElementException("Word was not found in dictionary.");
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
		for (int x = wordLength - 1; x >= 0; x--) {
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
					if (z.getWord().toLowerCase().equals(changedWord) && !changedWord.equals(word))
						suggestions.add(changedWord);
				}
			}
		}
		// Return suggestions
		return suggestions;
	}
}
