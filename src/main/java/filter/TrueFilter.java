package filter;

import graph.Graph;

public class TrueFilter implements Filter{
    @Override
    public boolean is(Graph graph) {
        return true;
    }

    @Override
    public String accept(FilterVisitor<String> filterVisitor) {
        return filterVisitor.visit(this);
    }
}
