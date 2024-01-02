package raf.classycraft.app.gui.tree;

import raf.classycraft.app.core.ApplicationFramework;
import raf.classycraft.app.gui.tree.factory.FactoryUtils;
import raf.classycraft.app.gui.tree.model.ClassyTreeItem;
import raf.classycraft.app.gui.tree.view.ClassyTreeView;
import raf.classycraft.app.gui.view.MainFrame;
import raf.classycraft.app.model.compositeAbstract.ClassyNode;
import raf.classycraft.app.model.compositeAbstract.ClassyNodeComposite;
import raf.classycraft.app.model.compositeImplement.Diagram;
import raf.classycraft.app.model.compositeImplement.MyPackage;
import raf.classycraft.app.model.compositeImplement.Project;
import raf.classycraft.app.model.compositeImplement.ProjectExplorer;
import raf.classycraft.app.model.elementDiagram.Connection;
import raf.classycraft.app.model.elementDiagram.DiagramElement;
import raf.classycraft.app.model.elementDiagram.Interclass;
import raf.classycraft.app.model.messageGenerator.EventTypes;
import raf.classycraft.app.model.messageGenerator.Type;

import javax.swing.*;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;

public class ClassyTreeImplementation implements ClassyTree{

    private ClassyTreeView treeView;
    private DefaultTreeModel treeModel;

    @Override
    public ClassyTreeView generateTree(ProjectExplorer projectExplorer) {
        ClassyTreeItem root = new ClassyTreeItem(projectExplorer);
        treeModel = new DefaultTreeModel(root);
        treeView = new ClassyTreeView(treeModel);
        return treeView;
    }

    @Override
    public void addChild(ClassyTreeItem parent) {
        if(!(parent.getClassyNode() instanceof ClassyNodeComposite))
            return;

        ClassyNodeComposite parentComposite = (ClassyNodeComposite) parent.getClassyNode();
        ClassyNode child = createChild(parentComposite);
        if(child == null){
            return;
        }
        parent.add(new ClassyTreeItem(child));
        ((ClassyNodeComposite) parent.getClassyNode()).addChild(child);
        treeView.expandPath(treeView.getSelectionPath());
        SwingUtilities.updateComponentTreeUI(treeView);

    }

    public void addDiagramElement(ClassyTreeItem parent, ClassyTreeItem child){
        if(!(parent.getClassyNode() instanceof ClassyNodeComposite))
            return;

        if(child.getClassyNode() instanceof Connection){
            child.getClassyNode().setName(((Connection) child.getClassyNode()).getConnectionInfo().getNameOfConnection());
        }

        parent.add(child);
        ((ClassyNodeComposite) parent.getClassyNode()).addChild(child.getClassyNode());
        treeView.expandPath(treeView.getSelectionPath());
        SwingUtilities.updateComponentTreeUI(treeView);
    }

    @Override
    public void removeChild(ClassyTreeItem selected) {

        if(selected ==null){
            ApplicationFramework.getInstance().getMessageGenerator().generateMessage(EventTypes.NODE_MUST_BE_SELECTED, Type.WARNING);
            return;
        }
        if(selected != null){
           ClassyTreeItem parent = (ClassyTreeItem) selected.getParent();
           if(parent == null) {
     //          ApplicationFramework.getInstance().getMessageGenerator().generateMessage(EventTypes.NODE_MUST_BE_SELECTED, Type.WARNING);
               return;
           }
            parent.remove(selected);
            ((ClassyNodeComposite) parent.getClassyNode()).removeChild(selected.getClassyNode());
            SwingUtilities.updateComponentTreeUI(treeView);
        }


    }

    @Override
    public ClassyTreeItem getSelectedNode() {
        return (ClassyTreeItem) treeView.getLastSelectedPathComponent();
    }

    @Override
    public void loadProject(ClassyNode node, ProjectExplorer projectExplorer) {
        if (node instanceof Project) {
            Project project = (Project) node;
            ClassyTreeItem loadedProject = new ClassyTreeItem(project);
            ((ClassyTreeItem) treeModel.getRoot()).add(loadedProject);

            projectExplorer.addChild(project);
            project.setParent(projectExplorer);

            project.getSubscribers().add(MainFrame.getInstance().getPackageView());
            treeView.expandPath(treeView.getSelectionPath());
            SwingUtilities.updateComponentTreeUI(treeView);


            System.out.println(project.getChildren().get(0).getName());
            if (!(project.getChildren().isEmpty())) loadPackage(project);
        } else if (node instanceof Diagram) {
            Diagram diagram = (Diagram) node;
            MyPackage myPackage = (MyPackage)MainFrame.getInstance().getClassyTree().getSelectedNode().getClassyNode();
            diagram.setParent(myPackage);
            ClassyTreeItem rootItem = (ClassyTreeItem) treeModel.getRoot();
            ClassyTreeItem projectTreeItem = findTreeItem(rootItem, myPackage);

            if (projectTreeItem != null) {

                ClassyTreeItem packageTreeItem = findTreeItem(projectTreeItem, diagram);

                if (packageTreeItem != null) {
                    treeView.setSelectionPath(new TreePath(packageTreeItem.getPath()));
                } else {
                    myPackage.addChild(diagram);
                    ClassyTreeItem newPackageTreeItem = new ClassyTreeItem(diagram);
                    projectTreeItem.add(newPackageTreeItem);

                    treeView.expandPath(new TreePath(newPackageTreeItem.getPath()));


                    SwingUtilities.updateComponentTreeUI(treeView);
                }
            }
            if (!(diagram.getChildren().isEmpty())) loadDiagramElement(diagram);
        } else if (node instanceof DiagramElement) {
            DiagramElement diagramElement = (DiagramElement) node;
            Diagram diagram = (Diagram) MainFrame.getInstance().getClassyTree().getSelectedNode().getClassyNode();
            diagramElement.setParent(diagram);
            ClassyTreeItem rootItem = (ClassyTreeItem) treeModel.getRoot();
            ClassyTreeItem projectTreeItem = findTreeItem(rootItem, diagram);

            if (projectTreeItem != null) {

                ClassyTreeItem packageTreeItem = findTreeItem(projectTreeItem, diagramElement);

                if (packageTreeItem != null) {
                    treeView.setSelectionPath(new TreePath(packageTreeItem.getPath()));
                } else {
                    diagram.addChild(diagramElement);
                    ClassyTreeItem newPackageTreeItem = new ClassyTreeItem(diagramElement);
                    projectTreeItem.add(newPackageTreeItem);

                    treeView.expandPath(new TreePath(newPackageTreeItem.getPath()));


                    SwingUtilities.updateComponentTreeUI(treeView);
                }
            }
        }
    }
   public void loadPackage(Project project){
        for (ClassyNode classyNode : project.getChildren()) {
            MyPackage pack = (MyPackage) classyNode;
            pack.setParent(project);
            ClassyTreeItem rootItem = (ClassyTreeItem) treeModel.getRoot();
            ClassyTreeItem projectTreeItem = findTreeItem(rootItem, project);

            if (projectTreeItem != null) {

                ClassyTreeItem packageTreeItem = findTreeItem(projectTreeItem, pack);

                if (packageTreeItem != null) {
                    treeView.setSelectionPath(new TreePath(packageTreeItem.getPath()));
                    pack.getSubscribers().add(MainFrame.getInstance().getPackageView());
                } else {
                    project.addChild(pack);
                    ClassyTreeItem newPackageTreeItem = new ClassyTreeItem(pack);
                    projectTreeItem.add(newPackageTreeItem);
                    pack.getSubscribers().add(MainFrame.getInstance().getPackageView());
                    treeView.expandPath(new TreePath(newPackageTreeItem.getPath()));


                    SwingUtilities.updateComponentTreeUI(treeView);
                }
            }
            if (!(pack.getChildren().isEmpty())) loadDiagram(pack);
        }
   }
   public void loadDiagram(MyPackage myPackage){
        for (ClassyNode classyNode : myPackage.getChildren()) {
            if (classyNode instanceof Diagram) {
                Diagram diagram = (Diagram) classyNode;
                diagram.setParent(myPackage);
                ClassyTreeItem rootItem = (ClassyTreeItem) treeModel.getRoot();
                ClassyTreeItem projectTreeItem = findTreeItem(rootItem, myPackage);

                if (projectTreeItem != null) {

                    ClassyTreeItem packageTreeItem = findTreeItem(projectTreeItem, diagram);

                    if (packageTreeItem != null) {
                        treeView.setSelectionPath(new TreePath(packageTreeItem.getPath()));
                    } else {
                        myPackage.addChild(diagram);
                        ClassyTreeItem newPackageTreeItem = new ClassyTreeItem(diagram);
                        projectTreeItem.add(newPackageTreeItem);

                        treeView.expandPath(new TreePath(newPackageTreeItem.getPath()));


                        SwingUtilities.updateComponentTreeUI(treeView);
                    }
                }
                if (!(diagram.getChildren().isEmpty())) loadDiagramElement(diagram);
            } else if (classyNode instanceof MyPackage) {
                MyPackage myPackage1 = (MyPackage) classyNode;
                myPackage1.setParent(myPackage);
                ClassyTreeItem rootItem = (ClassyTreeItem) treeModel.getRoot();
                ClassyTreeItem projectTreeItem = findTreeItem(rootItem, myPackage);

                if (projectTreeItem != null) {

                    ClassyTreeItem packageTreeItem = findTreeItem(projectTreeItem, myPackage1);

                    if (packageTreeItem != null) {
                        treeView.setSelectionPath(new TreePath(packageTreeItem.getPath()));
                    } else {
                        myPackage.addChild(myPackage1);
                        ClassyTreeItem newPackageTreeItem = new ClassyTreeItem(myPackage1);
                        projectTreeItem.add(newPackageTreeItem);

                        treeView.expandPath(new TreePath(newPackageTreeItem.getPath()));


                        SwingUtilities.updateComponentTreeUI(treeView);
                    }
                }
                if (!(myPackage1.getChildren().isEmpty()))loadDiagram(myPackage1);
            }
        }
   }
    public void loadDiagramElement(Diagram diagram){
        for (ClassyNode diagramElementChild : diagram.getChildren()) {
            DiagramElement diagramElement = (DiagramElement) diagramElementChild;
            diagramElement.setParent(diagram);
            ClassyTreeItem rootItem = (ClassyTreeItem) treeModel.getRoot();
            ClassyTreeItem projectTreeItem = findTreeItem(rootItem, diagram);

            if (projectTreeItem != null) {

                ClassyTreeItem packageTreeItem = findTreeItem(projectTreeItem, diagramElement);

                if (packageTreeItem != null) {
                    treeView.setSelectionPath(new TreePath(packageTreeItem.getPath()));
                } else {
                    diagram.addChild(diagramElement);
                    ClassyTreeItem newPackageTreeItem = new ClassyTreeItem(diagramElement);
                    projectTreeItem.add(newPackageTreeItem);

                    treeView.expandPath(new TreePath(newPackageTreeItem.getPath()));


                    SwingUtilities.updateComponentTreeUI(treeView);
                }
            }
            if (diagramElement instanceof Connection){
                Connection connection = (Connection) diagramElement;
                for (ClassyNode classyNode : diagram.getChildren()){
                    if (classyNode instanceof Interclass){
                        if (connection.getClassFrom().getPoint().equals(((Interclass) classyNode).getPoint())){
                            connection.setClassFrom((Interclass) classyNode);
                        } else if (connection.getClassTo().getPoint().equals(((Interclass) classyNode).getPoint())) {
                            connection.setClassTo((Interclass) classyNode);
                        }
                    }
                }
            }

        }
    }

    private ClassyNode createChild(ClassyNodeComposite parent) {
        return FactoryUtils.initNode(parent);
    }

    public ClassyTreeItem findTreeItem(ClassyTreeItem root, ClassyNode targetNode) {
        if (root.getClassyNode() == targetNode) {
            return root;
        }

        for (int i = 0; i < root.getChildCount(); i++) {
            ClassyTreeItem child = (ClassyTreeItem) root.getChildAt(i);
            ClassyTreeItem foundItem = findTreeItem(child, targetNode);
            if (foundItem != null) {
                return foundItem;
            }
        }

        return null;
    }

    public DefaultTreeModel getTreeModel() {
        return treeModel;
    }

    public ClassyTreeView getTreeView() {
        return treeView;
    }
}
