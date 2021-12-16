// --== CS400 File Header Information ==--	
// Name: Sreeram Danda
// Email: sdanda@wisc.edu
// Team: AB
// TA: Mu Cai
// Lecturer: Gary Dahl
// Notes to Grader: -N/A-

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * This class represents the data reader which takes in two paths to files that
 * represent the distances between intersections and points of interests at
 * intersections.
 */
public class DataReader {

	/**
	 * This method reads the given files and returns an arrayList of intersection
	 * objects
	 * 
	 * @return ArrayList<Intersection> Represents all the intersections and their
	 *         connections, and point of interests
	 * @throws FileNotFoundException Thrown if no file is found at given paths
	 */
	public static ArrayList<Intersection> readData() throws FileNotFoundException {
		try {
			// Initialize necessary variables
			File intersectionDistanceData = new File("distances.csv"); // File of distance data
			File pointOfInterestData = new File("pointOfInterests.csv"); // File of POI data
			Scanner reader = new Scanner(intersectionDistanceData); // Scanner for reading files
			String[] intersectionNames = reader.nextLine().split(","); // Names of all intersections
			ArrayList<Intersection> intersections = new ArrayList<Intersection>(); // Empty list of intersections
			int numOfLines = lineCounter(reader); // Count of number of lines in distance data file

			// Go through the file and create an intersection object for each intersection
			// in file
			reader = new Scanner(intersectionDistanceData);
			intersections = addDistanceData(intersections, intersectionNames, numOfLines, reader);

			// Count the lines in the POI file
			reader = new Scanner(pointOfInterestData);
			numOfLines = lineCounter(reader);

			// Add point of interest data
			reader = new Scanner(pointOfInterestData);
			intersections = addPoiData(intersections, numOfLines, reader);

			// Return the parsed data
			return intersections;

		} catch (FileNotFoundException e) {
			// Exception thrown if file not found
			throw new FileNotFoundException("Error. File path did not lead to a file");
		}
	}

	/**
	 * This method adds all the distance data to a given arrayList of intersections
	 * by adding all the connections to other intersections and their corresponding
	 * distance
	 * 
	 * @param intersections     The arrayList that needs the distance data
	 * @param intersectionNames The array of all the names of the intersections
	 * @param numOfLines        The number of lines in the distance data file
	 * @param reader            The scanner that is reading the distance data file
	 * @return ArrayList<Intersection> Represeting an array of all the intersections
	 *         with connections to corresponding neighbor intersections
	 */
	private static ArrayList<Intersection> addDistanceData(ArrayList<Intersection> intersections,
			String[] intersectionNames, int numOfLines, Scanner reader) {
		// Setup to add distance data
		ArrayList<Intersection> listOfIntersections = intersections;
		reader.nextLine();

		// Loop through lines in distance data file
		for (int i = 1; i <= numOfLines; i++) {
			// Hold the name of current intersection
			String currentIntersectionName = intersectionNames[i];
			// Make this an intersection
			Intersection toAdd = new Intersection(currentIntersectionName);

			// Data containing neighbor distance to neighbor intersections
			String[] data = reader.nextLine().split(",");

			// Iterate through the data adding valid connections to the intersection
			for (int c = 1; c < data.length; c++) {
				if (!data[c].equals("")) {
					toAdd.setConnections(Integer.parseInt(data[c]), intersectionNames[c]);
				}
			}

			// Add the finalized intersection to the list of intersections to be returned
			intersections.add(toAdd);
		}
		reader.close();
		// Return updated list of intersections
		return listOfIntersections;
	}

	/**
	 * This method adds all the point of interest data to their corresponding
	 * intersections
	 * 
	 * @param intersections The arrayList that needs the point of interest data
	 * @param numOfLines    The number of lines in the point of interest data file
	 * @param reader        The scanner that is reading the point of interest data
	 *                      file
	 * @return ArrayList<Intersection> Represeting an array of all the intersections
	 *         with connections to corresponding neighbor intersections
	 */
	private static ArrayList<Intersection> addPoiData(ArrayList<Intersection> intersections, int numOfLines,
			Scanner reader) {
		// Setup to add point of interest data
		ArrayList<Intersection> listOfIntersections = intersections;

		// Loop through lines in point of interest data file
		for (int i = 1; i <= numOfLines; i++) {
			// Data containing point of interest for a given intersection located at data[0]
			String[] data = reader.nextLine().split(",");

			// Loop though data and add points of interest to the intersections
			for (int c = 1; c < data.length; c++) {
				intersections.get(i - 1).addPoi(data[c]);
			}
		}
		reader.close();

		// Return updated list of intersections
		return listOfIntersections;
	}

	/**
	 * This method counts the number of lines in a given file.
	 * 
	 * @param reader The scanner for reading the file
	 * @return int Representing the number of lines in the file
	 */
	private static int lineCounter(Scanner reader) {
		int lineCounter = 0;
		while (reader.hasNext()) {
			reader.nextLine();
			lineCounter++;
		}
		reader.close();
		return lineCounter;
	}

	/**
	 * This method prints out all intersections with their connections and distance
	 * to other intersections
	 * 
	 * @throws FileNotFoundException Thrown if file was not found
	 */
	public static String printAllIntersectionsAndConnectionsWithDistance() throws FileNotFoundException {
		try {
			String output = "";
			int counter = 1;
			for (Intersection x : readData()) {
				// Add name of intersection to output
				output += ("#" + counter + " " + "Intersection: " + x.getName() + "\n");
				
				// Add all connections to output
				for (Connection y : x.getConnections()) {
					output += ("Connections: " + y.getTarget() + " (" + y.getDistance() + "ft.)\n");
				}
				
				// Add all points of interest to output
				output += ("Points of Interest: ");
				for (String z : x.getPoi()) {
					output += (z + ", ");
				}

				// Add blank line for next intersection
				output += ("\n\n");
				counter++;
			}

			return output;
		} catch (FileNotFoundException e) {
			throw new FileNotFoundException("File path did not lead to file");
		}
	}
}
