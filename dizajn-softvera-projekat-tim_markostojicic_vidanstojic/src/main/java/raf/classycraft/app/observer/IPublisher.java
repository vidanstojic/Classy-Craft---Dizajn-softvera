package raf.classycraft.app.observer;

public interface IPublisher {
    void addSubscriber(ISubscriber iSubscriber);
    void removeSubscriber(ISubscriber iSubscriber);
    void notifySub(Notification notification);

}
