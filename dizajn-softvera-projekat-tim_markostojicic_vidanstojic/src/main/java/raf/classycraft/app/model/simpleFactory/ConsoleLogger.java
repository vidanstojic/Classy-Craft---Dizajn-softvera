package raf.classycraft.app.model.simpleFactory;

import raf.classycraft.app.core.ApplicationFramework;
import raf.classycraft.app.model.simpleFactory.Logger;
import raf.classycraft.app.observer.Notification;

public class ConsoleLogger implements Logger {


    public ConsoleLogger(){
        ApplicationFramework.getInstance().getMessageGenerator().addSubscriber(this);

    }


    @Override
    public void update(Object notify) {
        Notification notification = (Notification) notify;
        this.log(notification.getMessage());
    }

    @Override
    public void log(String message) {
        System.out.println(message);
    }
}
