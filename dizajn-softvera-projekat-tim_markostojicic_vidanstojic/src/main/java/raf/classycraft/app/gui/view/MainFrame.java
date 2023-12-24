package raf.classycraft.app.gui.view;

import raf.classycraft.app.gui.controller.ActionManager;
import raf.classycraft.app.core.ApplicationFramework;
import raf.classycraft.app.gui.tree.ClassyTree;
import raf.classycraft.app.gui.tree.ClassyTreeImplementation;
import raf.classycraft.app.observer.ISubscriber;
import raf.classycraft.app.observer.NotificationMessageGenerator;

import javax.swing.*;
import java.awt.*;


public class MainFrame extends JFrame implements ISubscriber {
    private static MainFrame instance;
    private ActionManager actionManager;

    private ClassyTree classyTree;

    private PackageView packageView;





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

        JScrollPane scroll=new JScrollPane(projectExplorer);
        scroll.setMinimumSize(new Dimension(200,150));
        JSplitPane split=new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,scroll, desktop);
        getContentPane().add(split,BorderLayout.CENTER);
        split.setDividerLocation(250);
        split.setOneTouchExpandable(true);


        this.packageView = new PackageView();
        split.setRightComponent(new JScrollPane(packageView));
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
    public void update(Object notify) {

        NotificationMessageGenerator notification = (NotificationMessageGenerator) notify;
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

    public DiagramView getCurrentDiagramView() {

        Component selectedComponent = packageView.getTabbedPane().getSelectedComponent();
        if (selectedComponent instanceof JScrollPane) {
            JScrollPane scrollPane = (JScrollPane) selectedComponent;
            Component view = scrollPane.getViewport().getView();
            if (view instanceof DiagramView) {
                DiagramView currentDiagramView = (DiagramView) view;
                System.out.println(currentDiagramView.getDiagram().getName());
                return currentDiagramView;
            }
        }

        return null;
    }



    public ClassyTree getClassyTree() {
        return classyTree;
    }

    public PackageView getPackageView() {
        return packageView;
    }

    public void setPackageView(PackageView packageView) {
        this.packageView = packageView;
    }






}
