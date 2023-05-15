package Commands;

import Main.Connection;
import Main.Names;
import Main.Server;
import Messages.ServerMessages;

public class DisplayNames extends CommandB{
    public DisplayNames() {
        this.commandName = "Change name";
        this.description = "Changes the name displayed on your screen. Arguments required: (New name)";
        this.numArg = 1;
    }

    public void executeCommand(Commandhandler commandhandler, String[] args) {
        //Gets the server, user and naming-handler objects
        Server server = commandhandler.getServer();
        Connection user = commandhandler.getUser();
        Names namingHandler = commandhandler.getNamingHandler();

        //Gets the new name from the argument
        String newName = args[0].trim();

        //Checking if the new name is valid
        if (namingHandler.isNameValid(newName)) {
            String nameChangeInfo = "'" + user.getDisplayName() + "' changed their name to '" + newName + "'";

            //Log the info about the name change
            server.log(nameChangeInfo);
            // Broadcast the name change to all users
            server.broadcast(new ServerMessages(nameChangeInfo));

            // Set the user's screen name to the new name
            user.setDisplayName(newName);
            // Send the user a confirmation message
            user.sendMessage(new ServerMessages("Your name was successfully changed to '" + newName + "'"));
        }
    }
}