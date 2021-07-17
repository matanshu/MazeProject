package algorithms.search;
import java.util.*;

/**
 * BreadthFirstSearch Class
 * The Class extends the Abstract Class ASearchingAlgorithm
 * The Class represents the BFS algorithm in searching problem.
 * @author  Matan Shushan & Ido Kestenbaum
 */

public class BreadthFirstSearch extends ASearchingAlgorithm {

    protected Queue<AState> openList;
    protected HashSet<AState>closedList;

    /**
     * Constructor
     */
    public BreadthFirstSearch(){
        super();
        super.name = "BreadthFirstSearch";
        openList = new LinkedList<>();
        closedList = new HashSet<AState>() ;
    }

    /**
     * Method which solves the problem,
     * the solution includes the path for getting from start state to goal state
     * @param search - ISearchable object, means object which can be searched
     * @return the solution of the searching problem
     */
    public Solution solve(ISearchable search) {
        AState startState = search.getStartState();
        AState currState = startState;
        AState goalState = search.getGoalState();
        ArrayList<AState> neighbors = new ArrayList<>();
        HashSet<AState> copyOpenList = new HashSet<>();
        AState currNeighbor = null ;
        openList.add(currState);
        copyOpenList.add(currState);

        while (openList.isEmpty() == false) {
            closedList.add(openList.poll());
            if (currState.equals(goalState)) {
                return new Solution(currState);
            }
            neighbors = search.getAllSuccessors(currState);
            while (!neighbors.isEmpty()){
                currNeighbor = neighbors.remove(0);
                if (!closedList.contains(currNeighbor)) {
                     if (!copyOpenList.contains(currNeighbor)) {
                        currNeighbor.cameFrom = currState;
                        ((MazeState)currNeighbor).setDistanceFromStartPosition(((MazeState)currState).getDistanceFromStartPosition() + 1);
                        openList.add(currNeighbor);
                        copyOpenList.add(currNeighbor);
                    } else if (((MazeState)currState).getDistanceFromStartPosition() < ((MazeState)currNeighbor).getDistanceFromStartPosition()) {
                        currNeighbor.cameFrom = currState;
                        ((MazeState)currNeighbor).setDistanceFromStartPosition(((MazeState)currState).getDistanceFromStartPosition() + 1);
                    }
                }
            }
            currState = openList.peek();
            }
        return new Solution(currState);
    }

    /**
     * Method for getting the number of nodes that evaluated during searching for solution
     * @return the number of nodes that evaluated during searching solution
     */
    public int getNumberOfNodesEvaluated(){
        return closedList.size();
    }

}
