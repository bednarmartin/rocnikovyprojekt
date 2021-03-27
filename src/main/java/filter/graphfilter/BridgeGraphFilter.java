package filter.graphfilter;

import filter.FilterVisitor;
import graph.Graph;
import graph.Vertex;

public class BridgeGraphFilter implements GraphFilter {
    private int time = 0;

    @Override
    public boolean filter(Graph graph) {
        int numberOfVertices = graph.getNumberOfVertices();
        boolean[] visited = new boolean[numberOfVertices];
        int[] disc = new int[numberOfVertices];
        int[] low = new int[numberOfVertices];
        Vertex[] parent = new Vertex[numberOfVertices];

        for (int i = 0; i < numberOfVertices; i++) {
            parent[i] = null;
            visited[i] = false;
        }
        for (int i = 0; i < numberOfVertices; i++) {
            if (!visited[i]) {
                if (bridgeFinder(graph.getVertices().get(i), visited, disc, low, parent)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean bridgeFinder(Vertex vertex, boolean[] visited, int[] disc, int[] low, Vertex[] parent) {
        visited[vertex.getNumber()] = true;
        disc[vertex.getNumber()] = low[vertex.getNumber()] = ++time;
        for (Vertex neighbor : vertex.getAdjVertices()) {
            if (!visited[neighbor.getNumber()]) {
                parent[neighbor.getNumber()] = vertex;
                bridgeFinder(neighbor, visited, disc, low, parent);
                low[vertex.getNumber()] = Math.min(low[vertex.getNumber()], low[neighbor.getNumber()]);
                if (low[neighbor.getNumber()] > disc[vertex.getNumber()]) {
                    return true;
                }
            } else if (neighbor != parent[vertex.getNumber()])
                low[vertex.getNumber()] = Math.min(low[vertex.getNumber()], disc[neighbor.getNumber()]);
        }
        return false;
    }


    @Override
    public boolean is(Graph graph) {
        return this.filter(graph);
    }

    @Override
    public String accept(FilterVisitor<String> filterVisitor) {
        return filterVisitor.visit(this);
    }
}
