package raf.classycraft.app.observer;

import com.sun.source.tree.Tree;
import raf.classycraft.app.model.compositeAbstract.ClassyNode;

public class NotificationTree {
    private TreeNotificationType treeNotificationType;
    private ClassyNode classyNode;


    public NotificationTree(ClassyNode classyNode,TreeNotificationType treeNotificationType) {
        this.classyNode = classyNode;
        this.treeNotificationType = treeNotificationType;
    }
    public NotificationTree(){

    }

    public TreeNotificationType getTreeNotificationType() {
        return treeNotificationType;
    }

    public void setTreeNotificationType(TreeNotificationType treeNotificationType) {
        this.treeNotificationType = treeNotificationType;
    }

    public ClassyNode getClassyNode() {
        return classyNode;
    }

    public void setClassyNode(ClassyNode classyNode) {
        this.classyNode = classyNode;
    }
}
