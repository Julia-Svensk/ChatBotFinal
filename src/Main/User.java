package Main;

import Messages.DeserializedMessages;
import Messages.DeserializingMessages;
import UI.Gui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class User implements Runnable {
    private Socket socket;
    Server server;

    private Gui gui;

    //A reader for receiving messages from the server.
    private BufferedReader in;

    //A writer for sending messages to the server.
    private PrintWriter out;

    //The constructor for creating a new User object.
    public User(Socket socket, Server server) {
        try {
            //Connects to the chat server using the localhost IP address and port number 9999.
            this.socket = socket;

            //Initializes the output stream for sending messages to the server.
            out = new PrintWriter(socket.getOutputStream(), true);

            //Initializes the input stream for receiving messages from the server.
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            //Creates a new gui and passes this User object as a parameter.
            gui = new Gui(this);

            this.server = server;


        } catch (IOException e) {
            //Shuts down the User object if an IOException occurs.
            shutdown();
        }
    }

    //This method is called when the User object is started as a new thread.
    @Override
    public void run() {
        try {
            //Continuously reads messages from the server and displays them in the gui.
            String inMessage;
            while ((inMessage = in.readLine()) != null) {
                //Deserializes the incoming message into a DeserializedMessages object.
                DeserializedMessages deserializedMessage = DeserializingMessages.deserializedMessage(inMessage);

                //Adds the deserialized message to the GUI.
                gui.addMessage(deserializedMessage);
            }
        } catch (IOException e) {
            //Shuts down the User object if an IOException occurs.
            shutdown();
        }
    }

    //This method is called when the User object is shutting down.
    public void shutdown() {
        try {
            //Closes the output stream for sending messages to the server.
            out.close();

            //Closes the input stream for receiving messages from the server.
            in.close();

            //Closes the graphical user interface.
            gui.closeGui();

            //Closes the socket connection to the server if it is still open.
            if (!socket.isClosed()) {
                socket.close();
            }
        } catch (IOException e) {
            //Prints the stack trace if an IOException occurs.
            e.printStackTrace();
        }
    }

    //This method is called when the User object wants to send a message to the server.
    public void sendMessage(String message) {
        //Sends the message to the server using the output stream.
        out.println(message);
    }
}