package raf.classycraft.app.model.compositeImplement;

import com.sun.tools.javac.Main;
import raf.classycraft.app.gui.view.MainFrame;
import raf.classycraft.app.gui.view.PackageView;
import raf.classycraft.app.model.compositeAbstract.ClassyNode;
import raf.classycraft.app.model.compositeAbstract.ClassyNodeComposite;
import raf.classycraft.app.observer.*;

import java.util.ArrayList;
import java.util.List;

public class Package extends ClassyNodeComposite implements IPublisherTree {


    List<ISubscriberView> subscribers = new ArrayList<>();
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
                notifySub(diagrams, TreeNotification.ADDED_CHILD);
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
        notifySub(child, TreeNotification.DELETED_CHILD);
    }


    @Override
    public void addSubscriber(ISubscriberView iSubscriber) {
        this.subscribers.add(iSubscriber);
    }

    @Override
    public void removeSubscriber(ISubscriberView iSubscriber) {
        this.subscribers.remove(iSubscriber);
    }

    @Override
    public void notifySub(ClassyNode child, TreeNotification typeNotify) {
        for(ISubscriberView iSubscriberView : this.subscribers){
            iSubscriberView.update(child, typeNotify);
        }
    }
}
