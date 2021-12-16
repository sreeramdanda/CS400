import java.util.Arrays;
import java.util.ArrayList;

/**
 * This backend class represents this project's backend. It takes data from within the data wrangler and store it within
 * our graph data type; Dijkstra's algorithm is then used to determine shortest path between two points of interest,
 * which are provided by the input from the frontend.
 *
 * @author Nijae
 */
public class BackendDummy {
    // Private Static Fields; used statically throughout class
    private static DataWrangler data; // Gets data wrangler object
    private static ArrayList<Intersection> intersections; // Gets intersections from data wrangler
    private static CS400Graph<Intersection> map; // Gets graph of intersections

    /**
     * Initializes private fields of class. Iterates through intersections adding the intersections as verticies
     * and adding connections as edges.
     */
    public BackendDummy() {
        // Initialize private fields
        data = new DataWrangler();
        intersections = data.getAllIntersections();
        map = new CS400Graph<>();

        // Store data within map
        for(int i = 0; i < intersections.size(); i++) {
            // Insert intersection as vertex
            Intersection cIntersection = intersections.get(i);
            map.insertVertex(cIntersection);
            // Get current connections & add edges
            ArrayList<Connection> cConnections = cIntersection.getConnections();
            for(int e = 0; e < cConnections.size(); e++) {
                Connection cEdge = cConnections.get(e);
                map.insertEdge(cIntersection, cEdge.getTarget(), cEdge.getDistance());
            }
        }
    }

    /**
     * Takes two intersection points as parameters and gives the shortest path between the two
     * @param start Starting intersection
     * @param end Ending intersection
     * @return Formatted string including distance and path between the two
     */
    public Path getRoute(String start, String end) { return null; }

    /**
     * Takes three intersection points as parameters and gives the shortest path between the three. First stopping
     * at the first intersection then the second.
     * @param start Starting intersection
     * @param first First intersection to visit
     * @param second Second intersection to visit
     * @return Formatted string including distance and path between the three
     */
    public ArrayList<Path> getRoute(String start, String first, String second) { return null; }

    /**
     * Makes an intersection closed/open based upon previous state
     * @param intersection Intersection to toggle
     * @return True if intersection is active after method completion; false if intersection is closed after
     * method completion
     */
    public boolean toggleIntersection(String intersection) { return false; }

    /**
     * Reports all significant land marks and all intersections
     * @return Formatted string of points of interest and intersections
     */
    public Path getPoints() { return null; }
}
