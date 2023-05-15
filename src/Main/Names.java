package Main;

import java.util.List;
import java.util.Scanner;

public class Names {
    // List to keep track of used names
    private List<String> usedNames;
    // Current name of the user
    private String currentName;

    // Constructor to initialize the instance variables
    public Names(List<String> usedNames, String currentName) {
        this.usedNames = usedNames;
        this.currentName = currentName;
    }

    // Check if a name is already being used by another user
    private boolean isNameUsed(String name) {
        // Convert the name to a simplified format
        String simpleName = simplifyName(name);

        // Loop through the list of used names and check if any match the given name
        for (String usedName : usedNames) {
            // Convert the used name to a simplified format
            if (simplifyName(usedName).equals(simpleName)) {
                return true; // Name is already being used
            }
        }

        return false; // Name is not being used
    }

    // Convert a name to a simplified format to ensure that users cannot have the same name with different cases or spaces
    public static String simplifyName(String name) {
        return name.trim().toLowerCase().replaceAll("\\s+", "");
    }

    // Check if a name is valid (not null, not empty, and not already in use)
    public boolean isNameValid(String name) {
        if (name == null) {
            return false; // Name is null
        }

        if (name.trim().isEmpty()) {
            System.out.println("You need to write something as a name");
            return false; // Name is empty
        }

        if (isNameUsed(name)) {
            System.out.println("This name is already taken");
            return false; // Name is already being used
        }

        return true; // Name is valid
    }

    // Prompt the user to enter a valid name and return the name once it is entered
    public String getValidName() {
        String name;
        while (true) {
            System.out.print("Please write your name: ");
            String input = new Scanner(System.in).nextLine();

            if (isNameValid(input)) {
                name = input.trim();
                break; // Name is valid, exit the loop
            } else {
                System.out.println("Invalid name. Please try again.");
            }
        }

        return name; // Return the valid name
    }
}
