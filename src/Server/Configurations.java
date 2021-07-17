package Server;
import algorithms.mazeGenerators.AMazeGenerator;
import algorithms.mazeGenerators.MyMazeGenerator;
import algorithms.mazeGenerators.SimpleMazeGenerator;
import algorithms.search.ASearchingAlgorithm;
import algorithms.search.BestFirstSearch;
import algorithms.search.BreadthFirstSearch;
import algorithms.search.DepthFirstSearch;
import java.io.IOException;
import java.util.Properties;
import java.io.FileInputStream;
import java.io.InputStream;

/**
 * Configuration static Class which define 3 different definitions:
 * the type of searching algorithm for solving the maze
 * the number of Threads on ThreadPool
 * which way of generating new maze
 */
public class Configurations {

    private static Properties properties;

    public static void init() {
        try (InputStream input = new FileInputStream("resources/config.properties")) {
            properties = new Properties();
            properties.load(input);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * getting the type of searching algorithm for solving the maze
     * @return ASearchingAlgorithm
     */
    public static ASearchingAlgorithm getAlgorithm(){
        ASearchingAlgorithm aSearchingAlgorithm = null;
        switch (properties.getProperty("SearchingAlgorithm")){
            case "BestFirstSearch":
                aSearchingAlgorithm = new BestFirstSearch();
                break;
            case "BreadthFirstSearch":
                aSearchingAlgorithm = new BreadthFirstSearch();
                break;
            case "DepthFirstSearch":
                aSearchingAlgorithm = new DepthFirstSearch();
                break;
        }
        return aSearchingAlgorithm;
    }

    /**
     * getting the way of generating a new maze
     * @return AMazeGenerator
     */
    public static AMazeGenerator getGenerator(){
        AMazeGenerator aMazeGenerator = null;
        switch (properties.getProperty("MazeGenerator")){
            case "MyMazeGenerator":
                aMazeGenerator = new MyMazeGenerator();
                break;
            case "SimpleMazeGenerator":
                aMazeGenerator = new SimpleMazeGenerator();
                break;
        }
        return aMazeGenerator;
    }

    /**
     * getting the number of Threads on ThreadPool
     * @return int
     */
    public static int getNumberOfThreads(){
        return Integer.parseInt(properties.getProperty("ThreadPool"));
    }
}



