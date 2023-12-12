package raf.classycraft.app.gui.view;

import raf.classycraft.app.model.compositeAbstract.ClassyNode;
import raf.classycraft.app.model.compositeImplement.Diagram;
import raf.classycraft.app.model.compositeImplement.Package;
import raf.classycraft.app.model.compositeImplement.Project;
import raf.classycraft.app.observer.ISubscriber;
import raf.classycraft.app.observer.NotificationTree;
import raf.classycraft.app.observer.ProjectNotificationType;
import raf.classycraft.app.observer.TreeNotificationType;
import raf.classycraft.app.state.StateManager;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class PackageView extends JPanel implements ISubscriber {

    private JTabbedPane tabbedPane;
    private List<String> stringTabs = new ArrayList<>();
    private Label author;
    private Label nameOfProject;

    public static boolean flag = false;

    private StateManager stateManager;

    public PackageView(){
        tabbedPaneInit();
        stateManager = new StateManager();
    }


    public void tabbedPaneInit(){

        this.setLayout(new BorderLayout());

        tabbedPane = new JTabbedPane();
        this.add(tabbedPane, BorderLayout.CENTER);

        nameOfProject = new Label();
        author = new Label();
        nameOfProject.setAlignment(Label.CENTER);
        author.setAlignment(Label.CENTER);
        JPanel labelPanel = new JPanel(new GridLayout(2, 1));
        labelPanel.add(nameOfProject);
        labelPanel.add(author);
        this.add(labelPanel, BorderLayout.NORTH);

        MyDrawingToolBar toolBar = new MyDrawingToolBar();

        Dimension screenSize = MainFrame.getInstance().getSize();
        int height = screenSize.height;
        toolBar.setBorder(BorderFactory.createEmptyBorder(height / 50, 3, 0, 3));

        this.add(toolBar, BorderLayout.EAST);
    }

    public void addTab(String title, Component component ) {
        if(stringTabs.contains(title)){
            return;
        }
        JScrollPane scrollPane = new JScrollPane(component);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        tabbedPane.addTab(title, scrollPane);
      //  tabbedPane.addTab(title, component);
        this.stringTabs.add(title);
    }

    public void removeTab(ClassyNode child) {
        if(child == null)
            return;
        int indexOfTabRemove = tabbedPane.indexOfTab(child.getName());
        if (indexOfTabRemove != -1) {
            tabbedPane.removeTabAt(indexOfTabRemove);
            String tabToRemove= child.getName();
            this.stringTabs.remove(tabToRemove);
        }
    }
    public void changeTabTitle(ClassyNode child, String oldName){
        int indexOfTab = tabbedPane.indexOfTab(oldName);
        if (indexOfTab != -1) {
            tabbedPane.setTitleAt(indexOfTab, child.getName());
            this.stringTabs.remove(oldName);
            this.stringTabs.add(child.getName());
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

            NotificationTree notificationTree = (NotificationTree) notify;
            Package parentP = null;
            Project parent = null;
            Diagram child = null;
            if(notificationTree.getClassyNode() != null && notificationTree.getClassyNode() instanceof Diagram){
                child = (Diagram) notificationTree.getClassyNode();
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
                if (flag) {
                    if (child.findProject() != null && child.findProject() instanceof Project) {
                        Project project = child.findProject();
                        addTab(child.getName(), new DiagramView(child));
                    }
                }
            }
            else if(typeNotify == TreeNotificationType.DELETED_CHILD){
                removeTab(child);
            }

            else if(typeNotify == TreeNotificationType.RENAMED_CHILD){
                changeTabTitle(child, notificationTree.getOldNameNode());
            }

            else if(typeNotify == TreeNotificationType.PACKAGE_DELETED) {

                    List<ClassyNode> nodesToRemove = new ArrayList<>();
                    for(ClassyNode classyNode: parentP.getChildren()){
                            if(tabbedPane.getComponents() == null) {
                                return;
                            }
                            nodesToRemove.add(classyNode);
                    }
                    for(ClassyNode element : nodesToRemove){
                        parentP.removeChild(element);
                    }

            }
            else if(typeNotify == TreeNotificationType.PROJECT_DELETED){

                for(ClassyNode classyNode: parent.getChildren()){
                    if(classyNode instanceof Package) {
                        Package p = (Package) classyNode;
                        NotificationTree notificationTree1 = new NotificationTree(p, TreeNotificationType.PACKAGE_DELETED);
                        update(notificationTree1);
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

    // Start metode posto je PackageView mediator
    public void startAddClassState(){
        this.stateManager.setAddClassState();
    }
    public void startAddConnectionState(){
        this.stateManager.setAddConnectionState();
    }
    public void startEditClassState(){
        this.stateManager.setEditClassState();
    }
    public void startRemoveState(){
        this.stateManager.setRemoveState();
    }
    public void startMoveState(){
        this.stateManager.setMoveState();
    }
    public void startSelectionState(){
        this.stateManager.setSelectionState();
    }

    public void startDuplicateState(){this.stateManager.setDuplicateState();}

    public static boolean isFlag() {
        return flag;
    }

    public static void setFlag(boolean flag) {
        PackageView.flag = flag;
    }


    public StateManager getStateManager() {
        return stateManager;
    }
}
