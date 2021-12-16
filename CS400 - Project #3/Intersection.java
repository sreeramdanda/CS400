// --== CS400 File Header Information ==--	
// Name: Sreeram Danda
// Email: sdanda@wisc.edu
// Team: AB
// TA: Mu Cai
// Lecturer: Gary Dahl
// Notes to Grader: -N/A-

import java.util.ArrayList;

/**
 * This class represents one intersection and all its connections and points of
 * interests.
 */
public class Intersection {

	// Name of the intersection. Ex: "inter & section"
	private String name;
	
	// 
	public boolean isVisitable;

	// ArrayList of the connections a intersection has
	private ArrayList<Connection> connections = new ArrayList<Connection>();

	// ArrayList of the points of interest at each intersection
	private ArrayList<String> pointsOfInterests = new ArrayList<String>();

	/**
	 * Default constructor that names the intersection
	 * 
	 * @param name Name of the intersection
	 */
	public Intersection(String name) {
		this.name = name;
		isVisitable = true;
	}

	/**
	 * Returns the name of the intersection
	 * 
	 * @return String representing name of this intersection
	 */
	public String getName() {
		return name;
	}

	/**
	 * Returns the list of connections this intersection has to others
	 * 
	 * @return ArrayList of connections
	 */
	public ArrayList<Connection> getConnections() {
		return connections;
	}

	/**
	 * Returns the list of points of interest at this intersection
	 * 
	 * @return ArrayList of strings representing points of interests
	 */
	public ArrayList<String> getPoi() {
		return pointsOfInterests;
	}

	/**
	 * Setter for the name
	 * 
	 * @param name String representing the name of the intersection
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * Setter for visitablity of a node
	 * 
	 * @param a boolean representing the state of visibility
	 */
	public void setVisitility(boolean state) {
		isVisitable = state;
	}
	
	/**
	 * Getter for visitablity for a node
	 * 
	 * @return a boolean representing this intersection's visitability..
	 */
	public boolean getVisitility() {
		return isVisitable;
	}
	
	/**
	 * Setter for the connections this intersection has to others
	 * 
	 * @param data   int representing distance to target intersection
	 * @param target string representing the target intersection's name
	 */
	public void setConnections(int data, String target) {
		Connection toAdd = new Connection(target, data);
		connections.add(toAdd);
	}

	/**
	 * Setter for the points of interest this intersection has
	 * 
	 * @param pointOfInterests ArrayList of strings that name the point of interests
	 */
	public void addPoi(String poi) {
		this.pointsOfInterests.add(poi);
	}

	@Override
	/**
	 * Overrides the toString method to return the name of the intersection
	 *
	 * @return the name of this Intersection
	 */
	public String toString() {
		return name;
	}
}
