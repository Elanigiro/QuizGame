package fictional.quizfinal.utility;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Logging {

    public static void logDebug(String msg, String loggerName) {

        Logger lg = LoggerFactory.getLogger(loggerName);
        lg.debug(msg);
    }

    public static void logInfo(String msg, String loggerName) {

        Logger lg = LoggerFactory.getLogger(loggerName);
        lg.info(msg);
    }

    public static void logError(String error, String loggerName) {

        Logger lg = LoggerFactory.getLogger(loggerName);
        lg.error(error);
    }
}
