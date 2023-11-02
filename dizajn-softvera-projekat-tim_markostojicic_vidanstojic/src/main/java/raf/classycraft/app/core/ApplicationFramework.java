package raf.classycraft.app.core;

import raf.classycraft.app.model.simpleFactory.ConsoleLogger;
import raf.classycraft.app.model.simpleFactory.FileLogger;
import raf.classycraft.app.model.messageGenerator.MessageGenerator;
import raf.classycraft.app.view.MainFrame;

public class ApplicationFramework {

    private static ApplicationFramework instance;
    private ClassyRepository classyRepository;
    private ConsoleLogger consoleLogger;
    private FileLogger fileLogger;
    private MessageGenerator messageGenerator;

    private MainFrame mainFrame;


    private ApplicationFramework(){

    }

    public void initialize(){

        MainFrame.getInstance().setVisible(true);
        classyRepository = new ClassyRepositoryImplemention();
    }

    public static ApplicationFramework getInstance(){
        if(instance == null){
            instance = new ApplicationFramework();
        }
        return instance;
    }

}
