package filter;
import graph.Graph;

public class NegationFilter implements Filter{

    private final Filter filterToNegate;

    public NegationFilter(Filter filterToNegate) {
        this.filterToNegate = filterToNegate;

    }

    @Override
    public boolean is(Graph graph) {
        return !filterToNegate.is(graph);
    }

    @Override
    public String accept(FilterVisitor<String> filterVisitor) {
        return filterVisitor.visit(this);
    }

    public Filter getNegatedGraphFilter() {
        return this.filterToNegate;
    }
}
