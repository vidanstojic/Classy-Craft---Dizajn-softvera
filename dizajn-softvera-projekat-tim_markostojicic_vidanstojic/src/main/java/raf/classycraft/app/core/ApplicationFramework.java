package raf.classycraft.app.core;

import raf.classycraft.app.model.simpleFactory.ConsoleLogger;
import raf.classycraft.app.model.simpleFactory.FileLogger;
import raf.classycraft.app.model.messageGenerator.MessageGeneratorImplementation;
import raf.classycraft.app.model.simpleFactory.LoggerFactory;
import raf.classycraft.app.gui.view.MainFrame;
import raf.classycraft.app.serializer.JacksonSerializer;
import raf.classycraft.app.serializer.Serializer;

public class ApplicationFramework {

    private static ApplicationFramework instance;
    private ClassyRepository classyRepository;

    private MessageGeneratorImplementation messageGenerator;

    private FileLogger fileLogger;
    private ConsoleLogger consoleLogger;

    private Serializer serializer;

    private ApplicationFramework(){

    }

    public void initialize(){
        classyRepository = new ClassyRepositoryImplemention();
        messageGenerator = new MessageGeneratorImplementation();
        MainFrame.getInstance().setVisible(true);

        LoggerFactory loggerFactory = new LoggerFactory();

        consoleLogger = (ConsoleLogger) loggerFactory.createLogger("consoleLogger");
        fileLogger = (FileLogger) loggerFactory.createLogger("fileLogger");
        serializer = new JacksonSerializer();

    }

    public static ApplicationFramework getInstance(){
        if(instance == null){
            instance = new ApplicationFramework();
        }
        return instance;
    }

    public MessageGeneratorImplementation getMessageGenerator() {
        return messageGenerator;
    }

    public ClassyRepository getClassyRepository() {
        return classyRepository;
    }

    public Serializer getSerializer() {
        return serializer;
    }

}
