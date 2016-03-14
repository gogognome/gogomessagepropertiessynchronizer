package nl.gogognome.messagepropertiessynchronizer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class MessageProperties {

    private List<Line> lines = new ArrayList<>();

    public void parse(InputStream inputStream) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        String line = reader.readLine();
        while (line != null) {
            lines.add(parse(line));
            line = reader.readLine();
        }
    }

    private Line parse(String line) {
        if (line.trim().startsWith("#")) {
            return new NonKeyValueLine(line);
        }
        int separatorIndex = line.indexOf('=');
        if (separatorIndex != -1) {
            return new KeyValueLine(line.substring(0, separatorIndex).trim(), line.substring(separatorIndex + 1).trim());
        }
        return new NonKeyValueLine(line);
    }

    public int size() {
        return lines.size();
    }

    public Line get(int index) {
        return lines.get(index);
    }

}

