package algorithms.mazeGenerators;

/**
 * EmptyMazeGenerator Class
 * The Class extends the abstract Class AMazeGenerator,
 * and generating an empty maze
 */
public  class EmptyMazeGenerator extends AMazeGenerator {

    /**
     * Method for generating an empty Maze
     * if row < 2 or column < 3 generating a 15*15 maze
     * @param row - number of rows in maze
     * @param column- number of columns in maze
     * @return an empty Maze
     */
    public Maze generate (int row, int column) {
        if (row >= 2 && column >= 3) {
            Maze emptymaze = new Maze(row, column);
            return emptymaze;
        } else {
            System.out.println("cannot construct a empty maze with coordinates that less then 2*3 size");
            System.out.println( "we will create instead a default empty maze in size of 15*15");
            return this.generate(15,15);
        }
    }
}
