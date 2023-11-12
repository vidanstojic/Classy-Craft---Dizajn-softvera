package raf.classycraft.app.model.compositeImplement;

import raf.classycraft.app.gui.view.MainFrame;
import raf.classycraft.app.model.compositeAbstract.ClassyNode;
import raf.classycraft.app.model.compositeAbstract.ClassyNodeComposite;
import raf.classycraft.app.observer.*;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class Project extends ClassyNodeComposite implements IPublisher {

    private String author;
    private URL filepath;

    private List<ISubscriber> subscribers = new ArrayList<>();
    public Project(String name, ClassyNode parent){
        super.setName(name);
        super.setParent(parent);
        this.addSubscriber(MainFrame.getInstance().getPackageView());
    }

    public Project(String author) {
        this.author = author;
    }

    @Override
    public void addChild(ClassyNode child) {
        if (child != null &&  child instanceof Package){
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
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
        ProjectNotificationType projectNotificationType = new ProjectNotificationType(getName(), author);
        notifySub(projectNotificationType);
    }

    @Override
    public void setName(String name) {
        super.setName(name);
        ProjectNotificationType projectNotificationType = new ProjectNotificationType(getName(), author);
        notifySub(projectNotificationType);
    }

    public URL getFilepath() {
        return filepath;
    }

    public void setFilepath(URL filepath) {
        this.filepath = filepath;
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
        for(ISubscriber subscriber : this.subscribers){
            subscriber.update(notify);
        }

    }
    public void projectDeleted(ClassyNode node){
        NotificationTree notificationTree = new NotificationTree(node,TreeNotificationType.PROJECT_DELETED);
        notifySub(notificationTree);
    }
}
