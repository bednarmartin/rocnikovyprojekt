package filter;

import graph.Graph;

public interface Filter {

    boolean is(Graph graph);

    String accept(FilterVisitor<String> filterVisitor);
}
