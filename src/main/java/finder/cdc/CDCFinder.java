package finder.cdc;

import graph.Cycle;
import graph.Edge;
import graph.Graph;

import java.util.*;

public class CDCFinder {
    private final Graph graph;
    private final List<Cycle> cycles;
    private final Set<Set<Cycle>> CDCs;
    private final Exception exceptionStrategy;

    public CDCFinder(Graph graph, List<Cycle> cycles, Exception exceptionStrategy) {
        this.graph = graph;
        this.cycles = cycles;
        this.exceptionStrategy = exceptionStrategy;
        this.CDCs = new HashSet<>();
    }

    public CDCFinder(Graph graph, List<Cycle> cycles) {
        this.graph = graph;
        this.cycles = cycles;
        this.exceptionStrategy = new ContinueException();
        this.CDCs = new HashSet<>();
    }

    public Set<Set<Cycle>> find() {
        Cycle[] temp = new Cycle[2];
        try {
            findAllCombinations(temp, 0, cycles.size() - 1, 0, 2);
        } catch (StopRecursionException e) {
            return CDCs;
        }
        return CDCs;
    }


    private void findAllCombinations(Cycle[] data, int start, int end, int index, int size) throws StopRecursionException {
        if (index == size) {
            process(new HashSet<>(Arrays.asList(data)), new HashSet<>(), new HashSet<>(), data[1]);
            return;

        }
        for (int i = start; i <= end && end - i + 1 >= size - index; i++) {
            data[index] = cycles.get(i);
            findAllCombinations(data, i + 1, end, index + 1, size);
        }
    }

    private void process(Set<Cycle> toCheck, Set<Edge> twoTimesUsedEdges, Set<Set<Cycle>> used, Cycle last) throws StopRecursionException {
        for (Cycle cycle : toCheck) {
            if (cycle.equals(last)) {
                continue;
            }
            for (Edge edge : cycle.getEdges()) {
                if (last.getEdges().contains(edge)) {
                    twoTimesUsedEdges.add(edge);
                }
            }
        }
        if (twoTimesUsedEdges.size() == graph.getEdges().size()) {
            CDCs.add(toCheck);
            if (exceptionStrategy instanceof StopRecursionException) {
                throw new StopRecursionException();
            } else {
                return;
            }

        }

        for (Cycle cycle : cycles) {
            if (Collections.disjoint(cycle.getEdges(), twoTimesUsedEdges) && !toCheck.contains(cycle)) {
                Set<Cycle> newToCheck = new HashSet<>(toCheck);
                newToCheck.add(cycle);
                if (!used.contains(newToCheck)) {
                    used.add(newToCheck);
                    process(newToCheck, new HashSet<>(twoTimesUsedEdges), used, cycle);
                }

            }

        }
    }

}
