package filter.cyclefilter;

import filter.FilterVisitor;
import finder.CycleFinder;
import graph.Cycle;
import graph.Edge;
import graph.Graph;

import java.util.HashSet;
import java.util.Set;

public class ChordCycleFilter implements CycleFilter {

    @Override
    public void filter(Set<Cycle> cycles, Graph graph) {
        Set<Cycle> originalCycles = new HashSet<>(cycles);
        for (Edge edge : graph.getEdges()) {
            cycles.retainAll(getCyclesWithoutChord(edge, originalCycles));
        }


    }

    private Set<Cycle> getCyclesWithoutChord(Edge edge, Set<Cycle> cycles) {
        Set<Cycle> goodCycles = new HashSet<>();
        for (Cycle cycle : cycles) {
            if (cycle.getEdges().contains(edge)) {
                goodCycles.add(cycle);
                continue;
            }
            boolean hasFirst = false;
            boolean hasSecond = false;
            for (Edge edgeToCheck : cycle.getEdges()) {
                if (edge.getFirst().getNumber() == edgeToCheck.getFirst().getNumber() || edge.getFirst().getNumber() == edgeToCheck.getSecond().getNumber()) {
                    hasFirst = true;
                    if (hasSecond) {
                        break;
                    }
                }

                if (edge.getSecond().getNumber() == edgeToCheck.getFirst().getNumber() || edge.getSecond().getNumber() == edgeToCheck.getSecond().getNumber()) {
                    hasSecond = true;
                    if (hasFirst) {
                        break;
                    }
                }
            }
            if (!hasFirst || !hasSecond) {
                goodCycles.add(cycle);
            }
        }


        return goodCycles;

    }

    @Override
    public boolean is(Graph graph) {
        CycleFinder cycleFinder = new CycleFinder(graph);
        Set<Cycle> cycles = cycleFinder.findAllCycles();
        int original_size = cycles.size();
        this.filter(cycles, graph);
        return cycles.size() != original_size;
    }

    @Override
    public String accept(FilterVisitor<String> filterVisitor) {
        return filterVisitor.visit(this);
    }
}
