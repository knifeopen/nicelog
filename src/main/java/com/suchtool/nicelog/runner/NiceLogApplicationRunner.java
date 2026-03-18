package com.suchtool.nicelog.runner;

import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import com.suchtool.nicelog.enhance.logback.NiceLogLogbackAppender;
import com.suchtool.nicelog.property.NiceLogProperty;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;

@Slf4j
public class NiceLogApplicationRunner implements ApplicationRunner {
    private final NiceLogProperty niceLogProperty;

    public NiceLogApplicationRunner(NiceLogProperty niceLogProperty) {
        this.niceLogProperty = niceLogProperty;
    }

    @Override
    public void run(ApplicationArguments args) {
        try {
            log.info("nicelog runner run");

            if (Boolean.TRUE.equals(niceLogProperty.getLogbackEnabled())) {
                addLogbackAppender();
            }
        } catch (Throwable t) {
            System.err.println("nicelog runner error");
            t.printStackTrace();
        }
    }

    private void addLogbackAppender() {
        try {
            LoggerContext loggerContext = (LoggerContext) LoggerFactory.getILoggerFactory();
            NiceLogLogbackAppender appender = new NiceLogLogbackAppender(niceLogProperty);
            appender.setContext(loggerContext);
            appender.setName("NICE_LOG_LOGBACK_APPENDER");
            appender.start();

            Logger rootLogger = loggerContext.getLogger(Logger.ROOT_LOGGER_NAME);
            rootLogger.addAppender(appender);
        } catch (Throwable t) {
            System.err.println("nicelog addLogbackAppender error");
            t.printStackTrace();
        }
    }
}