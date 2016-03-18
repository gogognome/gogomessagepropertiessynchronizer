package nl.gogognome.messagepropertiessynchronizer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MessageProperties {

    private List<Line> lines = new ArrayList<>();

    public void addLine(String line) {
        lines.add(parse(line));
    }

    public void addLineInFront(Line line) {
        lines.add(0, line);
    }

    private Line parse(String line) {
        if (line.trim().startsWith("#")) {
            return new NonKeyValueLine(line);
        }
        int separatorIndex = line.indexOf('=');
        if (separatorIndex != -1) {
            return new KeyValueLine(line);
        }
        return new NonKeyValueLine(line);
    }

    public int size() {
        return lines.size();
    }

    public Line get(int index) {
        return lines.get(index);
    }

    @Override
    public String toString() {
        return lines.stream().map(line -> line.getOriginalLine()).collect(Collectors.joining(", "));
    }
}

