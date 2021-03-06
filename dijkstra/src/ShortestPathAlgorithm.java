import java.util.*;

public class ShortestPathAlgorithm{

    // static instance
    private static ShortestPathAlgorithm instance = new ShortestPathAlgorithm();

    // define port
    public Port port;

    public static ShortestPathAlgorithm getInstance() {
        return instance;
    }

    private ShortestPathAlgorithm() {
        port = new Port();
    }

    public String getVersion() {
        return "Dijkstra 1.0";
    }

    private int distances[];
    private Queue<Integer> queue;
    private Set<Integer> settled;
    private int number_of_nodes;
    private int adjacencyMatrix[][];

    public class Port implements IComponent{
        public String getVersion() {
            return instance.getVersion() + "\n";
        }

        @Override
        public String getShortestPath(int number_of_vertices, int adjacency_matrix[][], int source) {
            ShortestPathAlgorithm dijkstra = new ShortestPathAlgorithm(number_of_vertices);
            return dijkstra.dijkstra_algorithm(adjacency_matrix, source);
        }
    }


        private ShortestPathAlgorithm(int number_of_nodes)
        {
            this.number_of_nodes = number_of_nodes;
            distances = new int[number_of_nodes];
            settled = new HashSet<Integer>();
            queue = new LinkedList<Integer>();
            adjacencyMatrix = new int[number_of_nodes][number_of_nodes];
        }

        private String dijkstra_algorithm(int adjacency_matrix[][], int source)
        {
            String result = "";

            int evaluationNode;
            for (int i = 0; i < number_of_nodes; i++)
                for (int j = 0; j < number_of_nodes; j++)
                    adjacencyMatrix[i][j] = adjacency_matrix[i][j];

            for (int i = 0; i < number_of_nodes; i++)
            {
                distances[i] = Integer.MAX_VALUE;
            }

            queue.add(source);
            distances[source] = 0;

            while (!queue.isEmpty())
            {
                evaluationNode = getNodeWithMinimumDistanceFromQueue();
                settled.add(evaluationNode);
                evaluateNeighbours(evaluationNode);
            }

            result += ("Dijkstra says the Shorted Path to all nodes are ");
            for (int i = 0; i < distances.length - 1; i++)
            {
                result += (source + " to " + i + " is " + distances[i] + "\n");
            }
            return result;
        }

        private int getNodeWithMinimumDistanceFromQueue()
        {
            int min ;
            int node = 0;
            Iterator<Integer> iterator = queue.iterator();
            node = iterator.next();
            min = distances[node];

            for (int i = 0; i < distances.length; i++)
            {
                if (queue.contains(i))
                {
                    if (distances[i] <= min)
                    {
                        min = distances[i];
                        node = i;
                    }
                }
            }
            queue.remove(node);
            return node;
        }

        private void evaluateNeighbours(int evaluationNode)
        {
            int edgeDistance = -1;
            int newDistance = -1;

            for (int destinationNode = 0; destinationNode < number_of_nodes; destinationNode++)
            {
                if (!settled.contains(destinationNode))
                {
                    if (adjacencyMatrix[evaluationNode][destinationNode] != Integer.MAX_VALUE)
                    {
                        edgeDistance = adjacencyMatrix[evaluationNode][destinationNode];
                        newDistance = distances[evaluationNode] + edgeDistance;
                        if (newDistance < distances[destinationNode])
                        {
                            distances[destinationNode] = newDistance;
                        }
                        queue.add(destinationNode);
                    }
                }
            }
        }
}