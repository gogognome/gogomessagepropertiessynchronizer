package nl.gogognome.messagepropertiessynchronizer;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

public class TwoFileSynchronizerTest {

    private TwoFileSynchronizer twoFileSynchronizer = new TwoFileSynchronizer();

    @Test
    public void testDetermineLcsWithEmptyProperties() throws Exception {
        MessageProperties properties1 = new MessageProperties();
        MessageProperties properties2 = new MessageProperties();

        int[][] lcs = twoFileSynchronizer.determineLcs(properties1, properties2);
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

        int[][] lcs = twoFileSynchronizer.determineLcs(properties1, properties2);
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
}