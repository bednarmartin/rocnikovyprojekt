package filter.graphfilter;

import filter.FilterVisitor;
import finder.cut.ThreeCutEdgeFinder;
import graph.Graph;

public class ThreeCutGraphFilter implements GraphFilter {


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
        ThreeCutEdgeFinder threeCutEdgeFinder = new ThreeCutEdgeFinder();
        return !threeCutEdgeFinder.get(graph).isEmpty();
    }
}
