package user11681.language;

public class SyntaxError {
    public final String file;
    public final int line;
    public final int column;

    public SyntaxError(String file, int line, int column) {
        this.file = file;
        this.line = line;
        this.column = column;
    }
}
