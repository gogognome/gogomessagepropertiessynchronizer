package nl.gogognome.messagepropertiessynchronizer;

public class NonKeyValueLine implements Line {

    private final String line;

    public NonKeyValueLine(String line) {
        this.line = line;
    }

    public String getLine() {
        return line;
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

}
