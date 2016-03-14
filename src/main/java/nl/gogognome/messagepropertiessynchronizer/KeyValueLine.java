package nl.gogognome.messagepropertiessynchronizer;

public class KeyValueLine implements Line {

    private final String key;
    private final String value;

    public KeyValueLine(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }

    @Override
    public boolean equals(Line that) {
        if (that instanceof KeyValueLine) {
            KeyValueLine thatKeyValueProperty = (KeyValueLine) that;
            return this.key.equals(thatKeyValueProperty.key);
        }
        return false;
    }
}
