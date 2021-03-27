package writing.console;

import writing.Writer;

public class GraphConsoleWriter implements Writer {
    @Override
    public void write(String text) {
        System.out.print(text);
    }

    @Override
    public void writeln(String text) {
        System.out.println(text);
    }
}
