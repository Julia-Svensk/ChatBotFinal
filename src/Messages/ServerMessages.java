package Messages;

//Defines the server message class responsible for the servers messages
public class ServerMessages extends MessagesB {

    //Constructor to set the message string
    public ServerMessages(String message) {
        this.message = message;
    }

    //Implementation of the abstract formatMessage method to format the message object before sending it
    @Override
    public String formatMessage() {

        //Construct the message string by separating the "Server" string and the message using the serialization separator
        return "Server" + MessagesB.SERIALIZATION_SEPARATOR + message;
    }
}
