package writing.file;

import writing.FileWriter;

import java.io.File;
import java.io.IOException;

public class GraphFileWriter implements FileWriter {
    java.io.FileWriter fileWriter;

    public GraphFileWriter(String path) {
        try {
            File file = new File(path);
            file.createNewFile();
            fileWriter = new java.io.FileWriter(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void write(String text) {
        try {
            fileWriter.write(text);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    @Override
    public void writeln(String text) {
        try {
            fileWriter.write(text + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void close(){
        try{
            fileWriter.close();
        }catch (IOException e){
            e.printStackTrace();
        }

    }
}


