package determiner;

import graph.Graph;
import graph.Vertex;

import java.util.ArrayList;
import java.util.List;

public class ConnectedGraphDeterminer{
    private Graph graph;

    public boolean is(Graph graph) {
        this.graph = graph;
        List<Boolean> visited = new ArrayList<>(graph.getNumberOfVertices());
        for(int i = 0; i < graph.getNumberOfVertices(); i++){
            visited.add(false);
        }
        DFS(graph.getVertices().get(0), visited);
        return !visited.contains(false);
    }

    private void DFS(Vertex vertex, List<Boolean> visited) {
        visited.set(vertex.getNumber(), true);
        for (Vertex neighbor : graph.getVertices().get(vertex.getNumber()).getAdjVertices()) {
            if (!visited.get(neighbor.getNumber()))
                DFS(neighbor, visited);
        }
    }
}
