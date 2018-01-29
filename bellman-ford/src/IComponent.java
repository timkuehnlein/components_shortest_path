import java.util.Map;

public interface IComponent {

    Graph.CostPathPair<Integer> getShortestPath(Graph<Integer> graph, Graph.Vertex<Integer> start, Graph.Vertex<Integer> end);
    Map<Graph.Vertex<Integer>, Graph.CostPathPair<Integer>> getShortestPaths(Graph<Integer> graph, Graph.Vertex<Integer> start);
}