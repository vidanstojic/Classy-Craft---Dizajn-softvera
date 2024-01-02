package raf.classycraft.app.gui.tree;

import raf.classycraft.app.gui.tree.model.ClassyTreeItem;
import raf.classycraft.app.gui.tree.view.ClassyTreeView;
import raf.classycraft.app.model.compositeAbstract.ClassyNode;
import raf.classycraft.app.model.compositeImplement.Diagram;
import raf.classycraft.app.model.compositeImplement.Project;
import raf.classycraft.app.model.compositeImplement.ProjectExplorer;

public interface ClassyTree {
    ClassyTreeView generateTree(ProjectExplorer projectExplorer);
    void addChild(ClassyTreeItem parent);

    void removeChild(ClassyTreeItem selected);
    ClassyTreeItem getSelectedNode();
    void loadDiagramElement(Diagram diagram);
    void loadProject(ClassyNode node, ProjectExplorer projectExplorer);
}
