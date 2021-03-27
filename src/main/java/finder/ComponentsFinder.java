package finder;

import graph.Edge;
import graph.Graph;
import graph.Vertex;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ComponentsFinder {
    private final Graph graph;
    private final List<Boolean> visited;
    private final List<Vertex> vertices;

    public ComponentsFinder(Graph graph) {
        this.graph = graph;
        this.vertices = graph.getVertices();
        this.visited = new ArrayList<>(vertices.size());

        for (int i = 0; i < vertices.size(); i++) {
            visited.add(false);
        }
    }

    public Set<Set<Edge>> find() {
        Set<Set<Edge>> components = new HashSet<>();
        for (int i = 0; i < visited.size(); i++) {
            if (!visited.get(i)) {
                List<Boolean> visitedByThisVertex = new ArrayList<>(vertices.size());
                for (int j = 0; j < vertices.size(); j++) {
                    visitedByThisVertex.add(false);
                }
                DFS(vertices.get(i), visitedByThisVertex);
                Set<Vertex> componentVertices = new HashSet<>();
                for (int j = 0; j < visited.size(); j++) {
                    if (visitedByThisVertex.get(j)) {
                        componentVertices.add(vertices.get(j));
                    }
                }
                Set<Edge> edgesOfComponent = extractEdges(componentVertices);
                components.add(edgesOfComponent);
            }

        }
        return components;

    }

    private void DFS(Vertex vertex, List<Boolean> visitedByThisVertex) {
        visitedByThisVertex.set(vertex.getNumber(), true);
        visited.set(vertex.getNumber(), true);
        for (Vertex neighbor : graph.getVertices().get(vertex.getNumber()).getAdjVertices()) {
            if (!visitedByThisVertex.get(neighbor.getNumber()))
                DFS(neighbor, visitedByThisVertex);
        }
    }

    private Set<Edge> extractEdges(Set<Vertex> componentVertices) {
        Set<Edge> componentEdges = new HashSet<>();
        for (Edge edge : graph.getEdges()) {
            if (componentVertices.contains(edge.getFirst())) {
                componentEdges.add(edge);
                if (!componentVertices.contains(edge.getSecond())) {
                    System.out.println("SOMETHING BAD HAPPENED");
                }
            }
        }
        return componentEdges;
    }


}
