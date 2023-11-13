package raf.classycraft.app.gui.view;

import raf.classycraft.app.gui.tree.model.ClassyTreeItem;
import raf.classycraft.app.model.compositeAbstract.ClassyNode;
import raf.classycraft.app.model.compositeAbstract.ClassyNodeComposite;
import raf.classycraft.app.model.compositeImplement.Diagrams;
import raf.classycraft.app.model.compositeImplement.Package;
import raf.classycraft.app.model.compositeImplement.Project;
import raf.classycraft.app.observer.ISubscriber;
import raf.classycraft.app.observer.NotificationTree;
import raf.classycraft.app.observer.ProjectNotificationType;
import raf.classycraft.app.observer.TreeNotificationType;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class PackageView extends JPanel implements ISubscriber {

    private JTabbedPane tabbedPane;
    private List<String> stringTabs = new ArrayList<>();
    private Label author;
    private Label nameOfProject;
    private String nameP;
    private String nameA;

    public static boolean flag = false;

    public PackageView(){
        tabbedPaneInit();
    }


    public void tabbedPaneInit(){
        this.tabbedPane = new JTabbedPane();
        this.setLayout(new BorderLayout());
        this.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        nameOfProject = new Label();
        author = new Label();
        nameOfProject.setAlignment(Label.CENTER);
        author.setAlignment(Label.CENTER);

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0; // x osa kolona
        gbc.gridy = 0;// y osa red
        gbc.gridwidth = 1;// broj kolona koje zauzima
        gbc.weightx = 1.0;
        add(nameOfProject, gbc);

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.weightx = 1.0;
        add(author, gbc);

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
        if(stringTabs.contains(title)){
            return;
        }
        tabbedPane.addTab(title, component);
        this.stringTabs.add(title);
    }

    public void removeTab(ClassyNode child) {
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
        if(notify instanceof NotificationTree){
            if(flag == false)
                return;

            NotificationTree notificationTree = (NotificationTree) notify;
            Package parentP = null;
            Project parent = null;
            Diagrams child = null;
            if(notificationTree.getClassyNode() != null && notificationTree.getClassyNode() instanceof  Diagrams){
                child = (Diagrams) notificationTree.getClassyNode();
            }
            else if( !(notificationTree.getClassyNode() instanceof Package || notificationTree.getClassyNode() instanceof Project) ){
                return;
            }else if(notificationTree.getClassyNode() != null && notificationTree.getClassyNode() instanceof  Project){
                parent = (Project) notificationTree.getClassyNode();
            }else if (notificationTree.getClassyNode() != null && notificationTree.getClassyNode() instanceof  Package) {
                parentP = (Package) notificationTree.getClassyNode();
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

            else if(typeNotify == TreeNotificationType.PACKAGE_DELETED) {

                    for(ClassyNode classyNode: parentP.getChildren()){
                        if(classyNode instanceof Package){
                            System.out.println("MARE FEGET");
                            Package package1 = (Package) classyNode;
                            NotificationTree notificationTree1 = new NotificationTree(package1, TreeNotificationType.PACKAGE_DELETED);
                            update(notificationTree1);
                        }else {
                            removeTab(classyNode);
                            if(tabbedPane.getComponents() == null) {
                                return;
                            }
                        }

                    }

            }
            else if(typeNotify == TreeNotificationType.PROJECT_DELETED){

                for(ClassyNode classyNode: parent.getChildren()){
                    if(classyNode instanceof Package) {
                        Package p = (Package) classyNode;
                        for(ClassyNode classyNode1: p.getChildren()){
                            if(classyNode1 instanceof Package){
                                System.out.println("Vidan");
                                Package package1 = (Package) classyNode1;
                                for(ClassyNode classyNode2: package1.getChildren()){
                                    removeTab(classyNode2);
                                }
                            }
                            removeTab(classyNode1);
                        }
                    }
                }
            }
        }
        else if(notify instanceof ProjectNotificationType){
            ProjectNotificationType projectNotificationType = (ProjectNotificationType) notify;
            this.getAuthor().setText(projectNotificationType.getAuthor());
            this.getNameOfProject().setText(projectNotificationType.getName());
        }
    }

    public static boolean isFlag() {
        return flag;
    }

    public static void setFlag(boolean flag) {
        PackageView.flag = flag;
    }

    public String getNameP() {
        return nameP;
    }

    public void setNameP(String nameP) {
        this.nameP = nameP;
    }

    public String getNameA() {
        return nameA;
    }

    public void setNameA(String nameA) {
        this.nameA = nameA;
    }


}
