package logger;

import org.apache.log4j.*;

import java.io.IOException;

public class FileConfigurator {

    private static final String Training = "src/main/java/output/training.out";
    private static final String Matches = "src/main/java/output/matches.out";

    public static void configureTraining() throws IOException {
        Logger logRoot = Logger.getRootLogger();

        PatternLayout layout = new PatternLayout("%m%n");
        FileAppender appender = new FileAppender(
                layout,
                Training,
                true,
                false,
                8192);
        logRoot.addAppender(appender);
    }

    public static void configureMatches() throws IOException {
        Logger logRoot = Logger.getRootLogger();

        PatternLayout layout = new PatternLayout("%m%n");
        FileAppender appender = new FileAppender(
                layout,
                Matches,
                true,
                false,
                8192);
        logRoot.addAppender(appender);
    }
}
