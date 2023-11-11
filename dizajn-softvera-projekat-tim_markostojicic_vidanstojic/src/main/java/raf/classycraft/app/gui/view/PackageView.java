package raf.classycraft.app.gui.view;

import raf.classycraft.app.model.compositeAbstract.ClassyNode;
import raf.classycraft.app.model.compositeImplement.Project;
import raf.classycraft.app.observer.ISubscriber;
import raf.classycraft.app.observer.NotificationTree;
import raf.classycraft.app.observer.TreeNotificationType;

import javax.swing.*;
import java.awt.*;

public class PackageView extends JPanel implements ISubscriber {

    private JTabbedPane tabbedPane;
    private Label author;
    private Label nameOfProject;

    public static boolean flag = false;

    public PackageView(){

        tabbedPaneInit();
    }

    public void tabbedPaneInit(){
        this.tabbedPane = new JTabbedPane();
        this.setLayout(new BorderLayout());
        add(tabbedPane, BorderLayout.CENTER);

    }

    public void addTab(String title, Component component ) {
        tabbedPane.addTab(title, component);
    }

    public void removeTab(ClassyNode child){
        int indexOfTabRemove = tabbedPane.indexOfTab(child.getName());
        tabbedPane.removeTabAt(indexOfTabRemove);
    }

    public JTabbedPane getTabbedPane() {
        return tabbedPane;
    }

    public Label getAuthor() {
        return author;
    }

    public Label getNameOfProject() {
        return nameOfProject;
    }

    @Override
    public void update(Object notify) {
        if(flag == false){
            return;
        }
        NotificationTree notificationTree = (NotificationTree) notify;
        ClassyNode child = notificationTree.getClassyNode();
        TreeNotificationType typeNotify = notificationTree.getTreeNotificationType();


        if(typeNotify == TreeNotificationType.ADDED_CHILD){
            if( child.getParent().getParent() != null && child.getParent().getParent() instanceof Project){
                Project project = (Project) child.getParent().getParent();
                addTab(child.getName(), new DiagramView(project.getName(), project.getAuthor()));
            }

        }
        else if(typeNotify == TreeNotificationType.DELETED_CHILD){
            removeTab(child);
        }
    }

    public static boolean isFlag() {
        return flag;
    }

    public static void setFlag(boolean flag) {
        PackageView.flag = flag;
    }
}
