package raf.classycraft.app.gui.tree;

import raf.classycraft.app.core.ApplicationFramework;
import raf.classycraft.app.gui.tree.factory.FactoryUtils;
import raf.classycraft.app.gui.tree.model.ClassyTreeItem;
import raf.classycraft.app.gui.tree.view.ClassyTreeView;
import raf.classycraft.app.model.compositeAbstract.ClassyNode;
import raf.classycraft.app.model.compositeAbstract.ClassyNodeComposite;
import raf.classycraft.app.model.compositeImplement.MyPackage;
import raf.classycraft.app.model.compositeImplement.Project;
import raf.classycraft.app.model.compositeImplement.ProjectExplorer;
import raf.classycraft.app.model.elementDiagram.Connection;
import raf.classycraft.app.model.messageGenerator.EventTypes;
import raf.classycraft.app.model.messageGenerator.Type;

import javax.swing.*;
import javax.swing.tree.DefaultTreeModel;

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
    public void loadProject(Project node, ProjectExplorer projectExplorer) {
        ClassyTreeItem loadedProject = new ClassyTreeItem(node);
        ((ClassyTreeItem)treeModel.getRoot()).add(loadedProject);

        projectExplorer.addChild(node);
        //node.openChild();
        treeView.expandPath(treeView.getSelectionPath());
        SwingUtilities.updateComponentTreeUI(treeView);
        System.out.println(node.getChildren().get(0).getName());
        if (!node.getChildren().isEmpty())loadPackage(node);
    }
   public void loadPackage(Project project){
        MyPackage pack =(MyPackage) project.getChildren().get(0);
        project.addChild(pack);
        treeView.expandPath(treeView.getSelectionPath());
        SwingUtilities.updateComponentTreeUI(treeView);
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
