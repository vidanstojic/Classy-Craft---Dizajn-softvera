package raf.classycraft.app.model.CompositeImplement;

import raf.classycraft.app.model.CompositeAbstract.ClassyNode;
import raf.classycraft.app.model.CompositeAbstract.ClassyNodeComposite;

import java.util.List;

public class ProjectExplorer extends ClassyNodeComposite {

    private static ProjectExplorer insance;

    private ProjectExplorer() {

    }

    public static ProjectExplorer getInstance(){
        if(insance == null){
            insance = new ProjectExplorer();
        }
        return insance;
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
