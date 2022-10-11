package Picsart;

import lombok.extern.slf4j.Slf4j;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;

import java.util.concurrent.atomic.AtomicLong;

@Slf4j
public class TestWatcherRule extends TestWatcher {

    private static final String GREEN_CIRCLE = "\uD83D\uDFE2";
    private static final String RED_CIRCLE = "\uD83D\uDD34";

    private static final AtomicLong finishedTestsCount = new AtomicLong();

    private long started;

    @Override
    protected void starting(Description description) {
        log.info("Starting test {}", bold(description.getMethodName()));
        started = System.currentTimeMillis();
    }

    @Override
    protected void succeeded(Description description) {
        long finished = System.currentTimeMillis();
        log.info(
                "{} Test {} succeeded after {} ms",
                GREEN_CIRCLE,
                bold(description.getMethodName()),
                finished - started);
    }

    @Override
    protected void failed(Throwable e, Description description) {
        long finished = System.currentTimeMillis();
        log.info(
                "{} Test {} failed after {} ms",
                RED_CIRCLE,
                bold(description.getMethodName()),
                finished - started);
    }

    @Override
    protected void finished(Description description) {
        log.info("Finished tests count: {}", finishedTestsCount.incrementAndGet());
    }

    private String bold(String text) {
        return "\033[1m" + text + "\033[0m";
    }

}
