package algorithms.search;

import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.Position;

import java.util.ArrayList;
/**
 * The  class represnt object adapt of the maze problem. the class implemt the ISerchable interface.
 * @author  ido kestenbaum and matan shushan
 * @since 8.4.19
 */
public class SearchableMaze implements ISearchable {

    private Maze maze;

    public SearchableMaze(Maze maze) {
        if(maze != null){
            this.maze = new Maze(maze);
        }
    }

    /**
     *
     * @return the goal state of the maze
     */
    @Override
    public AState getGoalState() {
           return new MazeState(maze.getGoalPosition());
    }

    /**
     *
     * @return the start state of the maze
     */
    @Override
    public AState getStartState() {

        return new MazeState(maze.getStartPosition());
    }

    /**
     *the method return the all neighbors of a state
     * @param state - represent the current state in the maze
     * @return array list of al successors of a state
     */
    @Override
    public ArrayList<AState> getAllSuccessors(AState state) {
        if(state != null){
            ArrayList<AState> listOfSuccessors = new ArrayList<>();
            //now need to find the state successors and compute there cost
            int row = ((MazeState)state).getCurrentPosition().getRowIndex();
            int column = ((MazeState)state).getCurrentPosition().getColumnIndex();
            int[][] arrayMaze = maze.getMaze();
            boolean right = false, left = false, up = false, down = false;
            if(row - 1 >= 0 && arrayMaze[row - 1][column] == 0){//up
                AState upState = new MazeState(new Position(row - 1, column));
                upState.setCost(state.getCost() + 10); //set cost
                listOfSuccessors.add(upState);
                up = true;
            }
            if(row + 1  < arrayMaze.length  && arrayMaze[row+1][column] == 0){ //down

                AState downState = new MazeState(new Position(row + 1, column));
                downState.setCost(state.getCost() + 10); //set cost
                listOfSuccessors.add(downState);
                down = true;
            }
            if(column + 1 < arrayMaze[row].length && arrayMaze[row][column + 1] == 0){ //right
                AState rightState = new MazeState(new Position(row, column + 1));
                rightState.setCost(state.getCost() + 10); //set cost
                listOfSuccessors.add(rightState);
                right = true;
            }
            if(column - 1  >= 0  && arrayMaze[row][column - 1] == 0){ //left
                AState leftState = new MazeState(new Position(row , column - 1));
                leftState.setCost(state.getCost() + 10); //set cost
                listOfSuccessors.add(leftState);
                left = true;
            }
            if(row - 1 >= 0 && column + 1 < arrayMaze[row].length && (right || up)  && arrayMaze[row - 1][column + 1] == 0){ //diagonal right up
                AState diagoanlRightUpState = new MazeState(new Position(row - 1 , column + 1));
                diagoanlRightUpState.setCost(state.getCost() + 15); //set cost
                listOfSuccessors.add(diagoanlRightUpState);
            }
            if(column + 1 < arrayMaze[row].length && row + 1  < arrayMaze.length &&  (right || down) && arrayMaze[row + 1][column + 1] == 0){ //diagonal right down

                AState diagoanlRightDownState = new MazeState(new Position(row + 1 , column + 1));
                diagoanlRightDownState.setCost(state.getCost() + 15); //set cost
                listOfSuccessors.add(diagoanlRightDownState);
            }
            if(column - 1  >= 0 && row - 1 >= 0 && (left || up) && arrayMaze[row - 1][column - 1] == 0){ //diagonal left up

                AState diagoanlLeftUpState = new MazeState(new Position(row - 1 , column - 1));
                diagoanlLeftUpState.setCost(state.getCost() + 15); //set cost
                listOfSuccessors.add(diagoanlLeftUpState);
            }
            if(row + 1  < arrayMaze.length  && column - 1  >= 0  && (left || down) && arrayMaze[row + 1][column - 1] == 0){ //diagonal left down

                AState diagoanlLeftDownState = new MazeState(new Position(row + 1 , column - 1));
                diagoanlLeftDownState.setCost(state.getCost() + 15); //set cost
                listOfSuccessors.add(diagoanlLeftDownState);
            }
            return listOfSuccessors;
        }
        else {

            System.out.println("the input state is null! there no successors at all...!");
            return new ArrayList<>();
        }
    }
}
