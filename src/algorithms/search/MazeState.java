package algorithms.search;
import algorithms.mazeGenerators.Position;
import java.io.Serializable;
import java.util.Objects;

/**
 * MazeState Class
 * The Class extends the abstract class AState
 * represents a state which is adjusted to the maze problem.
 */

public class MazeState extends AState implements Serializable {

    private Position currentPosition;
    private int distanceFromStartPosition;

    /**
     * Constructor
     * @param currentPosition the current position of the state
     */
    public MazeState(Position currentPosition){
      if(currentPosition != null && currentPosition.getRowIndex() >= 0 && currentPosition.getColumnIndex() >= 0){
          this.currentPosition = new Position(currentPosition);
          this.cost = 1;
          this.astate = currentPosition.toString();
          this.distanceFromStartPosition = 0;
      }
      else {
          System.out.println("not a legal position!");
      }
    }

    /**
     * getting the current position
     * @return the current position
     */
    public Position getCurrentPosition() {
        return currentPosition;
    }

    /**
     * @param o object to be compared
     * @return 1, 0 or -1 accordingly to which object has more expensive cost
     */
    public int compareTo(Object o){
        if(o != null){
            MazeState mazeState = (MazeState)o;
            if(this.getCost() > mazeState.getCost()){
                return 1;
            }
            else if(this.getCost() == mazeState.getCost()){
                return 0;
            }
            return -1;
        }
        return 0;
    }

    /**
     * @return the distance from start position
     */
    public int getDistanceFromStartPosition() {
        return distanceFromStartPosition;
    }

    /**
     * setting new distance of current state from start position
     * @param distanceFromStartPosition
     */
    public void setDistanceFromStartPosition(int distanceFromStartPosition) {
        this.distanceFromStartPosition = distanceFromStartPosition;
    }

    /**
     * overriding the equals method
     * @param obj object to be compared
     * @return true or false accordingly to the equality of the objects
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        MazeState mazeState= (MazeState)obj;
        if(mazeState.currentPosition.equals(this.currentPosition)){
            return true;
        }
        return false;
    }

    /**
     * method for describing the maze state
     * @return description of this maze state
     */
    @Override
    public String toString() {
        return "MazeState{" +
                "currentPosition=" + currentPosition +
                '}';
    }

    /**
     * @return hash code of the maze state
     */
    @Override
    public int hashCode() {
        return Objects.hash(this.currentPosition.getRowIndex() + this.currentPosition.getColumnIndex());
    }
}
