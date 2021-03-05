package user11681.language;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Parser {
    private static final String ID_PATTERN = "[A-Za-z_$](\\w|\\$)*";
    private static final String WHITESPACE_PATTERN = "[ \t\n\r]*";
    private static final String WHITESPACE = " \t\n\r";

    private static final Set<Character> idChars = new HashSet<>(IntStream.concat(IntStream.range('A', 'Z' + 1), IntStream.range('a', 'z' + 1)).mapToObj((int character) -> (char) character).collect(Collectors.toList())); static {
        idChars.add('_');
        idChars.add('$');
    }

    private static final Set<String> compound = new HashSet<>(Set.of(
        "...",
        "->",
        "==",
        "<=",
        ">=",
        "<=>",
        "//",
        "/*",
        "*/",
        "::"
    ));

    public static void main(String... args) throws Throwable {
        Map<String, String> contents = new HashMap<>();
        List<Thread> threads = new ArrayList<>();

        Arrays.stream(args).map((String file) -> new Thread((ThrowingRunnable) () -> {
            String content = Files.readString(Path.of(Parser.class.getClassLoader().getResource(file).toURI()));

            contents.put(file, content);

            tokenize(content);
        })).forEach((Thread thread) -> {
            thread.start();

            threads.add(thread);
        });

        threads.forEach((ThrowingConsumer<Thread>) Thread::join);
    }

    private static TokenBuilder tokenize(String contents) {
        TokenBuilder builder = new TokenBuilder();
        StringIterator iterator = new StringIterator(contents);

        for (char character : iterator) {
            String token = builder.toString();

            if (whitespace(character)) {
                builder.next();

                if (character == '\n') {
                    builder.add("\n");
                }
            } else {
                if (idChar(character)) {
                    if (!id(token)) {
                        builder.next();
                    }

                    builder.append(character);
                } else {
                    if (id(token)) {
                        builder.next();
                        builder.append(character);
                    } else {
                        token = builder.append(character).toString();

                        CompoundResult compound = compound(token);

                        if (compound == null || compound.equal) {
                            builder.next();
                        }
                    }
                }
            }
        }

        return builder;
    }

    private static boolean whitespace(char character) {
        return WHITESPACE.indexOf(character) != -1;
    }

    private static boolean idChar(char character) {
        return idChars.contains(character);
    }

    private static boolean id(String string) {
        return string.matches(ID_PATTERN);
    }

    private static CompoundResult compound(String token) {
        if (compound.contains(token)) {
            return new CompoundResult(token, true);
        }

        for (String compound : compound) {
            if (compound.startsWith(token)) {
                return new CompoundResult(token, false);
            }
        }

        return null;
    }
}
