// --== CS400 File Header Information ==--
// Name: Sreeram Danda
// Email: sdanda@wisc.edu
// Team: AB
// TA: Mu Cai
// Lecturer: Gary Dahl
// Notes to Grader: -N/A-
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public interface BackendInterface {
	
	Word getWord(String word) throws NoSuchElementException;
	
	Word getWord(int num) throws NoSuchElementException;
	
	int getSize();
	
	boolean isEmpty();

	void insert(String word, List<String> definition, String origin);

	void add(String word, List<String> definition, String origin);
	
	ArrayList<String> getSuggestions(String word);
}
