package raf.classycraft.app.model.simpleFactory;

import raf.classycraft.app.observer.ISubscriber;

public interface Logger extends ISubscriber {
    void log(String message);
}
