package pl.grabinski.slayer.util.performance;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.rule.OutputCapture;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource(properties = {"log.selected.method.execution.times=true",
        "logging.level.pl.grabinski.slayer.util.performance=TRACE"})
public class PerformanceMonitorTest {

    @Autowired
    private Foo foo;

    @Rule
    public OutputCapture capture = new OutputCapture();

    @Test
    public void logMethodExecutionTime() throws Exception {
        assertEquals("foo", foo.foo());
        assertTrue(capture.toString().matches("(?s).*Execution of .*Foo.foo\\(\\) took \\d+ ms.*"));
    }

    static class Foo {

        @LogExecutionTime
        String foo() {
            return "foo";
        }
    }

    @TestConfiguration
    static class FooConfig {

        @Bean
        public Foo foo() {
            return new Foo();
        }
    }

}