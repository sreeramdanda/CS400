// --== CS400 File Header Information ==--
// Name: Nijae King
// Email: nrking@wisc.edu
// Team: Purple
// Role: Frontend
// TA: Mu Cai
// Lecturer: Florian
// Notes to Grader: n/a

import java.util.*;

public class Frontend {
    // Mode Enums

    enum Mode {
        ADDITION,
        SEARCH,
        RANDOM,
        EXIT
    }

    // Static Class Members

    static Backend backend = new Backend();
    static boolean runLoop = true;
    static Mode currMode;
    static Scanner scnr = new Scanner(System.in);

    // Static Class Methods

    static void promptMode() {
        // Prompt
        System.out.println("\nWhat mode would you like to enter?");

        // Print Out Options Based on First Char
        Mode[] allModes = Mode.values();
        for(int i = 0; i < allModes.length; i++) {
            System.out.println("["+allModes[i].toString().charAt(0)+"] - " + allModes[i]);
        }

        // Set Static Mode to Valid Mode
        char charMode;
        try {
            charMode = scnr.next(".").charAt(0);
            scnr.nextLine();
        } catch (InputMismatchException err) { promptMode(); return; }
        currMode = getModeFromChar(charMode);
    }

    static Mode getModeFromChar(char inputMode) {
        // Char to Mode Switch Statement
        switch(Character.toLowerCase(inputMode)) {
            case 'a':
                return Mode.ADDITION;
            case 's':
                return Mode.SEARCH;
            case 'n':
            case 'r':
                return Mode.RANDOM;
            case 'x':
            case 'e':
                return Mode.EXIT;
            default:
                System.out.println("Invalid character inputted: '"+inputMode+"'");
                if(currMode == null)
                    promptMode();
                return null;
        }
    }

    static boolean switchModes(String input) {
        // Backup Current Mode
        Mode saveMode = currMode;

        // Switch Modes
        char cMode = input.charAt(0);
        currMode = getModeFromChar(cMode);

        // If same mode, return true
        if(currMode == saveMode)
            return true;

        // If success, print message and return true
        if(currMode != null) {
            System.out.println("Switched to '"+currMode+"' mode");
            System.out.println("-".repeat(20));
            return true;
        }

        // If fail, restore old mode; return false
        currMode = saveMode;
        return false;
    }

    static boolean testSwitchModes(String input) {
        // Backup Current Mode
        Mode saveMode = currMode;

        // Switch Modes
        char cMode = input.charAt(0);
        currMode = getModeFromChar(cMode);

        // If same mode, return true
        if(currMode == saveMode)
            return true;

        // If success, print message and return true
        if(currMode != null) {
            return true;
        }

        // If fail, restore old mode; return false
        currMode = saveMode;
        return false;
    }

    static void printWord(String word) {
        try {
            if (backend.getWord(word) != null && !backend.getWord(word).getWord().isEmpty()) { // Found Word
                Word w = backend.getWord(word);
                List<String> definitions = w.getDefinitions();
                String origin = w.getOrigin();

                System.out.println(w.getWord().toLowerCase() + "(" + w.getOrigin() + "):");
                for (int i = 0; i < definitions.size(); i++) {
                    System.out.println("\t[" + i + "] " + definitions.get(i));
                }
            }
        } catch (NoSuchElementException err) {
            System.out.println("Perhaps you meant " + backend.getSuggestions(word) + "?");

            System.out.print("Do you want to enter this word into the dictionary? (y/n): ");
            char c = scnr.next(".").charAt(0);
            scnr.nextLine();
            if(c == 'y') switchModes("a");
        }
    }

    static void printWord(int word) {
        if(backend.getWord(word) != null && !backend.getWord(word).getWord().isEmpty()) { // Found Word
            List<String> definitions = backend.getWord(word).getDefinitions();
            String origin = backend.getWord(word).getOrigin();

            System.out.println(backend.getWord(word).getWord().toLowerCase()+"("+backend.getWord(word).getOrigin() + "):");
            for(int i = 0; i < definitions.size(); i++) {
                System.out.println("\t["+i+"] " + definitions.get(i));
            }
        } else {
            System.out.println("Word not found");
        }
    }

    static void testPrintWord(String word, char bSwitch) {
        try {
            if (backend.getWord(word) == null || backend.getWord(word).getWord().isEmpty()) // Found Word
                if (bSwitch == 'y') testSwitchModes("a");
        } catch (NoSuchElementException err) {
            if (bSwitch == 'y') testSwitchModes("a");
        }
    }

    static void additionMode() {
        System.out.println("Type a word you wish to add to your dictionary (Must exceed one character): ");
        System.out.println("(Alternatively, enter a char [(S)earch/(R)andom/(E)xit] to switch modes)");

        // Get Input
        String word = scnr.nextLine();
        List<String> definitions = new ArrayList<String>();
        boolean continuePrompt = true;
        String origin;

        // If Input is Valid Character => Switch Modes
        if(word.length() == 1) {
            switchModes(word);
            return;
        }

        // Get Definition(s) & Origin
        System.out.print("Type the definition of '" + word + "': ");
        definitions.add(scnr.nextLine());
        do {
            System.out.print("Would you like to add another definition (y/n): ");
            char c = scnr.next(".").charAt(0);
            scnr.nextLine();
            if(c == 'y') {
                System.out.print("Type the definition of '" + word + "': ");
                definitions.add(scnr.nextLine());
            } else if(c == 'n') {
                continuePrompt = false;
            } else {
                continue;
            }
        } while (continuePrompt);
        System.out.print("Type the origin of '" + word + "': ");
        origin = scnr.nextLine();

        // Add Word to Dictionary
        try {
            backend.insert(word, definitions, origin);
            System.out.println("\n'" + word + "' was successfully added to your dictionary!\n");
        } catch(NoSuchElementException|IllegalArgumentException err) {
            System.out.println("\n"+err.getMessage()+"\n");
        }
    }

    static void searchMode() {
        System.out.println("Type a word you wish to search from your dictionary: ");
        System.out.println("(Alternatively, enter a char [(A)ddition/(R)andom/(E)xit] to switch modes)");

        // Get Input
        String word = scnr.nextLine();

        // If Input is Valid Character => Switch Modes
        if(word.length() == 1) {
            switchModes(word);
            return;
        }

        // Search Word from Dictionary
        printWord(word);
    }

    static void randomMode() {
        // Search Word from Dictionary
        printWord( (new Random()).nextInt(backend.getSize()) );
        System.out.println("Enter a char [(A)ddition/(S)earch/(R)andom/(E)xit] to switch modes");

        // Get Input
        String word = scnr.next();
        scnr.nextLine();

        // If Input is Valid Character => Switch Modes
        if(word.length() == 1) {
            switchModes(word);
            return;
        }
    }

    static void exitMode() {
        System.out.println("Exiting program!");

        scnr.close();
        runLoop = false;
    }

    static void testExitMode() {
        scnr.close();
        runLoop = false;
    }

    static void enterMode() {
        System.out.println("Entered '" + currMode + "' mode");
        System.out.println("-".repeat(20));
        // Main Program Loop
        while(runLoop == true) {
            // Mode Switch Statement
            switch(currMode) {
                case ADDITION:
                    additionMode();
                    break;
                case SEARCH:
                    searchMode();
                    break;
                case RANDOM:
                    randomMode();
                    break;
                case EXIT:
                    exitMode();
                    break;
            }
        }
    }

    static void testEnterMode() {
        // Main Program Loop
        while(runLoop == true) {
            // Mode Switch Statement
            switch(currMode) {
                case ADDITION:
                    additionMode();
                    break;
                case SEARCH:
                    searchMode();
                    break;
                case RANDOM:
                    randomMode();
                    break;
                case EXIT:
                    testExitMode();
                    break;
            }
        }
    }

    public static void main(String[] args) {
        // Intro Text
        System.out.println("Welcome to the dictionary program\n");
        System.out.print("Word of the day: ");
        printWord( (new Random()).nextInt(backend.getSize()) );

        // Method Calls
        promptMode();
        enterMode();
    }
}