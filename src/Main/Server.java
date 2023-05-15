package Main;

import Messages.MessagesB;

import java.net.ServerSocket;
import java.net.Socket;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server implements Runnable, List<String> {

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

    // Main method to run the server
    public static void main(String[] args) {
        Server server = new Server();
        server.run();
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
                // Create user
                User user = new User();
                // Creates a new connection instance to handle the new connection
                Connection newConnection = new Connection(this, userSocket, "", null);
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
            if (connection == null || connection.getDisplayName() == null) {
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

    /**Bug fixing*/

    @Override
    public int size() {
        return this.size();
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public boolean contains(Object o) {
        return false;
    }

    @Override
    public Iterator<String> iterator() {
        return null;
    }

    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return null;
    }

    @Override
    public boolean add(String s) {
        return false;
    }

    @Override
    public boolean remove(Object o) {
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends String> c) {
        return false;
    }

    @Override
    public boolean addAll(int index, Collection<? extends String> c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return false;
    }

    @Override
    public void clear() {

    }

    @Override
    public String get(int index) {
        return null;
    }

    @Override
    public String set(int index, String element) {
        return null;
    }

    @Override
    public void add(int index, String element) {

    }

    @Override
    public String remove(int index) {
        return null;
    }

    @Override
    public int indexOf(Object o) {
        return 0;
    }

    @Override
    public int lastIndexOf(Object o) {
        return 0;
    }

    @Override
    public ListIterator<String> listIterator() {
        return null;
    }

    @Override
    public ListIterator<String> listIterator(int index) {
        return null;
    }

    @Override
    public List<String> subList(int fromIndex, int toIndex) {
        return null;
    }
}