package filter.graphfilter;

import filter.FilterVisitor;
import finder.cut.CutEdgeFinder;
import finder.cut.TwoCutEdgeFinder;
import graph.Graph;

public class TwoCutGraphFilter implements GraphFilter {
    @Override
    public boolean filter(Graph graph) {
        CutEdgeFinder cutEdgeFinder = new TwoCutEdgeFinder();
        return !cutEdgeFinder.get(graph).isEmpty();
    }

    @Override
    public boolean is(Graph graph) {
        return this.filter(graph);
    }

    @Override
    public String accept(FilterVisitor<String> filterVisitor) {
        return filterVisitor.visit(this);
    }
}
