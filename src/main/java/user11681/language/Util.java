package user11681.language;

@SuppressWarnings("unchecked")
public class Util {
    public static void handle(ThrowingRunnable runnable) {
        try {
            runnable.execute();
        } catch (Throwable throwable) {
            throw rethrow(throwable);
        }
    }

    public static <T> void handle(T argument, ThrowingConsumer<T> consumer) {
        try {
            consumer.execute(argument);
        } catch (Throwable throwable) {
            throw rethrow(throwable);
        }
    }

    public static RuntimeException rethrow(Throwable throwable) {
        return rethrowInternal(throwable);
    }

    private static <T extends Throwable> RuntimeException rethrowInternal(Throwable throwable) throws T {
        throw (T) throwable;
    }
}
