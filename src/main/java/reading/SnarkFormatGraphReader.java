package reading;

import graph.Edge;
import graph.Graph;
import graph.Vertex;

import java.io.*;
import java.util.*;

public class SnarkFormatGraphReader implements GraphReader {

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
            File file = new File(path);
            Scanner scanner = new Scanner(file);
            for (int i = 0; i < indexOfGraph; i++) {
                for (int j = 0; j < 4; j++) {
                    scanner.nextLine();
                }
            }
            scanner.nextLine();
            int[] first = new int[numberOfVertices];
            int[] second = new int[numberOfVertices];
            int[] third = new int[numberOfVertices];
            for (int i = 0; i < numberOfVertices; i++) {
                first[i] = (scanner.nextInt() - 1);
            }
            for (int i = 0; i < numberOfVertices; i++) {
                second[i] = (scanner.nextInt() - 1);
            }
            for (int i = 0; i < numberOfVertices; i++) {
                third[i] = (scanner.nextInt() - 1);
            }

            for (int i = 0; i < numberOfVertices; i++) {
                vertices.add(new Vertex(i));
            }
            for (int i = 0; i < numberOfVertices; i++) {
                vertices.get(i).addNeighbor(vertices.get(first[i]));
                vertices.get(i).addNeighbor(vertices.get(second[i]));
                vertices.get(i).addNeighbor(vertices.get(third[i]));
                edges.add(new Edge(vertices.get(i), vertices.get(first[i])));
                edges.add(new Edge(vertices.get(i), vertices.get(second[i])));
                edges.add(new Edge(vertices.get(i), vertices.get(third[i])));
            }


        } catch (IOException e) {
            e.printStackTrace();

        }
        graph.setVertices(vertices);
        graph.setEdges(edges);
        return graph;
    }


    @Override
    public int getNumberOfGraphs(String path, int numberOfVertices) {
        return ((GraphReader.countLinesNew(path) + 1) / 4);
    }
}
