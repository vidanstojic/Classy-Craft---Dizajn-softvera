package raf.classycraft.app.gui.tree.model;

import raf.classycraft.app.model.compositeAbstract.ClassyNode;

import javax.swing.tree.DefaultMutableTreeNode;

public class ClassyTreeItem extends DefaultMutableTreeNode {
    private ClassyNode classyNode;

    public ClassyTreeItem(ClassyNode classyNode){
        this.classyNode = classyNode;
    }

    @Override
    public String toString() {
        return classyNode.getName();
    }

    public void setName(String name){
        this.classyNode.setName(name);
    }

    public ClassyNode getClassyNode() {
        return classyNode;
    }

    public void setClassyNode(ClassyNode classyNode) {
        this.classyNode = classyNode;
    }
}
