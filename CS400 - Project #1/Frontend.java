// --== CS400 File Header Information ==--
// Name: Sreeram Danda
// Email: sdanda@wisc.edu
// Team: Blue
// Role: Frontend Developer
// TA: Mu Ta
// Lecturer: Gary Dahl
// Notes to Grader: -N/A-
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;

/**
 * This is the main class for the movie mapper program. This class instantiates
 * the backend and starts the base mode. The user can use r or g to switch to
 * ratings or genre selection mode.
 * 
 * @author Sreeram Danda
 *
 */
public class Frontend {

	public static void main(String[] args) {
		Frontend frontend = new Frontend();
		frontend.run(args);
	}
	
	public void run(String[] args) {
		int mode = 0; // 0 for base mode, 1 for genre mode, 2 for ratings mode, -1 for exit
		Backend backend = null;
		try {
			backend = new Backend(args);
		} catch (FileNotFoundException e) {
			System.out.println("File was not found!\nExiting Application.");
			System.exit(0);
		}
		if (backend != null)	
		run(backend);
		else {
			System.out.println("File was null.\nExiting");
			System.exit(1);
		}
	}
	
	public void run(Backend backend) {
		System.out.println("Welcome to Movie Mapper");
		int mode = 0;
		
		Scanner scanner = new Scanner(System.in);
		
		for (int x = 0; x < 11; x++) { // adds all ratings to selection
			backend.addAvgRating(String.valueOf(x));
		}

		while (mode == 0) { // base mode
			System.out.println("Entered Base Selection Mode");
			System.out.println("Use \"g\" to enter genre selection mode, \"r\" to enter rating selection mode \"x\" to exit");
			List<MovieInterface> topThreeMovies = backend.getThreeMovies(0); // prints the top three movies based on
			System.out.println("");
			if (topThreeMovies.isEmpty())
				System.out.println("No movies to display");

			for (MovieInterface x : topThreeMovies) {
				System.out.println(String.format("%s - %s", x.getAvgVote(), x.getTitle()));
			}
			String ratingToFilter = "";
			while (!ratingToFilter.equals("g") || !ratingToFilter.equals("r")) { // while loop to change modes
				System.out.println("\nEnter a rating between 0-10 to filter:");
				ratingToFilter = scanner.nextLine();
				if (ratingToFilter.equals("x")) {
					System.out.println("Thank you for using movie mapper."); // exits program
					mode = -1;
					break;
				} else if (ratingToFilter.equals("g")) { // moves to genre selection mode
					mode = 1;
					break;
				} else if (ratingToFilter.equals("r")) { // moves to ratings selection mode
					mode = 2;
					break;
				} else { // prints top three movies based on user input
					try {
						System.out.println("");
						topThreeMovies = backend.getThreeMovies(Integer.parseInt(ratingToFilter));
						if (topThreeMovies.isEmpty())
							System.out.println("No movies to display");
					for (MovieInterface x : topThreeMovies) {
						System.out.println(String.format("%s - %s", x.getAvgVote(), x.getTitle()));
					}
					} catch (NumberFormatException e) {
						System.out.println("Input was not a number. Please retype.");
					}
					System.out.println("");
				}
			}

			while (mode == 1) { // genre selection mode
				System.out.println("Welcome to Genere Selection Mode");
				System.out.println(
						"\nTo select genres enter the number corresponding to the genre located on left hand side.\nTo deselect enter the number of the selected genre.\n");
				/*
				List<String> genresToClear = backend.getGenres(); // removes all previously selected genres
				for (String x : genresToClear) {
					backend.removeGenre(x);
				}

				
				List<String> ratingsToClear = backend.getAvgRatings(); // removes all
				
				for(int x = ratingsToClear.size() - 1; x >= 0; x--) {
					backend.removeAvgRating(ratingsToClear.get(x));
				}
				*/

				List<String> listOfGeneres = backend.getAllGenres(); // prints all the genres available
				for (int x = 0; x < listOfGeneres.size(); x++) {
					System.out.println(String.format("%s) %s", x, listOfGeneres.get(x)));
				}
				String genreSelected = "";
				while (!genreSelected.equals("x")) { // conditions to change mode or exit mode
					List<String> listOfSelectedGenere = backend.getGenres();
					genreSelected = scanner.nextLine();
					if (genreSelected.equals("x")) { // return to base mode
						mode = 0;
						break;
					} else if (genreSelected.equals("r")) { // change to ratings selection mode
						mode = 2;
						break;
					} else {
					try {
					String genre = listOfGeneres.get(Integer.parseInt(genreSelected));
					if (listOfSelectedGenere.contains(genre)) {
							backend.removeGenre(genre);// add inputed selection for genre
					} else 
						backend.addGenre(listOfGeneres.get(Integer.parseInt(genreSelected)));// add inputed selection for genre
					
					} catch (NumberFormatException e) {
						System.out.println("Input was not a number (index of genre). Please retype.");
					} catch (IndexOutOfBoundsException e) {
						System.out.println("Input was out of bounds: " + e.getMessage());
					}
					}

					listOfGeneres = backend.getAllGenres(); // all genres in movie data

					for (int x = 0; x < listOfGeneres.size(); x++) { // print out selected genres and unselected genre
						listOfSelectedGenere = backend.getGenres();

						if (listOfSelectedGenere.contains(listOfGeneres.get(x))) {
							System.out.println(String.format("%s) %s [SELECTED]", x, listOfGeneres.get(x)));
						} else {
							System.out.println(String.format("%s) %s [UNSELECTED]", x, listOfGeneres.get(x)));
						}
					}
					System.out.println("\nHere are the movies that staisfy the selection:\n");

					List<MovieInterface> moviesToDisplay = backend.getThreeMovies(0); // print out valid movies
					for (MovieInterface x : moviesToDisplay) {
						System.out.println(String.format("%s", x.getTitle()));
					}
					System.out.println(
							"\nTo return to base selection mode enter 'x'.\nTo keep selecting/deselecting enter the number corresponding to a genre:");
				}
				System.out.println("Exiting Genre Selection Mode.\n");
			}

			while (mode == 2) { // rating selection mode
				System.out.println("Weclome to Ratings Selection Mode");

				/*
				List<String> genresToClear = backend.getGenres(); // clear previously selected genres
				for (String x : genresToClear) {
					backend.removeGenre(x);
				}

				List<String> ratingsToClear = backend.getAvgRatings(); // removes all

				for(int x = ratingsToClear.size() - 1; x >= 0; x--) {
					backend.removeAvgRating(ratingsToClear.get(x));
				}

				for (int x = 0; x < 11; x++) { // add all rating values to selection
					backend.addAvgRating(String.valueOf(x));
				}
				*/

				String selectedRating = "";
				while (!selectedRating.equals("x")) {
					List<String> selectedInt = backend.getAvgRatings();
					System.out.println("Here are the ratings you can select from:"); // prints out selected and
																						// unselected ratings
					for (int x = 0; x < 11; x++) {
						if (selectedInt.contains(String.valueOf(x))) {
							System.out.println(String.format("%s - [SELECTED]", x));
						} else {
							System.out.println(String.format("%s - [UNSELECTED]", x));
						}
					}

					System.out.println("Enter a rating to select: ");

					List<String> selectedRatings = backend.getAvgRatings();

					selectedRating = scanner.nextLine();

					if (selectedRating.equals("x")) {	// return to base selection
						System.out.println("Returning to base selection mode.");
						mode = 0;
						break;
					} else if (selectedRating.equals("g")) {	//change to genre selection mode
						System.out.println("Changing to genre selection mode");
						mode = 1;
						break;
					} else {
						Float rating = null; 
						try {
							rating = Float.parseFloat(selectedRating);
							String number = String.valueOf(rating.intValue());
							if (selectedRatings.contains(number))	// if rating is already selected remove the selection
							backend.removeAvgRating(number);
							else if (rating >= 0 && rating < 11)
								backend.addAvgRating(number);	// add rating to selection
							else
								System.out.println("Rating needs to be between 0-10. Please retype.");
						} catch (NumberFormatException e) {
							System.out.println("Cannot add a non-number rating. Please retype.");
						}
					}

					List<MovieInterface> moviesToDisplay = backend.getThreeMovies(0);	// print out valid movies 

					for (int x = 3; x < backend.getNumberOfMovies(); x += 3) {
						moviesToDisplay.addAll(backend.getThreeMovies(x));
					}

					System.out.println("\nThese are the movies with the selected ratings:");
					for (MovieInterface x : moviesToDisplay) {
						System.out.println(String.format("%s", x.getTitle()));
					}
					System.out
							.println("\nEnter another rating to select it or re-enter a selected rating to deselect.");
				}
			}
		}
	}
}
