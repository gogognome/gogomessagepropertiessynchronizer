package nl.gogognome.messagepropertiessynchronizer;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class TwoFileSynchronizer {

    public void synchronize(File source, File destination) throws IOException {
        MessageProperties sourceProperties = new MessageProperties();
        MessageProperties destinationProperties = new MessageProperties();
        try (InputStream sourceInputStream = new FileInputStream(source);
             InputStream destinationInputStream = new FileInputStream(destination)) {
            sourceProperties.parse(sourceInputStream);
            destinationProperties.parse(destinationInputStream);
        }

        int[][] lcs = new int[sourceProperties.size()+1][destinationProperties.size()+1];
        for (int row=1; row<destinationProperties.size()+1; row++) {
            for (int col=1; col<sourceProperties.size()+1; col++) {
                if (sourceProperties.get(col).equals(destinationProperties.get(row))) {
                    lcs[col][row] = 1 + lcs[col-1][row-1];
                } else {
                    lcs[col][row] = Math.max(lcs[col-1][row], lcs[col][row-1]);
                }
            }
        }
    }
}
