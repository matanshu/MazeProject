package main.java.view;
import algorithms.mazeGenerators.Maze;

/**
 * IView Interface
 * The interface define method for View layer objects
 * @author  Matan Shushan & Ido Kestenbaum
 */
public interface IView {

    /**
     * method which responsible on displaying the maze
     * @param maze the maze object to be displayed
     */
    void displayMaze(Maze maze);}
