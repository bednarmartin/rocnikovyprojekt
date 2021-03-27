package execute;

import filter.Filter;
import filter.OutputFilterVisitor;
import graph.Graph;
import writing.Writer;

public class GraphExcluder {
    private final Graph graph;
    private final Writer writer;

    public GraphExcluder(Graph graph, Writer writer) {
        this.graph = graph;
        this.writer = writer;
    }

    public boolean pass(int number, Filter... filters) {
        String answer = "GRAPH " + number + " - ";
        for (Filter filter : filters) {
            if (filter.is(graph)) {
                writer.writeln(answer + filter.accept(new OutputFilterVisitor()));
                return false;
            }
        }
        return true;
    }


}
