package algorithms.mazeGenerators;

/**
 * IMazeGenerator Interface
 * The interface define method for generating mazes
 * @author  Matan Shushan & Ido Kestenbaum
 */
public interface IMazeGenerator {

   /**
    * abstract method for generating maze
    * @param row - number of rows in maze
    * @param column- number of columns in maze
    * @return a Maze
    */
   Maze generate(int row, int column);

   /**
    * method for measuring the time duration of generating the maze in milliseconds
    * @param row - number of rows in maze
    * @param column - number of columns in maze
    * @return long which represents the time duration of generating the maze
    */
   long measureAlgorithmTimeMillis(int row, int column);
}

