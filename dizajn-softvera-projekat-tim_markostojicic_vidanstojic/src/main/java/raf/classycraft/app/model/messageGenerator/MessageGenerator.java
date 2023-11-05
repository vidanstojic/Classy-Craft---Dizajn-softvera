package raf.classycraft.app.model.messageGenerator;

import raf.classycraft.app.observer.IPublisher;
import raf.classycraft.app.observer.ISubscriber;
import raf.classycraft.app.observer.Notification;

import java.util.ArrayList;
import java.util.List;

public class MessageGenerator implements IPublisher {

    private String text;
    private Type tip;


    private List<ISubscriber> subscribers = new ArrayList<>();

    public MessageGenerator(){

    }

    public MessageGenerator(Type tip, String text){
        this.tip = tip;
        this.text = text;
    }
    public void generateMessage(Type tip, String tekst){
      //  notifySub(new Notification(message));
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

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Type getTip() {
        return tip;
    }

    public void setTip(Type tip) {
        this.tip = tip;
    }


    public List<ISubscriber> getSubscribers() {
        return subscribers;
    }

    public void setSubscribers(List<ISubscriber> subscribers) {
        this.subscribers = subscribers;
    }

}
