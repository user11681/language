package user11681.language;

import java.util.function.Consumer;

public interface ThrowingConsumer<T> extends Consumer<T> {
    void execute(T argument) throws Throwable;

    @Override
    default void accept(T argument) {
        Util.handle(argument, this);
    }
}
