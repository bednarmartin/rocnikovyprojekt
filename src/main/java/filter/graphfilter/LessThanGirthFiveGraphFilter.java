package filter.graphfilter;

import filter.FilterVisitor;
import finder.CycleFinder;
import graph.Cycle;
import graph.Graph;

import java.util.Set;

public class LessThanGirthFiveGraphFilter implements GraphFilter {

    @Override
    public boolean is(Graph graph) {
        return this.filter(graph);
    }

    @Override
    public String accept(FilterVisitor<String> filterVisitor) {
        return filterVisitor.visit(this);
    }

    @Override
    public boolean filter(Graph graph) {
        CycleFinder cycleFinder = new CycleFinder(graph);
        Set<Cycle> cycles = cycleFinder.findCyclesOfLenght(3);
        cycles.addAll(cycleFinder.findCyclesOfLenght(4));
        return !cycles.isEmpty();
    }
}
