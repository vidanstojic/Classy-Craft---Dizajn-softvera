package raf.classycraft.app.observer;

import raf.classycraft.app.model.compositeAbstract.ClassyNode;

public interface IPublisherTree {
    void addSubscriber(ISubscriberView iSubscriber);
    void removeSubscriber(ISubscriberView iSubscriber);
    void notifySub(Object notify, TreeNotification typeNotify);
}
