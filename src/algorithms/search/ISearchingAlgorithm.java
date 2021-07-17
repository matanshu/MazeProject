package algorithms.search;
/**
 * ISearchingAlgorithm Interface
 * define the methods that every Searching Algorithm should have
 * @author  Matan Shushan & Ido Kestenbaum
 */

public interface ISearchingAlgorithm {

    /**
     * Method which solves the problem,
     * the solution includes the path for getting from start state to goal state
     * @param search - ISearchable object, means object which can be searched
     * @return the solution of the searching problem
     */
    Solution solve(ISearchable search);

    /**
     * Method for getting the number of nodes that evaluated during searching for solution
     * @return the number of nodes that evaluated during searching solution
     */
    int getNumberOfNodesEvaluated();

    /**
     * Method which returns the searching algorithm name
     * @return algorithm name
     */
    String getName();
}
