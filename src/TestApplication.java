import org.junit.jupiter.api.Test;

import java.io.File;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class TestApplication {
    private Class clazz;
    private Object instance;
    private Object port;

    @Test
    public void testExcute() {
        Configuration.instance.setProperty("dijkstra");

        try {
            int number_of_vertices = 5;
            int[][] matrix ={{0,7,0,0,2},{0,0,1,0,2},{0,0,0,4,0},{0,5,0,0,0},{0,3,8,5,0}};
            int source = 1;

            System.out.println("pathToJar : " + Configuration.instance.pathToJar());
            URL[] urls = {new File(Configuration.instance.pathToJar()).toURI().toURL()};
            URLClassLoader urlClassLoader = new URLClassLoader(urls,Application.class.getClassLoader());
            clazz = Class.forName("ShortestPathAlgorithm",true,urlClassLoader);

            instance = clazz.getMethod("getInstance",new Class[0]).invoke(null,new Object[0]);
            port = clazz.getDeclaredField("port").get(instance);

            System.out.println(clazz.hashCode());
            System.out.println(instance.hashCode());
            System.out.println(port.hashCode());
            System.out.println(Arrays.toString(matrix));

            Method method = port.getClass().getMethod("getShortestPath",int.class,int[][].class,int.class);
            System.out.println(method);
            String result = (String) method.invoke(port,number_of_vertices,matrix, source);

            assertNotNull(result);
        } catch (Exception e) {
            System.out.println("--- exception");
            e.printStackTrace();
        }
    }
}
