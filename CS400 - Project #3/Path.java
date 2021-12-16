// --== CS400 File Header Information ==--
// Name: Nijae King
// Email: nrking@wisc.edu
// Team: Purple
// Role: Backend
// TA: Mu Cai
// Lecturer: Florian
// Notes to Grader: n/a

import java.util.ArrayList;

/**
 * This class holds the data from the backend. It stores distance, path (vertices), and points of interests along
 * the path given by the frontend.
 */
public class Path {
    private int distance;
    private ArrayList<Intersection> path;
    private ArrayList<String> poi;

    /**
     * Constructor method for the Path object
     * @param distance of path (weight)
     * @param path vertices visited in-order
     * @param poi points of interest along the path
     */
    public Path(int distance, ArrayList<Intersection> path, ArrayList<String> poi) {
        this.distance = distance;
        this.path = path;
        this.poi = poi;
    }

    /**
     * Gets the distance private field
     * @return distance
     */
    public int getDistance() { return distance; }

    /**
     * Gets the path private field
     * @return vertices visited in-order
     */
    public ArrayList<Intersection> getPath() { return path; }

    /**
     * Gets the points of interest private field
     * @return points of interest along the path
     */
    public ArrayList<String> getPOI() { return poi; }
}
