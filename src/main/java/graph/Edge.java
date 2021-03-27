package graph;

/**
 * A class representing a edge of a graph
 * Each Edge object contains two Vertex objects representing vertices of the edge
 * First vertex is the one with bigger vertex number
 */
public class Edge {
    private final Vertex first;
    private final Vertex second;

    /**
     * Constructor for class Edge
     *
     * @param first  the first vertex of the edge
     * @param second the second vertex of the edge
     */

    public Edge(Vertex first, Vertex second) {
        if (first.getNumber() > second.getNumber()) {
            this.first = first;
            this.second = second;
        } else {
            this.first = second;
            this.second = first;
        }
    }

    public Vertex getFirst() {
        return first;
    }

    public Vertex getSecond() {
        return second;
    }

    @Override
    public String toString() {
        return "[" + first.getNumber() + ", " + second.getNumber() + "]";
    }

    @Override
    public int hashCode() {
        return 556 * first.getNumber() + 1067 * second.getNumber() + 5831;
    }

    @Override
    public boolean equals(Object objectToCompare) {
        if (this == objectToCompare) {
            return true;
        }
        if (objectToCompare == null) {
            return false;
        }
        if (getClass() != objectToCompare.getClass()) {
            return false;
        }
        return (((Edge) objectToCompare).getFirst().getNumber() == this.first.getNumber()) && ((Edge) objectToCompare).getSecond().getNumber() == this.second.getNumber();


    }
}
