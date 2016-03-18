package nl.gogognome.messagepropertiessynchronizer;

public class NonKeyValueLine implements Line {

    private final String line;

    public NonKeyValueLine(String line) {
        this.line = line;
    }

    @Override
    public String getOriginalLine() {
        return line;
    }

    @Override
    public Line addTodoMessage() {
        return this;
    }

    @Override
    public boolean equals(Object object) {
        if (object instanceof NonKeyValueLine) {
            NonKeyValueLine that = (NonKeyValueLine) object;
            return this.line.equals(that.line);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return line.hashCode();
    }

    @Override
    public String toString() {
        return line;
    }
}
