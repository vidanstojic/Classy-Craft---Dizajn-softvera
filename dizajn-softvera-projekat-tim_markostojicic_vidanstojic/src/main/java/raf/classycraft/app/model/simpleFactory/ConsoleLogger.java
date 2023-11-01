package raf.classycraft.app.model.simpleFactory;

import raf.classycraft.app.model.simpleFactory.Logger;
import raf.classycraft.app.observer.Notification;

public class ConsoleLogger implements Logger {
    @Override
    public void update(Notification notification) {
        this.log(notification.getMessage());
    }

    @Override
    public void log(String message) {
        System.out.println(message);
    }
}
