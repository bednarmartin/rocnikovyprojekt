package filter.cyclefilter;

import filter.Filter;
import graph.Cycle;
import graph.Graph;

import java.util.Set;

public interface CycleFilter extends Filter {

    void filter(Set<Cycle> cycles, Graph graph);
}
