package raf.classycraft.app.model.messageGenerator;

import raf.classycraft.app.observer.IPublisher;
import raf.classycraft.app.observer.ISubscriber;
import raf.classycraft.app.observer.Notification;

import java.time.LocalDateTime;
import java.util.List;

public class MessageGenerator implements IPublisher {

    private String tekst;
    private Tip tip;
    private LocalDateTime timestamp;

    private List<ISubscriber> subscribers;

    public MessageGenerator(Tip tip, LocalDateTime timestamp, String text){
        this.tip = tip;
        this.timestamp = timestamp;
        this.tekst = tekst;
    }
    public void generateMessage(Tip tip, LocalDateTime timestamp, String tekst){
        String message = "["+tip +"]" + "["+timestamp +"]" + tekst;
        notifySub(new Notification(message));
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
    public void notifySub(Notification notification) {
        for(ISubscriber listener : subscribers){
            listener.update(notification);
        }
    }

    public String getTekst() {
        return tekst;
    }

    public void setTekst(String tekst) {
        this.tekst = tekst;
    }

    public Tip getTip() {
        return tip;
    }

    public void setTip(Tip tip) {
        this.tip = tip;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public List<ISubscriber> getSubscribers() {
        return subscribers;
    }

    public void setSubscribers(List<ISubscriber> subscribers) {
        this.subscribers = subscribers;
    }
}
