package algorithms.mazeGenerators;
import java.util.Random;

/**
 * Class SimpleMazeGenerator
 * The Class responsible on generating maze based on naive approach
 * @author  Matan Shushan & Ido Kestenbaum
 */
public class SimpleMazeGenerator extends AMazeGenerator{

    /**
     * method for generating maze based on naive approach
     * @param row - number of rows in maze
     * @param column- number of columns in maze
     * @return a Maze
     */
    @Override
    public Maze generate(int row, int column) {
            if(row >= 2 && column >= 3){
                Random rand = new Random();
                Maze simpleMazeGenerator = new Maze(row, column);
                int[][] maze = simpleMazeGenerator.getMaze();
                this.initMazeWithWalls(maze);
                int startingPoint = rand.nextInt(column);
                int finalPoint = rand.nextInt(column);
                maze[0][startingPoint] = 0;
                simpleMazeGenerator.setStartposition(new Position(0, startingPoint));
                simpleMazeGenerator.setFinalposition(new Position(row - 1, finalPoint));
                this.createMazeSolution(maze, 0, startingPoint, finalPoint);
                return simpleMazeGenerator;
            }
            else {
                System.out.println("cannot construct a simple maze with coordinates that less then 2*3 size");
                System.out.println( "we will create instead a default simple maze in size of 15*15");
                return this.generate(15,15);
            }
    }

    //

    /**
     * the method creating solution to the maze and marked it with 0 - not a wall
     * @param maze board maze which is 2d array
     * @param row the row index
     * @param column the col index
     * @param finalPoint final point of the maze
     */
    private void createMazeSolution(int[][] maze, int row, int column, int finalPoint){
        Random rand = new Random();
        while(maze[maze.length - 1][finalPoint] == 1){
            switch (rand.nextInt(3)){
                case 0:
                    if(row + 1 < maze.length){
                        maze[row + 1][column] = 0;
                        row = row + 1;
                    }
                    break;
                case 1:
                    if(column + 1 < maze[row].length){
                        maze[row][column + 1] = 0;
                        column = column + 1;
                    }
                    break;
                case 2:
                    if(column - 1 >= 0){
                        maze[row][column - 1] = 0;
                        column = column - 1;
                    }
                    break;
            }
        }
    }

    /**
     * method for measuring the time duration of generating the maze in milliseconds
     * @param row - number of rows in maze
     * @param column - number of columns in maze
     * @return long which represents the time duration of generating the maze
     */
    @Override
    public long measureAlgorithmTimeMillis(int row, int column) {
        return super.measureAlgorithmTimeMillis(row, column);
    }
}
