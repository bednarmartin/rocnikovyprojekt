package determiner;

import graph.Cycle;
import graph.Edge;
import graph.Graph;

import java.util.HashSet;
import java.util.Set;

public class StrongEdgesDeterminer {
    private final Graph graph;

    public StrongEdgesDeterminer(Graph graph){
            this.graph = graph;
    }

    public int determine(Set<Cycle> CDC) {
        int strong = 0;
        process(CDC);
        Set<Edge> edges = graph.getEdges();
        for (Edge edge : edges) {
            if (!edge.getFirst().getCycles().equals(edge.getSecond().getCycles())) {
                strong++;
            }
        }
        clean();
        return strong;

    }


    private void process(Set<Cycle> CDC) {
        for (Cycle cycle : CDC) {
            for (Edge edge : cycle.getEdges()) {
                edge.getFirst().addCycle(cycle);
                edge.getSecond().addCycle(cycle);
            }
        }
    }

    private void clean() {
        for (Edge edge : graph.getEdges()) {
            edge.getFirst().clearCycles();
            edge.getSecond().clearCycles();
        }
    }

}
