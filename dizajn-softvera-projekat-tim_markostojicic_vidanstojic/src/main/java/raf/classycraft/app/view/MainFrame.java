package raf.classycraft.app.view;

import raf.classycraft.app.controller.ActionManager;
import raf.classycraft.app.core.ApplicationFramework;
import raf.classycraft.app.model.messageGenerator.MessageGenerator;
import raf.classycraft.app.observer.ISubscriber;
import raf.classycraft.app.observer.Notification;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame implements ISubscriber {
    private static MainFrame instance;
    private ActionManager actionManager;



    // buduca polja za glavni view

    private MainFrame(){

    }

    private void initialize(){
        actionManager = new ActionManager();


        ApplicationFramework.getInstance().getMessageGenerator().addSubscriber(this);




        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        int screenHeight = screenSize.height;
        int screenWidth = screenSize.width;
        setSize(screenWidth / 2, screenHeight / 2);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("ClassyCrafT");

        MyMenyBar menu = new MyMenyBar();
        setJMenuBar(menu);

        MyToolBar toolBar = new MyToolBar();
        add(toolBar,BorderLayout.NORTH);


    }

    public static MainFrame getInstance(){
        if(instance == null){
            instance = new MainFrame();
            instance.initialize();
        }
        return instance;
    }

    public ActionManager getActionManager() {
        return actionManager;

    }


    @Override
    public void update(Notification notification) {
        // ovde treba JOptionPane
        JOptionPane optionPane = new JOptionPane();
    }
}
