import java.io.FileNotFoundException;
import java.util.ArrayList;

// --== CS400 File Header Information ==--	
// Name: Sreeram Danda
// Email: sdanda@wisc.edu
// Team: AB
// TA: Mu Cai
// Lecturer: Gary Dahl
// Notes to Grader: -N/A-

/**
 * This class represents the connection that one intersection has to another.
 */
public class Connection {

	// Distance to target intersection
	private int distance;

	// Name of target intersection
	private String targetIntersection;

	/**
	 * Default constructor for the connection class. Takes in a target intersection
	 * name, and the distance to the target intersection
	 * 
	 * @param target String name of the target intersection
	 * @param data   Int representing the distance to target intersection in feet
	 */
	public Connection(String target, int data) {
		this.distance = data;
		this.targetIntersection = target;
	}

	/**
	 * Getter for the distance
	 * 
	 * @return Int representing the distance to a target intersection
	 */
	public int getDistance() {
		return distance;
	}

	/**
	 * Getter for the name of the target intersection
	 *
	 * @return intersection representing the target intersection returns null if no intersection found.
	 */
	public Intersection getTarget() {
		try {
			ArrayList<Intersection> intersections = DataReader.readData();
			for(Intersection i : intersections) {
				if(i.getName().equals(this.targetIntersection))
					return i;
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Getter for the name of the target intersection
	 *
	 * @return intersection representing the target intersection returns null if no intersection found.
	 */
	public Intersection getTarget(ArrayList<Intersection> intersections) {
		for(Intersection i : intersections) {
			if(i.getName().equals(this.targetIntersection))
				return i;
		}
		return null;
	}
}
