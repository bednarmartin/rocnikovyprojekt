package execute;

import filter.Filter;
import graph.Graph;

import java.util.ArrayList;
import java.util.List;

public class EventToLookFor {

    public void processEvent(Graph graph, int number, String text, List<Filter> filters) {
        for (Filter filter : filters) {
            if (!filter.is(graph)) {
                System.out.println("GRAPH " + number + " - PASSED");
                return;
            }
        }
        try {
            throw new Exception();
        } catch (Exception e) {
            System.out.println(text);
            e.printStackTrace();
            System.exit(0);
        }
    }

    public void processEvent(Graph graph, int number, String text, Filter... filters) {
        List<Filter> listOfFilters = new ArrayList<>(List.of(filters));
        processEvent(graph, number, text, listOfFilters);
    }
}
