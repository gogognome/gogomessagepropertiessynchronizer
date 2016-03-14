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
    public boolean equals(Object object) {
        if (object instanceof KeyValueLine) {
            KeyValueLine that = (KeyValueLine) object;
            return this.key.equals(that.key);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return key.hashCode();
    }
}
