package user11681.language;

public interface ThrowingRunnable extends Runnable {
    void execute() throws Throwable;

    @Override
    default void run() {
        Util.handle(this);
    }
}
