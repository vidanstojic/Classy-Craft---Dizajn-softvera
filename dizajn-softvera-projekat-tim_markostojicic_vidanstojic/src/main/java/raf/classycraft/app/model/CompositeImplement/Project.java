package raf.classycraft.app.model.CompositeImplement;

import raf.classycraft.app.model.CompositeAbstract.ClassyNode;
import raf.classycraft.app.model.CompositeAbstract.ClassyNodeComposite;

import java.util.List;

public class Project extends ClassyNodeComposite {

    public Project() {

    }

    @Override
    public void addChild(ClassyNode child) {
        children.add(child);
    }

    @Override
    public void removeChild(ClassyNode child) {
        children.remove(child);
    }
}
