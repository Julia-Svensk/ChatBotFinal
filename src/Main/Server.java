package Main;

import Messages.MessagesB;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server extends ArrayList<String> implements Runnable {

    // List of active connections
    private final ArrayList<Connection> activeConnections;
    // Server log instance
    private final ServerLog serverLog;
    // Server socket
    private ServerSocket serverSocket;
    // Indicates if the server is closed
    private boolean isServerClosed;
    // Thread pool to handle connections
    private ExecutorService threadPool;

    // Constructor
    public Server() {
        activeConnections = new ArrayList<>();
        isServerClosed = false;
        serverLog = new ServerLog();
        log("The server has started.");
    }

    // Runnable interface's run method
    @Override
    public void run() {
        try {
            // Creates a new server socket on port 9999
            serverSocket = new ServerSocket(9999);
            // Creates a cached thread pool to handle connections
            threadPool = Executors.newCachedThreadPool();
            // Loop until the server is closed
            while (!isServerClosed) {
                // Accepts incoming connection requests and creates a socket for them
                Socket userSocket = serverSocket.accept();
                // Creates a new connection instance to handle the new connection
                Connection newConnection = new Connection(this, userSocket, "New user");
                // Adds the new connection to the active connections list
                activeConnections.add(newConnection);
                // Assigns a thread from the thread pool to run the Connection instance and starts executing it
                threadPool.execute(newConnection);
            }
        } catch (IOException e) {
            log("There was an error when shutting down the server.");
            log(e.toString());
            shutdownServer();
        }
    }

    // Broadcasts a message to all connected users
    public void broadcast(MessagesB message) {
        // Loops through active connections and sends the message to each of them
        for (Connection connection : activeConnections) {
            // If the connection or display name is null, skips it
            if (connection == null) {
                continue;
            }
            connection.sendMessage(message);
        }
    }

    // Writes a message to the server log
    public void log(String message) {
        serverLog.WriteToFile(message);
    }

    // Shuts down the server
    public void shutdownServer() {
        try {
            isServerClosed = true;
            // Closes the socket if it is not already closed
            if (!serverSocket.isClosed()) {
                serverSocket.close();
            }
            // Loops through active connections and shuts them down
            for (Connection connection : activeConnections) {
                connection.shutdown();
            }
        } catch (IOException e) {
            log(e.toString());
        }
    }

    // Removes a user from the active connections list
    public void removeUser(Connection targetConnection) {
        activeConnections.remove(targetConnection);
    }

    // Getter method for active connections list
    public ArrayList<Connection> getActiveConnections() {
        return this.activeConnections;
    }
}