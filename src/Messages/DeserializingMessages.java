package Messages;

//Defines the Deserializing messages class which is responsible for deserializing the messages
public class DeserializingMessages {

    //Constructor
    public DeserializingMessages() {}

    //This method deserializes messages from the server
    private static DeserializedMessages deserializeServerMessage(String message) {

        //Splits the message using a separator and extracts the content
        String[] messageData = message.split(MessagesB.SERIALIZATION_SEPARATOR, 2);
        String content = messageData[1];

        //Creates a new DeserializedMessages object with the extracted content and marks it as a server message
        return new DeserializedMessages("Server", content, true);
    }

    //This method deserializes messages from users
    private static DeserializedMessages deserializeUserMessages(String message) {

        //Splits the message using a separator and extracts the length of the username
        String[] messageData = message.split(MessagesB.SERIALIZATION_SEPARATOR, 3);
        int nameLength = Integer.parseInt(messageData[1]);

        //Calculates the starting index of the username within the message string
        int nameLengthNumDigits = (int)(Math.log10(nameLength) + 1);
        int nameStartIndex = 6 + 2 * MessagesB.SERIALIZATION_SEPARATOR.length() + nameLengthNumDigits;

        //Extracts the username and content from the message string and creates a new DeserializedMessages object with them
        String name = message.substring(nameStartIndex, nameStartIndex + nameLength);
        String content = message.substring(nameStartIndex + nameLength + MessagesB.SERIALIZATION_SEPARATOR.length());
        return new DeserializedMessages(name, content, false);
    }

    //This method determines whether a message is from the server or from a user and calls the corresponding deserialization method
    public static DeserializedMessages deserializedMessage(String message) {
        DeserializedMessages deserializedMessage;

        //Checks if the message is from the server and calls the corresponding deserialization method
        if (message.startsWith("Server" + MessagesB.SERIALIZATION_SEPARATOR)) {
            deserializedMessage = deserializeServerMessage(message);
        } else {
            //Otherwise, assumes the message is from a user and calls the corresponding deserialization method
            deserializedMessage = deserializeUserMessages(message);
        }

        //Returns the DeserializedMessages object
        return deserializedMessage;
    }
}
