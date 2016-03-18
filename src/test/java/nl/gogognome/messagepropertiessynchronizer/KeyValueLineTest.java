package nl.gogognome.messagepropertiessynchronizer;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class KeyValueLineTest {

    private KeyValueLine firstnameEn = new KeyValueLine("firstname=Firstname");
    private KeyValueLine firstnameEnWithSpaces = new KeyValueLine("  firstname  =  Firstname  ");
    private KeyValueLine firstnameNl = new KeyValueLine("firstname = Voornaam");
    private KeyValueLine cityEn = new KeyValueLine("city=City");

    @Test(expected = IllegalArgumentException.class)
    public void constructWithoutEqualsSignShouldThrowException() {
        new KeyValueLine("asda");
    }

    @Test
    public void testGetOriginalLine() {
        assertEquals("firstname=Firstname", firstnameEn.getOriginalLine());
        assertEquals("  firstname  =  Firstname  ", firstnameEnWithSpaces.getOriginalLine());
    }

    @Test
    public void testAddTodoMessage() {
        assertEquals("firstname=<TODO TRANSLATE>Firstname", firstnameEn.addTodoMessage().getOriginalLine());
        assertEquals("  firstname  =  <TODO TRANSLATE>Firstname  ", firstnameEnWithSpaces.addTodoMessage().getOriginalLine());
    }

    @Test
    public void testEquals() {
        assertEquals(firstnameEn, firstnameEn);
        assertEquals(firstnameEn, firstnameEnWithSpaces);
        assertEquals(firstnameEn, firstnameNl);
        assertNotEquals(firstnameEn, cityEn);
        assertNotEquals(firstnameEn, new NonKeyValueLine(firstnameEn.getOriginalLine()));
    }

    @Test
    public void testHashCode() {
        assertEquals(firstnameEn.hashCode(), firstnameEnWithSpaces.hashCode());
        assertEquals(firstnameEn.hashCode(), firstnameNl.hashCode());
        assertNotEquals(firstnameEn.hashCode(), cityEn.hashCode());
    }
}
