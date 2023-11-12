package raf.classycraft.app.observer;

import com.sun.source.tree.Tree;
import raf.classycraft.app.model.compositeAbstract.ClassyNode;

public class NotificationTree {
    private TreeNotificationType treeNotificationType;
    private ClassyNode classyNode;

    private String oldNameNode;

    private String projectName;
    private String authorName;

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

    public NotificationTree(String projectName, String authorName, TreeNotificationType treeNotificationType){
        this.projectName = projectName;
        this.authorName = authorName;
        this.treeNotificationType = treeNotificationType;
    }

    public NotificationTree(TreeNotificationType treeNotificationType, ClassyNode classyNode) {
        this.treeNotificationType = treeNotificationType;
        this.classyNode = classyNode;
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

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }
}
