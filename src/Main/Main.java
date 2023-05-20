package Main;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) {
        ExecutorService threadPool;
        Server server = new Server();
        threadPool = Executors.newCachedThreadPool();
        threadPool.execute(server);
        for (int i =  1; i < 3; i++) {
            try {
                User user = new User(new Socket("127.0.0." + i, 9999), server);
                threadPool.execute(user);
            } catch (UnknownHostException e) {
                System.out.println("");
            } catch (IOException e) {
                System.out.println("");
            }
        }
    }
}
