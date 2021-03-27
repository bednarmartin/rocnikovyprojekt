package filter.graphfilter;

import filter.FilterVisitor;
import filter.cyclefilter.ChordCycleFilter;
import filter.cyclefilter.CycleFilter;
import finder.CycleFinder;
import finder.cdc.CDCFinder;
import finder.cdc.StopRecursionException;
import graph.Cycle;
import graph.Graph;

import java.util.ArrayList;
import java.util.Set;

public class StrongCDCGraphFilter implements GraphFilter {

    @Override
    public boolean filter(Graph graph) {
        CycleFinder cycleFinder = new CycleFinder(graph);
        Set<Cycle> cycles = cycleFinder.findAllCycles();

        CycleFilter chordCycleFilter = new ChordCycleFilter();
        chordCycleFilter.filter(cycles, graph);

        CDCFinder cdcFinder = new CDCFinder(graph, new ArrayList<>(cycles), new StopRecursionException());
        Set<Set<Cycle>> CDCs = cdcFinder.find();
        return !CDCs.isEmpty();
    }

    @Override
    public String accept(FilterVisitor<String> filterVisitor) {
        return filterVisitor.visit(this);
    }

    @Override
    public boolean is(Graph graph) {
        return this.filter(graph);
    }

}
