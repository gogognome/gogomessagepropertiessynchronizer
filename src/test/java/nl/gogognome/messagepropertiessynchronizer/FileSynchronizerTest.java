package nl.gogognome.messagepropertiessynchronizer;

import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.junit.Assert.assertEquals;

public class FileSynchronizerTest {

    private FileSynchronizer fileSynchronizer = new FileSynchronizer("<TODO TRANSLATE>");

    @Test(expected = IOException.class)
    public void testSynchronize_sourceDoesNotExist_destinationDoesNotExist() throws Exception {
        File source = createFile(null);
        File destination = createFile(null);

        try {
            fileSynchronizer.synchronize(source, destination);

            Files.readAllLines(destination.toPath());
        } finally {
            source.delete();
            destination.delete();
        }
    }

    @Test
    public void testSynchronize_sourceExists_destinationDoesNotExist() throws Exception {
        List<String> sourceLines = Arrays.asList("a=A", "b=B");
        File source = createFile(sourceLines);
        File destination = createFile(null);

        try {
            fileSynchronizer.synchronize(source, destination);

            List<String> resultLines = Files.readAllLines(destination.toPath());

            assertEquals(Arrays.asList("a=<TODO TRANSLATE>A", "b=<TODO TRANSLATE>B"), resultLines);
        } finally {
            source.delete();
            destination.delete();
        }
    }

    @Test
    public void testSynchronize_sourceExists_destinationExists() throws Exception {
        List<String> sourceLines = Arrays.asList("a=A", "b=B");
        File source = createFile(sourceLines);
        List<String> destinationLines = Arrays.asList("a=A", "c=C");
        File destination = createFile(destinationLines);

        try {
            fileSynchronizer.synchronize(source, destination);

            List<String> resultLines = Files.readAllLines(destination.toPath());

            assertEquals(Arrays.asList("a=A", "b=<TODO TRANSLATE>B"), resultLines);
        } finally {
            source.delete();
            destination.delete();
        }
    }

    private File createFile(List<String> lines) throws IOException {
        File file = File.createTempFile("test-" + UUID.randomUUID(), ".properties");
        if (lines != null) {
            Files.write(file.toPath(), lines);
        } else {
            file.delete();
        }
        return file;
    }

    @Test
    public void testDetermineLcsWithEmptyProperties() throws Exception {
        MessageProperties properties1 = new MessageProperties();
        MessageProperties properties2 = new MessageProperties();

        int[][] lcs = fileSynchronizer.determineLcs(properties1, properties2);
        assertEquals("[0]", formatLcs(lcs));
    }

    @Test
    public void testDetermineLcsWithABDandBAD() throws Exception {
        MessageProperties properties1 = new MessageProperties();
        properties1.addLine("A");
        properties1.addLine("B");
        properties1.addLine("D");

        MessageProperties properties2 = new MessageProperties();
        properties2.addLine("B");
        properties2.addLine("A");
        properties2.addLine("D");

        int[][] lcs = fileSynchronizer.determineLcs(properties1, properties2);
        assertEquals(
                "[0, 0, 0, 0], " +
                "[0, 0, 1, 1], " +
                "[0, 1, 1, 1], " +
                "[0, 1, 1, 2]",
                formatLcs(lcs));
    }

    private String formatLcs(int[][] lcs) {
        StringBuilder sb = new StringBuilder();
        for (int[] row : lcs) {
            if (sb.length() > 0) {
                sb.append(", ");
            }
            sb.append(Arrays.toString(row));
        }
        return sb.toString();
    }

    @Test
    public void buildResultingMessageProperties() {
        MessageProperties properties1 = new MessageProperties();
        properties1.addLine("A=Message A");
        properties1.addLine("B=Message B");
        properties1.addLine("# Comment 1");
        properties1.addLine("D=Message D");
        properties1.addLine("E = Message E");

        MessageProperties properties2 = new MessageProperties();
        properties2.addLine("B=Bericht B");
        properties2.addLine("# Comment 1");
        properties2.addLine("D = Bericht D");
        properties2.addLine("F= Bericht F");

        int[][] lcs = fileSynchronizer.determineLcs(properties1, properties2);
        MessageProperties result = fileSynchronizer.buildResultingMessageProperties(lcs, properties1, properties2);

        assertEquals("A=<TODO TRANSLATE>Message A, B=Bericht B, # Comment 1, D = Bericht D, E = <TODO TRANSLATE>Message E", result.toString());
    }
}