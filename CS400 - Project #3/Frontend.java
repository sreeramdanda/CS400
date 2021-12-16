// --== CS400 File Header Information ==--
// Name: Gautama Manja
// Email: manja@wisc.edu
// Team: AB
// Role: Frontend Dev
// TA: Mu Cai
// Lecturer: Gary
// Notes to Grader: I did not comment the mode methods because I thought the first print statement of each method accurately describe the purpose enough

import java.util.*;
import java.lang.StringBuilder;
import java.lang.System;

public class Frontend {
    //class members of frontend
    static Backend backend = new Backend();
    static Scanner scan = new Scanner(System.in);
    static Mode currentMode;


    enum Mode {
	CONSTRUCTION,
	BASIC,
	SCHEDULER,
	PROMINENT,
        NEARBY,
	OVERVIEW,
	EXITPROGRAM
    }
    
    public static void main(String[] args){
	getFirstMode();
	enterIntoMode();
    }

    /**
     * Gets the desired starting mode of the user and uses private helper to change mode of Frontend
     *
     *
     */
    public static void getFirstMode(){
	System.out.println("Select a mode to enter");
	System.out.println("b - Basic mode");
	System.out.println("c - Construction mode");
	System.out.println("s - Scheduler mode");
	System.out.println("n - Nearby points mode");
	System.out.println("p - Prominent mode");
	System.out.println("o - Overview mode");
	System.out.println("e - Exit");

	char inputChar;
	try{
	    String inputString = scan.nextLine();
	    inputChar = inputString.charAt(0);
	}catch (Exception e) {
	    getFirstMode();
	    return;
	}
	modeSelectionHelper(inputChar);
    }

    /**
     *Helper method that determines the mode of the frontend based on the char inputted
     *
     * @param char - the input character for the desired mode
     */
    protected static void modeSelectionHelper(char inputChar){
	switch(inputChar){
	    case 'b': 
		currentMode = Mode.BASIC;
		System.out.println("\n\nEntering basic mode...\n\n");
		break;
	    case 'c':
		currentMode = Mode.CONSTRUCTION;
		System.out.println("\n\nEntering construction mode...\n\n");
		break;
	    case 's':
		currentMode = Mode.SCHEDULER;
		System.out.println("\n\nEntering scheduler mode...\n\n");
	        break;
	    case 'p':
		currentMode = Mode.PROMINENT;
		System.out.println("\n\nEntering prominent mode...\n\n");
		break;
	    case 'o':
		currentMode = Mode.OVERVIEW;
		System.out.println("\n\nEntering overview  mode...\n\n");
		break;
	    case 'e':
		currentMode = Mode.EXITPROGRAM;
		break;
	    case 'n':
		currentMode = Mode.NEARBY; 
		break;
	    default:
		System.out.println("\n\nThe character inputted is not valid\n\n");
		getFirstMode();
	}


    }

    /**
     * The main execution loop of the program, which simply runs the chosen mode continuously in a while loop unless mode has been 
     * switched
     */
    public static void enterIntoMode(){
        while(true){
	    switch(currentMode){
		case BASIC: 
		    basicMode();
		    break;
		case CONSTRUCTION:
		    constructionMode();
		    break;
		case SCHEDULER:
		    schedulerMode();
		    break;
		case PROMINENT:
		    prominentMode();
		    break;
		case OVERVIEW:
		    overviewMode();
		    break;
		case NEARBY:
		    nearbyMode();
		    break;
		case EXITPROGRAM:
		    scan.close();
		    System.out.println("Exiting...\nExited successfully, have a nice day!");
		    System.exit(0);
	    }
	}
    }
    
    /**
     *This method takes input and uses the helper method to determine the new mode
     *
     *@param usrInput - string containing one or zero chars that determines new mode
     */
    public static void switchModes(String usrInput){
	if(usrInput.length() == 0){modeSelectionHelper('x'); return;}
    	char newMode = usrInput.charAt(0);
	modeSelectionHelper(newMode);
    }

    public static void constructionMode(){
	System.out.println("\n\nWelcome to construction mode! This mode will toggle any intersection as being under construction, ");
	System.out.println("so that it cannot be traversed through or used as a starting/ending intersection");
	System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
	
	System.out.println("\n\nEnter the intersection you would like to toggle as under/not under construction, or type a valid char");
	System.out.println("(b/e/n/o/p/s) to change mode");
	String constructionInput = scan.nextLine();
	//check for mode switch
	if(constructionInput.length() <= 1){switchModes(constructionInput); return;}
	
	try{
	    boolean result = backend.toggleIntersection(constructionInput);
	    if(!result){System.out.println("\n\nThis intersection is now under construction!");}
	    else{System.out.println("\n\nThis intersection is no longer in construction!");}
	}catch(Exception e){
	    System.out.println("\n\nThere was an error, please try again");
	}
    }

    public static void basicMode(){
	System.out.println("\n\nWelcome to the basic mode! This mode takes two intersections and finds the shortest path between them");
	System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");

	System.out.println("\n\nEnter the starting intersection, or type a valid character(c/e/n/o/p/s) to switch modes");
	String firstInput = scan.nextLine();
	//check for mode switch
	if(firstInput.length() <= 1){switchModes(firstInput); return;}

	System.out.println("\n\nThe first intersection is " + firstInput);

	System.out.println("\n\nNow enter the destination intersection");
	String secondInput = scan.nextLine();
	
	System.out.println("\n\nThe second intersection is " + secondInput);


	try{
	    Path outputPath = backend.getRoute(firstInput, secondInput);
	    System.out.println("\n\nThe shortest path between " + firstInput + " and " + secondInput + " is " + printPath(outputPath) + " (" + outputPath.getDistance() + " feet)!");
	}catch(NoSuchElementException e){
	    System.out.println("\n\n" +  e.getMessage());
	}	
    }

    /**
     * Prints a path seperated by ", to ". Additionally starts with a newline and 
     * for every 8 intersections after a newline is added.
     *
     * @param path the path to print
     * @return the path in a readable format
     */
    private static String printPath(Path path) {
	    ArrayList<Intersection> intersections = path.getPath();
	    StringBuilder builder = new StringBuilder();
	    for (int i = 0; i < intersections.size(); i++) {
		    if (i % 8 == 0)
			    builder.append("\n");
		    builder.append(intersections.get(i));
		    if (i != intersections.size() - 1)
			    builder.append(", to ");
	    }
	    return builder.toString();
    }

    /**
     * Prints a list of paths, with each path call separated with ".\nThen Take ".
     *
     * @param paths the ArrayList of Path objects
     * @return A readable string of all associated paths in the list
     */
    private static String printPaths(ArrayList<Path> paths) {
	    StringBuilder builder = new StringBuilder();
	    for (int i = 0; i < paths.size(); i++) {
		    builder.append(printPath(paths.get(i)));
		    if (i != paths.size() - 1)
			    builder.append(".\nThen take ");
	    }
	    return builder.toString();
    }

    public static void schedulerMode(){
	System.out.println("\n\nWelcome to scheduler mode! This mode is like the basic mode, but finds the shortest path between 3 intersections");
	System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");

	System.out.println("\n\nEnter the starting intersection, or type a valid character(b/c/e/n/o/p) to switch modes");
	String firstInput = scan.nextLine();
	if(firstInput.length() <= 1){switchModes(firstInput); return;}
	
	System.out.println("\n\nEnter the second intersection");
	String secondInput = scan.nextLine();
	
	System.out.println("\n\nEnter the final intersection");
	String finalInput = scan.nextLine();

	try{
	    ArrayList<Path> outputPath = backend.getRoute(firstInput, secondInput, finalInput);
	    int totalDistance = (outputPath.get(0).getDistance() + outputPath.get(1).getDistance());
	    System.out.println("\n\nThe shortest path from " + firstInput + " to " + secondInput + ", and then to " + finalInput + ", is " + printPaths(outputPath) + " (Total: " + totalDistance + " feet)!");
	} catch(NoSuchElementException e){
	    System.out.println("\n\n" +  e.getMessage());
	}

    }

    public static void prominentMode(){
	System.out.println("\n\nWelcome to prominent mode! This mode takes two intersections and finds all points of interest between them");
	System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");

	System.out.println("\n\nEnter the starting intersection, or type a valid character(c/e/n/o/p/s) to switch modes");
	String firstInput = scan.nextLine();
	//check for mode switch
	if(firstInput.length() <= 1){switchModes(firstInput); return;}

	System.out.println("\n\nThe first intersection is " + firstInput);

	System.out.println("\n\nNow enter the destination intersection");
	String secondInput = scan.nextLine();
	
	System.out.println("\n\nThe second intersection is " + secondInput);

	try{
	    Path outputPath = backend.getRoute(firstInput, secondInput);
	    ArrayList<String> allPOI = outputPath.getPOI();
	    System.out.println("\n\nThe Points of Interest along this shortest path are listed below:");
	    for(String s : allPOI){System.out.println(s);}
	}catch(NoSuchElementException e){
	    System.out.println("\n\n" +  e.getMessage());
	}	

    }

    public static void overviewMode(){
	System.out.println("\n\nWelcome to overview mode! This mode lists all intersections and points of interests");
	System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
	
	Path output = backend.getPoints();
	ArrayList<Intersection> allIntersections = output.getPath();
	ArrayList<String> allPOI = output.getPOI();
	System.out.println("\n\n---------------------------------------------ALL INTERSECTIONS-----------------------------------------------");
	for(Intersection i : allIntersections){System.out.println(i.getName());}
	System.out.println("\n\n-------------------------------------------ALL POINTS OF INTEREST-------------------------------------------");
	for(String s : allPOI){System.out.println(s);}
	
	System.out.println("\n\nType a valid character(b/c/e/p/s) to switch modes");
	String input = scan.nextLine();
	switchModes(input);
	return;
    }
    
    public static void nearbyMode(){
	System.out.println("\n\nWelcome to Nearby Points mode! This mode lists all points of interest near the input intersection and any points of");
	System.out.println("interest of any adjacent intersections");
	System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
	
	System.out.println("\n\nEnter an intersection, or type a valid character(b/c/e/o/p/s) to switch modes");
	String input = scan.nextLine();
	if(input.length() <=1){switchModes(input); return;}

	try{
	    System.out.println("\n\n");
	    Path output = backend.getPoints(input);
	    for(String s : output.getPOI()){System.out.println(s);}
	}
	catch(Exception e){System.out.println("There was an error, please try again!"); }
        
	    }
}
