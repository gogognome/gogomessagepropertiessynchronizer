package nl.gogognome.messagepropertiessynchronizer;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class CommentLineTest {

    private CommentLine line1 = new CommentLine("# comment 1");
    private CommentLine line2 = new CommentLine("# ");
    private CommentLine line3 = new CommentLine("#");

    @Test
    public void testGetOriginalLine() {
        assertEquals("# comment 1", line1.getOriginalLine());
        assertEquals("# ", line2.getOriginalLine());
        assertEquals("#", line3.getOriginalLine());
    }

    @Test
    public void testAddTodoMessage() {
        assertEquals(line1.getOriginalLine(), line1.addTodoMessage("<TODO TRANSLATE>").getOriginalLine());
        assertEquals(line2.getOriginalLine(), line2.addTodoMessage("<TODO TRANSLATE>").getOriginalLine());
        assertEquals(line3.getOriginalLine(), line3.addTodoMessage("<TODO TRANSLATE>").getOriginalLine());
    }

    @Test
    public void testEquals() {
        assertEquals(line1, line1);
        assertEquals(line2, line2);
        assertEquals(line3, line3);
        assertEquals(line1, line2);
        assertEquals(line1, line3);
        assertEquals(line2, line3);
        assertNotEquals(line1, new KeyValueLine(line1.getOriginalLine() + "=" + line1.getOriginalLine()));
        assertNotEquals(line1, new NonKeyValueLine(line1.getOriginalLine()));
    }

    @Test
    public void testHashCode() {
        assertEquals(line1.hashCode(), new CommentLine(line1.getOriginalLine()).hashCode());
        assertEquals(line1.hashCode(), line2.hashCode());
        assertEquals(line1.hashCode(), line3.hashCode());
        assertEquals(line2.hashCode(), line3.hashCode());
    }

    @Test
    public void testToString() {
        assertEquals("# comment 1", line1.toString());
        assertEquals("# ", line2.toString());
    }

}
