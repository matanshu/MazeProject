package main.java.view;
import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.Position;
import algorithms.search.AState;
import algorithms.search.MazeState;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.EventHandler;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.ScrollEvent;
import javafx.scene.paint.Color;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;

/**
 * Class MazeDisplayer
 * The Class extends the Canvas Class,
 * and responisble on drawing the maze
 * @author  Matan Shushan & Ido Kestenbaum
 */
public class MazeDisplayer extends Canvas {

    private Maze maze;
    private int characterPositionRow = 1;
    private int characterPositionColumn = 1;
    private int finalPositionRow = 1;
    private int finalPositionColumn = 1;
    private ArrayList<AState> solutionPath;
    private boolean ifDisplaySolution = false;
    public javafx.scene.control.Button solutionButton;
    //region Properties
    private StringProperty ImageFileNameWall = new SimpleStringProperty();
    private StringProperty ImageFileNameCharacter = new SimpleStringProperty();
    private StringProperty ImageFileNameFinish = new SimpleStringProperty();
    private StringProperty MediaBackgroundMusic = new SimpleStringProperty();
    private StringProperty MediaFinakMusic = new SimpleStringProperty();

    /**
     * @return the image file name of the wall
     */
    public String getImageFileNameWall() {
        return ImageFileNameWall.get();
    }

    /**
     * setting new param for the image file name of the wall
     * @param imageFileNameWall
     */
    public void setImageFileNameWall(String imageFileNameWall) {
        this.ImageFileNameWall.set(imageFileNameWall);
    }

    /**
     * @return the image file name of the character
     */
    public String getImageFileNameCharacter() {
        return ImageFileNameCharacter.get();
    }

    /**
     * setting new param for the image file name of the character
     * @param imageFileNameCharacter
     */
    public void setImageFileNameCharacter(String imageFileNameCharacter) {
        this.ImageFileNameCharacter.set(imageFileNameCharacter);
    }

    /**
     * @return the image file name of the finish state
     */
    public String getImageFileNameFinish() {
        return ImageFileNameFinish.get();
    }

    /**
     * setting new param for the image file name of the finish state
     * @param imageFileNameFinish
     */
    public void setImageFileNameFinish(String imageFileNameFinish) {
        this.ImageFileNameFinish.set(imageFileNameFinish);
    }

    /**
     * setting new position to maze character
     * @param row row index
     * @param column col index
     */
    public void setCharacterPosition(int row, int column) {
        int oldRow = characterPositionRow;
        int oldColumn = characterPositionColumn;
        characterPositionRow = row;
        characterPositionColumn = column;
        redrawCharacter(oldRow, oldColumn);
    }

    /**
     * @return the row position of the character
     */
    public int getCharacterPositionRow() {
        return characterPositionRow;
    }

    /**
     * @return the col position of the character
     */
    public int getCharacterPositionColumn() {
        return characterPositionColumn;
    }

    /**
     * setting new maze
     * @param maze
     */
    public void setMaze(Maze maze) {
        this.maze = maze;
        redraw();
    }

    /**
     * first drawing maze when setting new maze
     */
    public void redraw() {
        if (maze != null) {
            double canvasHeight = getHeight();
            double canvasWidth = getWidth();
            double cellHeight = canvasHeight / maze.getMaze().length;
            double cellWidth = canvasWidth / maze.getMaze()[0].length;
            try {
                GraphicsContext gc = getGraphicsContext2D();
                gc.clearRect(0, 0, getWidth(), getHeight());
                Image wallImage = new Image(new FileInputStream(ImageFileNameWall.get()));
                Image characterImage = new Image(new FileInputStream(ImageFileNameCharacter.get()));
                Image finishImage = new Image(new FileInputStream(ImageFileNameFinish.get()));
                //Draw Maze
                for (int i = 0; i < maze.getMaze().length; i++) {
                    for (int j = 0; j < maze.getMaze()[i].length; j++) {
                        if (maze.getMaze()[i][j] == 1) {
                            gc.drawImage(wallImage, j * cellWidth, i * cellHeight, cellWidth, cellHeight);
                        }
                    }
                }
                gc.drawImage(finishImage, maze.getGoalPosition().getColumnIndex() * cellWidth, maze.getGoalPosition().getRowIndex() * cellHeight, cellWidth, cellHeight);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * method which responsible on clearing the Character from previous position
     * and drawing her at the updated position
     * @param oldRow the previous raw coordinate
     * @param oldColumm the previous col coordinate
     */
    public void redrawCharacter(int oldRow, int oldColumm) {
        try {
            double canvasHeight = getHeight();
            double canvasWidth = getWidth();
            double cellHeight = canvasHeight / maze.getMaze().length; //row
            double cellWidth = canvasWidth / maze.getMaze()[0].length; //column
            GraphicsContext gc = getGraphicsContext2D();
            gc.clearRect(oldColumm * cellWidth, oldRow * cellHeight, cellWidth, cellHeight);
            Image characterImage = new Image(new FileInputStream(ImageFileNameCharacter.get()));
            gc.drawImage(characterImage, characterPositionColumn * cellWidth, characterPositionRow * cellHeight, cellWidth, cellHeight);
            MazeState mazeState = new MazeState(new Position(oldRow, oldColumm));
            if (ifDisplaySolution && solutionPath.contains(mazeState)) {
                gc.fillRect(oldColumm * cellWidth, oldRow * cellHeight, cellWidth, cellHeight);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * method which responsible on displaying the solution path
     * @param solutionPath ArrayList of AStates combining the solution path
     */
    public void displaySolution(ArrayList<AState> solutionPath) {
        ifDisplaySolution = true;
        this.solutionPath = solutionPath;
        double canvasHeight = getHeight();
        double canvasWidth = getWidth();
        double cellHeight = canvasHeight / maze.getMaze().length; //row
        double cellWidth = canvasWidth / maze.getMaze()[0].length; //column
        GraphicsContext gc = getGraphicsContext2D();
        gc.setFill(Color.RED);
        gc.setGlobalAlpha(0.6);
        for (int i = 0; i < solutionPath.size(); i++) {
            gc.fillRect(((MazeState) solutionPath.get(i)).getCurrentPosition().getColumnIndex() * cellWidth, ((MazeState) solutionPath.get(i)).getCurrentPosition().getRowIndex() * cellHeight, cellWidth, cellHeight);
        }
    }

    /**
     * method which responsible on hiding the solution
     */
    public void hideSolution() {
        ifDisplaySolution = false;
        double canvasHeight = getHeight();
        double canvasWidth = getWidth();
        double cellHeight = canvasHeight / maze.getMaze().length; //row
        double cellWidth = canvasWidth / maze.getMaze()[0].length; //column
        try {
            GraphicsContext gc = getGraphicsContext2D();
            gc.setGlobalAlpha(1);
            Image wallImage = new Image(new FileInputStream(ImageFileNameWall.get()));
            for (int i = 0; i < solutionPath.size(); i++) {
                if (((MazeState) solutionPath.get(i)).getCurrentPosition().getColumnIndex() != finalPositionColumn || ((MazeState) solutionPath.get(i)).getCurrentPosition().getRowIndex() != finalPositionRow) {
                    gc.clearRect(((MazeState) solutionPath.get(i)).getCurrentPosition().getColumnIndex() * cellWidth, ((MazeState) solutionPath.get(i)).getCurrentPosition().getRowIndex() * cellHeight, cellWidth, cellHeight);
                }
            }
            try {
                Image finishImage = new Image(new FileInputStream(ImageFileNameFinish.get()));
                gc.drawImage(finishImage, maze.getGoalPosition().getColumnIndex() * cellWidth, maze.getGoalPosition().getRowIndex() * cellHeight, cellWidth, cellHeight);
                Image characterImage = new Image(new FileInputStream(ImageFileNameCharacter.get()));
                gc.drawImage(characterImage, characterPositionColumn * cellWidth, characterPositionRow * cellHeight, cellWidth, cellHeight);
            } catch (Exception e) {
            }
        } catch (Exception e) {
        }
    }

    /**
     * method which responsible on getting the maze object
     * @return the maze object
     */
    public Maze getMaze() {
        return maze;
    }

    /**
     * method which responsible on zooming in and out the gui screen size
     */
    public void zoom() {
        setOnScroll(new EventHandler<ScrollEvent>() {
            @Override
            public void handle(ScrollEvent event) {
                double zoom = 1.05;
                double y = event.getDeltaY();
                double x = event.getDeltaX();
                if (y < 0) {
                    zoom = 0.95;
                } else {
                    zoom = 1.05;
                }
                setScaleX(getScaleX() * zoom);
                setScaleY(getScaleY() * zoom);
                event.consume();
            }
        });
    }
}


