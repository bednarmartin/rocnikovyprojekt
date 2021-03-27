import constructor.CompleteGraphConstructor;
import constructor.EdgeGraphConstructor;
import constructor.GraphConstructor;
import execute.EventToLookFor;
import execute.GraphExcluder;
import filter.Filter;
import filter.NegationFilter;
import filter.TrueFilter;
import filter.graphfilter.*;
import finder.ComponentsFinder;
import finder.cut.EdgeFinder;
import finder.cut.ThreeCutEdgeFinder;
import reading.*;
import graph.*;
import writing.WritingStrategy;
import writing.console.ConsoleWritingStrategy;
import writing.console.GraphConsoleWriter;


import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Runner {

    public static void main(String[] args) {

        GraphReader graphReader = new AdjacentVerticesGraphReader();
        int numberOfGraphs = graphReader.getNumberOfGraphs("./src/main/resources/cubicgraphs/8g3e.txt", 8);

        for (int i = 0; i < numberOfGraphs; i++) {
            Graph graph = graphReader.read("./src/main/resources/cubicgraphs/8g3e.txt", 8, i);
            GraphExcluder graphExcluder = new GraphExcluder(graph, new GraphConsoleWriter());

            if (!graphExcluder.pass(i + 1, new BridgeGraphFilter(), new TwoCutGraphFilter())) {
                continue;
            }

            EdgeFinder threeCutEdgeFinder = new ThreeCutEdgeFinder();
            Set<Set<Edge>> threeCutEdges = threeCutEdgeFinder.get(graph);

            GraphFilter strongCDCGraphFilter = new StrongCDCGraphFilter();
            List<Filter> filters = new ArrayList<>();
            boolean hasComponent = false;
            boolean componentsHasStrongCDC = true;
            for (Set<Edge> cutEdges : threeCutEdges) {
                hasComponent = true;
                graph.getEdges().removeAll(cutEdges);
                GraphConstructor graphConstructor = new EdgeGraphConstructor(graph.getEdges());
                Graph newGraph = graphConstructor.construct();
                ComponentsFinder componentsFinder = new ComponentsFinder(newGraph);
                Set<Set<Edge>> components = componentsFinder.find();

                for (Set<Edge> componentEdges : components) {

                    GraphConstructor componentEdgesGraphConstructor = new EdgeGraphConstructor(componentEdges);
                    Graph newComponentGraph = componentEdgesGraphConstructor.construct();

                    GraphConstructor completeGraphConstructor = new CompleteGraphConstructor(newComponentGraph);
                    newComponentGraph = completeGraphConstructor.construct();

                    if (!strongCDCGraphFilter.filter(newComponentGraph)) {
                        componentsHasStrongCDC = false;
                        break;
                    }

                    graph.getEdges().addAll(cutEdges);
                }
                if(!componentsHasStrongCDC){
                    break;
                }

            }
            if(componentsHasStrongCDC && hasComponent) {
                System.out.println("GRAPH " + (i + 1) + " - PASSED");
                continue;
            }
            filters.add(new NegationFilter(strongCDCGraphFilter));
            EventToLookFor eventToLookFor = new EventToLookFor();
            eventToLookFor.processEvent(graph, i + 1, "GRAPH " + (i + 1) + " - HAS NOT STRONG CDC", filters);

        }

    }
}
