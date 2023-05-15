package Commands;

import Main.Connection;
import Messages.ServerMessages;

public class Help extends CommandB{
    public Help() {
        this.commandName = "help";
        this.description = "Gives you information about the different commands.";
        this.numArg = 0;
    }

    public void executeCommand(Commandhandler commandhandler, String[] args) {
        Connection user = commandhandler.getUser();

        StringBuilder allCommandsInformation = new StringBuilder();

        //Retrieves the command information from each command and appends it to the StringBuilder
        for (CommandB command : commandhandler.getCommands()) {
            String[] commandInfo = command.getCommandInfo();
            for (String str : commandInfo) {
                allCommandsInformation.append(str).append("\n");
            }
        }

        //Sends all command information as a ServerMessage to the user
        user.sendMessage(new ServerMessages(allCommandsInformation.toString()));
    }
}

