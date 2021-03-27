package finder.cut;

import determiner.ConnectedGraphDeterminer;
import graph.Edge;
import graph.Graph;

import java.util.*;

public abstract class CutEdgeFinder implements EdgeFinder {
    Graph graph;
    List<Edge> edgesToCheck;
    Set<Set<Edge>> cutEdges;
    int CUT_SIZE;

    void prepare(Graph graph, int CUT_SIZE) {
        this.CUT_SIZE = CUT_SIZE;
        this.graph = graph;
        this.cutEdges = new HashSet<>();
        this.edgesToCheck = new ArrayList<>(graph.getEdges());
    }

    boolean isCut(Set<Edge> edgesToCheck) {
        for (Edge edge : edgesToCheck) {
            graph.getVertices().get(edge.getFirst().getNumber()).getAdjVertices().remove(edge.getSecond());
            graph.getVertices().get(edge.getSecond().getNumber()).getAdjVertices().remove(edge.getFirst());
        }
        ConnectedGraphDeterminer connectedGraphDeterminer = new ConnectedGraphDeterminer();
        boolean isCut = !connectedGraphDeterminer.is(graph);
        for (Edge edge : edgesToCheck) {
            graph.getVertices().get(edge.getFirst().getNumber()).getAdjVertices().add(edge.getSecond());
            graph.getVertices().get(edge.getSecond().getNumber()).getAdjVertices().add(edge.getFirst());
        }
        return isCut;
    }


}
