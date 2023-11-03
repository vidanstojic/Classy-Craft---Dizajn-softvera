package raf.classycraft.app.model.simpleFactory;

import raf.classycraft.app.core.ApplicationFramework;

import java.io.File;
import java.util.concurrent.ConcurrentSkipListSet;

public class LoggerFactory {

    /// PROMENITI CEO LOGGER FACTORY
    private ConsoleLogger consoleLogger;
    private FileLogger fileLogger;

    public LoggerFactory(){
        init();
    }

    private void init(){
        consoleLogger = new ConsoleLogger();
        fileLogger = new FileLogger();
    }

}
