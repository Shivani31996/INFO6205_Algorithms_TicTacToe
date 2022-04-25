package logger;

import org.apache.log4j.Logger;

import java.io.IOException;
import java.sql.SQLException;

public class LogManager {

    /* Get actual class name to be printed on */
    private static Logger log = Logger.getLogger(LogManager.class.getName());

    public static void logResult(String result) throws IOException {
        FileConfigurator.configure();
        log.info(result);
    }
}
