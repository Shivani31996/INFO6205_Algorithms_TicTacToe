package logger;

import org.apache.log4j.Logger;

import java.io.IOException;

public class LogManager {

    /* Get actual class name to be printed on */
    private static Logger log = Logger.getLogger(LogManager.class.getName());
    private static Logger log1 = Logger.getLogger(LogManager.class.getName());

    public static void logTraining(String result) throws IOException {
        FileConfigurator.configureTraining();
        log.info(result);
    }

    public static void logMatches(String result) throws IOException {
        FileConfigurator.configureMatches();
        log1.info(result);
    }
}
