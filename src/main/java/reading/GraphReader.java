package reading;

import graph.Edge;
import graph.Graph;
import graph.Vertex;

import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public interface GraphReader {

    Graph read(String path, int numberOfVertices, int indexOfGraph);

    int getNumberOfGraphs(String path, int numberOfVertices);


    /*
     * by martinus
     * https://stackoverflow.com/questions/453018/number-of-lines-in-a-file-in-java
     */
    static int countLinesNew(String filename) {
        int count = 0;
        try (InputStream is = new BufferedInputStream(new FileInputStream(filename))) {
            byte[] c = new byte[1024];

            int readChars = is.read(c);
            if (readChars == -1) {
                return 0;
            }
            while (readChars == 1024) {
                for (int i = 0; i < 1024; ) {
                    if (c[i++] == '\n') {
                        ++count;
                    }
                }
                readChars = is.read(c);
            }

            while (readChars != -1) {
                for (int i = 0; i < readChars; ++i) {
                    if (c[i] == '\n') {
                        ++count;
                    }
                }
                readChars = is.read(c);
            }


        } catch (IOException e) {
            e.printStackTrace();
        }
        return count == 0 ? 1 : count;
    }

}
