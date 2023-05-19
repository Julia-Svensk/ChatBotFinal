package Messages;

//The base class for all messages
public abstract class MessagesB {
    // separator string used for serializing and deserializing messages
    public static final String SERIALIZATION_SEPARATOR = ":";

    protected String message;

    //Default constructor
    public MessagesB() {}

    //Abstract method for formatting the message object before sending it
    public abstract String formatMessage();

    //Getter for the message string
    public String getMessage() {
        return this.message;
    }
}