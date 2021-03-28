package filter.graphfilter;

import graph.Cycle;
import graph.Graph;

import java.util.Set;

public interface CDCFilter extends GraphFilter {

    Set<Set<Cycle>> getCDCs(Graph graph);
}
