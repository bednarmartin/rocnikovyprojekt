package graph;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * A class representing a vertex of a graph
 * Each Vertex object contains its corresponding number, List of adjacent vertices and Set of cycles which it is part of
 */

public class Vertex implements Comparable<Vertex> {
    private final List<Vertex> adjacentVertices;
    private final Set<Cycle> cycles;
    private final int number;

    /**
     * Constructor for class Vertex
     *
     * @param number vertex number
     */

    public Vertex(int number) {
        adjacentVertices = new ArrayList<>();
        cycles = new HashSet<>();
        this.number = number;

    }


    public List<Vertex> getAdjVertices() {
        return adjacentVertices;
    }


    public Set<Cycle> getCycles() {
        return cycles;
    }


    public int getNumber() {
        return number;
    }


    /**
     * Adds a Cycle object to a Set of Cycles
     *
     * @param cycle Cycle to add
     */
    public void addCycle(Cycle cycle) {
        cycles.add(cycle);
    }

    /**
     * Adds a Vertex object to a list of adjacent vertices
     *
     * @param vertex Vertex to add
     */

    public void addNeighbor(Vertex vertex) {
        adjacentVertices.add(vertex);
    }

    /**
     * Clears the Set of cycles
     */

    public void clearCycles() {
        cycles.clear();
    }

    @Override
    public String toString() {
        return String.valueOf(number);
    }


    @Override
    public int hashCode() {
        return 31 * number + 5;
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
        } else {
            return ((Vertex) objectToCompare).getNumber() == this.number;
        }
    }

    @Override
    public int compareTo(Vertex vertexToCompare) {
        return Integer.compare(this.number, vertexToCompare.getNumber());
    }
}
