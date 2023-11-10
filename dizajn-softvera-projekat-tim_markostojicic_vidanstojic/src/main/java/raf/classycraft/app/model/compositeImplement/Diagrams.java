package raf.classycraft.app.model.compositeImplement;

import raf.classycraft.app.model.compositeAbstract.ClassyNode;
import raf.classycraft.app.observer.IPublisherTree;
import raf.classycraft.app.observer.ISubscriberView;
import raf.classycraft.app.observer.TreeNotification;

import java.util.ArrayList;
import java.util.List;

public class Diagrams extends ClassyNode implements IPublisherTree {

    List<ISubscriberView> subscriberViews = new ArrayList<>();
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
        notifySub(name, TreeNotification.RENAMED_CHILD);
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
    public void addSubscriber(ISubscriberView iSubscriber) {
        this.subscriberViews.add(iSubscriber);
    }

    @Override
    public void removeSubscriber(ISubscriberView iSubscriber) {
        this.subscriberViews.remove(iSubscriber);
    }

    @Override
    public void notifySub(Object notify, TreeNotification typeNotify) {

        for(ISubscriberView subscriberView : this.subscriberViews){
            subscriberView.update(notify, typeNotify);
        }
    }


}
