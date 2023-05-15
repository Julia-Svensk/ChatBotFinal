package Messages;

//Defines the deserialized messages class which takes care of keeping information about the deserialized messages after they've been sent
public class DeserializedMessages {
    public String sender;

    public String content;

    //A boolean showing whether the message was sent by the chat server or a user.
    public Boolean fromServer;

    //The constructor
    public DeserializedMessages(String sender, String content, boolean fromServer) {
        this.sender = sender;
        this.content = content;
        this.fromServer = fromServer;
    }
}