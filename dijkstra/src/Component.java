import java.util.LinkedList;

public class Component{

    // static instance
    private static Component instance = new Component();

    // define port
    public Port port;

    public static Component getInstance() {
        return instance;
    }

    private Component() {
        port = new Port();
    }

    public String getVersion() {
        return "Dijkstra 0.9";
    }

    public class Port implements IComponent{
        public void printVersion() {
            System.out.println(getVersion() + "\n");
        }

        @Override
        public LinkedList<Vertex> getShortestPath(Vertex source, Vertex target) {
            return null;
        }
    }
}