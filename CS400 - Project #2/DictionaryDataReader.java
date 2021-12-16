// --== CS400 File Header Information ==--
// Name: Anthony Segedi
// Email: segedi@wisc.edu
// Team: Blue
// Group: AB
// TA: Mu Cai    
// Lecturer: Florian
// Notes to Grader: None
import java.util.regex.PatternSyntaxException;
import java.lang.StringBuilder;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.zip.DataFormatException;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
public class DictionaryDataReader {

	/**
	 * Returns a list of all words added by the dictionary.
	 * @return a List<Word> containing all parsed Word objects.
	 */
	public static List<Word> readData(String filename) throws IOException, DataFormatException, FileNotFoundException {
		// Initialize huge array
		ArrayList<Word> list = new ArrayList<>(100000);
		// May throw a FileNotFoundException in creation of FileReader
		BufferedReader reader = new BufferedReader(new FileReader(filename));
		Word word = null;
		Word previous = null;
		while (reader.ready()) {
			word = parse(reader.readLine(), previous);
				if (word != null) {
					list.add(word);
					previous = word;
				}
				word = null;
		}
		if (list.isEmpty())
			throw new DataFormatException("No words could be successfully parsed from file");
		return list;
	}
	
	/** 
	 * Utilizes an optimized switch statement to find words that match the types given by the dictionary file.
	 * a word is a type or part of a definition/ the word itself. Some cases are very specific. Cannot guarentee all
	 * cases are covered as of 03/16/21
	 * @param words The String array of words
	 * @param index The int index of the current word
	 */
	private static boolean isType(String[] words, int index) {
		if (index >= words.length)
			return false;
			switch (words[index]) {
				case "adv.":
				case "Adv.":
				case "-adv.":
				case "-Adv.":
				case "v.":
				case "V.":
				case "-v.":
				case "-V.":
				case "adj.":
				case "Adj.":
				case "-adj.":
				case "-Adj.":
				case "prefix":
				case "Prefix":
				case "suffix":
				case "Suffix":
				case "n.":
				case "N.":
				case "-n.":
				case "-N>":
				case "n.pl.":
				case "N.pl":
				case "predic.":
				case "Predic.":
				case "gram.":
				case "Gram.":
				case "comb.":
				case "Comb.":
				case "colloq.":
				case "Collo1.":
				case "hist.":
				case "Hist.":
				case "esp.":
				case "Esp.":
				case "-prep":
				case "-Prep":
				case "naut.":
				case "Naut.":
				case "aeron.":
				case "Aeron.":
				case "Pron.":
				case "pron.":
				case "abbr.":
				case "Abbr.":
				case "var.":
				case "Var.":
				case "contr.":
				case "Contr.":
				case "attrib.":
				case "Attrib.":
				case "int.":
				case "Int.":
				case "slang":
				case "Slang":
				case "offens.":
				case "Offens.":
				case "pawn.":
				case "Pawn.":
				case "symb.":
				case "Symb.":
					// Is a valid type or used only by types
					return true;
				case "US":
					// Make sure it is followed by "slang"
					index++;
					if (index < words.length)
						return words[index].equalsIgnoreCase("slang");
					return false;
				case "&":
					// Make sure it is followed by one of the types
					// Case where the word is out of bounds is handled
					return isType(words, ++index);
				case "coarse":
					// Make sure it is followed by "slang"
					index++;
					if (index < words.length)
						return words[index].equalsIgnoreCase("slang");
					return false;
				case "past":
					// check for next word to be "part."
					index++;
					if (index < words.length)
						return words[index].equalsIgnoreCase("part.");
					return false;
				default:
					return false;
			}
	}

	/**
	 * Parses the given input, returing a Word object
	 * @param input the input to parse
	 * @param previous The previous word parsed (used for the cases where -Usage is the first word. Handles adding it to the definition list)
	 * @return A Word object from appropriately formatted text or null if the input was blank or a single letter.
	 * @throws DataFormatException if the input cannot be parsed with spaces or if word did not have associated data (checks same line)
	 */
	private static Word parse(String input, Word previous) throws DataFormatException {
		// avoid empty lines
		if (input.isBlank()) 
			return null;
		final String SPACE = " ";
		String[] parsed = null;
		try {
			parsed = input.split(SPACE);
		} catch (PatternSyntaxException e) {
			throw new DataFormatException("Input could not be parsed by spaces");
		}

		// Handle cases where the Dictionary uses letters as headings, otherwise throw DataFormatException
		if (parsed.length < 2)
			if (parsed[0].length() == 1)
				return null;
			else
				throw new DataFormatException("Word without data format error: " + input);

		// Handle the special case of the word "-Usage"
		if (parsed[0].equals("-Usage")) {
			if (previous == null)
				throw new DataFormatException("Illegal use of \"-Usage\" before the first Word object was created.");
			List<String> list = previous.getDefinitions();
			list.add(input);
			// Return null - don't add a new word
			return null;
		}

	       	String word = "";
		String type = "";
		String current = null;
		int parseStart = 0;
		// for loop parsing for the word itself, stops at the first type given (@see #isType(String[], int))
		for (int i = 0; i < parsed.length; i++) {
			current = parsed[i];
			if (i == 0 || !isType(parsed, i)) {
				// if the current word is not a type
				word += current;
				word += SPACE;
				// skip the break
				continue;
			}
			// word was a type listed, ending loop
			// start parsing at current index in next loop (for definitions with types included)
			parseStart = i;
			break;
		}
		// Avoid adding blank words - possible errors
		if (word.isBlank())
			throw new DataFormatException("Parsed a blank word: " + Arrays.toString(parsed));
		// Clean word string - removing number if needed
		word = word.trim();
		int lastIndex = word.length() - 1;
		if (Character.isDigit(word.charAt(lastIndex)))
			word = word.substring(0, lastIndex);

		// Handle the special case of the word ending in a digit and being preceded by the same word
		if (previous != null && word.equals(previous.getWord())) {
			// Add it as a definition
			List<String> defList = previous.getDefinitions();
			input.replaceAll("\\[", "Origin: ");
			input.replaceAll("\\]", "");
			defList.add("[ " + input + "  ]");
			return null;
		}

		ArrayList<String> definitions = new ArrayList<>(5);
		// Use StringBuilder over buffer since I don't need it to be synchronized - more efficient
		StringBuilder builder = new StringBuilder();
		int originStart = -1;

		// for loop that iterates adding all found definitions
		for (int i = parseStart; i < parsed.length; i++) {
			current = parsed[i];
			if (current.startsWith("[")) {
				originStart = i;
				break;
			} else if (current.length() == 1) {
				char digit = current.charAt(0);
				if (Character.isDigit(digit)) {
					// Add the parts of speech to the first definition, but skip the number
					if (digit == '1')
						continue;
				// The only case in dictionary should be when listing multiple definitions (trimmed)
					definitions.add(builder.toString().trim());
					// clears buffer
					builder.setLength(0);
					// The continue skips adding the number to the definition
					continue;
				}
			}
			builder.append(current);
			builder.append(SPACE);
		}
		
		// Add last definition (trimmed)
		definitions.add(builder.toString().trim());

		// reset builder
		builder.setLength(0);

		String origin = "";
		// for loop that iterates for the origin
		if (originStart >= 0)
			for (int i = originStart; i < parsed.length; i++) {
				current = parsed[i];
				builder.append(current);
				if (current.endsWith("]")) {
					// Gets rid of brackets
					String temp = builder.toString();
					temp = temp.substring(1, temp.length() - 1);
					origin = temp;
					break;
				} else {
					builder.append(SPACE);
				}
			}
		else
			origin = "No listed origin.";
		return new Word(word, definitions, origin);
	}

	/**
	 * Returns a dummy list containing fabricated Word objects.
	 * @return A list of static Word objects (always returns same Word objects).
	 */
	private static List<Word> giveDummy() {
		
		// Create list of dummy Word objects
		List<Word> list = new ArrayList<>(4);

		// Create word Strings
		String[] words = {"Land-Cloud", "Boi", "Destiny", "Java"};

		// Create definition lists
		List<String> list1 = new ArrayList<>(2);
		list1.add("A heckin cute fluffin doggo. Is a cuddling cloud.");
		list1.add("Fog touching the ground.");
		List<String> list2 = new ArrayList<>(2);
		list2.add("As in a guy, dude, bro, sick kid, baller, etc.");
		list2.add("A lil boyo");
		List<String> list3 = new ArrayList<>(1);
		list3.add("Fate, as in a known future, or something that will pass.");
		List<String> list4 = new ArrayList<>(1);
		list4.add("High-level coding language.");

		// Create definition list array
		List[] definitions = {list1, list2, list3, list4};

		// Create origin array
		String[] origins = {"Internet", "AJ", "Anonymous", "Java - summarized"};

		// Adds dummy words as defined above
		for (int i = 0; i < words.length; i++) {
			list.add(new Word(words[i], definitions[i], origins[i]));
		}
		return list;
	}
}
