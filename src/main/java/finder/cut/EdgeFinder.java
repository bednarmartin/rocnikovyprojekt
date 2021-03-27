package finder.cut;

import graph.Edge;
import graph.Graph;

import java.util.Set;

public interface EdgeFinder {

    Set<Set<Edge>> get(Graph graph);
}
