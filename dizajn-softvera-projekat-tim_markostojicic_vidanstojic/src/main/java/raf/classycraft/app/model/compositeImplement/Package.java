package raf.classycraft.app.model.compositeImplement;

import raf.classycraft.app.gui.view.MainFrame;
import raf.classycraft.app.model.compositeAbstract.ClassyNode;
import raf.classycraft.app.model.compositeAbstract.ClassyNodeComposite;
import raf.classycraft.app.observer.*;

import java.util.ArrayList;
import java.util.List;

public class Package extends ClassyNodeComposite implements IPublisher {


    List<ISubscriber> subscribers = new ArrayList<>();
    public Package(String name, ClassyNode parent){
        super.setName(name);
        super.setParent(parent);
        this.addSubscriber(MainFrame.getInstance().getPackageView());
    }

    @Override
    public void addChild(ClassyNode child) {
        if (child != null &&  child instanceof Diagrams){
            Diagrams diagrams = (Diagrams) child;
            if (!this.getChildren().contains(diagrams)){
                this.getChildren().add(diagrams);
                NotificationTree notificationTree = new NotificationTree(diagrams, TreeNotificationType.ADDED_CHILD);
                notifySub(notificationTree);
            }
        }
        else if (child != null &&  child instanceof Package){
            Package packageChild = (Package) child;
            if (!this.getChildren().contains(packageChild)){
                this.getChildren().add(packageChild);
            }
        }
    }

    @Override
    public void removeChild(ClassyNode child) {
        if(this.getChildren().contains(child)){
            this.getChildren().remove(child);
        }
        NotificationTree notificationTree = new NotificationTree(child, TreeNotificationType.DELETED_CHILD);
        notifySub(notificationTree);
    }


    @Override
    public void addSubscriber(ISubscriber iSubscriber) {
        this.subscribers.add(iSubscriber);
    }

    @Override
    public void removeSubscriber(ISubscriber iSubscriber) {
        this.subscribers.remove(iSubscriber);
    }

    @Override
    public void notifySub(Object notify) {
        NotificationTree notificationTree = (NotificationTree) notify;
        for(ISubscriber iSubscriber : this.subscribers){
            iSubscriber.update(notificationTree);
        }
    }

    public Project findProject() {
        ClassyNode currentNode = this;

        while (!(currentNode instanceof Project)) {
            ClassyNode parent = currentNode.getParent();
            if (parent == null) {
                return null;
            }
            currentNode = parent;
        }
        Project project = (Project) currentNode;
        return project;
    }
}
