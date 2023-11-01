package raf.classycraft.app.model.messageGenerator;

import raf.classycraft.app.observer.IPublisher;
import raf.classycraft.app.observer.ISubscriber;

import java.time.LocalDateTime;

public class MessageGenerator implements IPublisher {

    private String tekst;
    private Tip tip;
    private LocalDateTime timestamp;

    public MessageGenerator(Tip tip, LocalDateTime timestamp){
        this.tip = tip;
        this.timestamp = timestamp;
        generateMessage(tip, timestamp);
    }
    public void generateMessage(Tip tip, LocalDateTime timestamp){
    /// na osnovu cega generisati poruku, gde pisem akciju
    }

    @Override
    public void addSubscriber(ISubscriber iSubscriber) {

    }

    @Override
    public void removeSubscriber(ISubscriber iSubscriber) {

    }

    @Override
    public void notifySub() {

    }
}
