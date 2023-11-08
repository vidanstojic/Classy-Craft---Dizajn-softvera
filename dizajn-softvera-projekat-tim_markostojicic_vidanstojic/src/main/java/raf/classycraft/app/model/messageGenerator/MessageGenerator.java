package raf.classycraft.app.model.messageGenerator;

import raf.classycraft.app.observer.IPublisher;

public interface MessageGenerator extends IPublisher {
    void generateMessage(EventTypes eventType,Type type);
}
