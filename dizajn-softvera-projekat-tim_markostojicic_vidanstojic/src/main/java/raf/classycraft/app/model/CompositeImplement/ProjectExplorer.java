package raf.classycraft.app.model.CompositeImplement;

import raf.classycraft.app.model.CompositeAbstract.ClassyNode;
import raf.classycraft.app.model.CompositeAbstract.ClassyNodeComposite;

import java.util.List;

public class ProjectExplorer extends ClassyNodeComposite {

    public ProjectExplorer(List<ClassyNode> children) {
        super(children);
    }

    @Override
    public void addChild(ClassyNode child) {

    }

    @Override
    public void removeChild(ClassyNode child) {

    }
}
