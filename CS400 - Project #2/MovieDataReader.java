import java.util.List;
import java.util.zip.DataFormatException;
import java.util.ArrayList;
import java.io.IOException;
import java.io.Reader;
import java.io.BufferedReader;

// --== CS400 File Header Information ==--
// Name: Gautama Manja
// Email: manja@wisc.edu
// Team: AB Blue
// Role: Data Wrangler
// TA: Mu Cai
// Lecturer: Gary Dahl
// Notes to Grader: 
//


/**
 * @author Gautama Manja
 * This class implements the MovieDataReader Interface and contains a method 
 * that will return a list of Movie objects when passed in a valid .csv file
 */
public class MovieDataReader implements MovieDataReaderInterface{
    
    public List<MovieInterface> readDataSet(Reader inputFileReader) throws IOException, DataFormatException{
	List<MovieInterface> movies = new ArrayList<MovieInterface>();

	BufferedReader fileRead = new BufferedReader(inputFileReader);
	String currentLine = "";
	//The first line models the data format, so we must parse it individually first
	currentLine = fileRead.readLine();
        String[] array = currentLine.split(","); // array contains all tokens of the header
	int formatSize = array.length; // size of each line in terms of tokens
	
	int[] indices = new int[6];
	//after for loop, indices array will tell us where the relevant information is for all of the parsed strings
	for(int i = 0; i < formatSize; i++){
	    switch(array[i]){
		case "title":
		    indices[0] = i;
		    break;
		case "year":
		    indices[1] = i;
		    break;
		case "genre":
		    indices[2] = i;
		    break;
		case "director":
		    indices[3] = i;
		    break;
		case "description":
		    indices[4] = i;
		    break;
		case "avg_vote":
		    indices[5] = i;
		    break;
	    }
	}

	//parse every line, checking for DataFormatExceptions and IOExceptions while building up a list of Movie objects
	while((currentLine = fileRead.readLine()) != null){
	    String[] tokens = currentLine.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1); //only split when comma is not in quotes, credits in README
	    //Check all relevant fields for DataFormatExceptions
	    if(tokens.length < formatSize || tokens.length > formatSize) {
	        throw new DataFormatException("Data is not correctly formatted");
	    }
	    if(tokens[indices[0]] == null || !(tokens[indices[0]] instanceof String) ){
		throw new DataFormatException("Title is not correctly formatted");
	    }
	    if(tokens[indices[1]] == null || !(Integer.valueOf(tokens[indices[1]]) instanceof Integer) || Integer.valueOf(tokens[indices[1]]).intValue() > 9999
			||  Integer.valueOf(tokens[indices[1]]).intValue() < 1000 ){
		throw new DataFormatException("Year is not correctly formatted");
	    }
	    if(tokens[indices[2]] == null ||!(tokens[indices[2]] instanceof String)){
		throw new DataFormatException("Genre is not correctly formatted");
	    }
	    if(tokens[indices[3]] == null ||!(tokens[indices[3]] instanceof String)){
		throw new DataFormatException("Director is not correctly formatted");
	    }
	    if(tokens[indices[4]] == null ||!(tokens[indices[4]] instanceof String)){
		throw new DataFormatException("Description is not correctly formatted");
	    }
	    if(tokens[indices[5]] == null ||!(Float.valueOf(tokens[indices[5]]) instanceof Float) || Float.valueOf(tokens[indices[5]]).floatValue() < 0.0 
			 || Float.valueOf(tokens[indices[5]]).floatValue() > 10.0){
		throw new DataFormatException("Average Vote is not correctly formatted");
	    }

	    //parse the "genre" string further into a list of strings while cleaning it up
	    String[] removeQuotes = tokens[indices[2]].split("\"");
	    String temp = "";
	    for(int i = 0; i < removeQuotes.length; i++){
		temp += removeQuotes[i];
	    }  
	    temp = temp.trim();
	    String[] genreTokens = temp.split(",");
	    List<String> genre = new ArrayList<String>();
	    for(int i = 0; i < genreTokens.length; i++){
		genre.add(genreTokens[i].trim());
	    }

	    //create movie
	    MovieInterface movieToAdd = new Movie(tokens[indices[0]], Integer.valueOf(tokens[indices[1]]), genre, tokens[indices[3]],
			   tokens[indices[4]], Float.valueOf(tokens[indices[5]]));
	    movies.add(movieToAdd);
	}
	//close reader and return list
	fileRead.close();
	return movies;	
    }
	
}
