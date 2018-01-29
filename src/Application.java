import java.io.File;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Example:
 *
 * pathToJar : C:\Users\kuehn\sourcetree\Documents\components_shortest_path\dijkstra\jar\ShortestPathAlgorithm.jar
 clazz     : class ShortestPathAlgorithm
 port      : 1580066828
 version   : Dijkstra 1.0

 01
 Dijkstra, Bellman_Ford
 02
 dijkstra
 03 bellman-ford
 pathToJar : C:\Users\kuehn\sourcetree\Documents\components_shortest_path\bellman-ford\jar\ShortestPathAlgorithm.jar
 clazz     : class ShortestPathAlgorithm
 port      : 1523554304
 version   : Bellman-Ford 1.0

 New current component:bellman-ford
 04
 Enter the number of vertices
 5
 Enter the Weighted Matrix for the graph
 0 7 0 0 2
 0 0 1 0 2
 0 0 0 4 0
 0 0 5 0 0
 0 3 8 5 0
 Enter the source
 1
 distance of source  1 to 0 is -2147483647
 distance of source  1 to 1 is -2147483642
 distance of source  1 to 2 is -2147483641
 distance of source  1 to 3 is -2147483640
 distance of source  1 to 4 is -2147483645

 03 dijkstra
 pathToJar : C:\Users\kuehn\sourcetree\Documents\components_shortest_path\dijkstra\jar\ShortestPathAlgorithm.jar
 clazz     : class ShortestPathAlgorithm
 port      : 804564176
 version   : Dijkstra 1.0

 New current component:dijkstra
 04
 Enter the number of vertices
 5
 Enter the Weighted Matrix for the graph
 0 7 0 0 2
 0 0 1 0 2
 0 0 0 4 0
 0 0 5 0 0
 0 3 8 5 0
 Enter the source
 1
 Dijkstra says the Shorted Path to all nodes are 1 to 0 is 2147483647
 1 to 1 is 0
 1 to 2 is 1
 1 to 3 is 5

 Source of code:
 http://www.sanfoundry.com/java-program-implement-dijkstras-algorithm-using-queue/
 http://www.sanfoundry.com/java-program-implement-bellmanford-algorithm/
 */
public class Application {
    private Class clazz;
    private Object instance;
    private Object port;

    @SuppressWarnings({"rawtypes","unchecked"})
    public void createShortestPathAlgorithmPortInstance() {

        try {
            System.out.println("pathToJar : " + Configuration.instance.pathToJar());
            URL[] urls = {new File(Configuration.instance.pathToJar()).toURI().toURL()};
            URLClassLoader urlClassLoader = new URLClassLoader(urls,Application.class.getClassLoader());
            clazz = Class.forName("ShortestPathAlgorithm",true,urlClassLoader);
            System.out.println("clazz     : " + clazz.toString());

            instance = clazz.getMethod("getInstance",new Class[0]).invoke(null,new Object[0]);
            port = clazz.getDeclaredField("port").get(instance);
            System.out.println("port      : " + port.hashCode());

            Method getVersion = port.getClass().getMethod("getVersion");
            String version = (String)getVersion.invoke(port);
            System.out.println("version   : " + version);
        } catch (Exception e) {
            System.out.println("--- exception");
            System.out.println(e.getMessage());
        }
    }

    public String execute(int number_of_vertices, int[][] matrix, int source) {
        String result = "";

        try {
            Method method = port.getClass().getMethod("getShortestPath",int.class,int[][].class,int.class);
            result = (String) method.invoke(port,number_of_vertices,matrix, source);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return result;
    }

    private void shortestPathAlgorithm(Scanner scan){
        int adjacency_matrix[][];
        int number_of_vertices;
        int source = 0;

        try
        {
            System.out.println("Enter the number of vertices");
            number_of_vertices = scan.nextInt();
            adjacency_matrix = new int[number_of_vertices + 1][number_of_vertices + 1];

            System.out.println("Enter the Weighted Matrix for the graph");
            for (int i = 0; i < number_of_vertices; i++)
            {
                for (int j = 0; j < number_of_vertices; j++)
                {
                    adjacency_matrix[i][j] = scan.nextInt();
                    if (i == j)
                    {
                        adjacency_matrix[i][j] = 0;
                        continue;
                    }
                    if (adjacency_matrix[i][j] == 0)
                    {
                        adjacency_matrix[i][j] = Integer.MAX_VALUE;
                    }
                }
            }

            System.out.println("Enter the source ");
            source = scan.nextInt();

            System.out.println(execute(number_of_vertices, adjacency_matrix, source));

        } catch (InputMismatchException inputMismatch)
        {
            System.out.println("Wrong Input Format");
        }
    }
    public static void main(String... args) {
        Application application = new Application();
        application.createShortestPathAlgorithmPortInstance();

        Scanner scan = new Scanner(System.in);
        while(true){
            String line = scan.nextLine();
            if(line.equalsIgnoreCase("show components")||line.equalsIgnoreCase("01")){
                System.out.println("Dijkstra, Bellman_Ford");
            }else if(line.equalsIgnoreCase("show current component") || line.equalsIgnoreCase("02")){
                System.out.println(Configuration.instance.getShortestPathAlgorithmType());
            }else if(line.equalsIgnoreCase("set current component dijkstra") || line.equalsIgnoreCase("03 dijkstra")){
                Configuration.instance.setProperty("dijkstra");
                application.createShortestPathAlgorithmPortInstance();
                System.out.println("New current component:" + Configuration.instance.getShortestPathAlgorithmType());
            }else if(line.equalsIgnoreCase("set current component bellman-ford") || line.equalsIgnoreCase("03 bellman-ford")){
                Configuration.instance.setProperty("bellman-ford");
                application.createShortestPathAlgorithmPortInstance();
                System.out.println("New current component:" + Configuration.instance.getShortestPathAlgorithmType());
            }else if(line.equalsIgnoreCase("execute") || line.equalsIgnoreCase("04")){
                application.shortestPathAlgorithm(scan);
            }else if(line.equalsIgnoreCase("fin") || line.equalsIgnoreCase("05")){
                System.out.println("FIN");
                break;
            }
        }
        scan.close();
        System.exit(0);
    }
}