package raf.classycraft.app.core;

import com.sun.tools.javac.Main;
import raf.classycraft.app.model.CompositeImplement.ProjectExplorer;
import raf.classycraft.app.view.MainFrame;

public class ApplicationFramework {

    private static ApplicationFramework instance;
    private ClassyRepository classyRepository;

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
