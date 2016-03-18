package nl.gogognome.messagepropertiessynchronizer;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MessagePropertiesTest {

    private MessageProperties messageProperties = new MessageProperties();

    @Test
    public void initialMessagePropertiesHasNoLines() throws Exception {
        assertEquals(0, messageProperties.size());
    }

    @Test
    public void parseKeyValuePair() throws Exception {
        messageProperties.addLine("name=Naam");
        assertEquals(1, messageProperties.size());

        Line line = messageProperties.get(0);
        assertEquals(KeyValueLine.class, line.getClass());

        assertEquals("name=Naam", line.toString());
    }

    @Test
    public void parseCommentLine() throws Exception {
        messageProperties.addLine("# No comments please!");
        assertEquals(1, messageProperties.size());

        Line line = messageProperties.get(0);
        assertEquals(NonKeyValueLine.class, line.getClass());

        assertEquals("# No comments please!", ((NonKeyValueLine) line).getOriginalLine());
    }

    @Test
    public void parseCommentLineShouldIgnoreWhitespacesInFront() throws Exception {
        messageProperties.addLine("   # No comments please!");
        assertEquals(1, messageProperties.size());

        Line line = messageProperties.get(0);
        assertEquals(NonKeyValueLine.class, line.getClass());

        assertEquals("   # No comments please!", ((NonKeyValueLine) line).getOriginalLine());
    }

    @Test
    public void parseEmptyLine() throws Exception {
        messageProperties.addLine("");
        assertEquals(1, messageProperties.size());

        Line line = messageProperties.get(0);
        assertEquals(NonKeyValueLine.class, line.getClass());

        assertEquals("", ((NonKeyValueLine) line).getOriginalLine());
    }

    @Test
    public void parseLineWithWhiteSpacesOnly() throws Exception {
        messageProperties.addLine("\t  \t");
        assertEquals(1, messageProperties.size());

        Line line = messageProperties.get(0);
        assertEquals(NonKeyValueLine.class, line.getClass());

        assertEquals("\t  \t", ((NonKeyValueLine) line).getOriginalLine());
    }

    @Test
    public void parseLineWithoutKeyValuePair() throws Exception {
        messageProperties.addLine("This is a test.");
        assertEquals(1, messageProperties.size());

        Line line = messageProperties.get(0);
        assertEquals(NonKeyValueLine.class, line.getClass());

        assertEquals("This is a test.", ((NonKeyValueLine) line).getOriginalLine());
    }

    @Test
    public void parseMultipleLinesShouldStoreLinesInOrder() throws Exception {
        messageProperties.addLine("This is a test.");
        messageProperties.addLine("bla=Bla");
        messageProperties.addLine("# No comments please!");

        assertEquals(3, messageProperties.size());
        assertEquals("This is a test.", messageProperties.get(0).toString());
        assertEquals("bla=Bla", messageProperties.get(1).toString());
        assertEquals("# No comments please!", messageProperties.get(2).toString());
    }

    @Test
    public void testAddingLines() {
        messageProperties.addLine("A");
        messageProperties.addLine("B");
        messageProperties.addLineInFront(new NonKeyValueLine("C"));
        messageProperties.addLineInFront(new NonKeyValueLine("D"));
        messageProperties.addLine("E");

        assertEquals(5, messageProperties.size());
        assertEquals("D, C, A, B, E", messageProperties.toString());
    }
}
