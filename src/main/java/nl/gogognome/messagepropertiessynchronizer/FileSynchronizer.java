package nl.gogognome.messagepropertiessynchronizer;

import static java.util.stream.Collectors.*;
import java.io.*;
import java.nio.file.*;

public class FileSynchronizer {

    private String todoMessage;

    public FileSynchronizer(String todoMessage) {
        this.todoMessage = todoMessage;
    }

    public void synchronize(File source, File destination) throws IOException {
        MessageProperties sourceProperties = new MessageProperties();
        Files.lines(source.toPath()).forEach(sourceProperties::addLine);
        MessageProperties destinationProperties = new MessageProperties();

        if (destination.exists()) {
            Files.lines(destination.toPath()).forEach(destinationProperties::addLine);
        }

        int[][] lcs = determineLcs(sourceProperties, destinationProperties);
        MessageProperties resultProperties = buildResultingMessageProperties(lcs, sourceProperties, destinationProperties);

        Files.write(destination.toPath(), resultProperties.getLinesStream().map(Line::getOriginalLine).collect(toList()));
    }

    protected int[][] determineLcs(MessageProperties sourceProperties, MessageProperties destinationProperties) {
        int[][] lcs = new int[sourceProperties.size()+1][destinationProperties.size()+1];
        for (int row=1; row<destinationProperties.size()+1; row++) {
            for (int col=1; col<sourceProperties.size()+1; col++) {
                if (sourceProperties.get(col-1).equals(destinationProperties.get(row-1))) {
                    lcs[col][row] = 1 + lcs[col-1][row-1];
                } else {
                    lcs[col][row] = Math.max(lcs[col-1][row], lcs[col][row-1]);
                }
            }
        }
        return lcs;
    }

    protected MessageProperties buildResultingMessageProperties(int[][] lcs, MessageProperties sourceProperties, MessageProperties destinationProperties) {
        MessageProperties result = new MessageProperties();
        int row = destinationProperties.size();
        int col = sourceProperties.size();
        while (row != 0 || col != 0) {
            if (col > 0 && row > 0 && lcs[col-1][row-1] +1 == lcs[col][row] && sourceProperties.get(col-1).equals(destinationProperties.get(row-1))) { // line was not modified
                col--;
                row--;
                result.addLineInFront(destinationProperties.get(row));
            } else if (col > 0 && lcs[col-1][row] == lcs[col][row]) { // line was added
                col--;
                Line oldLine = destinationProperties.getLineWithKey(((KeyValueLine) sourceProperties.get(col)).getKey());
                if (oldLine != null) {
                    result.addLineInFront(oldLine);
                } else {
                    result.addLineInFront(sourceProperties.get(col).addTodoMessage(todoMessage));
                }
            } else { // line was deleted
                row--;
            }
        }
        return result;
    }
}
