import execute.EventToLookFor;
import execute.GraphExcluder;
import filter.NegationFilter;
import filter.graphfilter.*;
import reading.*;
import graph.*;
import writing.console.GraphConsoleWriter;

public class Runner {

    public static void main(String[] args) {

        GraphReader graphReader = new AdjacentVerticesGraphReader();
        int numberOfGraphs = graphReader.getNumberOfGraphs("./src/main/resources/24g3e5c.txt", 24);

        for (int i = 5700; i < numberOfGraphs; i++) {
            Graph graph = graphReader.read("./src/main/resources/24g3e5c.txt", 24, i);
            GraphExcluder graphExcluder = new GraphExcluder(graph, new GraphConsoleWriter());

            if (!graphExcluder.pass(i + 1, new BridgeGraphFilter(), new TwoCutGraphFilter(), new ThreeCutGraphFilter())) {
                continue;
            }

            EventToLookFor eventToLookFor = new EventToLookFor();
            eventToLookFor.processEvent(graph, i + 1, "GRAPH " + (i + 1) + " - HAS NOT STRONG CDC", new NegationFilter(new StrongCDCGraphFilter()));

        }

    }
}
