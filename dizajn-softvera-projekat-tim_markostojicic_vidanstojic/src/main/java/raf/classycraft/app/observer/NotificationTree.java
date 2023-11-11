package raf.classycraft.app.observer;

import com.sun.source.tree.Tree;
import raf.classycraft.app.model.compositeAbstract.ClassyNode;

public class NotificationTree {
    private TreeNotificationType treeNotificationType;
    private ClassyNode classyNode;

    private String oldNameNode;

    public NotificationTree(ClassyNode classyNode,TreeNotificationType treeNotificationType) {
        this.classyNode = classyNode;
        this.treeNotificationType = treeNotificationType;
    }
    public NotificationTree(){

    }

    public NotificationTree(ClassyNode classyNode,TreeNotificationType treeNotificationType, String oldNameNode) {
        this.classyNode = classyNode;
        this.treeNotificationType = treeNotificationType;
        this.oldNameNode = oldNameNode;
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

    public String getOldNameNode() {
        return oldNameNode;
    }

    public void setOldNameNode(String oldNameNode) {
        this.oldNameNode = oldNameNode;
    }
}
