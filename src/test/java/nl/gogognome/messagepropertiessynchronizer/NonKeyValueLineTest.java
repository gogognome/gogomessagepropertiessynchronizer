package nl.gogognome.messagepropertiessynchronizer;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class NonKeyValueLineTest {

    private NonKeyValueLine line1 = new NonKeyValueLine("# comment line");
    private NonKeyValueLine line2 = new NonKeyValueLine("   ");
    private NonKeyValueLine line3 = new NonKeyValueLine("");

    @Test
    public void testEquals() {
        assertEquals(line1, line1);
        assertEquals(line2, line2);
        assertEquals(line3, line3);
        assertNotEquals(line1, line2);
        assertNotEquals(line1, line3);
        assertNotEquals(line2, line3);
        assertNotEquals(line1, new KeyValueLine(line1.getLine(), line1.getLine()));
    }

    @Test
    public void testHashCode() {
        assertEquals(line1.hashCode(), new NonKeyValueLine(line1.getLine()).hashCode());
        assertNotEquals(line1.hashCode(), line2.hashCode());
        assertNotEquals(line1.hashCode(), line3.hashCode());
        assertNotEquals(line2.hashCode(), line3.hashCode());
    }
}
