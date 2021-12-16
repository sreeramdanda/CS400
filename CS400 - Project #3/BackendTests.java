// --== CS400 File Header Information ==--
// Name: Nijae King
// Email: nrking@wisc.edu
// Team: Purple
// Role: Backend
// TA: Mu Cai
// Lecturer: Florian
// Notes to Grader: n/a

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.xml.crypto.Data;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

/**
 * This class tests the backend to verify the backend functions as intended. Also tests graph ADT to verify it works
 * with current implementation.
 *
 * @author Nijae
 */
public class BackendTests {
    // Private Static Fields; used statically throughout class
    private static ArrayList<Intersection> data;
    private static Backend backend; // Backend object to test
    private static ArrayList<Intersection> intersections; // Gets intersections from data wrangler
    private static CS400Graph<Intersection> map; // Gets graph of intersections

    /**
     * Resets backend data after each tests to verify correctness
     */
    @BeforeEach
    public void setBackend() {
        try {
            data = DataReader.readData();
        } catch(FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
        backend = new Backend();
        intersections = backend.getIntersections();
        map = backend.getMap();
    }

    /**
     * This test verifies all data is inputed into the graph ADT correctly
     */
    @Test
    public void testInsertion() {
        int numOfIntersections = data.size();
        int numOfConnections = 0;
        // Test initialization of data wrangler object
        if(intersections.size() != numOfIntersections)
            fail("Failed to grab all intersections");
        // Test insertion of intersections into map
        if(map.getVertexCount() != numOfIntersections)
            fail("Failed to insert all intersections into map");
        // Test insertion of edges into map
        for(int i = 0; i < intersections.size(); i++) {
            // Grab connections
            numOfConnections += intersections.get(i).getConnections().size();
        }
        if(map.getEdgeCount() != numOfConnections)
            fail("Failed to insert connections into map");
    }

    /**
     * This test verifies intersections are removed from the graph ADT when they're prompted to invalidate the
     * intersection by toggleIntersection() method.
     */
    @Test
    public void testClosure() {
        // Remove node
        backend.toggleIntersection("Observatory & Babcock");
        map = backend.getMap(); // Update map to newly created map
        if(map.getVertexCount() != intersections.size()-1)
            fail("Failed to remove intersection");
        // Reinsert node
        backend.toggleIntersection("Observatory & Babcock");
        map = backend.getMap(); // Update map to newly created map
        if(map.getVertexCount() != intersections.size())
            fail("Failed to reinsert intersection");
        // Remove multiple nodes
        backend.toggleIntersection("Mills & University");
        backend.toggleIntersection("Johnson & Randall");
        map = backend.getMap(); // Update map to newly created map
        if(map.getVertexCount() != intersections.size()-2)
            fail("Failed to remove intersection");
    }

    /**
     * This test verifies the single-parameter getPoints(...) returns the correct points of interest surrounding and
     * including a single intersection
     */
    @Test
    public void testSinglePoints() {
        String searchIntersection = "Randall & Dayton";
        Path path = backend.getPoints(searchIntersection);
        ArrayList<String> points = path.getPOI();
        // Search for 'Randall & Dayton' point of interest
        if(!points.contains("Vantage Point"))
            fail("Failed to find points of interest");
    }

    /**
     * This test verifies the no-parameter getPoints() returns all the intersections and points of interest from the
     * data wrangler
     */
    @Test
    public void testAllPoints() {
        Path all = backend.getPoints();
        int numPointsInterest = 0; // Store number of points of interest
        // Verify all intersections
        if(all.getPath().size() != intersections.size())
            fail("Failed to get all intersections");
        // Get number of points of interest
        for(int i = 0; i < intersections.size(); i++) {
            numPointsInterest += intersections.get(i).getPoi().size();
        }
        if(all.getPOI().size() != numPointsInterest)
            fail("Failed to get all points of interest");
    }

    /**
     * This test verifies the double-parameter method, getRoute(start, end), returns the correct distance, path, and
     * points of interested from the source to the destination
     */
    @Test
    public void testGetRoute() {
        // Test 1 (Short Path)
        Path route = backend.getRoute("Observatory & Babcock", "Linden & Babcock");
        // Verify distance is correct
        if(route.getDistance() != 460)
            fail("Failed to get shortest path");
        // Verify path starts at 'Observatory & Babcock' and ends at 'Liden & Babcock'
        if(!route.getPath().get(0).getName().equalsIgnoreCase("Observatory & Babcock") ||
                !route.getPath().get(1).getName().equalsIgnoreCase("Linden & Babcock"))
            fail("Failed to get shortest path");

        //Test 2 (Short Path w/ POIs)
        route = backend.getRoute("Mills & Johnson", "Brooks & Johnson");
        // Verify distance is correct
        if(route.getDistance() != 441)
            fail("Failed to get shortest path");
        // Verify point of interest
        if(!route.getPOI().get(0).equalsIgnoreCase("Grand Central"))
            fail("Failed to find point of interest");
    }
}
