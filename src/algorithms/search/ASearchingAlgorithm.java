package algorithms.search;

/**
 * Abstract Class ASearchingAlgorithm
 * The Class implements the ISearchingAlgorithm Interface,
 * and represents abstract class of Searching Algorithm
 * @author  Matan Shushan & Ido Kestenbaum
 */
public abstract class ASearchingAlgorithm implements ISearchingAlgorithm{

    protected int visitedNodes;
    protected String name;

    /**
     * constructor
     */
    public ASearchingAlgorithm() {
        this.visitedNodes = 0;
    }

    /**
     * Method which returns the searching algorithm name
     * @return algorithm name
     */
    @Override
    public String getName() {
        return this.name;
    }

    /**
     * Method which solves the problem,
     * the solution includes the path for getting from start state to goal state
     * @param search - ISearchable object, means object which can be searched
     * @return the solution of the searching problem
     */
    @Override
    public Solution solve(ISearchable search) {
        return null;
    }

    /**
     * Method for getting the number of nodes that evaluated during searching for solution
     * @return the number of nodes that evaluated during searching solution
     */
    @Override
    public int getNumberOfNodesEvaluated() {
        return 0;
    }

}
