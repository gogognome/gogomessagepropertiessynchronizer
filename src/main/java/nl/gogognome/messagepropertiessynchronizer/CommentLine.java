package nl.gogognome.messagepropertiessynchronizer;

public class CommentLine implements Line{

    private final String line;

    public CommentLine(String line) {
        this.line = line;
    }

    @Override
    public String getOriginalLine() {
        return line;
    }

    @Override
    public Line addTodoMessage(String todoMessage) {
        return this;
    }

    @Override
    public boolean equals(Object object) {
        return object instanceof CommentLine;
    }

    @Override
    public int hashCode() {
        return 83;
    }

    @Override
    public String toString() {
        return line;
    }

}
