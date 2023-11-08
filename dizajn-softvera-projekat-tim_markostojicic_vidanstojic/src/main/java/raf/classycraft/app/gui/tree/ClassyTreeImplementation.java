package raf.classycraft.app.gui.tree;

import raf.classycraft.app.gui.tree.model.ClassyTreeItem;
import raf.classycraft.app.gui.tree.view.ClassyTreeView;
import raf.classycraft.app.model.compositeImplement.ProjectExplorer;

import javax.swing.tree.DefaultTreeModel;

public class ClassyTreeImplementation implements ClassyTree{

    private ClassyTreeView treeView;
    private DefaultTreeModel treeModel;

    @Override
    public ClassyTreeView generateTree(ProjectExplorer projectExplorer) {
        return null;
    }

    @Override
    public void addChild(ClassyTreeItem parent) {

    }

    @Override
    public ClassyTreeItem getSelectedNode() {
        return null;
    }
}
