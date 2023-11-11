package raf.classycraft.app.model.compositeImplement;

import raf.classycraft.app.model.compositeAbstract.ClassyNode;
import raf.classycraft.app.observer.IPublisher;
import raf.classycraft.app.observer.ISubscriber;
import raf.classycraft.app.observer.NotificationTree;

import java.util.ArrayList;
import java.util.List;

public class Diagrams extends ClassyNode implements IPublisher {

    List<ISubscriber> subscribers = new ArrayList<>(); // nije jos dodan DijagramView u listu
    public Diagrams(String name, ClassyNode parent){
        super.setName(name);
        super.setParent(parent);
    }


    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public String getName() {
        return super.getName();
    }

    @Override
    public void setName(String name) {
        super.setName(name);
    }

    @Override
    public ClassyNode getParent() {
        return super.getParent();
    }

    @Override
    public void setParent(ClassyNode parent) {
        super.setParent(parent);
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
        NotificationTree notificationTree = new NotificationTree();// ovde ce tek biti potrebno da se promeni nesto kasnije u toku projekta
        // kada bude bilo bitno da DijagramView slusa Dijagram
        for(ISubscriber subscriberView : this.subscribers){
            subscriberView.update(notificationTree);
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

    public Package findPackage(){
        ClassyNode currentNode = this;

        while (!(currentNode instanceof Package)) {
            ClassyNode parent = currentNode.getParent();
            if (parent == null) {
                return null;
            }
            currentNode = parent;
        }
        Package packageParent = (Package) currentNode;
        return packageParent;
    }
}
