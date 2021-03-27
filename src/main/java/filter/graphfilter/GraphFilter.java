package filter.graphfilter;

import filter.Filter;
import graph.Graph;

public interface GraphFilter extends Filter {

    boolean filter(Graph graph);
}
