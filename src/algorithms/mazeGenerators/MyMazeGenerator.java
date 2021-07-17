package algorithms.mazeGenerators;
import java.util.ArrayList;
import java.util.Random;

/**
 * Class MyMazeGenerator
 * The Class responsible on generating maze based on Randomized Prim's Algorithm
 * @author  Matan Shushan & Ido Kestenbaum
 */
public class MyMazeGenerator extends AMazeGenerator {

    /**
     * method for generating maze based on Randomized Prim's Algorithm
     * @param row - number of rows in maze
     * @param column- number of columns in maze
     * @return a Maze
     */
    @Override
    public Maze generate(int row, int column) {
        if(row >= 2 && column >= 3) {
            ArrayList<Position> candidateCells = new ArrayList<>();
            Random rand = new Random();
            Maze primMaze = new Maze(row, column);
            Position startingPoint = new Position(rand.nextInt(row), rand.nextInt(column)); //random starting point
            primMaze.setStartposition(startingPoint);//setting starting point
            Position goalPoint = null;
            int[][] maze = primMaze.getMaze();
            this.initMazeWithWalls(maze);
            maze[startingPoint.getRowIndex()][startingPoint.getColumnIndex()] = 0; //setting starting point
            functionForCandidateCells(maze, candidateCells, startingPoint.getRowIndex(), startingPoint.getColumnIndex()); //insert candidates cells of the startingPoint cell into list

            while (!candidateCells.isEmpty()) {
                Position chosenCell = candidateCells.get(new Random().nextInt(candidateCells.size())); //choosing a specific cell from candidates
                maze[chosenCell.getRowIndex()][chosenCell.getColumnIndex()] = 0; //setting chosen cell to passage state
                candidateCells.remove(chosenCell); //remove chosen cell from list
                ArrayList<Position> neighbors = functionForFindingNeighbors(maze, chosenCell.getRowIndex(), chosenCell.getColumnIndex()); //finding candidate cell neighbors
                Position chosenNeighbor = neighbors.get(new Random().nextInt(neighbors.size())); //choosing a specific cell from neighbors
                functionSetCellToStatePassage(maze, chosenCell, chosenNeighbor); //connect the chosen cell with the neighbor by setting the cell in-between to state Passage
                functionForCandidateCells(maze, candidateCells, chosenCell.getRowIndex(), chosenCell.getColumnIndex()); //insert candidates cells of the chosen cell into list
                if (candidateCells.size() == 1) {
                    goalPoint = candidateCells.get(0);
                }
            }
            if (goalPoint != null) {
                primMaze.setFinalposition(goalPoint);
            }
            return primMaze;
        }
        else {
            System.out.println("cannot construct a my maze with coordinates that less then 2*3 size");
            System.out.println( "we will create instead a default simple maze in size of 15*15");
            return this.generate(15,15);
        }
    }

    /**
     * private method for connecting the chosen cell with the neighbor by setting the cell in-between to state Passage
     * @param maze board maze which is 2d array
     * @param chosenCell the chosen sell
     * @param chosenNeighbor the chosen neighbor
     */
    private void functionSetCellToStatePassage(int[][] maze, Position chosenCell, Position chosenNeighbor){
        if(chosenCell.getRowIndex() == chosenNeighbor.getRowIndex()){
            if(chosenCell.getColumnIndex() > chosenNeighbor.getColumnIndex()){
                maze[chosenCell.getRowIndex()][chosenCell.getColumnIndex() - 1] = 0;
            }
            else {
                maze[chosenCell.getRowIndex()][chosenCell.getColumnIndex() + 1] = 0;
            }
        }
        else {
            if(chosenCell.getRowIndex() > chosenNeighbor.getRowIndex()){
                maze[chosenCell.getRowIndex() - 1][chosenCell.getColumnIndex()] = 0;
            }
            else {
                maze[chosenCell.getRowIndex() + 1][chosenCell.getColumnIndex()] = 0;
            }
        }
    }

    /**
     *
     * @param maze board maze which is 2d array
     * @param row the row index
     * @param column the column index
     * @return ArrayList of Position which represent the found neighbors
     */
    private ArrayList<Position> functionForFindingNeighbors(int[][] maze, int row, int column) {
        ArrayList<Position> neighbors = new ArrayList<>();
        if(row - 2 >= 0 ){ //up
            if(maze[row - 2][column] == 0){
                neighbors.add(new Position(row - 2, column ));
            }
        }

        if(row + 2 < maze.length){ //down
            if(maze[row + 2][column] == 0){
                neighbors.add(new Position(row + 2, column));
            }
        }

        if(column + 2 < maze[row].length){ //right
            if(maze[row][column + 2] == 0){
                neighbors.add(new Position(row, column + 2));
            }
        }

        if(column - 2 >= 0){ //left
            if(maze[row][column - 2] == 0){
                neighbors.add(new Position(row, column - 2));
            }
        }

        return neighbors;
    }

    /**
     * method for computing all the candidate cells to be without cells
     * @param maze board maze which is 2d array
     * @param candidateCells
     * @param row the row index
     * @param column the col index
     */
    private void functionForCandidateCells(int[][] maze, ArrayList<Position> candidateCells, int row, int column){
        if(row - 2 >= 0){ //up
            if(maze[row - 2][column] == 1){
                candidateCells.add(new Position(row - 2, column));
            }
        }

        if(row + 2 < maze.length){ //down
            if(maze[row + 2][column] == 1){
                candidateCells.add(new Position(row + 2, column));
            }
        }

        if(column + 2 < maze[row].length){ //right
            if(maze[row][column + 2] == 1){
                candidateCells.add(new Position(row, column + 2));
            }
        }

        if(column - 2 >= 0){ //left
            if(maze[row][column - 2] == 1){
                candidateCells.add(new Position(row, column - 2));
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
