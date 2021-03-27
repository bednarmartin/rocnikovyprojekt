package manipulator;

import graph.Edge;
import graph.Graph;

import java.util.Set;

public class ThreeCutGraphManipulator {
    private final Graph graph;
    private final Set<Edge> threeCutEdges;

    public ThreeCutGraphManipulator(Graph graph, Set<Edge> threeCutEdges) {
        this.graph = graph;
        this.threeCutEdges = threeCutEdges;

    }


}
