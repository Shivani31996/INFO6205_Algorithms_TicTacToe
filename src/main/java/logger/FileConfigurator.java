package logger;

import org.apache.log4j.*;

import java.io.IOException;

public class FileConfigurator {

    private static final String FILE_NAME = "src/main/java/output/log.out";

    public static void configure() throws IOException {
        Logger logRoot = Logger.getRootLogger();

        PatternLayout layout = new PatternLayout("%m%n");
        FileAppender appender = new FileAppender(
                layout,
                FILE_NAME,
                true,
                false,
                8192);
        logRoot.addAppender(appender);
    }
}
