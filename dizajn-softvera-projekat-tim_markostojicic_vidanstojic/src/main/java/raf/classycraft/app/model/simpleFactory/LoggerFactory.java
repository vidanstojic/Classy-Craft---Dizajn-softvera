package raf.classycraft.app.model.simpleFactory;

import raf.classycraft.app.core.ApplicationFramework;

import java.io.File;
import java.util.concurrent.ConcurrentSkipListSet;

public class LoggerFactory {

    public LoggerFactory(){

    }

    public Logger createLogger(String loggerType){
        if(loggerType.toLowerCase().equals("consolelogger")) {
            return new ConsoleLogger();
        } else if (loggerType.toLowerCase().equals("filelogger")) {
            return new FileLogger();
        }
        return null;
    }
}
