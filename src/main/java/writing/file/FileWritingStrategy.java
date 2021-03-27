package writing.file;

import filter.NegationFilter;
import filter.OutputFilterVisitor;
import filter.graphfilter.GraphFilter;
import graph.Graph;
import writing.WritingStrategy;

public class FileWritingStrategy implements WritingStrategy {
    GraphFileWriter graphFileWriter;

    public FileWritingStrategy(String path) {
        this.graphFileWriter = new GraphFileWriter(path);
    }

    public void process(Graph graph, GraphFilter graphFilter, String prefix) {
        String suffix;
        if (graphFilter.filter(graph)) {
            suffix = graphFilter.accept(new OutputFilterVisitor());
        } else {
            suffix = new NegationFilter(graphFilter).accept(new OutputFilterVisitor());
        }
        String answer = prefix + suffix;
        graphFileWriter.writeln(answer);

    }

    void close() {
        graphFileWriter.close();
    }
}
