package finder.cdc;

import constructor.CompleteGraphConstructor;
import constructor.EdgeGraphConstructor;
import constructor.GraphConstructor;
import filter.graphfilter.CDCFilter;
import finder.ComponentsFinder;
import finder.CycleFinder;
import finder.cut.EdgeFinder;
import finder.cut.ThreeCutEdgeFinder;
import graph.Cycle;
import graph.Edge;
import graph.Graph;

import java.util.*;

public class ThreeCutCDCFinder {
    private final Graph graph;

    public ThreeCutCDCFinder(Graph graph) {
        this.graph = graph;
    }

    public Map<Graph, Set<Set<Cycle>>> find(CDCFilter cdcFilter) {
        Map<Graph, Set<Set<Cycle>>> CDCsOfComponents = new HashMap<>();
        List<Graph> components = this.getComponents(graph);
        for (Graph component : components) {
            CDCsOfComponents.put(component, cdcFilter.getCDCs(component));
        }
        return CDCsOfComponents;
    }

    public Map<Graph, Set<Set<Cycle>>> find(Graph graph) {
        Map<Graph, Set<Set<Cycle>>> CDCsOfComponents = new HashMap<>();
        List<Graph> components = this.getComponents(graph);
        for (Graph component : components) {

            CycleFinder cycleFinder = new CycleFinder(component);
            Set<Cycle> cycles = cycleFinder.findAllCycles();

            CDCFinder cdcFinder = new CDCFinder(component, new ArrayList<>(cycles));
            CDCsOfComponents.put(graph, cdcFinder.find());
        }
        return CDCsOfComponents;
    }

    private List<Graph> getComponents(Graph graph) {
        List<Graph> components = new ArrayList<>();
        EdgeFinder threeCutEdgeFinder = new ThreeCutEdgeFinder();
        Set<Set<Edge>> threeCutEdges = threeCutEdgeFinder.get(graph);

        for (Set<Edge> cutEdges : threeCutEdges) {
            graph.getEdges().removeAll(cutEdges);
            GraphConstructor graphConstructor = new EdgeGraphConstructor(graph.getEdges());
            Graph newGraph = graphConstructor.construct();

            ComponentsFinder componentsFinder = new ComponentsFinder(newGraph);
            Set<Set<Edge>> graphEdgeComponents = componentsFinder.find();

            for (Set<Edge> componentEdges : graphEdgeComponents) {
                GraphConstructor componentEdgesGraphConstructor = new EdgeGraphConstructor(componentEdges);
                Graph newComponentGraph = componentEdgesGraphConstructor.construct();

                GraphConstructor completeGraphConstructor = new CompleteGraphConstructor(newComponentGraph);
                newComponentGraph = completeGraphConstructor.construct();
                components.add(newComponentGraph);
                graph.getEdges().addAll(cutEdges);
            }
        }
        return components;
    }
}
