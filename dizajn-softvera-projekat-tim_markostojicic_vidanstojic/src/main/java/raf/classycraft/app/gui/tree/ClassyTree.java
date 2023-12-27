package raf.classycraft.app.gui.tree;

import raf.classycraft.app.gui.tree.model.ClassyTreeItem;
import raf.classycraft.app.gui.tree.view.ClassyTreeView;
import raf.classycraft.app.model.compositeImplement.Project;
import raf.classycraft.app.model.compositeImplement.ProjectExplorer;

public interface ClassyTree {
    ClassyTreeView generateTree(ProjectExplorer projectExplorer);
    void addChild(ClassyTreeItem parent);

    void removeChild(ClassyTreeItem selected);
    ClassyTreeItem getSelectedNode();

    void loadProject(Project node, ProjectExplorer projectExplorer);
}
