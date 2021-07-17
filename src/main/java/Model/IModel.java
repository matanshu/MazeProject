package main.java.Model;
import algorithms.mazeGenerators.Maze;
import javafx.scene.input.KeyCode;

/**
 * IModel Interface
 * The interface define methods for Model layer objects
 * @author  Matan Shushan & Ido Kestenbaum
 */
public interface IModel {

    /**
     * method for generating maze
     * @param width - maze width size
     * @param height- maze height size
     */
    void generateMaze(int width, int height);

    /**
     * method which responsible on moving the Character
     * @param movement key press to move by user
     */
    void moveCharacter(KeyCode movement);

    /**
     * getting the maze board
     * @return maze board
     */
    int[][] getMaze();

    /**
     * getting the current Character position row
     * @return Character position row
     */
    int getCharacterPositionRow();

    /**
     * getting the current Character position col
     * @return Character position col
     */
    int getCharacterPositionColumn();

    /**
     * getting the maze object
     * @return maze object
     */
    Maze getObjectMaze();

    /**
     * setting new boolean value to finish variable
     * @param finish
     */
    void setFinish(boolean finish);

    /**
     * showing the solution
     */
    void showSolution();

    /**
     * stop running the servers
     */
    void stopServers();

    /**
     *  saving maze into file
     * @param string name of the file
     */
    void saveMaze(String string);

    /**
     * loading maze from file
     * @param string name of the file
     */
    void loadMaze(String string);

    /**
     * exit the program
     */
    void exitProgram();
}
