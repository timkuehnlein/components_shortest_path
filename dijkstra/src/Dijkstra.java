import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Dijkstra{

    // static instance
    private static Dijkstra instance = new Dijkstra();

    // define port
    public Port port;

    public static Dijkstra getInstance() {
        return instance;
    }

    private Dijkstra() {
        port = new Port();
    }

    public String getVersion() {
        return "Dijkstra 0.9";
    }

    private List<Vertex> nodes;
    private List<Edge> edges;
    private Graph graph;
    private DijkstraAlgorithm dijkstra;

    public class Port implements IComponent{
        public void printVersion() {
            System.out.println(getVersion() + "\n");
        }

        @Override
        public int getShortestPath(int source, int target) {
            initDijkstra();
            dijkstra.execute(nodes.get(source));
            LinkedList<Vertex> path = dijkstra.getPath(nodes.get(target));

            for (Vertex vertex : path) {
                System.out.println(vertex);
            }
            return dijkstra.getDistance(nodes.get(target));
        }
    }

    private void initDijkstra() {
        nodes = new ArrayList<Vertex>();
        edges = new ArrayList<Edge>();
        for (int i = 0; i < 11; i++) {
            Vertex location = new Vertex("Node_" + i, "Node_" + i);
            nodes.add(location);
        }

        addLane("Edge_0", 0, 1, 85);
        addLane("Edge_1", 0, 2, 217);
        addLane("Edge_2", 0, 4, 173);
        addLane("Edge_3", 2, 6, 186);
        addLane("Edge_4", 2, 7, 103);
        addLane("Edge_5", 3, 7, 183);
        addLane("Edge_6", 5, 8, 250);
        addLane("Edge_7", 8, 9, 84);
        addLane("Edge_8", 7, 9, 167);
        addLane("Edge_9", 4, 9, 502);
        addLane("Edge_10", 9, 10, 40);
        addLane("Edge_11", 1, 10, 600);
        graph = new Graph(nodes, edges);
        dijkstra = new DijkstraAlgorithm(graph);
    }

    private void addLane(String laneId, int sourceLocNo, int destLocNo, int duration) {
        Edge lane = new Edge(laneId,nodes.get(sourceLocNo), nodes.get(destLocNo), duration );
        edges.add(lane);
    }
}