package writing;

import filter.graphfilter.GraphFilter;
import graph.Graph;


public interface WritingStrategy {

    void process(Graph graph, GraphFilter graphFilter, String prefix);

}
