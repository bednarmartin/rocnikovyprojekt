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

public class StrongCDCGraphFilter implements CDCFilter {

    @Override
    public boolean filter(Graph graph) {
        return !this.getCDCs(graph).isEmpty();
    }

    @Override
    public String accept(FilterVisitor<String> filterVisitor) {
        return filterVisitor.visit(this);
    }

    @Override
    public boolean is(Graph graph) {
        return this.filter(graph);
    }

    @Override
    public Set<Set<Cycle>> getCDCs(Graph graph) {
        CycleFinder cycleFinder = new CycleFinder(graph);
        Set<Cycle> cycles = cycleFinder.findAllCycles();

        CycleFilter chordCycleFilter = new ChordCycleFilter();
        chordCycleFilter.filter(cycles, graph);

        CDCFinder cdcFinder = new CDCFinder(graph, new ArrayList<>(cycles), new StopRecursionException());
        return cdcFinder.find();
    }

}
