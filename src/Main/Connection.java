package Main;

import Commands.Commandhandler;
import Messages.MessagesB;
import Messages.ServerMessages;
import Messages.UserMessages;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

// Defines the Connection class. It handles communication between the server and the user.
public class Connection implements Runnable {
    private final Server server;
    private final Socket socket;
    private final String userName;
    private BufferedReader in;
    private PrintWriter out;
    private String displayName;
    private Connection user;

    public Connection(Server server, Socket socket, String userName, Connection user) {
        this.server = server;
        this.user = user;
        this.userName = userName;
        this.socket = socket;
    }

    // Sends a message to the user
    public void sendMessage(MessagesB message) {
        String formattedMessage = message.formatMessage();
        out.println(formattedMessage);
    }

    @Override
    public void run() {
        try {
            // Set up input and output streams
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);

            // Send successful connection message to the user
            sendMessage(new ServerMessages("You have successfully connected to the server."));

            // Initializes Naming and Command handlers
            Names namingHandler = new Names(server, userName);
            Commandhandler commandHandler = new Commandhandler(server, userName, user);

            // Get valid username and log user's connection to the server
            displayName = namingHandler.getValidName();
            server.broadcast(new ServerMessages(displayName + " connected"));
            server.log(displayName + " connected");

            String message;

            // Continuously receive messages from user and process them
            while ((message = in.readLine()) != null) {
                // Try to process the message as a command
                boolean isCommandProcessed = commandHandler.processesCommands(message);

                // If the message is not a command, broadcast it to all users
                if (!isCommandProcessed) {
                    MessagesB newMessage = new UserMessages(this, message);
                    server.broadcast(newMessage);
                }
            }
        } catch (IOException e) {
            shutdown();
        }
    }

    public void shutdown() {
        if (!socket.isClosed()) {
            // Broadcast the user's disconnection to all users and remove the user from the server
            String disconnectInfo = displayName + " disconnected!";
            System.out.println(disconnectInfo);
            server.broadcast(new ServerMessages(disconnectInfo));
            server.removeUser(this);
        }

        try {
            // Close input and output streams and the socket
            in.close();
            out.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public BufferedReader getIn() {
        return in;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}