package nl.gogognome.messagepropertiessynchronizer;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class KeyValueLineTest {

    private KeyValueLine firstnameEn = new KeyValueLine("firstname", "Firstname");
    private KeyValueLine firstnameNl = new KeyValueLine("firstname", "Voornaam");
    private KeyValueLine cityEn = new KeyValueLine("city", "City");

    @Test
    public void testEquals() {
        assertEquals(firstnameEn, firstnameEn);
        assertEquals(firstnameEn, firstnameNl);
        assertNotEquals(firstnameEn, cityEn);
        assertNotEquals(firstnameEn, new NonKeyValueLine(firstnameEn.getKey()));
        assertNotEquals(firstnameEn, new NonKeyValueLine(firstnameEn.getValue()));
    }

    @Test
    public void testHashCode() {
        assertEquals(firstnameEn.hashCode(), firstnameNl.hashCode());
        assertNotEquals(firstnameEn.hashCode(), cityEn.hashCode());
    }
}
