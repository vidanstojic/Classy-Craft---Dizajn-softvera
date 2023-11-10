package raf.classycraft.app.gui.view;

import com.sun.tools.javac.Main;
import raf.classycraft.app.model.compositeAbstract.ClassyNode;
import raf.classycraft.app.observer.ISubscriber;
import raf.classycraft.app.observer.ISubscriberView;
import raf.classycraft.app.observer.TreeNotification;

import javax.swing.*;
import java.awt.*;

public class PackageView extends JPanel implements ISubscriberView {

    private JTabbedPane tabbedPane;
    private Label author;
    private Label nameOfProject;

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
    public void update(ClassyNode child, TreeNotification typeNotify) {
     //   System.out.println("Update called with typeNotify: " + typeNotify);
        if(typeNotify == TreeNotification.ADDED_CHILD){
            addTab(child.getName(), new DiagramView());
        }
        else if(typeNotify == TreeNotification.DELETED_CHILD){
            removeTab(child);
        }
    }
}
