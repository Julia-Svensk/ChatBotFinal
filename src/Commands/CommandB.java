package Commands;

// Define the CommandB class
// Base for other commands
public abstract class CommandB {

    // Use this character to start writing a command
    protected static final String PREFIX = "*";

    // The name of the command
    public String commandName;

    // A brief description of the command
    protected String description;

    // The number of arguments required by the command
    protected int numArg;

    // Constructor
    public CommandB() {
        // Default constructor does nothing
    }

    // Abstract method to execute the command
    abstract void executeCommand(Commandhandler commandhandler, String[] args);

    // Get information about the command
    public String[] getCommandInfo() {
        // Create an array with two elements
        String[] commandInfo = new String[2];

        // Set the second element to the command prompt
        commandInfo[0] = PREFIX + commandName;

        // Set the first element to the command description
        commandInfo[1] = description;

        // Return the array
        return commandInfo;
    }

    // Get the command prompt
    public String getCommand() {
        return PREFIX + commandName;
    }
}