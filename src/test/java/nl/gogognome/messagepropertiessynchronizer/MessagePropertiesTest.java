package nl.gogognome.messagepropertiessynchronizer;

import nl.gogognome.messagespropertiessynchronizer.KeyValueLine;
import nl.gogognome.messagespropertiessynchronizer.Line;
import nl.gogognome.messagespropertiessynchronizer.MessageProperties;
import org.junit.Assert;
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
}
