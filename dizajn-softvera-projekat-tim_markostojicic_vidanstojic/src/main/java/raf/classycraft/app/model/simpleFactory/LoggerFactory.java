package raf.classycraft.app.model.simpleFactory;

import raf.classycraft.app.core.ApplicationFramework;

import java.io.File;
import java.util.concurrent.ConcurrentSkipListSet;

public class LoggerFactory {

    /// PROMENITI CEO LOGGER FACTORY
    private ConsoleLogger consoleLogger = new ConsoleLogger();
    private FileLogger fileLogger = new FileLogger();

    public LoggerFactory(){

    }

    public Logger loggerType(String loggerType){
        if(loggerType.toLowerCase().equals("consolelogger")) {
            return consoleLogger;
        } else if (loggerType.toLowerCase().equals("filelogger")) {
            return fileLogger;
        }
        return null;
    }
}
