package raf.classycraft.app.observer;

import raf.classycraft.app.model.compositeAbstract.ClassyNode;

public interface ISubscriberView {
    void update(ClassyNode child, TreeNotification typeNotify);
}
