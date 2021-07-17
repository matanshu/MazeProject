package main.java.Model;
import Client.Client;
import IO.MyCompressorOutputStream;
import IO.MyDecompressorInputStream;
import Server.Server;
import algorithms.mazeGenerators.*;
import javafx.scene.control.Alert;
import javafx.scene.input.KeyCode;
import Client.IClientStrategy;
import java.io.*;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashSet;
import java.util.Observable;
import Server.ServerStrategyGenerateMaze;
import Server.ServerStrategySolveSearchProblem;
import algorithms.mazeGenerators.Maze;
import algorithms.search.Solution;

/**
 * MyModel Class
 * The Class extends the Observable Class and implements the IModel Interface
 * The Class responsible on the logic part and communicate with the ViewModel layer
 */
public class MyModel extends Observable implements IModel {
    private Maze maze;
    private Server mazeGeneratingServer;
    private Server solveSearchProblemServer;
    private Solution mazeSolution;
    private boolean finish;
    private static HashSet<String> tableOfMaze;
    private int characterPositionRow = 1;
    private int characterPositionColumn = 1;
    private int goalPositionRow;
    private int goalPositionColumn;

    /**
     * MyModel Constructor
     * init ServerStrategyGenerateMaze and ServerStrategySolveSearchProblem
     * and start running the servers
     */
    public MyModel() {
        mazeGeneratingServer = new Server(5400, 1000, new ServerStrategyGenerateMaze());
        solveSearchProblemServer = new Server(5401, 1000, new ServerStrategySolveSearchProblem());
        finish = false;
        this.startServers();
        tableOfMaze = new HashSet<>();
    }

    /**
     * method which responsible on start running the servers
     */
    private void startServers() {
        mazeGeneratingServer.start();
        solveSearchProblemServer.start();
    }

    /**
     * method which responsible on stop running the servers
     */
    public void stopServers() {
        mazeGeneratingServer.stop();
        solveSearchProblemServer.stop();
    }

    /**
     * method for generating maze
     * @param width - maze width size
     * @param height- maze height size
     */
    @Override
    public void generateMaze(int width, int height) {
        try {
            Client client = new Client(InetAddress.getLocalHost(), 5400, new IClientStrategy() {
                public void clientStrategy(InputStream inFromServer, OutputStream outToServer) {
                    try {
                        ObjectOutputStream toServer = new ObjectOutputStream(outToServer);
                        ObjectInputStream fromServer = new ObjectInputStream(inFromServer);
                        toServer.flush();
                        int[] mazeDimensions = new int[]{height, width};
                        toServer.writeObject(mazeDimensions);
                        toServer.flush();
                        byte[] compressedMaze = (byte[]) ((byte[]) fromServer.readObject());
                        InputStream is = new MyDecompressorInputStream(new ByteArrayInputStream(compressedMaze));
                        byte[] decompressedMaze = new byte[height * width + 12];
                        is.read(decompressedMaze);
                        maze = new Maze(decompressedMaze);
                        characterPositionRow = maze.getStartPosition().getRowIndex();
                        characterPositionColumn = maze.getStartPosition().getColumnIndex();
                        goalPositionRow = maze.getGoalPosition().getRowIndex();
                        goalPositionColumn = maze.getGoalPosition().getColumnIndex();
                        maze.print();
                        System.out.println();
                    } catch (Exception var10) {
                        var10.printStackTrace();
                    }
                }
            });
            client.communicateWithServer();
        } catch (UnknownHostException var1) {
            var1.printStackTrace();
        }
        setChanged();
        notifyObservers();

    }

    /**
     * method which responsible on showing the solution
     */
    public void showSolution() {
        try {
            Client client = new Client(InetAddress.getLocalHost(), 5401, new IClientStrategy() {
                public void clientStrategy(InputStream inFromServer, OutputStream outToServer) {
                    try {
                        ObjectOutputStream toServer = new ObjectOutputStream(outToServer);
                        ObjectInputStream fromServer = new ObjectInputStream(inFromServer);
                        toServer.flush();
                        toServer.writeObject(maze);
                        toServer.flush();
                        mazeSolution = (Solution) fromServer.readObject();
                    } catch (Exception var10) {
                        var10.printStackTrace();
                    }
                }
            });
            client.communicateWithServer();
        } catch (UnknownHostException var1) {
            var1.printStackTrace();
        }
        setChanged();
        notifyObservers(mazeSolution);
    }

    /**
     * getting the maze board
     * @return maze board
     */
    @Override
    public int[][] getMaze() {
        return maze.getMaze();
    }

    /**
     * getting the maze object
     * @return maze object
     */
    public Maze getObjectMaze() {
        return this.maze;
    }

    /**
     * method which responsible on moving the Character acoordingly to his key movement
     * @param movement key press to move by user
     */
    @Override
    public void moveCharacter(KeyCode movement) {
        if (finish == false) {
            switch (movement) {
                //UP
                case UP:
                case DIGIT8:
                    if (characterPositionRow - 1 < 0 || characterPositionRow - 1 >= getMaze().length || getMaze()[characterPositionRow - 1][characterPositionColumn] == 1)
                        break;
                    else {
                        characterPositionRow--;
                    }
                    break;

                //DOWN
                case DOWN:
                case DIGIT2:

                    if (characterPositionRow + 1 >= getMaze().length || getMaze()[characterPositionRow + 1][characterPositionColumn] == 1)
                        break;
                    else
                        characterPositionRow++;
                    break;

                //RIGHT
                case RIGHT:
                case DIGIT6:
                    if (characterPositionColumn + 1 >= getMaze()[characterPositionRow].length || getMaze()[characterPositionRow][characterPositionColumn + 1] == 1)
                        break;
                    else
                        characterPositionColumn++;
                    break;

                // LEFT
                case LEFT:
                case DIGIT4:

                    if (characterPositionColumn - 1 < 0 || getMaze()[characterPositionRow][characterPositionColumn - 1] == 1)
                        break;
                    else
                        characterPositionColumn--;
                    break;

                // RIGHTUP
                case DIGIT9:

                    if (characterPositionRow - 1 < 0 || characterPositionColumn + 1 >= getMaze()[characterPositionRow].length || getMaze()[characterPositionRow - 1][characterPositionColumn + 1] == 1)
                        break;
                    else {
                        characterPositionRow--;
                        characterPositionColumn++;
                    }
                    break;

                // LEFTUP
                case DIGIT7:
                    if (characterPositionRow - 1 < 0 || characterPositionColumn - 1 < 0 || getMaze()[characterPositionRow - 1][characterPositionColumn - 1] == 1)
                        break;
                    else {
                        characterPositionRow--;
                        characterPositionColumn--;

                    }

                    break;

                // RIGHTDOWN
                case DIGIT3:
                    if (characterPositionRow + 1 >= getMaze().length || characterPositionColumn + 1 >= getMaze()[characterPositionRow].length || getMaze()[characterPositionRow + 1][characterPositionColumn + 1] == 1)
                        break;
                    else {
                        characterPositionRow++;
                        characterPositionColumn++;

                    }

                    break;
                // LEFTDOWN
                case DIGIT1:
                    if (characterPositionRow + 1 >= getMaze().length || characterPositionColumn - 1 < 0 || getMaze()[characterPositionRow + 1][characterPositionColumn - 1] == 1)
                        break;
                    else {
                        characterPositionRow++;
                        characterPositionColumn--;
                    }
                    break;
            }
        }
        if (characterPositionColumn == maze.getGoalPosition().getColumnIndex() && characterPositionRow == maze.getGoalPosition().getRowIndex()) {
            finish = true;
        }
        setChanged();
        notifyObservers(finish);
    }

    /**
     * setting new boolean value to finish variable
     * @param finish
     */
    public void setFinish(boolean finish) {
        this.finish = finish;
    }

    /**
     * getting the current Character position row
     * @return Character position row
     */
    @Override
    public int getCharacterPositionRow() {
        return characterPositionRow;
    }


    /**
     * getting the current Character position col
     * @return Character position col
     */
    @Override
    public int getCharacterPositionColumn() {
        return characterPositionColumn;
    }

    /**
     * exit the program
     */
    public void exitProgram() {
        this.stopServers();
    }

    /**
     *  saving maze into file
     * @param string name of the file
     */
    public void saveMaze(String string) {
        if (maze != null) {
            try {
                maze.setStartposition(new Position(characterPositionRow, characterPositionColumn));
                String tempDirectoryPath = System.getProperty("java.io.tmpdir");
                String path = tempDirectoryPath + "/" + string + ".txt";
                //String path = tempDirectoryPath + "/" + key;
                File file = new File(path);
                file.createNewFile();
                if (!tableOfMaze.contains(path)) {
                    OutputStream out = new MyCompressorOutputStream(new FileOutputStream(path));
                    out.write(maze.toByteArray());
                    out.flush();
                    out.close();
                }
            } catch (IOException var16) {
                var16.printStackTrace();
            }
        }
    }

    /**
     * loading maze from file
     * @param string name of the file
     */
    public void loadMaze(String string) {
        String tempDirectoryPath = System.getProperty("java.io.tmpdir");
        //File folder = new File("./mazes");
        File folder = new File(tempDirectoryPath);
        File[] listOfFiles = folder.listFiles();
        String path = string + ".txt";
        boolean flag = false;
        try {
            for (File file : listOfFiles) {
                if (file.getName().equals(path)) {
                    flag = true;
                }
            }
            if (flag) {
                InputStream in = new MyDecompressorInputStream(new FileInputStream(tempDirectoryPath + "/" + string + ".txt"));
                BufferedReader input = new BufferedReader(new InputStreamReader(new FileInputStream(tempDirectoryPath + "/" + string + ".txt")));
                input.readLine();
                int rows = Integer.parseInt(input.readLine());
                input.readLine();
                int columns = Integer.parseInt(input.readLine());
                System.out.println(rows + "" + columns);
                byte[] savedMazeBytes = new byte[rows * columns + 12];
                in.read(savedMazeBytes);
                maze = new Maze(savedMazeBytes);
                maze.print();
                characterPositionRow = maze.getStartPosition().getRowIndex();
                characterPositionColumn = maze.getStartPosition().getColumnIndex();
                goalPositionRow = maze.getGoalPosition().getRowIndex();
                goalPositionColumn = maze.getGoalPosition().getColumnIndex();
                in.close();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText(" the file is not exist! "); //edit
                alert.show();
            }
        } catch (Exception e) {
        }
        setChanged();
        notifyObservers();
    }
}





























