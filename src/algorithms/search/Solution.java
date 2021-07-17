package algorithms.search;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Stack;

/**
 * Solution Class
 * The Class represents an object of solution in searching problem
 * @author  Matan Shushan & Ido Kestenbaum
 */
public class Solution implements Serializable {

    private ArrayList<AState> solutionList;

    /**
     * Constructor
     * @param goalState the goal state
     */
    public Solution(AState goalState){
        solutionList = new ArrayList<>();
        if(goalState != null){
            Stack<AState> stackStates = new Stack<>();
            AState state = goalState;
            while (state != null){
                stackStates.push(state);
                state = state.getCameFrom();
            }
            while (!stackStates.empty()){
                solutionList.add(stackStates.pop());
            }
        }
    }

    /**
     * The method shows the path of the solution
     * @return array list of states from the start position to the goal position
     */
    public ArrayList<AState> getSolutionPath(){
        return this.solutionList;
    }


    @Override
    public String toString() {
        return ".........";
    }

}
