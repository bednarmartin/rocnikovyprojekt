package constructor;

import graph.Edge;
import graph.Graph;
import graph.Vertex;

public class CompleteGraphConstructor implements GraphConstructor {
    private final Graph graph;

    public CompleteGraphConstructor(Graph graph) {
        this.graph = graph;
    }


    @Override
    public Graph construct() {
        int numberOfNewVertex = graph.getNumberOfVertices();
        Vertex newVertex = new Vertex(numberOfNewVertex);
        for (Vertex vertex : graph.getVertices()) {
            if (vertex.getAdjVertices().size() < 3) {
                vertex.getAdjVertices().add(newVertex);
                newVertex.getAdjVertices().add(vertex);
                Edge newEdge = new Edge(vertex, newVertex);
                graph.getEdges().add(newEdge);
            }
        }
        graph.getVertices().add(newVertex);
        graph.incrementNumberOfVertices();

        return graph;
    }
}
