import graph.Cycle;
import graph.Edge;
import graph.Graph;
import graph.Vertex;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashSet;

public class GraphStructureTest {

    @Test
    @DisplayName("Should check creating of Vertex objects")
    void checkCreatingVertexObjects() {
        Assertions.assertAll(
                () -> Assertions.assertEquals(new Vertex(5).getNumber(), 5),
                () -> Assertions.assertEquals(new Vertex(10).getNumber(), 10),
                () -> Assertions.assertEquals(new Vertex(13).getNumber(), 13));
    }

    @Test
    @DisplayName("Should check creating of Edge objects")
    void checkCreatingEdgeObjects() {
        final Edge edgeToCheck = new Edge(new Vertex(7), new Vertex(8));
        Assertions.assertAll(
                () -> Assertions.assertEquals(edgeToCheck.getFirst().getNumber(), 8),
                () -> Assertions.assertEquals(edgeToCheck.getSecond().getNumber(), 7));

        final Edge anotherEdgeToCheck = new Edge(new Vertex(15), new Vertex(13));
        Assertions.assertAll(
                () -> Assertions.assertEquals(anotherEdgeToCheck.getFirst().getNumber(), 15),
                () -> Assertions.assertEquals(anotherEdgeToCheck.getSecond().getNumber(), 13));

    }

    @Test
    @DisplayName("Should check creating of Cycle objects")
    void checkCreatingCycleObjects() {
        Assertions.assertAll(
                () -> Assertions.assertEquals(new Cycle(new HashSet<>(), 5).getNumber(), 5),
                () -> Assertions.assertEquals(new Cycle(new HashSet<>(), 10).getNumber(), 10),
                () -> Assertions.assertEquals(new Cycle(new HashSet<>(), 13).getNumber(), 13));
    }

    @Test
    @DisplayName("Should check creating of Graph objects")
    void checkCreatingGraphObjects() {
        Assertions.assertAll(
                () -> Assertions.assertEquals(new Graph(5).getNumberOfVertices(), 5),
                () -> Assertions.assertEquals(new Graph(10).getNumberOfVertices(), 10),
                () -> Assertions.assertEquals(new Graph(13).getNumberOfVertices(), 13));
    }

}
