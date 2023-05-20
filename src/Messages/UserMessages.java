package Messages;

import Main.Connection;

//Defines the user messages class which handles the messages sent by the user
public class UserMessages extends MessagesB {
    //The Connection object representing the sender of the message
    private final Connection sender;

    //Constructor to set the sender and message strings
    public UserMessages(Connection sender, String message) {
        this.sender = sender;
        this.message = message;
    }

    //Implementation of the abstract formatMessage method to format the message object before sending it
    @Override
    public String formatMessage() {

        //Extracts the display name of the sender from the Connection object
        String screenName = this.sender.getUserName();

        //Constructs the message string by separating the sender's screen name and message using the serialization separator
        return "User" + MessagesB.SERIALIZATION_SEPARATOR +
                screenName.length() +
                MessagesB.SERIALIZATION_SEPARATOR + screenName +
                MessagesB.SERIALIZATION_SEPARATOR + this.message;
    }

    //Getter method for the sender's display name
    public String getSenderName() {
        return sender.getUserName();
    }
}
