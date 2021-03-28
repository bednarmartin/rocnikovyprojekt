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
                () -> Assertions.assertEquals(5, new Vertex(5).getNumber()),
                () -> Assertions.assertEquals(10, new Vertex(10).getNumber()),
                () -> Assertions.assertEquals(13, new Vertex(13).getNumber()));
    }

    @Test
    @DisplayName("Should check creating of Edge objects")
    void checkCreatingEdgeObjects() {
        final Edge edgeToCheck = new Edge(new Vertex(7), new Vertex(8));
        Assertions.assertAll(
                () -> Assertions.assertEquals(8,edgeToCheck.getFirst().getNumber()),
                () -> Assertions.assertEquals(7,edgeToCheck.getSecond().getNumber()));

        final Edge anotherEdgeToCheck = new Edge(new Vertex(15), new Vertex(13));
        Assertions.assertAll(
                () -> Assertions.assertEquals(15,anotherEdgeToCheck.getFirst().getNumber()),
                () -> Assertions.assertEquals(13,anotherEdgeToCheck.getSecond().getNumber()));

    }

    @Test
    @DisplayName("Should check creating of Cycle objects")
    void checkCreatingCycleObjects() {
        Assertions.assertAll(
                () -> Assertions.assertEquals(5,new Cycle(new HashSet<>(), 5).getNumber()),
                () -> Assertions.assertEquals(10,new Cycle(new HashSet<>(), 10).getNumber()),
                () -> Assertions.assertEquals(13,new Cycle(new HashSet<>(), 13).getNumber()));
    }

    @Test
    @DisplayName("Should check creating of Graph objects")
    void checkCreatingGraphObjects() {
        Assertions.assertAll(
                () -> Assertions.assertEquals(5,new Graph(5).getNumberOfVertices()),
                () -> Assertions.assertEquals(10,new Graph(10).getNumberOfVertices()),
                () -> Assertions.assertEquals(13,new Graph(13).getNumberOfVertices()));
    }

}
