package nl.gogognome.messagepropertiessynchronizer;

public class NonKeyValueLine implements Line {

    private final String line;

    public NonKeyValueLine(String line) {
        this.line = line;
    }

    @Override
    public boolean equals(Line that) {
        if (that instanceof NonKeyValueLine) {
            NonKeyValueLine thatNonKeyValueProperty = (NonKeyValueLine) that;
            return this.line.equals(thatNonKeyValueProperty.line);
        }
        return false;
    }
}
