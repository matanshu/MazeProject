package algorithms.search;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Stack;

/**
 * DepthFirstSearch Class
 * The Class extends the Abstract Class ASearchingAlgorithm
 * The Class represents the DFS algorithm in searching problem.
 * @author  Matan Shushan & Ido Kestenbaum
 */

public class DepthFirstSearch extends ASearchingAlgorithm {

    private Stack<AState> stack;
    private HashSet<AState> visited;

    /**
     * Constructor
     */
    public DepthFirstSearch(){
        super();
        super.name = "DepthFirstSearch";
        stack = new Stack<>() ;
        visited = new HashSet<>() ;
    }

    /**
     * Method which solves the problem,
     * the solution includes the path for getting from start state to goal state
     * @param search - ISearchable object, means object which can be searched
     * @return the solution of the searching problem
     */
    public Solution solve(ISearchable search) {
        AState startState = search.getStartState();
        AState goalState = search.getGoalState();
        AState currState;
        currState = startState;
        ArrayList<AState> neighbors = new ArrayList<>();
        AState currNeighbor = null;
        HashSet<AState> copyStack = new HashSet<>();
        stack.push(currState);
        copyStack.add(currState);

        while (!stack.isEmpty()) {
            currState = stack.pop();
            visited.add(currState);
            if (currState.equals(goalState)) {
                return new Solution(currState);
            }
            neighbors = search.getAllSuccessors(currState);
            while (!neighbors.isEmpty()) {
                currNeighbor = neighbors.remove(0);
                if (!visited.contains(currNeighbor)) {
                    if (copyStack.contains(currNeighbor)) {
                        currNeighbor.setCameFrom(currState);
                    }
                     else {
                        currNeighbor.cameFrom = currState;
                        stack.push(currNeighbor);
                        copyStack.add(currNeighbor);
                    }
                }
            }
        }
        return new Solution(currState);
    }

    /**
     * Method for getting the number of nodes that evaluated during searching for solution
     * @return the number of nodes that evaluated during searching solution
     */
    public int getNumberOfNodesEvaluated(){
        return visited.size();
    }
}


