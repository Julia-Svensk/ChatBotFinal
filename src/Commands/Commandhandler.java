package Commands;

import Main.Connection;
import Main.Names;
import Main.Server;
import Messages.ServerMessages;

import java.util.ArrayList;

//Define Commandhandler class
//Handles the commands written by the user
public class Commandhandler {

    // An array list containing all registered commands
    protected final ArrayList<CommandB> commands;

    // The server object
    private final Server server;

    // The user's connection handler object
    private final Connection user;

    private final String userName;

    // The naming handler object responsible for changing a client's screen name
    private final Names namingHandler;

    // Constructor
    public Commandhandler(Server server, String userName, Connection user) {
        // Initialize the class variables
        this.server = server;
        this.userName = userName;
        this.user = user;
        // Initialize the namingHandler variable as an object of the Names class which handles the users name displayed onscreen
        this.namingHandler = new Names(server, userName);

        // Initialize the commands array list and add the HelpCommand and NicknameCommand objects
        commands = new ArrayList<>();
        commands.add(new Help());
        commands.add(new DisplayNames());
    }

    // Splits the command string at every space to get the arguments separated, then returns an array with the arguments in it.
    // The number of arguments returned depends on how many arguments the command requires.
    public String[] getArguments(String message, int numArg) {
        return message.trim().split(" ", numArg + 1);
    }

    // Checks for commands in the text from the user and processes the commands. Returns the boolean "isCommandProcessed" which is set to false if it doesn't find a command.
    public boolean processesCommands(String message) {
        // If it doesn't start with * (the prefix) it's not a command and is therefore false and should not be processed.
        if (!message.startsWith(CommandB.PREFIX)) {
            return false;
        }

        boolean isCommandProcessed = false;
        try {
            //Makes the whole message lowercase so that it can be compared to the commands
            String lcMessage = message.toLowerCase();
            // Loop through all registered commands to find the one that matches the user's input
            for (CommandB command : commands) {
                //Gets an exact string required to execute a command and compares it to the input message
                //If it doesn't match it continues onto the next command
                String requiredCommand = command.getCommand();
                if (!lcMessage.startsWith(requiredCommand)) {
                    continue;
                }

                //Once it is done with finding a matching command it sets the isCommandsProcessed to true
                isCommandProcessed = true;

                //Gets the arguments from the input message
                String[] arguments = getArguments(message, command.numArg);
                //If there weren't enough arguments it sends out an error message.
                if (arguments.length - 1 < command.numArg) {
                    String errorMessage = "The command you wrote requires " + command.numArg + " arguments. " + "You only wrote " + (arguments.length - 1) + ". " + "Write '*help' for more information about the commands.";
                    user.sendMessage(new ServerMessages(errorMessage));
                    break;
                }

                //Executes the found command with the saved arguments from above
                command.executeCommand(this, arguments);
                break;
            }
            //If an error occurs send error to console and print error message
        } catch (Exception e) {
            e.printStackTrace();
            user.sendMessage(new ServerMessages("Something went wrong with your command, please try again."));
        }
        return isCommandProcessed;
    }

    // Get the server object
    public Server getServer() {
        return server;
    }

    // Get the client's connection handler object
    public Connection getUser() {
        return user;
    }

    // Get the naming handler object responsible for changing a client's screen name
    public Names getNamingHandler() {
        return namingHandler;
    }

    // Get the array list
    public ArrayList<CommandB> getCommands() {
        return commands;
    }
}
