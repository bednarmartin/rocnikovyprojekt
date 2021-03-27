package graph;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * * A class representing a graph
 * * Each Graph object contains number of vertices, List of vertices and Set of edges of the graph
 */
public class Graph {
    private int numberOfVertices;
    private List<Vertex> vertices;

    private Set<Edge> edges;

    /**
     * Constructor for Graph class
     *
     * @param numberOfVertices number of vertices in the graph
     */
    public Graph(int numberOfVertices) {
        this.numberOfVertices = numberOfVertices;
        this.vertices = new ArrayList<>(numberOfVertices);
        this.edges = new HashSet<>();
    }

    public int getNumberOfVertices() {
        return numberOfVertices;
    }

    public void incrementNumberOfVertices() {
        this.numberOfVertices++;
    }

    public List<Vertex> getVertices() {
        return vertices;
    }


    public void setVertices(List<Vertex> vertices) {
        this.vertices = vertices;
    }


    public Set<Edge> getEdges() {
        return edges;
    }


    public void setEdges(Set<Edge> edges) {
        this.edges = edges;
    }


    @Override
    public String toString() {
        StringBuilder answer = new StringBuilder();
        for (int i = 0; i < numberOfVertices; i++) {
            for (Vertex toPrintVertex : vertices.get(i).getAdjVertices()) {
                answer.append(toPrintVertex.getNumber());
                answer.append(" ");
            }
            answer.deleteCharAt(answer.length() - 1);
            answer.append("\n");

        }

        return answer.toString();
    }
}
