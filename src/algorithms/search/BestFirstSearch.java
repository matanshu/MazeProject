package algorithms.search;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * BestFirstSearch Class
 * The Class extends the search algorithm BreadthFirstSearch.
 * The class needs to find the best solution of the problem
 * @author  Matan Shushan & Ido Kestenbaum
 */
public class BestFirstSearch extends BreadthFirstSearch  {
    /**
     * BestFirstSearch Constructor
     */
    public BestFirstSearch() {
        super();
        super.name = "BestFirstSearch";
        super.openList = new PriorityQueue<AState>(new Comparator<AState>() {
            @Override
            public int compare(AState o1, AState o2) {
                return o1.compareTo(o2);
            }
        });
    }
}

