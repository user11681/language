package user11681.language;

public class CompoundResult {
    public final String compound;
    public final boolean equal;

    public CompoundResult(String compound, boolean equal) {
        this.compound = compound;
        this.equal = equal;
    }

    @Override
    public String toString() {
        return this.compound;
    }
}
