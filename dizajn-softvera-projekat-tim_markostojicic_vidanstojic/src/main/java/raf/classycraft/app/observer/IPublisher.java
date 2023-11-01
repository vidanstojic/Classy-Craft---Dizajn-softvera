package raf.classycraft.app.observer;

import raf.classycraft.app.model.messageGenerator.MessageGenerator;

public interface IPublisher {
    void addSubscriber(ISubscriber iSubscriber);
    void removeSubscriber(ISubscriber iSubscriber);
    void notifySub(Object notification);

}
