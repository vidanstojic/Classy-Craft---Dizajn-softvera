package raf.classycraft.app.model.compositeImplement;

import com.fasterxml.jackson.annotation.*;
import raf.classycraft.app.gui.view.MainFrame;
import raf.classycraft.app.model.compositeAbstract.ClassyNode;
import raf.classycraft.app.model.compositeAbstract.ClassyNodeComposite;
import raf.classycraft.app.observer.*;

import java.util.ArrayList;
import java.util.List;
/*
@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type"
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = MyPackage.class, name = "concrete2"),
})*/
@JsonTypeName("MyPackage")
public class MyPackage extends ClassyNodeComposite implements IPublisher {


    @JsonIgnore
    private List<ISubscriber> subscribers = new ArrayList<>();
    public MyPackage(String name, ClassyNode parent){
        super.setName(name);
        super.setParent(parent);
        this.addSubscriber(MainFrame.getInstance().getPackageView());
    }
    public MyPackage(){

    }
    @Override
    public void addChild(ClassyNode child) {
        if (child != null &&  child instanceof Diagram){
            Diagram diagrams = (Diagram) child;
            if (!this.getChildren().contains(diagrams)){
                this.getChildren().add(diagrams);
                NotificationTree notificationTree = new NotificationTree(diagrams, TreeNotificationType.ADDED_CHILD);
                notifySub(notificationTree);
            }
        }
        else if (child != null &&  child instanceof MyPackage){
            MyPackage myPackageChild = (MyPackage) child;
            if (!this.getChildren().contains(myPackageChild)){
                this.getChildren().add(myPackageChild);
            }
        }
    }

    @Override
    public void removeChild(ClassyNode child) {
        if(child != null && child instanceof Diagram){
            if(getChildren().contains(child)){
                getChildren().remove(child);
                NotificationTree notificationTree = new NotificationTree(child, TreeNotificationType.DELETED_CHILD);
                notifySub(notificationTree);
            }
        }else if (child != null && child instanceof MyPackage){
            if(getChildren().contains(child)){
                getChildren().remove(child);
                NotificationTree notificationTree = new NotificationTree(child, TreeNotificationType.PACKAGE_DELETED);
                notifySub(notificationTree);
            }
        }
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
 
    public void nameChangedDiagram(ClassyNode child, String oldName){
            NotificationTree notificationTree = new NotificationTree(child, TreeNotificationType.RENAMED_CHILD, oldName);
            notifySub(notificationTree);
    }

    public void packageDeleted(ClassyNode node){
        NotificationTree notificationTree = new NotificationTree(node,TreeNotificationType.PACKAGE_DELETED);
        notifySub(notificationTree);
    }

}
