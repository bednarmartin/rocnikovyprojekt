package finder;

import graph.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * A class representing an algorithm for finding cycles in graph
 */
public class CycleFinder {
    private final Graph graph;
    private final Set<Cycle> cycles;
    private final Set<Set<Edge>> edgesCycles;
    int counter;

    /**
     * Constructor for CycleFinder class
     *
     * @param graph graph to find cycles of
     */

    public CycleFinder(Graph graph) {
        this.graph = graph;
        this.cycles = new HashSet<>();
        this.edgesCycles = new HashSet<>();
        this.counter = 0;
    }

    /**
     * Method for finding all cycles of selected lenght in graph
     *
     * @param number lenght of cycles
     * @return Set of cycles of selected lenght
     */

    public Set<Cycle> findCyclesOfLenght(int number) {
        cycles.clear();
        edgesCycles.clear();
        boolean[] marked = new boolean[graph.getNumberOfVertices()];
        for (int i = 0; i < graph.getNumberOfVertices() - (number - 1); i++) {
            List<Vertex> vertices = new ArrayList<>();
            vertices.add(graph.getVertices().get(i));
            DFS(marked, number - 1, graph.getVertices().get(i), graph.getVertices().get(i), vertices);
            marked[i] = true;
        }

        makeCycles();

        return new HashSet<>(cycles);


    }

    public Set<Cycle> findAllCycles() {
        Set<Cycle> allCycles = new HashSet<>();

        for (int i = 3; i <= graph.getNumberOfVertices(); i++) {
            allCycles.addAll(findCyclesOfLenght(i));
        }
        return allCycles;


    }

    private void DFS(boolean[] marked, int n, Vertex vertex, Vertex start, List<Vertex> vertices) {
        marked[vertex.getNumber()] = true;
        if (n == 0) {

            marked[vertex.getNumber()] = false;
            if (vertex.getAdjVertices().contains(start)) {
                edgesCycles.add(makeCycleSet(vertices));

            }
            return;

        }

        for (int i = 0; i < graph.getNumberOfVertices(); i++) {
            if (!marked[i] && vertex.getAdjVertices().contains(graph.getVertices().get(i))) {
                List<Vertex> newVertices = new ArrayList<>(vertices);
                newVertices.add(graph.getVertices().get(i));
                DFS(marked, n - 1, graph.getVertices().get(i), start, newVertices);
            }

        }
        marked[vertex.getNumber()] = false;

    }

    /**
     * Method to transform List of vertices into a Set of edges representing cycle
     *
     * @param vertices list of vertices to trasnform
     * @return set of edges representing cycle
     */

    private Set<Edge> makeCycleSet(List<Vertex> vertices) {
        vertices.add(vertices.get(0));
        Set<Edge> edges = new HashSet<>();
        for (int i = 1; i < vertices.size(); i++) {
            edges.add(new Edge(vertices.get(i - 1), vertices.get(i)));

        }
        vertices.remove(vertices.size() - 1);
        return edges;
    }

    /**
     * Method to transform Set of Set of edges to Set of Cycles
     */
    private void makeCycles() {
        for (Set<Edge> setEdge : edgesCycles) {
            cycles.add(new Cycle(setEdge, counter++));
        }

    }


}
