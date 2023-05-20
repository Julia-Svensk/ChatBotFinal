/*package Main;

import java.util.Scanner;

public class Names {
    // Current name of the user
    private String currentName;

    // Constructor to initialize the instance variables
    public Names() {
        this.currentName = "";
    }

    // Check if a name is valid (not null, not empty)
    public boolean isNameValid(String name) {
        if (name == null) {
            return false; // Name is null
        }

        if (name.trim().isEmpty()) {
            System.out.println("You need to write something as a name");
            return false; // Name is empty
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
}*/
