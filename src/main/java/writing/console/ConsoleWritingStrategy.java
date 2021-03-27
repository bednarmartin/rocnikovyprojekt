package writing.console;

import filter.NegationFilter;
import filter.OutputFilterVisitor;
import filter.graphfilter.GraphFilter;
import graph.Graph;
import writing.WritingStrategy;

public class ConsoleWritingStrategy implements WritingStrategy {
    GraphConsoleWriter graphConsoleWriter;

    public ConsoleWritingStrategy() {
        this.graphConsoleWriter = new GraphConsoleWriter();
    }

    public void process(Graph graph, GraphFilter graphFilter, String prefix) {
        String suffix;
        if (graphFilter.filter(graph)) {
            suffix = graphFilter.accept(new OutputFilterVisitor());
        } else {
            suffix = new NegationFilter(graphFilter).accept(new OutputFilterVisitor());
        }
        String answer = prefix + suffix;
        graphConsoleWriter.writeln(answer);

    }


}
