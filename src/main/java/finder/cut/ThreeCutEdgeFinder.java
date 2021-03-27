package finder.cut;

import graph.Edge;
import graph.Graph;
import graph.Vertex;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class ThreeCutEdgeFinder extends CutEdgeFinder {

    @Override
    public Set<Set<Edge>> get(Graph graph) {
        prepare(graph, 3);
        Edge[] temp = new Edge[CUT_SIZE];
        findAllCombinations(temp, 0, edgesToCheck.size() - 1, 0, CUT_SIZE);
        return cutEdges;

    }

    private void findAllCombinations(Edge[] data, int start, int end, int index, int size) {
        if (index == size) {
            Set<Edge> toCheck = new HashSet<>(Arrays.asList(data));
            Set<Vertex> edgeVertices = new HashSet<>();
            for (Edge edge : toCheck) {
                edgeVertices.add(edge.getFirst());
                edgeVertices.add(edge.getSecond());
            }
            if (edgeVertices.size() == 4) {
                return;
            }

            if (isCut(toCheck)) {
                cutEdges.add(toCheck);
            }
            return;
        }
        for (int i = start; i <= end && end - i + 1 >= size - index; i++) {
            data[index] = edgesToCheck.get(i);
            findAllCombinations(data, i + 1, end, index + 1, size);
        }

    }



}

