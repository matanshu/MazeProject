package Server;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Server Class
 * The class is the server of the program
 * ths server responsible on handling and responding on client requests
 */
public class Server {

    private int port;
    private int listeningInterval;
    private IServerStrategy serverStrategy;
    private volatile boolean stop;
    private ExecutorService executor;

    /**
     * Server Constructor
     * @param port the port of the server
     * @param listeningInterval
     * @param serverStrategy the specific strategy / role of the server
     */
    public Server(int port, int listeningInterval, IServerStrategy serverStrategy) {
        this.port = port;
        this.listeningInterval = listeningInterval;
        this.serverStrategy = serverStrategy;
    }

    /**
     * allocating new thread as a running server
     */
    public void start() {
        new Thread(() -> {
            runServer();
        }).start();
    }

    /**
     * running the server
     * the server is waiting for handling assignments from clients
     */
    private void runServer() {
        Configurations.init();
        executor = Executors.newFixedThreadPool(Configurations.getNumberOfThreads());
        try {
            ServerSocket serverSocket = new ServerSocket(port);
            serverSocket.setSoTimeout(listeningInterval);
            while (!stop) {
                try {
                    Socket clientSocket = serverSocket.accept(); // blocking call
                    executor.execute(new Thread(() -> handleClient(clientSocket)));
                } catch (SocketTimeoutException e) {
                }
            }
            executor.shutdown();
            serverSocket.close();
        } catch (IOException e) {
        }
    }

    /**
     * handling the client job accordingly to the appropriate strategy
     * @param clientSocket
     */
    private void handleClient(Socket clientSocket) {
        try {
            serverStrategy.serverStrategy(clientSocket.getInputStream(), clientSocket.getOutputStream());
            clientSocket.close();
        } catch (IOException e) {
        }
    }

    /**
     * stopping server work
     */
    public void stop() {
        stop = true;
    }
}
