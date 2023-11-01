package raf.classycraft.app.model.compositeImplement;

import raf.classycraft.app.model.compositeAbstract.ClassyNode;
import raf.classycraft.app.model.compositeAbstract.ClassyNodeComposite;

public class ProjectExplorer extends ClassyNodeComposite {


    public ProjectExplorer() {

    }


    @Override
    public void addChild(ClassyNode child) {
        children.add(child);
    }

    @Override
    public void removeChild(ClassyNode child) {
        children.remove(child);
    }
    @Override
    public ClassyNode getParent(){
        return null;
    }
}
