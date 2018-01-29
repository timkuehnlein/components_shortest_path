import java.util.Scanner;

public class ShortestPathAlgorithm
{
    private int distances[];
    private int numberofvertices;
    public static final int MAX_VALUE = 999;

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
        return "Bellman-Ford 1.0";
    }


    public class Port{
        public String getShortestPath(int numberofvertices, int adjacencymatrix[][], int source){
            ShortestPathAlgorithm bellmanford = new ShortestPathAlgorithm(numberofvertices);
            return bellmanford.BellmanFordEvaluation(source, adjacencymatrix);
        }
        public String getVersion() {
            return instance.getVersion() + "\n";
        }

    }

    private ShortestPathAlgorithm(int numberofvertices)
    {
        this.numberofvertices = numberofvertices;
        distances = new int[numberofvertices + 1];
    }

    private String BellmanFordEvaluation(int source, int adjacencymatrix[][])
    {
        String result = "";
        for (int node = 1; node <= numberofvertices; node++)
        {
            distances[node] = MAX_VALUE;
        }

        distances[source] = 0;
        for (int node = 1; node <= numberofvertices - 1; node++)
        {
            for (int sourcenode = 1; sourcenode <= numberofvertices; sourcenode++)
            {
                for (int destinationnode = 1; destinationnode <= numberofvertices; destinationnode++)
                {
                    if (adjacencymatrix[sourcenode][destinationnode] != MAX_VALUE)
                    {
                        if (distances[destinationnode] > distances[sourcenode]
                                + adjacencymatrix[sourcenode][destinationnode])
                            distances[destinationnode] = distances[sourcenode]
                                    + adjacencymatrix[sourcenode][destinationnode];
                    }
                }
            }
        }

        for (int sourcenode = 1; sourcenode <= numberofvertices; sourcenode++)
        {
            for (int destinationnode = 1; destinationnode <= numberofvertices; destinationnode++)
            {
                if (adjacencymatrix[sourcenode][destinationnode] != MAX_VALUE)
                {
                    if (distances[destinationnode] > distances[sourcenode]
                            + adjacencymatrix[sourcenode][destinationnode])
                        System.out.println("The Graph contains negative egde cycle");
                }
            }
        }

        for (int vertex = 1; vertex <= numberofvertices; vertex++)
        {
            result = result + "distance of source  " + source + " to "
                    + vertex + " is " + distances[vertex];
        }
        return result;
    }


}
