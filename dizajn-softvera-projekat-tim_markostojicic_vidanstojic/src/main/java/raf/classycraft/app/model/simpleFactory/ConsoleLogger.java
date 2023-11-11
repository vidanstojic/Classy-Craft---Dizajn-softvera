package raf.classycraft.app.model.simpleFactory;

import raf.classycraft.app.core.ApplicationFramework;
import raf.classycraft.app.observer.NotificationMessageGenerator;

public class ConsoleLogger implements Logger {


    public ConsoleLogger(){
        ApplicationFramework.getInstance().getMessageGenerator().addSubscriber(this);

    }


    @Override
    public void update(Object notify) {
        NotificationMessageGenerator notification = (NotificationMessageGenerator) notify;
        this.log(notification.getMessage());
    }

    @Override
    public void log(String message) {
        System.out.println(message);
    }
}
