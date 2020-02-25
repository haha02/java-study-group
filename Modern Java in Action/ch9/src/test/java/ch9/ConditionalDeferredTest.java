package ch9;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ConditionalDeferredTest {
  private final static Logger logger = Logger.getLogger(ConditionalDeferredTest.class.getName());

  @Test
  @DisplayName("Conditional deferred execution")
  void deferExecution() {
    String logMessage = "Info level is enabled";
    if (logger.isLoggable(Level.INFO))
      logger.info(logMessage);

    // the logging message is always evaluated, even if the logger isn’t enabled for the message level passed as an argument
    logger.log(Level.INFO, logMessage);

    logger.log(Level.INFO, () -> logMessage);
  }

}
