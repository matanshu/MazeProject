package algorithms.search;

/**
 * Abstract Class AState
 * The Class represents an object of state in searching problem.
 * @author  Matan Shushan & Ido Kestenbaum
 */
public abstract class AState implements Comparable {
    protected AState cameFrom;
    protected int cost;
    protected String astate;

    /**
     *
     * @param o object to be compared
     * @return
     */
    @Override
    public abstract int compareTo(Object o);

    /**
     * getting the previous state that came from
     * @return the previous state
     */
    public AState getCameFrom() {
        return this.cameFrom;
    }

    /**
     * getting the cost of this current state
     * @return the cost of state
     */
    public int getCost() {
        return cost;
    }

    /**
     * @return the name of this state
     */
    public String getAstate() {
        return astate;
    }

    /**
     * the method update the father node (state) of the current state
     * @param cameFrom - a father node
     */
    public void setCameFrom(AState cameFrom) {
        this.cameFrom = cameFrom;
    }

    /**
     * the method update the cost of the state
     * @param cost - new cost
     */
    public void setCost(int cost) {
        this.cost = cost;
    }
}