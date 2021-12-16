// --== CS400 File Header Information ==--
// Name: Anthony John Segedi
// Email: segedi@wisc.edu
// Team: Blue
// Group: AB
// TA: Mu Cai
// Lecturer: Florian
// Notes: None

import java.io.StringReader;

/**
 * This class contains a set of tests for the back end of the Movie Mapper project.
 */
public class TestBackend {
	
	private static final boolean USING_DUMMY = false;

	public static void main(String[] args) {
		(new TestBackend()).runTests();
	}

	/**
	 * Runs all tests
	 */
	public void runTests() {
		// add all tests to this method
		if (this.testInitialNumberOfMovies()) {
			System.out.println("Test initial number of movies: PASSED");
		} else {
			System.out.println("Test initial number of movies: FAILED");
		}
		if (this.testGetAllGenres()) {
			System.out.println("Test get all genres: PASSED");
		} else {
			System.out.println("Test get all genres: FAILED");
		}
		if (this.testGetThreeMoviesInitial()) {
			System.out.println("Test get three movies sorted by rating: PASSED");
		} else {
			System.out.println("Test get three movies sorted by rating: FAILED");
		}
		String output = "Test Selections: " + (testSelections() ? "PASSED" : "FAILED");
		output += "\nTest Getters and Setters: " + (testGettersAndSetters() ? "PASSED" : "FAILED");
		System.out.println(output);
	}
	
	/**
	 * This test instantiates the back end with three movies and tests whether the
	 * initial selection is empty (getNumberOfMovies() returns 0). It passes when
	 * 0 is returned and fails in all other cases, including when an exception is
	 * thrown.
	 * @return true if the test passed, false if it failed
	 */
	public boolean testInitialNumberOfMovies() {
		try {
			// instantiate once BackendInterface is implemented
			BackendInterface backendToTest = new Backend(new StringReader(
					"title,original_title,year,genre,duration,country,language,director,writer,production_company,actors,description,avg_vote\n"
					+ "The Source of Shadows,The Source of Shadows,2020,Horror,83,USA,English,\"Ryan Bury, Jennifer Bonior\",\"Jennifer Bonior, Trevor Botkin\",Four Thieves Productions,\"Ashleigh Allard, Tom Bonington, Eliane Gagnon, Marissa Kaye Grinestaff, Jenna Heffernan, Joshua Hummel, Janice Kingsley, Chris Labasbas, Jared Laufree, Dominic Lee, Vic May, Sienna Mazzone, Lizzie Mounter, Grace Mumm, Ashley Otis\",\"A series of stories woven together by one of our most primal fears, the fear of the unknown.\",3.5\n"
					+ "The Insurrection,The Insurrection,2020,Action,90,USA,English,Rene Perez,Rene Perez,,\"Michael Paré, Wilma Elles, Joseph Camilleri, Rebecca Tarabocchia, Jeanine Harrington, Malorie Glavan, Danner Boyd, Michael Cendejas, Woody Clendenen, Keely Dervin, Aaron Harvey, Tony Jackson, Michael Jarrod, Angelina Karo, Bernie Kelly\",The director of the largest media company wants to expose how left-wing powers use film to control populations.,2.9\n"
					+ "Valley Girl,Valley Girl,2020,\"Comedy, Musical, Romance\",102,USA,English,Rachel Lee Goldenberg,\"Amy Talkington, Andrew Lane\",Sneak Preview Productions,\"Jessica Rothe, Josh Whitehouse, Jessie Ennis, Ashleigh Murray, Chloe Bennet, Logan Paul, Mae Whitman, Mario Revolori, Rob Huebel, Judy Greer, Alex Lewis, Alex MacNicoll, Danny Ramirez, Andrew Kai, Allyn Rachel\",\"Set to a new wave '80s soundtrack, a pair of young lovers from different backgrounds defy their parents and friends to stay together. A musical adaptation of the 1983 film.\",5.4\n"
			));
			if (backendToTest.getNumberOfMovies() == 0) {
				// test passed
				return true;
			} else {
				// test failed
				return false;
			}
		} catch (NullPointerException e) {
			System.out.println("Encountered NullPointer, likely from MovieDataReader class if it has not been implemented (otherwise check name in Backend.java)");
			System.out.println("Error Msg: " + e.getMessage());
			e.printStackTrace();
			// test failed
			return false;
		}
	}
	
	/**
	 * This test instantiates the back end with three movies and tests whether
	 * the getAllGenres method return the correct set of genres for those three
	 * movies.
	 * @return true if the test passed, false if it failed
	 */
	public boolean testGetAllGenres() {
		// NOTE Did not pass this test individually since the genres that this test expects were not loaded as the dummy class I made does not read in like this.
		// To fix this I used a final boolean USING_DUMMY with appropriate genres
		try {
			// instantiate once BackendInterface is implemented
			BackendInterface backendToTest = new Backend(new StringReader(
					"title,original_title,year,genre,duration,country,language,director,writer,production_company,actors,description,avg_vote\n"
					+ "The Source of Shadows,The Source of Shadows,2020,Horror,83,USA,English,\"Ryan Bury, Jennifer Bonior\",\"Jennifer Bonior, Trevor Botkin\",Four Thieves Productions,\"Ashleigh Allard, Tom Bonington, Eliane Gagnon, Marissa Kaye Grinestaff, Jenna Heffernan, Joshua Hummel, Janice Kingsley, Chris Labasbas, Jared Laufree, Dominic Lee, Vic May, Sienna Mazzone, Lizzie Mounter, Grace Mumm, Ashley Otis\",\"A series of stories woven together by one of our most primal fears, the fear of the unknown.\",3.5\n"
					+ "The Insurrection,The Insurrection,2020,Action,90,USA,English,Rene Perez,Rene Perez,,\"Michael Paré, Wilma Elles, Joseph Camilleri, Rebecca Tarabocchia, Jeanine Harrington, Malorie Glavan, Danner Boyd, Michael Cendejas, Woody Clendenen, Keely Dervin, Aaron Harvey, Tony Jackson, Michael Jarrod, Angelina Karo, Bernie Kelly\",The director of the largest media company wants to expose how left-wing powers use film to control populations.,2.9\n"
					+ "Valley Girl,Valley Girl,2020,\"Comedy, Musical, Romance\",102,USA,English,Rachel Lee Goldenberg,\"Amy Talkington, Andrew Lane\",Sneak Preview Productions,\"Jessica Rothe, Josh Whitehouse, Jessie Ennis, Ashleigh Murray, Chloe Bennet, Logan Paul, Mae Whitman, Mario Revolori, Rob Huebel, Judy Greer, Alex Lewis, Alex MacNicoll, Danny Ramirez, Andrew Kai, Allyn Rachel\",\"Set to a new wave '80s soundtrack, a pair of young lovers from different backgrounds defy their parents and friends to stay together. A musical adaptation of the 1983 film.\",5.4\n"
			)); 
			if (!USING_DUMMY) {
				if (backendToTest.getAllGenres().size() == 5
					&& backendToTest.getAllGenres().contains("Horror")
					&& backendToTest.getAllGenres().contains("Action")
					&& backendToTest.getAllGenres().contains("Comedy")
					&& backendToTest.getAllGenres().contains("Musical")
					&& backendToTest.getAllGenres().contains("Romance")) {
					// test passed
					return true;
				} else {
					// test failed
					String allGenres = "";
					for (String genre : backendToTest.getAllGenres()) {
						allGenres += "\"" + genre + "\", ";
					}
					System.out.println("Genres in all genres list: " + allGenres);
					System.out.println("All Genre List expected: \"Horror\", \"Action\", \"Comedy\", \"Musical\", \"Romance\""); 
					System.out.println("**Note that quotes are only for showing invisible characters, such as spaces.");
					return false;
				}
			} else {
				return (backendToTest.getAllGenres().size() == 5
					&& backendToTest.getAllGenres().contains("Action")
					&& backendToTest.getAllGenres().contains("Adventure")
					&& backendToTest.getAllGenres().contains("Mystery")
					&& backendToTest.getAllGenres().contains("Documentary")
					&& backendToTest.getAllGenres().contains("Romance"));
			}
		} catch (NullPointerException e) {
			System.out.println("Encountered NullPointer, likely from MovieDataReader class if it has not been implemented (otherwise check name in Backend.java)");
			System.out.println("Error Msg: " + e.getMessage());
			e.printStackTrace();
			// test failed
			return false;
		}
	}
	
	/**
	 * This test instantiates the back end with three movies and tests whether the
	 * initial list returned by getThreeMovies starting with the first movie (0)
	 * is empty. It passes when 0 is returned and fails in all other cases, including
	 * when an exception is thrown.
	 * @return true if the test passed, false if its failed
	 */
	public boolean testGetThreeMoviesInitial() {
		try {
			// instantiate once BackendInterface is implemented
			BackendInterface backendToTest = new Backend(new StringReader(
					"title,original_title,year,genre,duration,country,language,director,writer,production_company,actors,description,avg_vote\n"
					+ "The Source of Shadows,The Source of Shadows,2020,Horror,83,USA,English,\"Ryan Bury, Jennifer Bonior\",\"Jennifer Bonior, Trevor Botkin\",Four Thieves Productions,\"Ashleigh Allard, Tom Bonington, Eliane Gagnon, Marissa Kaye Grinestaff, Jenna Heffernan, Joshua Hummel, Janice Kingsley, Chris Labasbas, Jared Laufree, Dominic Lee, Vic May, Sienna Mazzone, Lizzie Mounter, Grace Mumm, Ashley Otis\",\"A series of stories woven together by one of our most primal fears, the fear of the unknown.\",3.5\n"
					+ "The Insurrection,The Insurrection,2020,Action,90,USA,English,Rene Perez,Rene Perez,,\"Michael Paré, Wilma Elles, Joseph Camilleri, Rebecca Tarabocchia, Jeanine Harrington, Malorie Glavan, Danner Boyd, Michael Cendejas, Woody Clendenen, Keely Dervin, Aaron Harvey, Tony Jackson, Michael Jarrod, Angelina Karo, Bernie Kelly\",The director of the largest media company wants to expose how left-wing powers use film to control populations.,2.9\n"
					+ "Valley Girl,Valley Girl,2020,\"Comedy, Musical, Romance\",102,USA,English,Rachel Lee Goldenberg,\"Amy Talkington, Andrew Lane\",Sneak Preview Productions,\"Jessica Rothe, Josh Whitehouse, Jessie Ennis, Ashleigh Murray, Chloe Bennet, Logan Paul, Mae Whitman, Mario Revolori, Rob Huebel, Judy Greer, Alex Lewis, Alex MacNicoll, Danny Ramirez, Andrew Kai, Allyn Rachel\",\"Set to a new wave '80s soundtrack, a pair of young lovers from different backgrounds defy their parents and friends to stay together. A musical adaptation of the 1983 film.\",5.4\n"
			));
			if (backendToTest.getThreeMovies(0).size() == 0) {
				// test passed
				return true;
			} else {
				// test failed
				return false;
			}
		} catch (NullPointerException e) {
			System.out.println("Encountered NullPointer, likely from MovieDataReader class if it has not been implemented (otherwise check name in Backend.java)");
			System.out.println("Error Msg: " + e.getMessage());
			e.printStackTrace();
			// test failed
			return false;
		}
	}
	
	/**
	 * Tests to make sure that the Backend considers the selections correctly when returning the three movie lists.
	 * Considers tests with selections that have one genre, multiple genres, and ratings. Also checks the index part of
	 * getThreeMovies(int) in dummyClass.
	 * @return true if test passes, false otherwise.
	 */
	public boolean testSelections() {
		BackendInterface backend = new Backend(new StringReader(
					"title,original_title,year,genre,duration,country,language,director,writer,production_company,actors,description,avg_vote\n"
					+ "The Source of Shadows,The Source of Shadows,2020,Horror,83,USA,English,\"Ryan Bury, Jennifer Bonior\",\"Jennifer Bonior, Trevor Botkin\",Four Thieves Productions,\"Ashleigh Allard, Tom Bonington, Eliane Gagnon, Marissa Kaye Grinestaff, Jenna Heffernan, Joshua Hummel, Janice Kingsley, Chris Labasbas, Jared Laufree, Dominic Lee, Vic May, Sienna Mazzone, Lizzie Mounter, Grace Mumm, Ashley Otis\",\"A series of stories woven together by one of our most primal fears, the fear of the unknown.\",3.5\n"
					+ "The Insurrection,The Insurrection,2020,Action,90,USA,English,Rene Perez,Rene Perez,,\"Michael Paré, Wilma Elles, Joseph Camilleri, Rebecca Tarabocchia, Jeanine Harrington, Malorie Glavan, Danner Boyd, Michael Cendejas, Woody Clendenen, Keely Dervin, Aaron Harvey, Tony Jackson, Michael Jarrod, Angelina Karo, Bernie Kelly\",The director of the largest media company wants to expose how left-wing powers use film to control populations.,2.9\n"
					+ "Valley Girl,Valley Girl,2020,\"Comedy, Musical, Romance\",102,USA,English,Rachel Lee Goldenberg,\"Amy Talkington, Andrew Lane\",Sneak Preview Productions,\"Jessica Rothe, Josh Whitehouse, Jessie Ennis, Ashleigh Murray, Chloe Bennet, Logan Paul, Mae Whitman, Mario Revolori, Rob Huebel, Judy Greer, Alex Lewis, Alex MacNicoll, Danny Ramirez, Andrew Kai, Allyn Rachel\",\"Set to a new wave '80s soundtrack, a pair of young lovers from different backgrounds defy their parents and friends to stay together. A musical adaptation of the 1983 film.\",5.4\n"
			));
		if (!USING_DUMMY) {
			/*
			 * Horror 3.5
			 * Action 2.9
			 * Comedy, Musical, Romance 5.4
			 */
			if (!backend.getThreeMovies(0).isEmpty() && backend.getNumberOfMovies() != 0) {
				System.out.println("Error: movies not empty to start");
				return false;
			}
			backend.addGenre("Action");
			if (backend.getThreeMovies(0).size() != 1 && backend.getNumberOfMovies() != 1) {
				System.out.println("Incorrect number of movies in return");
				return false;
			}
			backend.removeGenre("Action");
			backend.addGenre("Musical");
			backend.addGenre("Romance");
			if (backend.getThreeMovies(0).size() != 1 && backend.getNumberOfMovies() != 1) {
				System.out.println("Incorrect number of movies returned: exp 1");
				return false;
			}
			backend.addAvgRating("5.4");
			if (backend.getThreeMovies(0).size() != 1 && backend.getNumberOfMovies() != 1) {
				System.out.println("Broken rating check");
				return false;
			}
			backend.addAvgRating("3");
			backend.removeAvgRating("5.4");
			if (backend.getThreeMovies(0).size() != 0 && backend.getNumberOfMovies() != 0) {
				System.out.println("Broken rating check");
				return false;
			}
		} else {
			/*
			 * Action 4.9
			 * Adventure 1.2
			 * Mystery 4.7
			 * Documentary 3.9
			 * Romance, Action, Adventure 2.4
			 */
			if (!backend.getThreeMovies(0).isEmpty() && backend.getNumberOfMovies() != 0) {
				System.out.println("Error: movies not empty to start");
				return false;
			}
			backend.addGenre("Action");
			if (backend.getThreeMovies(0).size() != 2 && backend.getNumberOfMovies() != 2){
				System.out.println("Incorrect number of movies in return");
				return false;
			}
			if (backend.getThreeMovies(1).size() != 1) {
				System.out.println("Incorret number of movies returned for index 1");
				return false;
			}
			backend.addGenre("Romance");
			if (backend.getThreeMovies(0).size() != 1 && backend.getNumberOfMovies() != 1) {
				System.out.println("Incorrect number of movies returned: exp 1");
				return false;
			}
			backend.addAvgRating("2.4");
			if (backend.getThreeMovies(0).size() != 1 && backend.getNumberOfMovies() != 1) {
				System.out.println("Broken rating check");
				return false;
			}
			backend.addAvgRating("1");
			backend.removeAvgRating("2.4");
			if (backend.getThreeMovies(0).size() != 0 && backend.getNumberOfMovies() != 0) {
				System.out.println("Broken rating check");
				return false;
			}
		}
		return true;
	}

	/**
	 * Tests the add, remove, and get methods for genres and ratings
	 * @return true if test passes, false otherwise.
	 */
	public boolean testGettersAndSetters() {
		BackendInterface backend = new Backend(new StringReader(
					"title,original_title,year,genre,duration,country,language,director,writer,production_company,actors,description,avg_vote\n"
					+ "The Source of Shadows,The Source of Shadows,2020,Horror,83,USA,English,\"Ryan Bury, Jennifer Bonior\",\"Jennifer Bonior, Trevor Botkin\",Four Thieves Productions,\"Ashleigh Allard, Tom Bonington, Eliane Gagnon, Marissa Kaye Grinestaff, Jenna Heffernan, Joshua Hummel, Janice Kingsley, Chris Labasbas, Jared Laufree, Dominic Lee, Vic May, Sienna Mazzone, Lizzie Mounter, Grace Mumm, Ashley Otis\",\"A series of stories woven together by one of our most primal fears, the fear of the unknown.\",3.5\n"
					+ "The Insurrection,The Insurrection,2020,Action,90,USA,English,Rene Perez,Rene Perez,,\"Michael Paré, Wilma Elles, Joseph Camilleri, Rebecca Tarabocchia, Jeanine Harrington, Malorie Glavan, Danner Boyd, Michael Cendejas, Woody Clendenen, Keely Dervin, Aaron Harvey, Tony Jackson, Michael Jarrod, Angelina Karo, Bernie Kelly\",The director of the largest media company wants to expose how left-wing powers use film to control populations.,2.9\n"
					+ "Valley Girl,Valley Girl,2020,\"Comedy, Musical, Romance\",102,USA,English,Rachel Lee Goldenberg,\"Amy Talkington, Andrew Lane\",Sneak Preview Productions,\"Jessica Rothe, Josh Whitehouse, Jessie Ennis, Ashleigh Murray, Chloe Bennet, Logan Paul, Mae Whitman, Mario Revolori, Rob Huebel, Judy Greer, Alex Lewis, Alex MacNicoll, Danny Ramirez, Andrew Kai, Allyn Rachel\",\"Set to a new wave '80s soundtrack, a pair of young lovers from different backgrounds defy their parents and friends to stay together. A musical adaptation of the 1983 film.\",5.4\n"
			));
			// Genre
			if (backend.getGenres().size() != 0) {
				System.out.println("Error with initial size of genre list");
				return false;
			}
			backend.addGenre("Genre");
			if (backend.getGenres().size() != 1) {
				System.out.println("Error with addGenre(String)");
				return false;
			}
			if (!backend.getGenres().get(0).equals("Genre")) {
				System.out.println("Error with String added by addGenre(String)");
				return false;
			}
			backend.removeGenre("Genre");
			if (backend.getGenres().size() != 0) {
				System.out.println("Error with removeGenre(String)");
				return false;
			}
			// Rating
			if (backend.getAvgRatings().size() != 0) {
				System.out.println("Error with initial size of ratings list");
				return false;
			}
			backend.addAvgRating("Genre");
			if (backend.getAvgRatings().size() != 1) {
				System.out.println("Error with addAvgRating(String)");
				return false;
			}
			if (!backend.getAvgRatings().get(0).equals("Genre")) {
				System.out.println("Error with String added by addAvgRating(String)");
				return false;
			}
			backend.removeAvgRating("Genre");
			if (backend.getGenres().size() != 0) {
				System.out.println("Error with removeAvgRating(String)");
				return false;
			}

		return true;
	}

}
