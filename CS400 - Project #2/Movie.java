import java.util.List;

// --== CS400 File Header Information ==--
// Name: Gautama Manja
// Email: manja@wisc.edu
// Team: AB Blue
// Role: Data Wrangler
// TA: Mu Cai
// Lecturer: Gary Dahl
// Notes to Grader: 
//

public class Movie implements MovieInterface
{	//all the necessary fields for a movie class
	private String title;
	private Integer year;
	private List<String> genre;
	private String director;
	private String description;
	private Float avg_vote;
	
	/**
	 * Constructor method for the Movie class
	 */
	public Movie(String title, Integer year, List<String> genre, String director,
			String description, Float avg_vote) 
	{
		this.title = title;
		this.year = year;
		this.genre = genre;
		this.director = director;
		this.description = description;
		this.avg_vote = avg_vote;
	}

	@Override
	public String getTitle()
	{    return this.title;}

	@Override
	public Integer getYear() 
	{    return this.year;}

	@Override
	public List<String> getGenres()
	{    return this.genre;}

	@Override
	public String getDirector() 
	{    return this.director;}

	@Override
	public String getDescription()
	{    return this.description;}

	@Override
	public Float getAvgVote()
	{    return this.avg_vote;}

	/**
	 *CompareTo function that compares two movies via average votes such that a list will return in descending order
	 */
	@Override
	public int compareTo(MovieInterface otherMovie)
	{    
	    if(avg_vote > otherMovie.getAvgVote()){
	        return -1;
	    }
      	    if(avg_vote == otherMovie.getAvgVote()){
	        return 0;
 	    }
	    
	    return 1;
	}
	/**
	 * ToString method that returns a structured string that represents all fields within the movie class.
	 */
	@Override
	public String toString(){
	    String genreString = null;
	    for(String s : genre){
		if(genreString == null){genreString = s;}
		genreString += ", " + s; 
	    }

	     String returnString = "\nTitle: " + title + "\nYear: " + year.toString() + "\nGenre(s): " + genreString
		     + "\nDirector: " + director + "\nDescription: " + description + "\nAverage Vote: " + avg_vote.toString();
	     return returnString;
	}
}

