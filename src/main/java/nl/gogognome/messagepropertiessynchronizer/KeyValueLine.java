package nl.gogognome.messagepropertiessynchronizer;

public class KeyValueLine implements Line {

    private final String line;
    private final String key;
    private final int todoInsertionIndex;

    public KeyValueLine(String line) {
        this.line = line;
        int separatorIndex = line.indexOf('=');
        if (separatorIndex == -1) {
            throw new IllegalArgumentException("Line does not contain a '='. A KeyValueLine must contain a  '='");
        }
        key = line.substring(0, separatorIndex).trim();
        todoInsertionIndex = line.lastIndexOf(line.substring(separatorIndex+1).trim());
    }

    @Override
    public String getOriginalLine() {
        return line;
    }

    @Override
    public Line addTodoMessage() {
        return new KeyValueLine(line.substring(0, todoInsertionIndex) + "<TODO TRANSLATE>" + line.substring(todoInsertionIndex));
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

    @Override
    public String toString() {
        return line;
    }
}
