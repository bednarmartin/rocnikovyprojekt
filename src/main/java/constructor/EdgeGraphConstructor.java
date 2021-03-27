package constructor;

import graph.Edge;
import graph.Graph;
import graph.Vertex;

import java.util.*;

public class EdgeGraphConstructor implements GraphConstructor {
    private final Set<Edge> edges;

    public EdgeGraphConstructor(Set<Edge> edges) {
        this.edges = edges;
    }


    @Override
    public Graph construct() {
        TreeSet<Vertex> vertices = extractVertices();
        List<Vertex> newVertices = new ArrayList<>();
        Graph graph = new Graph(vertices.size());
        for (int i = 0; i < vertices.size(); i++) {
            newVertices.add(new Vertex(i));
        }
        graph.setVertices(newVertices);

        ArrayList<Vertex> originalVertices = new ArrayList<>();

        while (!vertices.isEmpty()) {
            originalVertices.add(vertices.pollFirst());
        }

        for (int i = 0; i < originalVertices.size(); i++) {
            addNewAdjacentVertices(newVertices.get(i), originalVertices.get((i)), originalVertices, newVertices);
        }

        Set<Edge> newEdges = extractEdges(originalVertices, newVertices);
        graph.setEdges(newEdges);

        return graph;


    }


    private TreeSet<Vertex> extractVertices() {
        TreeSet<Vertex> vertices = new TreeSet<>();
        for (Edge edge : edges) {
            vertices.add(edge.getFirst());
            vertices.add(edge.getSecond());
        }
        return vertices;

    }

    private void addNewAdjacentVertices(Vertex newVertex, Vertex originalVertex, List<Vertex> originalVertices, List<Vertex> newVertices) {
        for (Edge edge : edges) {
            if (originalVertex.equals(edge.getFirst())) {
                Vertex originalNeighbor = edge.getSecond();
                int newNumber = originalVertices.indexOf(originalNeighbor);
                newVertex.getAdjVertices().add(newVertices.get(newNumber));


            } else if (originalVertex.equals(edge.getSecond())) {
                Vertex originalNeighbor = edge.getFirst();
                int newNumber = originalVertices.indexOf(originalNeighbor);
                newVertex.getAdjVertices().add(newVertices.get(newNumber));
            }

        }
    }

    private Set<Edge> extractEdges(List<Vertex> originalVertices, List<Vertex> newVertices) {
        Set<Edge> newEdges = new HashSet<>();
        for (Edge edge : edges) {
            Vertex originalFirst = edge.getFirst();
            Vertex originalSecond = edge.getSecond();
            int newFirstNumber = originalVertices.indexOf(originalFirst);
            int newSecondNumber = originalVertices.indexOf(originalSecond);
            newEdges.add(new Edge(newVertices.get(newFirstNumber), newVertices.get(newSecondNumber)));
        }

        return newEdges;

    }
}
