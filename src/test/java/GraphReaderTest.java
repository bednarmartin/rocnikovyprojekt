import graph.Edge;
import graph.Graph;
import graph.Vertex;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import reading.AdjacentVerticesGraphReader;
import reading.GraphReader;
import reading.SnarkFormatGraphReader;

import java.util.Set;

public class GraphReaderTest {

    @Test
    @DisplayName("Check reading the second graph with 6 vertices from file with adjacent vertices encoding")
    void checkReadingTheSecondGraphWithSixVerticesFromFile() {
        GraphReader graphReader = new AdjacentVerticesGraphReader();
        Graph graph = graphReader.read("./src/main/resources/cubicgraphs/6g3e.txt", 6, 1);
        Assertions.assertAll(
                () -> Assertions.assertEquals(6, graph.getNumberOfVertices()),
                () -> Assertions.assertEquals(9, graph.getEdges().size()),
                () -> Assertions.assertEquals(6, graph.getVertices().size()));

        for (Vertex vertex : graph.getVertices()) {
            Assertions.assertEquals(vertex.getAdjVertices().size(), 3);
        }
        Vertex vertex_0 = graph.getVertices().get(0);
        Assertions.assertAll(
                () -> Assertions.assertTrue(vertex_0.getAdjVertices().contains(new Vertex(2))),
                () -> Assertions.assertTrue(vertex_0.getAdjVertices().contains(new Vertex(3))),
                () -> Assertions.assertTrue(vertex_0.getAdjVertices().contains(new Vertex(4)))
        );

        Vertex vertex_1 = graph.getVertices().get(1);
        Assertions.assertAll(
                () -> Assertions.assertTrue(vertex_1.getAdjVertices().contains(new Vertex(2))),
                () -> Assertions.assertTrue(vertex_1.getAdjVertices().contains(new Vertex(3))),
                () -> Assertions.assertTrue(vertex_1.getAdjVertices().contains(new Vertex(4)))
        );

        Vertex vertex_2 = graph.getVertices().get(2);
        Assertions.assertAll(
                () -> Assertions.assertTrue(vertex_2.getAdjVertices().contains(new Vertex(0))),
                () -> Assertions.assertTrue(vertex_2.getAdjVertices().contains(new Vertex(1))),
                () -> Assertions.assertTrue(vertex_2.getAdjVertices().contains(new Vertex(5)))
        );

        Vertex vertex_3 = graph.getVertices().get(3);
        Assertions.assertAll(
                () -> Assertions.assertTrue(vertex_3.getAdjVertices().contains(new Vertex(0))),
                () -> Assertions.assertTrue(vertex_3.getAdjVertices().contains(new Vertex(1))),
                () -> Assertions.assertTrue(vertex_3.getAdjVertices().contains(new Vertex(5)))
        );

        Vertex vertex_4 = graph.getVertices().get(4);
        Assertions.assertAll(
                () -> Assertions.assertTrue(vertex_4.getAdjVertices().contains(new Vertex(0))),
                () -> Assertions.assertTrue(vertex_4.getAdjVertices().contains(new Vertex(1))),
                () -> Assertions.assertTrue(vertex_4.getAdjVertices().contains(new Vertex(5)))
        );

        Vertex vertex_5 = graph.getVertices().get(5);
        Assertions.assertAll(
                () -> Assertions.assertTrue(vertex_5.getAdjVertices().contains(new Vertex(2))),
                () -> Assertions.assertTrue(vertex_5.getAdjVertices().contains(new Vertex(3))),
                () -> Assertions.assertTrue(vertex_5.getAdjVertices().contains(new Vertex(4)))
        );

        Set<Edge> graphEdges = graph.getEdges();

        Assertions.assertAll(
                () -> Assertions.assertTrue(graphEdges.contains(new Edge(new Vertex(0), new Vertex(2)))),
                () -> Assertions.assertTrue(graphEdges.contains(new Edge(new Vertex(0), new Vertex(3)))),
                () -> Assertions.assertTrue(graphEdges.contains(new Edge(new Vertex(0), new Vertex(4)))),
                () -> Assertions.assertTrue(graphEdges.contains(new Edge(new Vertex(1), new Vertex(2)))),
                () -> Assertions.assertTrue(graphEdges.contains(new Edge(new Vertex(1), new Vertex(3)))),
                () -> Assertions.assertTrue(graphEdges.contains(new Edge(new Vertex(1), new Vertex(4)))),
                () -> Assertions.assertTrue(graphEdges.contains(new Edge(new Vertex(2), new Vertex(0)))),
                () -> Assertions.assertTrue(graphEdges.contains(new Edge(new Vertex(2), new Vertex(1)))),
                () -> Assertions.assertTrue(graphEdges.contains(new Edge(new Vertex(2), new Vertex(5)))),
                () -> Assertions.assertTrue(graphEdges.contains(new Edge(new Vertex(3), new Vertex(0)))),
                () -> Assertions.assertTrue(graphEdges.contains(new Edge(new Vertex(3), new Vertex(1)))),
                () -> Assertions.assertTrue(graphEdges.contains(new Edge(new Vertex(3), new Vertex(5)))),
                () -> Assertions.assertTrue(graphEdges.contains(new Edge(new Vertex(4), new Vertex(0)))),
                () -> Assertions.assertTrue(graphEdges.contains(new Edge(new Vertex(4), new Vertex(1)))),
                () -> Assertions.assertTrue(graphEdges.contains(new Edge(new Vertex(4), new Vertex(5)))),
                () -> Assertions.assertTrue(graphEdges.contains(new Edge(new Vertex(5), new Vertex(2)))),
                () -> Assertions.assertTrue(graphEdges.contains(new Edge(new Vertex(5), new Vertex(3)))),
                () -> Assertions.assertTrue(graphEdges.contains(new Edge(new Vertex(5), new Vertex(4))))
        );


    }

    @Test
    @DisplayName("Check number of graphs in file")
    void checkNumberOfGraph() {
        GraphReader graphReader = new AdjacentVerticesGraphReader();
        Assertions.assertAll(
                () -> Assertions.assertEquals(1, graphReader.getNumberOfGraphs("./src/main/resources/cubicgraphs/4g3e.txt", 4)),
                () -> Assertions.assertEquals(2, graphReader.getNumberOfGraphs("./src/main/resources/cubicgraphs/6g3e.txt", 6)),
                () -> Assertions.assertEquals(5, graphReader.getNumberOfGraphs("./src/main/resources/cubicgraphs/8g3e.txt", 8)),
                () -> Assertions.assertEquals(19, graphReader.getNumberOfGraphs("./src/main/resources/cubicgraphs/10g3e.txt", 10)),
                () -> Assertions.assertEquals(85, graphReader.getNumberOfGraphs("./src/main/resources/cubicgraphs/12g3e.txt", 12)),
                () -> Assertions.assertEquals(509, graphReader.getNumberOfGraphs("./src/main/resources/cubicgraphs/14g3e.txt", 14))
        );
    }

    @Test
    @DisplayName("Check number of snarks in file")
    void checkNumberOfSnarks() {
        GraphReader graphReader = new SnarkFormatGraphReader();
        Assertions.assertAll(
                () -> Assertions.assertEquals(2, graphReader.getNumberOfGraphs("./src/main/resources/snarks/s18_c4.txt", 18)),
                () -> Assertions.assertEquals(6, graphReader.getNumberOfGraphs("./src/main/resources/snarks/s20_c4.txt", 20)),
                () -> Assertions.assertEquals(20, graphReader.getNumberOfGraphs("./src/main/resources/snarks/s22_c4.txt", 22)),
                () -> Assertions.assertEquals(38, graphReader.getNumberOfGraphs("./src/main/resources/snarks/s24_c4.txt", 24)),
                () -> Assertions.assertEquals(280, graphReader.getNumberOfGraphs("./src/main/resources/snarks/s26_c4.txt", 26)),
                () -> Assertions.assertEquals(2900, graphReader.getNumberOfGraphs("./src/main/resources/snarks/s28_c4.txt", 28)),
                () -> Assertions.assertEquals(28399, graphReader.getNumberOfGraphs("./src/main/resources/snarks/s30_c4.txt", 30))

        );
    }

}
