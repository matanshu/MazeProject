package algorithms.mazeGenerators;

/**
 * Abstract Class AMazeGenerator
 * The Class implements ImazeGenerator Interface,
 * and Responsible on generating different kinds of mazes
 * @author  Matan Shushan & Ido Kestenbaum
 */
public abstract class AMazeGenerator implements IMazeGenerator {

    /**
     * abstract method for generating maze
     * @param row - number of rows in maze
     * @param column- number of columns in maze
     * @return a Maze
     */
    @Override
    public abstract Maze generate(int row, int column);

    /**
     * method for measuring the time duration of generating the maze in milliseconds
     * @param row - number of rows in maze
     * @param column - number of columns in maze
     * @return long which represents the time duration of generating the maze
     */
    @Override
    public long measureAlgorithmTimeMillis(int row, int column) {
        long timeBefore = System.currentTimeMillis();
        generate(row,column);
        long timeAfter = System.currentTimeMillis();
        return timeAfter - timeBefore;
    }

    /**
     * method for initializing Maze with walls
     * @param maze - 2D array of a maze
     */
    protected void initMazeWithWalls(int[][] maze){
        if(maze != null && maze.length >= 2 && maze[0].length >= 2){
            for(int i = 0; i < maze.length; i++){
                for(int J = 0; J < maze[i].length; J++){
                    maze[i][J] = 1;
                }
            }
        }
    }
}
