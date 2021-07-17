package Server;
import algorithms.mazeGenerators.Maze;
import algorithms.search.*;
import java.io.*;
import java.util.HashMap;

/**
 * ServerStrategySolveSearchProblem Class which implements IServerStrategy Interface
 * The Class acting as strategy of mazes searching problem solver
 */
public class ServerStrategySolveSearchProblem implements IServerStrategy {
    static HashMap<Integer, String> tableOfMaze;

    public ServerStrategySolveSearchProblem() {
        tableOfMaze = new HashMap<Integer, String>();
    }

    /**
     * solving maze searching problem, strategy implementation
     * getting from client the maze object and sending to client the maze solution
     * @param inFromClient input getting from client
     * @param outToClient output sending to client
     */
    @Override
    public void serverStrategy(InputStream inFromClient, OutputStream outToClient) {
        try {
            String tempDirectoryPath = System.getProperty("java.io.tmpdir");
            ObjectInputStream fromClient = new ObjectInputStream(inFromClient);
            ObjectOutputStream toClient = new ObjectOutputStream(outToClient);
            toClient.flush();
            Maze maze = (Maze) fromClient.readObject();
            int key = maze.hashCode();
            String path = tempDirectoryPath + "/" + key;

            /**
             * checking if the current maze already was in the past
             * in this case, pull the existing solution of this maze
             */
            if (tableOfMaze.containsKey(key)) {
                FileInputStream fileIn = new FileInputStream(path);
                ObjectInputStream objectin = new ObjectInputStream(fileIn);
                Solution solution1 = (Solution) objectin.readObject();
                toClient.writeObject(solution1);
                fromClient.close();
                toClient.close();
            }

            else {
                ASearchingAlgorithm searchingAlgorithm = Configurations.getAlgorithm();//there 3 different algorithms for solving
                SearchableMaze test = new SearchableMaze(maze);
                Solution solution = searchingAlgorithm.solve(test);
                FileOutputStream fileout = new FileOutputStream(path);
                ObjectOutputStream objectout = new ObjectOutputStream(fileout);
                objectout.writeObject(solution);
                tableOfMaze.put(key, path);
                objectout.close();
                fileout.close();
                FileInputStream fileIn = new FileInputStream(path);
                ObjectInputStream objectin = new ObjectInputStream(fileIn);
                objectin.close();
                fileIn.close();
                toClient.writeObject(solution);
                fromClient.close();
                toClient.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}