import filter.graphfilter.BridgeGraphFilter;
import filter.graphfilter.GraphFilter;
import filter.graphfilter.TwoCutGraphFilter;
import graph.Graph;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import reading.AdjacentVerticesGraphReader;
import reading.GraphReader;

public class GraphFilterTest {

    @Test
    @DisplayName("Check finding bridge of the graph")
    void checkBridge() {
        GraphReader graphReader = new AdjacentVerticesGraphReader();
        for (int i = 0; i < 10; i++) {
            Graph graph = graphReader.read("./src/main/resources/cubicgraphs/12g3e.txt", 12, i);
            GraphFilter graphFilter = new BridgeGraphFilter();
            if(i <= 3){
                Assertions.assertTrue(graphFilter.filter(graph));
            }else{
                Assertions.assertFalse(graphFilter.filter(graph));
            }



        }
    }

    @Test
    @DisplayName("Check finding bridge or 2-cut in graphs")
    void checkBridgeOrTwoCut(){
        GraphReader graphReader = new AdjacentVerticesGraphReader();
        for (int i = 0; i < 85; i++) {
            Graph graph = graphReader.read("./src/main/resources/cubicgraphs/12g3e.txt", 12, i);
            GraphFilter bridgeGraphFilter = new BridgeGraphFilter();
            GraphFilter twoCutGraphFilter = new TwoCutGraphFilter();
            if(i <= 3){
                Assertions.assertTrue(bridgeGraphFilter.filter(graph));
            }else if(i <= 26 || i == 63){
                Assertions.assertFalse(bridgeGraphFilter.filter(graph));
                Assertions.assertTrue(twoCutGraphFilter.filter(graph));
            }else{
                Assertions.assertFalse(bridgeGraphFilter.filter(graph));
                Assertions.assertFalse(twoCutGraphFilter.filter(graph));
            }



        }
    }
}
