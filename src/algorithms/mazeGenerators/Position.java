package algorithms.mazeGenerators;
import java.io.Serializable;

/**
 * Class Position
 * The Class imlpements ths Serializable Interface,
 * and represents cell coordinates in board maze
 * @author  Matan Shushan & Ido Kestenbaum
 */

public class Position implements Serializable {
        private int mazeRow ;
        private  int mazeColumn;

    /**
     * Constructor for building Position object
     * @param row row index of cell Position
     * @param column col index of cell Position
     */
    public Position(int row, int column){
            if(row >= 0 && column >= 0){
                mazeRow = row;
                mazeColumn = column;
            }
            else {
                System.out.println("cannot construct position with negative coordinates");
            }
        }

    /**
     * Copy Constructor for building Position object
     * @param position the Position object to be copied
     */
    public Position(Position position){
            if(position == null){
                System.out.println("this is a null position please give a proper one!");
            }
            else if(position.getRowIndex() >= 0 && position.getColumnIndex() >= 0){
                this.mazeColumn = position.getColumnIndex();
                this.mazeRow = position.getRowIndex();
            }
            else{
                System.out.println("cannot construct position with negative coordinates");
            }
        }

    /**
     * @return the row index of the Position
     */
    public int getRowIndex(){
            return mazeRow;
        }

    /**
     * @return the col index of the Position
     */
    public int getColumnIndex(){
            return mazeColumn;
        }

    /**
     * method for describing Position coordinates: row and col
     * @return string for describing the Position object
     */
    @Override
    public String toString() {
        return "Position{" +
                "mazeRow=" + mazeRow +
                ", mazeColumn=" + mazeColumn +
                '}';
    }

    /**
     *
     * @param obj
     * @return true if obj Object is equal to this Position Object
     * Otherwise returns false
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        Position position= (Position) obj;
        if(position.getRowIndex() == this.getRowIndex() && position.getColumnIndex() == this.getColumnIndex()){
            return true;
        }
        return false;
    }

}
