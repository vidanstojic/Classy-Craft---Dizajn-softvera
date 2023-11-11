package raf.classycraft.app.gui.view;

import raf.classycraft.app.model.compositeAbstract.ClassyNode;
import raf.classycraft.app.model.compositeImplement.Diagrams;
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
        this.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        Label imeProjekta = new Label("Projekat");
        Label imeAutora = new Label("Mare autor");
        imeProjekta.setAlignment(Label.CENTER);
        imeAutora.setAlignment(Label.CENTER);

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0; // x osa kolona
        gbc.gridy = 0;// y osa red
        gbc.gridwidth = 1;// broj kolona koje zauzima
        gbc.weightx = 1.0;
        add(imeProjekta, gbc);

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.weightx = 1.0;
        add(imeAutora, gbc);

        tabbedPane = new JTabbedPane();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        add(tabbedPane, gbc);

    }

    public void addTab(String title, Component component ) {
        tabbedPane.addTab(title, component);
    }

    public void removeTab(ClassyNode child){
        int indexOfTabRemove = tabbedPane.indexOfTab(child.getName());
        if (indexOfTabRemove != -1) {
            tabbedPane.removeTabAt(indexOfTabRemove);
        }
    }

    public void changeTabTitle(ClassyNode child, String oldName){
        int indexOfTab = tabbedPane.indexOfTab(oldName);
        if (indexOfTab != -1) {
            tabbedPane.setTitleAt(indexOfTab, child.getName());
        }
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
        Diagrams child = null;
        if(notificationTree.getClassyNode() != null && notificationTree.getClassyNode() instanceof  Diagrams){
            child = (Diagrams) notificationTree.getClassyNode();
        }
        else{
            return;
        }
        TreeNotificationType typeNotify = notificationTree.getTreeNotificationType();


        if(typeNotify == TreeNotificationType.ADDED_CHILD){
            if( child.findProject() != null && child.findProject() instanceof Project){
                Project project = child.findProject();
                addTab(child.getName(), new DiagramView(project.getName(), project.getAuthor()));
            }

        }
        else if(typeNotify == TreeNotificationType.DELETED_CHILD){
            removeTab(child);
        }

        else if(typeNotify == TreeNotificationType.RENAMED_CHILD){
            changeTabTitle(child, notificationTree.getOldNameNode());
        }
    }

    public static boolean isFlag() {
        return flag;
    }

    public static void setFlag(boolean flag) {
        PackageView.flag = flag;
    }
}
