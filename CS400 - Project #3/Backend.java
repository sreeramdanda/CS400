// --== CS400 File Header Information ==--
// Name: Nijae King
// Email: nrking@wisc.edu
// Team: Purple
// Role: Backend
// TA: Mu Cai
// Lecturer: Florian
// Notes to Grader: n/a

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.NoSuchElementException;

/**
 * This backend class represents this project's backend. It takes data from within the data wrangler and store it within
 * our graph data type; Dijkstra's algorithm is then used to determine shortest path between two points of interest,
 * which are provided by the input from the frontend.
 *
 * @author Nijae
 */
public class Backend {
    // Private Static Fields; used statically throughout class
    private static ArrayList<Intersection> intersections; // Gets intersections from data wrangler
    private static CS400Graph<Intersection> map; // Gets graph of intersections

    /**
     * Initializes private fields of class. Iterates through intersections adding the intersections as verticies
     * and adding connections as edges.
     */
    public Backend() {
        // Initialize private fields
        try {
            intersections = DataReader.readData();
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
        updateMap(); // Initialize and load data into map
    }

    /**
     * Private helper method. Clears map but initializing with a new graph object. Then gets the list of intersections
     * and stores each intersection that is valid along with their connections.
     */
    private void updateMap() {
        map = new CS400Graph<>(); // Create new map
        // Store data within map
        for(int i = 0; i < intersections.size(); i++) {
            // Insert intersection as vertex
            Intersection cIntersection = intersections.get(i);
            // Verify intersection is valid
            if(cIntersection.isVisitable) {
                map.insertVertex(cIntersection);
            }
        }
        addEdges();
    }

    /**
     * Private helper method. addEdges must be called after all vertices are already added. Otherwise edges will be
     * created with null targets creating errors.
     */
    private void addEdges() {
        for(int i = 0; i < intersections.size(); i++) {
            Intersection cIntersection = intersections.get(i);
            // Verify intersection is valid
            if(cIntersection.isVisitable) {
                // Get current connections & add edges
                ArrayList<Connection> cConnections = cIntersection.getConnections();
                for (int e = 0; e < cConnections.size(); e++) {
                    Connection cEdge = cConnections.get(e);
                    Intersection target = cEdge.getTarget(intersections);
                    if (target != null && target.isVisitable)
                        map.insertEdge(cIntersection, target, cEdge.getDistance());
                }
            }
        }
    }

    /**
     * Takes two intersection points as parameters and gives the shortest path between the two
     * @param start Starting intersection
     * @param end Ending intersection
     * @return Path object containing the distance, path, and points of interest passed from start to end node
     */
    public Path getRoute(String start, String end) {
        Path route = null;
        Intersection startVertex = null;
        Intersection endVertex = null;
        // Find intersections from name
        for (int i = 0; i < intersections.size(); i++) {
            Intersection cIntersection = intersections.get(i);
            if(cIntersection.getName().equalsIgnoreCase(start) && cIntersection.isVisitable == true) { // If intersection matches start string
                startVertex = cIntersection;
            }
            if(cIntersection.getName().equalsIgnoreCase(end)  && cIntersection.isVisitable == true) { // If intersection matches end string
                endVertex = cIntersection;
            }
        }
        // Confirm each vertex was found
        if(startVertex == null || endVertex == null)
            throw new NoSuchElementException("Intersection with specified name does not exist");
        // Find shortest path
        // NoSuchElementException error should be caught by Frontend
        CS400Graph<Intersection>.Path djikstras = map.dijkstrasShortestPath(startVertex, endVertex);
        // Get data fields to pass to route
        int distance = djikstras.distance;
        ArrayList<Intersection> path = new ArrayList<Intersection>(djikstras.dataSequence);
        ArrayList<String> points = new ArrayList<>();
        // Get points of interest
        for(int i = 0; i < path.size(); i++) {
            ArrayList<String> cPoints = path.get(i).getPoi();
            for(int p = 0; p < cPoints.size(); p++) {
                points.add(cPoints.get(p));
            }
        }

        // Convert to Path object
        route = new Path(distance, path, points);
        return route;
    }

    /**
     * Takes three intersection points as parameters and gives the shortest path between the three. First stopping
     * at the first intersection then the second.
     * @param start Starting intersection
     * @param first First intersection to visit
     * @param second Second intersection to visit
     * @return Array list of Path objects containing the distance, path, and points of interest passed
     * from start to first node to second node
     */
    public ArrayList<Path> getRoute(String start, String first, String second) {
        ArrayList<Path> routes = new ArrayList<>();
        // Find paths
        routes.add(getRoute(start, first));
        routes.add(getRoute(first, second));
        return routes;
    }

    /**
     * Makes an intersection closed/open based upon previous state
     * @param intersection Intersection to toggle
     * @return True if intersection is active after method completion; false if intersection is closed after
     * method completion
     * @throws NoSuchElementException if string does not match name of any existing intersection
     */
    public boolean toggleIntersection(String intersection) {
        Intersection toToggle = null;
        // Search for intersection
        for(int i = 0; i < intersections.size(); i++) {
            Intersection cIntersection = intersections.get(i);
            if(cIntersection.getName().equalsIgnoreCase(intersection)) {
                toToggle = cIntersection;
                break;
            }
        }
        // If not found, throw error
        if(toToggle == null)
            throw new NoSuchElementException("Intersection with specified name does not exist");
        toToggle.isVisitable = !toToggle.isVisitable;
        updateMap(); // Updates map clearing of invalid vertices
        return toToggle.isVisitable;
    }

    /**
     * Reports all significant land marks and all intersections
     * @return Path object containing all intersections and points of interest
     */
    public Path getPoints() {
        Path collection; // Path object to hold data
        ArrayList<String> points = new ArrayList<>();

        // Get points of interest
        for(int i = 0; i < intersections.size(); i++) {
            // Iterate through each intersections points of interest
            Intersection cIntersection = intersections.get(i);
            ArrayList<String> cPoints = cIntersection.getPoi();
            for(int p = 0; p < cPoints.size(); p++)
                points.add(cPoints.get(p));
        }
        collection = new Path(0, intersections, points);
        return collection;
    }

    /**
     * Reports all points of interest at the intersection and within one intersection from the given intersection
     * @param name Name of intersection
     * @return Path object containing all points of interest
     */
    public Path getPoints(String name) {
        Path collection = null; // Path object to hold data
        ArrayList<String> poi = new ArrayList<>();
        // Find intersection by name
        intersections.forEach((c) -> {
            if(c.getName().equalsIgnoreCase(name)) {
                ArrayList<String> cPoints = c.getPoi();
                // Add current points to arraylist
                for(int i = 0; i < cPoints.size(); i++) {
                    poi.add(cPoints.get(i));
                }
                // Find all connections
                ArrayList<Connection> connections = c.getConnections();
                connections.forEach((e) -> {
                    // Add each targets points of interest
                    Intersection target = e.getTarget();
                    ArrayList<String> targetPoints = target.getPoi();
                    for(int t = 0; t < targetPoints.size(); t++) {
                        poi.add(targetPoints.get(t));
                    }
                });
            }
        });
        // Initialize path object and return
        collection = new Path(0, null, poi);
        return collection;
    }

    /**
     * Protected method, implemented for testing purposes
     * @return map private field
     */
    protected CS400Graph<Intersection> getMap() { return map; }

    /**
     * Protected method, implemented for testing purposes
     * @return intersections private field
     */
    protected ArrayList<Intersection> getIntersections() { return intersections; }
}
