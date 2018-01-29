import java.util.LinkedList;

public interface IComponent {
    LinkedList<Vertex> getShortestPath(Vertex source, Vertex target);
}