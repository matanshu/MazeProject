package Server;
import IO.MyCompressorOutputStream;
import algorithms.mazeGenerators.Maze;
import java.io.*;

/**
 * ServerStrategyGenerateMaze Class which implements IServerStrategy Interface
 * The Class acting as strategy of mazes creator
 */
public class ServerStrategyGenerateMaze implements IServerStrategy {

    /**
     * generating maze strategy implementation
     * getting from client the maze size and sending to client the generated maze
     * @param inFromClient input getting from client
     * @param outToClient output sending to client
     */
    @Override
    public void serverStrategy(InputStream inFromClient, OutputStream outToClient) {
        try {
            ObjectInputStream fromClient = new ObjectInputStream(inFromClient);
            ObjectOutputStream toClient = new ObjectOutputStream(outToClient);
            toClient.flush();
            int[] mazeDetails = (int[]) fromClient.readObject();
            Maze maze = Configurations.getGenerator().generate(mazeDetails[0], mazeDetails[1]);
            byte[] byteMaze = maze.toByteArray();
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            OutputStream myCompressorOutputStream = new MyCompressorOutputStream(byteArrayOutputStream);
            myCompressorOutputStream.write(byteMaze);
            toClient.writeObject(byteArrayOutputStream.toByteArray());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
}
