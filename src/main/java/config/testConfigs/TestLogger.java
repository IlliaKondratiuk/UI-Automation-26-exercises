package config.testConfigs;

import org.junit.jupiter.api.extension.AfterTestExecutionCallback;
import org.junit.jupiter.api.extension.BeforeTestExecutionCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestWatcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

public class TestLogger implements TestWatcher, BeforeTestExecutionCallback, AfterTestExecutionCallback {

    private static final Logger logger = LoggerFactory.getLogger(TestLogger.class);

    @Override
    public void testDisabled(ExtensionContext context, Optional<String> reason) {
        logger.warn("🚫 Test disabled: {}. Reason: {}", context.getDisplayName(), reason.orElse("No reason provided"));
    }

    @Override
    public void testSuccessful(ExtensionContext context) {
        logger.info("✅ Test passed: {}", context.getDisplayName());
    }

    @Override
    public void testFailed(ExtensionContext context, Throwable cause) {
        logger.error("❌ Test failed: {}. Reason: {}", context.getDisplayName(), cause.toString());
    }

    @Override
    public void testAborted(ExtensionContext context, Throwable cause) {
        logger.warn("⚠️ Test aborted: {}. Reason: {}", context.getDisplayName(), cause.toString());
    }

    @Override
    public void beforeTestExecution(ExtensionContext context) throws Exception {
        logger.info("✅ Test started: {}", context.getDisplayName());
    }

    @Override
    public void afterTestExecution(ExtensionContext context) throws Exception {
        logger.info("✅ Test ended: {}", context.getDisplayName());
    }
}
