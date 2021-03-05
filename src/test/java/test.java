import org.junit.jupiter.api.Test;
import org.junit.platform.commons.annotation.Testable;
import user11681.language.Parser;

@Testable
class test {
    @Test
    void test() throws Throwable {
        Parser.main("test.lang");
    }
}
