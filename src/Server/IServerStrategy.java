package Server;
import java.io.*;

/**
 * IServerStrategy Interfaace
 * interface for defining different server strategies to implement
 */
public interface IServerStrategy {

    /**
     * server strategy to implement accordingly to needed operation
     * @param inFromClient input getting from client
     * @param outToClient output sending to client
     */
    void serverStrategy(InputStream inFromClient, OutputStream outToClient);
}
