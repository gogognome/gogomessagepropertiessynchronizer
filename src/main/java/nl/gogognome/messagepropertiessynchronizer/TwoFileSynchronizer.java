package nl.gogognome.messagepropertiessynchronizer;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class TwoFileSynchronizer {

    public void synchronize(File source, File destination) throws IOException {
        MessageProperties sourceProperties = new MessageProperties();
        Files.lines(source.toPath()).forEach(sourceProperties::addLine);
        MessageProperties destinationProperties = new MessageProperties();
        Files.lines(destination.toPath()).forEach(destinationProperties::addLine);

        determineLcs(sourceProperties, destinationProperties);
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
}
