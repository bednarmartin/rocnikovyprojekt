package graph;

import java.util.Set;

/**
 * A class representing a cycle in a graph
 * Each Cycle object contains Set of Edge objects representing the cycle and number of cycle
 */
public class Cycle{
    private final Set<Edge> edges;
    private final int number;

    /**
     * Constructor for Cycle class
     * @param edges Set of Edge objects representing the cycle
     * @param number number of cycle to differentiate the cycles
     */
    public Cycle(Set<Edge> edges, int number) {
        this.edges = edges;
        this.number = number;
    }

    public Set<Edge> getEdges() {
        return this.edges;
    }

    @Override
    public String toString() {
        return String.valueOf(number);
    }

    @Override
    public int hashCode() {
        return 548 * number + 123;
    }

    public int getNumber() {
        return number;
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
        return this.number == ((Cycle) objectToCompare).getNumber();
    }

}
