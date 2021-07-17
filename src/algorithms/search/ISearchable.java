package algorithms.search;
import java.util.ArrayList;
/**
 * ISearchable Interface
 * represents Searchable objects
 * @author  Matan Shushan & Ido Kestenbaum
 */
public interface ISearchable {
    /**
     * getting the goal state
     * @return goal state
     */
    AState getGoalState();

    /**
     * getting the start state
     * @return start state
     */
    AState getStartState();

    /**
     * getting all successors of given state
     * @param state the given state
     * @return all successors of given state
     */
    ArrayList<AState> getAllSuccessors(AState state);
}
