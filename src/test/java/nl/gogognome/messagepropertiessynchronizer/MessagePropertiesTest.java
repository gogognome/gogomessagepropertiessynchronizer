package nl.gogognome.messagepropertiessynchronizer;

import org.junit.Test;

import java.io.ByteArrayInputStream;

import static org.junit.Assert.assertEquals;

public class MessagePropertiesTest {

    private MessageProperties messageProperties = new MessageProperties();

    @Test
    public void parseEmptyInputStream() throws Exception {
        messageProperties.parse(new ByteArrayInputStream("".getBytes("ISO-8859-1")));
        assertEquals(0,  messageProperties.size());
    }

    @Test
    public void parseSingleKeyValuePair() throws Exception {
        messageProperties.parse(new ByteArrayInputStream("name=Naam".getBytes("ISO-8859-1")));
        assertEquals(1, messageProperties.size());

        Line line = messageProperties.get(0);
        assertEquals(KeyValueLine.class, line.getClass());

        assertEquals("name", ((KeyValueLine) line).getKey());
        assertEquals("Naam", ((KeyValueLine) line).getValue());
    }

    @Test
    public void parseSingleKeyValuePairShouldTrimKeyAndValue() throws Exception {
        messageProperties.parse(new ByteArrayInputStream("  name  =  Naam  ".getBytes("ISO-8859-1")));

        Line line = messageProperties.get(0);

        assertEquals("name", ((KeyValueLine) line).getKey());
        assertEquals("Naam", ((KeyValueLine) line).getValue());
    }

    @Test
    public void parseSingleCommentLine() throws Exception {
        messageProperties.parse(new ByteArrayInputStream("# No comments please!".getBytes("ISO-8859-1")));
        assertEquals(1, messageProperties.size());

        Line line = messageProperties.get(0);
        assertEquals(NonKeyValueLine.class, line.getClass());

        assertEquals("# No comments please!", ((NonKeyValueLine) line).getLine());
    }

    @Test
    public void parseSingleCommentLineShouldIgnoreWhitespacesInFront() throws Exception {
        messageProperties.parse(new ByteArrayInputStream("   # No comments please!".getBytes("ISO-8859-1")));
        assertEquals(1, messageProperties.size());

        Line line = messageProperties.get(0);
        assertEquals(NonKeyValueLine.class, line.getClass());

        assertEquals("   # No comments please!", ((NonKeyValueLine) line).getLine());
    }

    @Test
    public void parseSingleEmptyLine() throws Exception {
        messageProperties.parse(new ByteArrayInputStream("\n".getBytes("ISO-8859-1"))); // newline needed, because otherwise zero lines are returned
        assertEquals(1, messageProperties.size());

        Line line = messageProperties.get(0);
        assertEquals(NonKeyValueLine.class, line.getClass());

        assertEquals("", ((NonKeyValueLine) line).getLine());
    }

    @Test
    public void parseSingleLineWithWhiteSpacesOnly() throws Exception {
        messageProperties.parse(new ByteArrayInputStream("\t  \t".getBytes("ISO-8859-1")));
        assertEquals(1, messageProperties.size());

        Line line = messageProperties.get(0);
        assertEquals(NonKeyValueLine.class, line.getClass());

        assertEquals("\t  \t", ((NonKeyValueLine) line).getLine());
    }

    @Test
    public void parseSingleLineWithoutKeyValuePair() throws Exception {
        messageProperties.parse(new ByteArrayInputStream("This is a test.".getBytes("ISO-8859-1")));
        assertEquals(1, messageProperties.size());

        Line line = messageProperties.get(0);
        assertEquals(NonKeyValueLine.class, line.getClass());

        assertEquals("This is a test.", ((NonKeyValueLine) line).getLine());
    }
}
