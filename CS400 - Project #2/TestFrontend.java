// --== CS400 File Header Information ==--
// Name: Sreeram Danda
// Email: sdanda@wisc.edu
// Team: Blue
// Role: Frontend Developer
// TA: Mu Ta
// Lecturer: Gary Dahl
// Notes to Grader: -N/A-
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.io.StringReader;

/**
 * This class contains a set of tests for the front end of the Movie Mapper project.
 */
public class TestFrontend {

	public static void main(String[] args) {
		(new TestFrontend()).runTests();
	}

	/**
	 * This method calls all of the test methods in the class and ouputs pass / fail
	 * for each test.
	 */
	public void runTests() {
		System.out.print("Test enter 'x' to exit (WARNING: if 'x' does not exit app, test won't exit and run indefinitely!): ");
		if (this.enterXToExit()) {
			System.out.println("PASSED");
		} else {
			System.out.println("FAILED");
		}
		System.out.print("Test frontend initially lists no movie (WARNING: if 'x' does not exit app, test won't exit and run indefinitely!): ");
		if (this.testFrontendInitialOutputNoMovie()) {
			System.out.println("PASSED");
		} else {
			System.out.println("FAILED");
		}
		System.out.print("Test 'g' load genre selection screen (WARNING: if pressing 'x' twice from the genre selection screen does not exit app, test won't exit and run indefinitely!): ");
		if (this.testFrontendGForGenres()) {
			System.out.println("PASSED");
		} else {
			System.out.println("FAILED");
		}
		System.out.print("Test 'r' load ratings selection screen (WARNING: if pressing 'x' twice from the ratings selection screen does not exit app, test won't exit and run indefinitely!): ");
		if (this.testFrontendRForRatings()) {
			System.out.println("PASSED");
		} else {
			System.out.println("FAILED");
		}
		System.out.println("Test genre selection initally lists no movie (WARNING: if pressing 'x' twice from the genre selection screen does not exit app, test won't exit and run indefinitely!): ");
		if (this.testFrontendNoGenereSelected()) {
			System.out.println("PASSED");
		} else {
			System.out.println("FAILED");
		}
}

    /**
	 * This test runs the front end and redirects its output to a string. It then
	 * passes in 'x' as a command. When the front end exists, the tests succeeds.
	 * If 'x' does not exist the app, the test will not terminate (it won't fail
	 * explicitely in this case). The test fails explicitely if the front end is
	 * not instantiated or if an exception occurs.
	 * @return true if the test passed, false if it failed
	 */
	public boolean enterXToExit() {
		PrintStream standardOut = System.out;
		InputStream standardIn = System.in;
		try {
			// set the input stream to our input (with an x to test of the program exists)
			String input = "x";
			InputStream inputStreamSimulator = new ByteArrayInputStream(input.getBytes());
			System.setIn(inputStreamSimulator);
			ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
			// set the output to the stream captor to read the output of the front end
			System.setOut(new PrintStream(outputStreamCaptor));
			// instantiate when front end is implemented
			Object frontend = new Frontend();
			((Frontend)frontend).run(new Backend(new StringReader(
					"title,original_title,year,genre,duration,country,language,director,writer,production_company,actors,description,avg_vote\n"
					+ "The Source of Shadows,The Source of Shadows,2020,Horror,83,USA,English,\"Ryan Bury, Jennifer Bonior\",\"Jennifer Bonior, Trevor Botkin\",Four Thieves Productions,\"Ashleigh Allard, Tom Bonington, Eliane Gagnon, Marissa Kaye Grinestaff, Jenna Heffernan, Joshua Hummel, Janice Kingsley, Chris Labasbas, Jared Laufree, Dominic Lee, Vic May, Sienna Mazzone, Lizzie Mounter, Grace Mumm, Ashley Otis\",\"A series of stories woven together by one of our most primal fears, the fear of the unknown.\",3.5\n"
					+ "The Insurrection,The Insurrection,2020,Action,90,USA,English,Rene Perez,Rene Perez,,\"Michael Paré, Wilma Elles, Joseph Camilleri, Rebecca Tarabocchia, Jeanine Harrington, Malorie Glavan, Danner Boyd, Michael Cendejas, Woody Clendenen, Keely Dervin, Aaron Harvey, Tony Jackson, Michael Jarrod, Angelina Karo, Bernie Kelly\",The director of the largest media company wants to expose how left-wing powers use film to control populations.,2.9\n"
					+ "Valley Girl,Valley Girl,2020,\"Comedy, Musical, Romance\",102,USA,English,Rachel Lee Goldenberg,\"Amy Talkington, Andrew Lane\",Sneak Preview Productions,\"Jessica Rothe, Josh Whitehouse, Jessie Ennis, Ashleigh Murray, Chloe Bennet, Logan Paul, Mae Whitman, Mario Revolori, Rob Huebel, Judy Greer, Alex Lewis, Alex MacNicoll, Danny Ramirez, Andrew Kai, Allyn Rachel\",\"Set to a new wave '80s soundtrack, a pair of young lovers from different backgrounds defy their parents and friends to stay together. A musical adaptation of the 1983 film.\",5.4\n"
			)));
			// set the output back to standard out for running the test
			System.setOut(standardOut);
			// same for standard in
			System.setIn(standardIn);
			if (frontend == null) {
				// test fails
				return false;
			} else {
				// test passed
				return true;
			}
		} catch (Exception e) {
			// make sure stdin and stdout are set correctly after we get exception in test
			System.setOut(standardOut);
			System.setIn(standardIn);
			e.printStackTrace();
			// test failed
			return false;
		}
	}

	/**
	 * This test runs the front end and redirects its output to a string. It then
	 * passes in 'x' as a command to exit the app. The test succeeds if the front
	 * end does not contain any of the three movies (the movie titles are not in
	 * the string captured from the front end) by default. It fails if any of
	 * those three titles are present in the string or an exception occurs.
	 * @return true if the test passed, false if it failed
	 */
	public boolean testFrontendInitialOutputNoMovie() {
		PrintStream standardOut = System.out;
		InputStream standardIn = System.in;
		try {
			// set the input stream to our input (with an x to test of the program exists)
			String input = "x";
			InputStream inputStreamSimulator = new ByteArrayInputStream(input.getBytes());
			System.setIn(inputStreamSimulator);
			ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
			// set the output to the stream captor to read the output of the front end
			System.setOut(new PrintStream(outputStreamCaptor));
			// instantiate when front end is implemented
			Object frontend = new Frontend();
			((Frontend)frontend).run(new Backend(new StringReader(
					"title,original_title,year,genre,duration,country,language,director,writer,production_company,actors,description,avg_vote\n"
					+ "The Source of Shadows,The Source of Shadows,2020,Horror,83,USA,English,\"Ryan Bury, Jennifer Bonior\",\"Jennifer Bonior, Trevor Botkin\",Four Thieves Productions,\"Ashleigh Allard, Tom Bonington, Eliane Gagnon, Marissa Kaye Grinestaff, Jenna Heffernan, Joshua Hummel, Janice Kingsley, Chris Labasbas, Jared Laufree, Dominic Lee, Vic May, Sienna Mazzone, Lizzie Mounter, Grace Mumm, Ashley Otis\",\"A series of stories woven together by one of our most primal fears, the fear of the unknown.\",3.5\n"
					+ "The Insurrection,The Insurrection,2020,Action,90,USA,English,Rene Perez,Rene Perez,,\"Michael Paré, Wilma Elles, Joseph Camilleri, Rebecca Tarabocchia, Jeanine Harrington, Malorie Glavan, Danner Boyd, Michael Cendejas, Woody Clendenen, Keely Dervin, Aaron Harvey, Tony Jackson, Michael Jarrod, Angelina Karo, Bernie Kelly\",The director of the largest media company wants to expose how left-wing powers use film to control populations.,2.9\n"
					+ "Valley Girl,Valley Girl,2020,\"Comedy, Musical, Romance\",102,USA,English,Rachel Lee Goldenberg,\"Amy Talkington, Andrew Lane\",Sneak Preview Productions,\"Jessica Rothe, Josh Whitehouse, Jessie Ennis, Ashleigh Murray, Chloe Bennet, Logan Paul, Mae Whitman, Mario Revolori, Rob Huebel, Judy Greer, Alex Lewis, Alex MacNicoll, Danny Ramirez, Andrew Kai, Allyn Rachel\",\"Set to a new wave '80s soundtrack, a pair of young lovers from different backgrounds defy their parents and friends to stay together. A musical adaptation of the 1983 film.\",5.4\n"
			)));
			// set the output back to standard out for running the test
			System.setOut(standardOut);
			// same for standard in
			System.setIn(standardIn);
			String appOutput = outputStreamCaptor.toString();
			if (frontend == null || appOutput.contains("The Source of Shadows")
							|| appOutput.contains("The Insurrection")
							|| appOutput.contains("Valley Girl")) {
				// test failed
				return false;
			} else {
				// test passed
				return true;
			}
		} catch (Exception e) {
			// make sure stdin and stdout are set correctly after we get exception in test
			System.setOut(standardOut);
			System.setIn(standardIn);
			e.printStackTrace();
			// test failed
			return false;
		}
	}

	/**
	 * This test runs the front end and redirects its output to a string. It then
	 * passes in 'g' as a command to go to the genre selection mode. It then exists
	 * the app by pressing 'x' to go back to the main mode and another 'x' to exit.
	 * The test succeeds if the genre selection screen contains all five genres
	 * from the data. It fails if any of them are missing, the front end has not
	 * been instantiated (is null), or there is an exception.
	 * @return true if the test passed, false if it failed
	 */
	public boolean testFrontendGForGenres() {
		PrintStream standardOut = System.out;
		InputStream standardIn = System.in;
		try {
			// set the input stream to our input (with an g to test of the program lists genres)
			String input = "g" + System.lineSeparator() + "x" + System.lineSeparator() + "x";
			InputStream inputStreamSimulator = new ByteArrayInputStream(input.getBytes());
			System.setIn(inputStreamSimulator);
			ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
			// set the output to the stream captor to read the output of the front end
			System.setOut(new PrintStream(outputStreamCaptor));
			// instantiate when front end is implemented
			Object frontend = new Frontend();
			((Frontend)frontend).run(new Backend(new StringReader(
					"title,original_title,year,genre,duration,country,language,director,writer,production_company,actors,description,avg_vote\n"
					+ "The Source of Shadows,The Source of Shadows,2020,Horror,83,USA,English,\"Ryan Bury, Jennifer Bonior\",\"Jennifer Bonior, Trevor Botkin\",Four Thieves Productions,\"Ashleigh Allard, Tom Bonington, Eliane Gagnon, Marissa Kaye Grinestaff, Jenna Heffernan, Joshua Hummel, Janice Kingsley, Chris Labasbas, Jared Laufree, Dominic Lee, Vic May, Sienna Mazzone, Lizzie Mounter, Grace Mumm, Ashley Otis\",\"A series of stories woven together by one of our most primal fears, the fear of the unknown.\",3.5\n"
					+ "The Insurrection,The Insurrection,2020,Action,90,USA,English,Rene Perez,Rene Perez,,\"Michael Paré, Wilma Elles, Joseph Camilleri, Rebecca Tarabocchia, Jeanine Harrington, Malorie Glavan, Danner Boyd, Michael Cendejas, Woody Clendenen, Keely Dervin, Aaron Harvey, Tony Jackson, Michael Jarrod, Angelina Karo, Bernie Kelly\",The director of the largest media company wants to expose how left-wing powers use film to control populations.,2.9\n"
					+ "Valley Girl,Valley Girl,2020,\"Comedy, Musical, Romance\",102,USA,English,Rachel Lee Goldenberg,\"Amy Talkington, Andrew Lane\",Sneak Preview Productions,\"Jessica Rothe, Josh Whitehouse, Jessie Ennis, Ashleigh Murray, Chloe Bennet, Logan Paul, Mae Whitman, Mario Revolori, Rob Huebel, Judy Greer, Alex Lewis, Alex MacNicoll, Danny Ramirez, Andrew Kai, Allyn Rachel\",\"Set to a new wave '80s soundtrack, a pair of young lovers from different backgrounds defy their parents and friends to stay together. A musical adaptation of the 1983 film.\",5.4\n"
			)));
			// set the output back to standard out for running the test
			System.setOut(standardOut);
			// same for standard in
			System.setIn(standardIn);
			// add all tests to this method
			String appOutput = outputStreamCaptor.toString();
			if (frontend != null && appOutput.contains("Horror")
							&& appOutput.contains("Action")
							&& appOutput.contains("Comedy")
							&& appOutput.contains("Musical")
							&& appOutput.contains("Romance")) {
				// test passes if all genres from the data are displayed on the screen
				return true;
			} else {
				// test failed
				return false;
			}
		} catch (Exception e) {
			// make sure stdin and stdout are set correctly after we get exception in test
			System.setOut(standardOut);
			System.setIn(standardIn);
			e.printStackTrace();
			// test failed
			return false;
		}
	}

	// TODO: Front End Developer, add at least 2 more tests
	/**
	*This test runs the front end and redirects its output to a string. It then
	*passes in 'r' to enter ratings selection mode. It then exits the mode by pressing 'x'
	*and then again exits the app by inputting 'x'. The test passes if the rating selection screen all 11 integers
	*from 0 to 10 that a user can input. It fails if any of the integers are missing, the front end has not been instantiated
	*or if an exception is thrown.
	*@return true if the test passed, false if it fails.
	**/
	public boolean testFrontendRForRatings() {
		PrintStream standardOut = System.out;
		InputStream standardIn = System.in;
		try {
			String input = "r" + System.lineSeparator() + "x" + System.lineSeparator() +"x";
			InputStream inputStreamSimulator = new ByteArrayInputStream(input.getBytes());
			System.setIn(inputStreamSimulator);
			ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

			System.setOut(new PrintStream(outputStreamCaptor));
			//instantiate after frontend is implemented
			Object frontend = new Frontend();
			((Frontend)frontend).run(new Backend(new StringReader(
					"title,original_title,year,genre,duration,country,language,director,writer,production_company,actors,description,avg_vote\n"
					+ "The Source of Shadows,The Source of Shadows,2020,Horror,83,USA,English,\"Ryan Bury, Jennifer Bonior\",\"Jennifer Bonior, Trevor Botkin\",Four Thieves Productions,\"Ashleigh Allard, Tom Bonington, Eliane Gagnon, Marissa Kaye Grinestaff, Jenna Heffernan, Joshua Hummel, Janice Kingsley, Chris Labasbas, Jared Laufree, Dominic Lee, Vic May, Sienna Mazzone, Lizzie Mounter, Grace Mumm, Ashley Otis\",\"A series of stories woven together by one of our most primal fears, the fear of the unknown.\",3.5\n"
					+ "The Insurrection,The Insurrection,2020,Action,90,USA,English,Rene Perez,Rene Perez,,\"Michael Paré, Wilma Elles, Joseph Camilleri, Rebecca Tarabocchia, Jeanine Harrington, Malorie Glavan, Danner Boyd, Michael Cendejas, Woody Clendenen, Keely Dervin, Aaron Harvey, Tony Jackson, Michael Jarrod, Angelina Karo, Bernie Kelly\",The director of the largest media company wants to expose how left-wing powers use film to control populations.,2.9\n"
					+ "Valley Girl,Valley Girl,2020,\"Comedy, Musical, Romance\",102,USA,English,Rachel Lee Goldenberg,\"Amy Talkington, Andrew Lane\",Sneak Preview Productions,\"Jessica Rothe, Josh Whitehouse, Jessie Ennis, Ashleigh Murray, Chloe Bennet, Logan Paul, Mae Whitman, Mario Revolori, Rob Huebel, Judy Greer, Alex Lewis, Alex MacNicoll, Danny Ramirez, Andrew Kai, Allyn Rachel\",\"Set to a new wave '80s soundtrack, a pair of young lovers from different backgrounds defy their parents and friends to stay together. A musical adaptation of the 1983 film.\",5.4\n"
			))); 

			System.setOut(standardOut);
			System.setIn(standardIn);
			String appOutput = outputStreamCaptor.toString();
			int ratingNumCounter = 0;
			for (int x = 0; x < 11; x++){
				if (frontend != null && appOutput.contains(Integer.toString(x))){
					ratingNumCounter++;
				}
			}
			if(ratingNumCounter == 11){
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {

			System.setOut(standardOut);
			System.setIn(standardIn);
			e.printStackTrace();

			return false;
		}
	}

	/**
	*This test runs the front end and redirects its output to a string. It then
	*passes in 'g' to enter genre selection mode. It then exits the mode by passing in 'x'
	*and then exits the program by passing in 'x'. The test passes if no movies are shown when
	*no genre is selected. It fails if any movie is shown when it should not be, the front end has not been instantiated
	*or if an exception is thrown.
	*@return true if the test passes, false if it fails
	**/
	public boolean testFrontendNoGenereSelected(){
		PrintStream standardOut = System.out;
		InputStream standardIn = System.in;
		try {
			String input = "g" + System.lineSeparator() + "x" + System.lineSeparator() +"x";
			InputStream inputStreamSimulator = new ByteArrayInputStream(input.getBytes());
			System.setIn(inputStreamSimulator);
			ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

			System.setOut(new PrintStream(outputStreamCaptor));
			//instantiate after frontend is implemented
			Object frontend = new Frontend();
			((Frontend)frontend).run(new Backend(new StringReader(
					"title,original_title,year,genre,duration,country,language,director,writer,production_company,actors,description,avg_vote\n"
					+ "The Source of Shadows,The Source of Shadows,2020,Horror,83,USA,English,\"Ryan Bury, Jennifer Bonior\",\"Jennifer Bonior, Trevor Botkin\",Four Thieves Productions,\"Ashleigh Allard, Tom Bonington, Eliane Gagnon, Marissa Kaye Grinestaff, Jenna Heffernan, Joshua Hummel, Janice Kingsley, Chris Labasbas, Jared Laufree, Dominic Lee, Vic May, Sienna Mazzone, Lizzie Mounter, Grace Mumm, Ashley Otis\",\"A series of stories woven together by one of our most primal fears, the fear of the unknown.\",3.5\n"
					+ "The Insurrection,The Insurrection,2020,Action,90,USA,English,Rene Perez,Rene Perez,,\"Michael Paré, Wilma Elles, Joseph Camilleri, Rebecca Tarabocchia, Jeanine Harrington, Malorie Glavan, Danner Boyd, Michael Cendejas, Woody Clendenen, Keely Dervin, Aaron Harvey, Tony Jackson, Michael Jarrod, Angelina Karo, Bernie Kelly\",The director of the largest media company wants to expose how left-wing powers use film to control populations.,2.9\n"
					+ "Valley Girl,Valley Girl,2020,\"Comedy, Musical, Romance\",102,USA,English,Rachel Lee Goldenberg,\"Amy Talkington, Andrew Lane\",Sneak Preview Productions,\"Jessica Rothe, Josh Whitehouse, Jessie Ennis, Ashleigh Murray, Chloe Bennet, Logan Paul, Mae Whitman, Mario Revolori, Rob Huebel, Judy Greer, Alex Lewis, Alex MacNicoll, Danny Ramirez, Andrew Kai, Allyn Rachel\",\"Set to a new wave '80s soundtrack, a pair of young lovers from different backgrounds defy their parents and friends to stay together. A musical adaptation of the 1983 film.\",5.4\n"
			)));

			System.setOut(standardOut);
			System.setIn(standardIn);
			String appOutput = outputStreamCaptor.toString();
			if(frontend == null || appOutput.contains("The Source of Shadows")
						|| appOutput.contains("The Insurrection")
						|| appOutput.contains("Valley Girl")){
				return false;
			} else {
				return true;
			}
		} catch (Exception e){

			System.setOut(standardOut);
			System.setIn(standardIn);
			e.printStackTrace();

			return false;
		}
	}
}
