package raf.classycraft.app.core;

import raf.classycraft.app.model.simpleFactory.ConsoleLogger;
import raf.classycraft.app.model.simpleFactory.FileLogger;
import raf.classycraft.app.model.messageGenerator.MessageGenerator;
import raf.classycraft.app.model.simpleFactory.LoggerFactory;
import raf.classycraft.app.view.MainFrame;

public class ApplicationFramework {

    private static ApplicationFramework instance;
    private ClassyRepository classyRepository;

    private MessageGenerator messageGenerator;

    private LoggerFactory loggerFactory;

    private ApplicationFramework(){

    }

    public void initialize(){

        MainFrame.getInstance().setVisible(true);
        classyRepository = new ClassyRepositoryImplemention();
        loggerFactory = new LoggerFactory();

    }

    public static ApplicationFramework getInstance(){
        if(instance == null){
            instance = new ApplicationFramework();
        }
        return instance;
    }

    public MessageGenerator getMessageGenerator() {
        return messageGenerator;
    }
}
