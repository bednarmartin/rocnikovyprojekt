package reading;

import graph.Edge;
import graph.Graph;
import graph.Vertex;

import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Class for reading graph files representing graphs with adjacent vertices
 */

public class AdjacentVerticesGraphReader implements GraphReader {

    /**
     * Read a particular graph from a file
     *
     * @param path             file path to read from
     * @param numberOfVertices number of vertices of the graphs
     * @param indexOfGraph     index of graph to read
     * @return Graph object of read graph
     */
    @SuppressWarnings("DuplicatedCode")
    @Override
    public Graph read(String path, int numberOfVertices, int indexOfGraph) {
        Graph graph = new Graph(numberOfVertices);
        Set<Edge> edges = new HashSet<>();
        List<Vertex> vertices = new ArrayList<>(numberOfVertices);

        for (int i = 0; i < numberOfVertices; i++) {
            vertices.add(new Vertex(i));
        }

        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(path));

            for (int i = 0; i < indexOfGraph; i++) {
                for (int j = 0; j <= numberOfVertices; j++) {
                    bufferedReader.readLine();
                }
            }

            for (int i = 0; i < numberOfVertices; i++) {
                String line = bufferedReader.readLine();
                String[] splitLine = line.split(" ");
                for (String s : splitLine) {
                    vertices.get(i).addNeighbor(vertices.get(Integer.parseInt(s)));
                    edges.add(new Edge(vertices.get(i), vertices.get(Integer.parseInt(s))));
                }
            }

            graph.setVertices(vertices);
            graph.setEdges(edges);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return graph;
    }

    @Override
    public int getNumberOfGraphs(String path, int numberOfVertices) {
        return (GraphReader.countLinesNew(path) + 2) / (numberOfVertices + 1);

    }


}

