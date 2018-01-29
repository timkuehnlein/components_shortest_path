import java.io.File;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Application {
    private Object port;

    @SuppressWarnings({"rawtypes","unchecked"})
    public void createShortestPathAlgorithmPortInstance() {
        Object instance;

        try {
            System.out.println("pathToJar : " + Configuration.instance.pathToJar());
            URL[] urls = {new File(Configuration.instance.pathToJar()).toURI().toURL()};
            URLClassLoader urlClassLoader = new URLClassLoader(urls,Application.class.getClassLoader());
            Class clazz = Class.forName("ShortestPathAlgorithm",true,urlClassLoader);
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

    public int execute(int number_of_vertices, int[][] matrix, int source) {
        int result = Integer.MAX_VALUE;

        try {
            Method method = port.getClass().getMethod("getShortestPath",int.class,int[][].class,int.class);
            result = (Integer)method.invoke(port,number_of_vertices,matrix, source);
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
            for (int i = 1; i <= number_of_vertices; i++)
            {
                for (int j = 1; j <= number_of_vertices; j++)
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

            execute(number_of_vertices, adjacency_matrix, source);

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
            String line = scan.next();
            if(line.equalsIgnoreCase("show components")||line.equalsIgnoreCase("01")){
                System.out.println("Dijkstra, Bellman_Ford");
            }else if(line.equalsIgnoreCase("show current component") || line.equalsIgnoreCase("02")){
                System.out.println(Configuration.instance.getShortestPathAlgorithmType());
            }else if(line.equalsIgnoreCase("set current component dijkstra") || line.equalsIgnoreCase("03 dijkstra")){
                Configuration.instance.setProperty("dijkstra");
            }else if(line.equalsIgnoreCase("set current component bellman-ford") || line.equalsIgnoreCase("03 bellman-ford")){
                Configuration.instance.setProperty("bellman-ford");
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