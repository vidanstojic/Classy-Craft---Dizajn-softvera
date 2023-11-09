package raf.classycraft.app.gui.view;

import raf.classycraft.app.gui.controller.ActionManager;
import raf.classycraft.app.core.ApplicationFramework;
import raf.classycraft.app.gui.tree.ClassyTree;
import raf.classycraft.app.gui.tree.ClassyTreeImplementation;
import raf.classycraft.app.observer.ISubscriber;
import raf.classycraft.app.observer.Notification;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class MainFrame extends JFrame implements ISubscriber {
    private static MainFrame instance;
    private ActionManager actionManager;

    private ClassyTree classyTree;



    // buduca polja za glavni view

    private MainFrame(){

    }

    private void initialize(){
        actionManager = new ActionManager();
        classyTree = new ClassyTreeImplementation();

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



        JTree projectExplorer = classyTree.generateTree(ApplicationFramework.getInstance().getClassyRepository().getRoot());
        JPanel desktop = new JPanel();

        JMenuBar proba = new JMenuBar();
        JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP, JTabbedPane.SCROLL_TAB_LAYOUT);
        JPanel tabbed1 = new JPanel();
        tabbedPane.addTab("Tab1",tabbed1);
        JPanel tabbe2 = new JPanel();
        tabbedPane.addTab("Tab2", tabbe2);
        proba.add(tabbedPane);

        JScrollPane scroll=new JScrollPane(projectExplorer);
        scroll.setMinimumSize(new Dimension(200,150));
        JSplitPane split=new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,scroll,proba);
        getContentPane().add(split,BorderLayout.CENTER);
        split.setDividerLocation(250);
        split.setOneTouchExpandable(true);

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
        //  JOptionPane.showMessageDialog(null, "Uh-oh!", "Error", JOptionPane.ERROR_MESSAGE);
        // uh oh je tekst, a error je naslov
        if(notification.getType() == raf.classycraft.app.model.messageGenerator.Type.ERROR){
            JOptionPane.showMessageDialog(null,notification.getMessageText(), notification.getType().toString(), JOptionPane.ERROR_MESSAGE);
        }
        else if(notification.getType() == raf.classycraft.app.model.messageGenerator.Type.WARNING){
            JOptionPane.showMessageDialog(null,notification.getMessageText(), notification.getType().toString(), JOptionPane.WARNING_MESSAGE);
        }
        else if(notification.getType() == raf.classycraft.app.model.messageGenerator.Type.INFORMATION){
            JOptionPane.showMessageDialog(null,notification.getMessageText(), notification.getType().toString(), JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public ClassyTree getClassyTree() {
        return classyTree;
    }
}
