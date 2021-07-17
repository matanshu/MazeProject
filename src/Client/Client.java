package Client;
import java.net.InetAddress;
import java.net.Socket;

/**
 * Client Class
 * The Class represents a client
 * @author  Matan Shushan & Ido Kestenbaum
 */
public class Client {

    private InetAddress serverIP;
    private int serverPort;
    private IClientStrategy clientStrategy;

    /**
     * Constructor for building Client Object
     * @param serverIP server IP address
     * @param serverPort server Port address
     * @param clientStrategy interface for client strategy according to needed action
     */
    public Client(InetAddress serverIP, int serverPort, IClientStrategy clientStrategy) {
        this.serverIP = serverIP;
        this.serverPort = serverPort;
        this.clientStrategy = clientStrategy;
    }

    /**
     * starting the client operation
     * connect with server
     * applying appropriate client strategy
     */
    private void start() {
        try {
            Socket theServer = new Socket(serverIP, serverPort);
            System.out.println(String.format("Client is connected to server (IP: %s, port: %s)", serverIP, serverPort));
            clientStrategy.clientStrategy(theServer.getInputStream(), theServer.getOutputStream());
            theServer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * communicating with server
     * applying start method
     */
    public void communicateWithServer(){
        this.start();
    }
}
