// --== CS400 File Header Information ==--
// Name: Anthony Segedi
// Email: segedi@wisc.edu
// Team: Blue
// Group: AB
// TA: Mu Cai    
// Lecturer: Florian
// Notes to Grader: None
import java.lang.StringBuffer;
import java.util.List;
public class Word implements Comparable<Word> {
	private String word;
	private List<String> definitions;
	private String origin;

	/**
	 * Creates a Word object with given parameters.
	 */
	public Word(String word, List<String> definitions, String origin) {
		this.word = word;
		this.definitions = definitions;
		this.origin = origin;
	}

	/**
	 * Returns the word defined.
	 * @return this object's word.
	 */
	public String getWord() {
		return word;
	}

	/**
	 * Returns this word's origin.
	 * @return the origin of the word.
	 */
	public String getOrigin() {
		return origin;
	}

	/**
	 * Returns this word's definitions.
	 * @return all applicable definitions.
	 */
	public List<String> getDefinitions() {
		return definitions;
	}

	@Override
	public String toString() {
		StringBuffer buffer = new StringBuffer();
		buffer.append(word + "\n\nDefinitions:\n");
		for (int i = 0; i < definitions.size(); i++) {
			buffer.append("" + (i + 1) + " - " + definitions.get(i) + "\n");
		}
		buffer.append("\nOrigin\n" + origin);
		return buffer.toString();
	}

	@Override
	public int compareTo(Word o) {
		return word.toLowerCase().compareTo(o.getWord().toLowerCase());
	}
}
