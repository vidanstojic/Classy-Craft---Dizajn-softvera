package raf.classycraft.app.gui.tree;

import raf.classycraft.app.gui.tree.model.ClassyTreeItem;
import raf.classycraft.app.gui.tree.view.ClassyTreeView;
import raf.classycraft.app.model.compositeAbstract.ClassyNode;
import raf.classycraft.app.model.compositeAbstract.ClassyNodeComposite;
import raf.classycraft.app.model.compositeImplement.Diagrams;
import raf.classycraft.app.model.compositeImplement.Package;
import raf.classycraft.app.model.compositeImplement.Project;
import raf.classycraft.app.model.compositeImplement.ProjectExplorer;

import javax.swing.*;
import javax.swing.tree.DefaultTreeModel;
import java.util.Random;

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

        ClassyNode child = createChild(parent.getClassyNode());
        parent.add(new ClassyTreeItem(child));
        ((ClassyNodeComposite) parent.getClassyNode()).addChild(child);
        treeView.expandPath(treeView.getSelectionPath());
        SwingUtilities.updateComponentTreeUI(treeView);

    }

    @Override
    public void removeChild(ClassyTreeItem selected) {

        if(selected != null){
            ClassyTreeItem parent = (ClassyTreeItem) selected.getParent();
            parent.remove(selected);
            ((ClassyNodeComposite) parent.getClassyNode()).removeChild(selected.getClassyNode());
            SwingUtilities.updateComponentTreeUI(treeView);
        }


    }

    @Override
    public ClassyTreeItem getSelectedNode() {
        return (ClassyTreeItem) treeView.getLastSelectedPathComponent();
    }

    private ClassyNode createChild(ClassyNode parent) {
        if (parent instanceof ProjectExplorer)
            return  new Project("Project" +new Random().nextInt(100),parent);
        else if(parent instanceof Project)
            return new Package("Package"+new Random().nextInt(100), parent);
        else if(parent instanceof Package){
            Object[] selectionValues = {"Package","Diagram"};
            String initialSelection = "Diagram";
            Object selection = JOptionPane.showInputDialog(null, "Do you want a new subpackage, or would you like a new diagram?",
                    "Add new item", JOptionPane.QUESTION_MESSAGE, null, selectionValues, initialSelection);
            // proveriti da li ovaj JOptionPane sme da stoji ovde
            if(selection.equals("Diagram")){
                return new Diagrams("Diagram"+new Random().nextInt(100), parent);
            }
            else if(selection.equals("Package")){
                return new Package("Package"+new Random().nextInt(100), parent);
            }
            else{
                return null;
            }


        }


        return null;
    }
}
