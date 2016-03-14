package nl.gogognome.messagepropertiessynchronizer;

import org.junit.Assert;

public class KeyValueLineTest {

    public void testEquals() {
        KeyValueLine firstnameEn = new KeyValueLine("firstname", "Firstname");
        KeyValueLine firstnameNl = new KeyValueLine("firstname", "Voornaam");
        KeyValueLine cityEn = new KeyValueLine("city", "City");

        Assert.assertEquals(firstnameEn, firstnameEn);
        Assert.assertEquals(firstnameEn, firstnameNl);
        Assert.assertNotEquals(firstnameEn, cityEn);
        Assert.assertNotEquals(firstnameEn, new NonKeyValueLine(firstnameEn.getKey()));
        Assert.assertNotEquals(firstnameEn, new NonKeyValueLine(firstnameEn.getValue()));
    }
}
