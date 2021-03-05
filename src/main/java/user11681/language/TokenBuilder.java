package user11681.language;

import java.util.ArrayList;
import java.util.List;

public class TokenBuilder {
    protected final List<String> tokens = new ArrayList<>();

    protected StringBuilder builder = new StringBuilder();
    protected String string;

    @Override
    public String toString() {
        return this.string == null ? this.string = this.builder.toString() : this.string;
    }

    public boolean empty() {
        return this.builder.isEmpty();
    }

    public TokenBuilder append(char character) {
        this.builder.append(character);

        this.string = null;

        return this;
    }

    public void add(String token) {
        this.tokens.add(token);
    }

    public TokenBuilder next() {
        if (!this.empty()) {
            this.tokens.add(this.toString());
            this.builder = new StringBuilder();

            this.string = null;
        }

        return this;
    }
}
