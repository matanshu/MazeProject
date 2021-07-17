package algorithms.mazeGenerators;
import java.io.Serializable;

/**
 * Class Maze
 * The Class implements the Serializable Interface,
 * and represents Maze object
 * @author  Matan Shushan & Ido Kestenbaum
 */
public class Maze implements Serializable{
    private int mazeRow ;
    private  int mazeColumn;
    private Position finalposition;
    private Position startposition;
    public  int[][] maze;

    /**
     * constructor for building maze object
     * @param mazeRow the number of maze rows
     * @param mazeColumn the number of maze columns
     */
    public Maze(int mazeRow, int mazeColumn) {
        if (mazeRow >= 2 && mazeColumn>= 3 ) {
            this.mazeRow = mazeRow;
            this.mazeColumn = mazeColumn;
            maze = new int[mazeRow][mazeColumn];
            this.setStartposition(new Position(0,0));
            this.setFinalposition(new Position(mazeRow - 1, mazeColumn - 1));
            maze[finalposition.getRowIndex()][finalposition.getColumnIndex()]=0;
            maze[startposition.getRowIndex()][startposition.getColumnIndex()]=0;
        }
        else {
            System.out.println("the maze's coordinates have to be at least in 2*3 size");
        }
    }

    /**
     * deep copy constructor for building maze object
     * @param otherMaze maze object to be copied
     */
    public Maze(Maze otherMaze) {
        if(otherMaze == null){
            System.out.println("this is a null maze please give a proper one!");
        }
        else if (otherMaze.getMazeRow() >= 2 && otherMaze.getMazeColumn() >= 3 ) {
            mazeRow = otherMaze.getMazeRow();
            mazeColumn = otherMaze.getMazeColumn();
            maze = new int[otherMaze.getMazeRow()][otherMaze.getMazeColumn()];
            this.copyArrayValues(otherMaze.getMaze());
            if(otherMaze.getGoalPosition() != null){
                finalposition = new Position(otherMaze.getGoalPosition());
                maze[finalposition.getRowIndex()][finalposition.getColumnIndex()]=0;
            }
            if(otherMaze.getStartPosition() != null){
                startposition = new Position(otherMaze.getStartPosition());
                maze[startposition.getRowIndex()][startposition.getColumnIndex()]=0;
            }
        }
        else {
            System.out.println("the maze's coordinates have to be at least in 2*3 size");
        }
    }

    /**
     * constructor for building maze object
     * @param bytesArray array of byte
     * the maze will be build from bytesArray
     */
    public Maze(byte[] bytesArray){
        this.mazeRow = (int)bytesArray[0]*127 + (int)bytesArray[1];
        this.mazeColumn = (int)bytesArray[2]*127 + (int)bytesArray[3];
        this.startposition = new Position((int)bytesArray[4]*127 + (int)bytesArray[5], (int)bytesArray[6]*127 + (int)bytesArray[7]);
        this.finalposition = new Position((int)bytesArray[8]*127 + (int)bytesArray[9], (int)bytesArray[10]*127 + (int)bytesArray[11]);
        this.maze = new int[mazeRow][mazeColumn];
        int index = 12;
        for(int i=0;i<mazeRow;i++) {
            for (int j = 0; j < mazeColumn; j++) {
                maze[i][j] = (int)bytesArray[index];
                index++;
            }
        }
    }

    /**
     * method for converting the maze board from int array to byte array
     * @return the converted byte array
     */
    public byte[] toByteArray(){
        final int num = 127;
        int mazeRowInt = mazeRow / num;
        int mazeRowIntMode = mazeRow % num;

        byte mazeRowByteInteger = (byte)mazeRowInt;
        byte mazeRowByte = (byte)mazeRowIntMode;

        int mazeColumnInt = mazeColumn / num;
        int mazeColumnIntMode = mazeColumn % num;

        byte mazeColumnByteInteger = (byte)mazeColumnInt;
        byte mazeColumnByte = (byte)mazeColumnIntMode;

        int startPositionRowIndexInt = this.getStartPosition().getRowIndex() / num;
        int startPositionRowIndexIntMode = this.getStartPosition().getRowIndex() % num;

        byte startPositionRowIndexByteInteger = (byte)startPositionRowIndexInt;
        byte startPositionRowIndexByte = (byte)startPositionRowIndexIntMode;

        int startPositionColumnIndexInt = this.getStartPosition().getColumnIndex() / num;
        int startPositionColumnIndexIntMode = this.getStartPosition().getColumnIndex() % num;

        byte startPositionColumnIndexByteInteger = (byte)startPositionColumnIndexInt;
        byte startPositionColumnIndexByte = (byte)startPositionColumnIndexIntMode;

        int finalPositionRowIndexInt = this.getGoalPosition().getRowIndex() / num;
        int finalPositionRowIndexIntMode = this.getGoalPosition().getRowIndex() % num;

        byte finalPositionRowIndexByteInteger = (byte)finalPositionRowIndexInt;
        byte finalPositionRowIndexByte = (byte)finalPositionRowIndexIntMode;

        int finalPositionColumnIndexInt = this.getGoalPosition().getColumnIndex() / num;
        int finalPositionColumnIndexMode = this.getGoalPosition().getColumnIndex() % num;

        byte finalPositionColumnIndexByteInteger = (byte)finalPositionColumnIndexInt;
        byte finalPositionColumnIndexByte = (byte)finalPositionColumnIndexMode;

        byte[] byteArray = new byte[mazeColumn*mazeRow +12]; //initial the length of the bytearray

        byteArray[0] = mazeRowByteInteger;
        byteArray[1] = mazeRowByte;

        byteArray[2] = mazeColumnByteInteger;
        byteArray[3] = mazeColumnByte;

        byteArray[4] = startPositionRowIndexByteInteger;
        byteArray[5] = startPositionRowIndexByte;

        byteArray[6] = startPositionColumnIndexByteInteger;
        byteArray[7] = startPositionColumnIndexByte;

        byteArray[8] = finalPositionRowIndexByteInteger;
        byteArray[9] = finalPositionRowIndexByte;

        byteArray[10] = finalPositionColumnIndexByteInteger;
        byteArray[11] = finalPositionColumnIndexByte;

        int j=12;
        for(int i=0;i<mazeRow;i++) {// set the maze information to the byteArray
            for (int m = 0; m < mazeColumn; m++) {
                byteArray[j] = (byte) (maze[i][m]);
                j++;
            }
        }
        return byteArray;
    }

    /**
     * method for copying the values from otherMazeArray to Maze object board
     * @param otherMazeArray the maze board to be copied
     */
    private void copyArrayValues(int[][] otherMazeArray){
        for (int i = 0; i < maze.length; i++){
            for (int J = 0; J < maze[i].length; J++){
                this.maze[i][J] = otherMazeArray[i][J];
            }
        }
    }

    /**
     * @return the number of rows in the maze
     */
    public int getMazeRow(){
        return mazeRow;
    }

    /**
     * @return the number of columns in the maze
     */
    public int getMazeColumn(){
        return mazeColumn;
    }

    /**
     * @return the Start position in the maze
     */
    public Position getStartPosition(){
        return startposition;
    }

    /**
     * @return the end position in the maze
     */
    public Position getGoalPosition(){
        return finalposition;
    }

    /**
     * @return  the maze in 2D array of int
     */
    public int[][] getMaze() {
        return maze;
    }

    /**
     * method for setting the final position of the maze
     * @param finalposition
     */
    public void setFinalposition(Position finalposition) {
        if(finalposition.getRowIndex() >= this.mazeRow || finalposition.getColumnIndex() >= mazeColumn){
            System.out.println("cannot set final point to these coordinates because it's out of bounds of the maze's size");
        }
        else {
            this.finalposition = new Position(finalposition);
        }
    }

    /**
     * method for setting the start position of the maze
     * @param startposition
     */
    public void setStartposition(Position startposition) {
        if(startposition.getRowIndex() >= this.mazeRow || startposition.getColumnIndex() >= mazeColumn){
            System.out.println("cannot set starting point to these coordinates because it's out of bounds of the maze's size");
        }
        else {
            this.startposition = new Position(startposition);
        }
    }

    /**
     *the method prints the board maze including the start, end positions
     */
    public void print (){
        Position start = getStartPosition();
        Position goal = getGoalPosition();
        for(int i=0;i<getMazeRow();i++){
            for(int j=0;j<getMazeColumn();j++){
                if (i == start.getRowIndex() && j == start.getColumnIndex() ) {
                    System.out.print("S");
                    System.out.print(" ");
                }
                else if (i == goal.getRowIndex() && j == goal.getColumnIndex() ) {
                    System.out.print("E");
                    System.out.print(" ");
                }
                else {
                    System.out.print(maze[i][j]);
                    System.out.print(" ");
                }
                if(j==getMazeColumn()-1)
                    System.out.println();
            }
        }
    }
}