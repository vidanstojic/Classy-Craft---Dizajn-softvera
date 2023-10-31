package raf.classycraft.app.model.CompositeAbstract;

import java.util.List;

public abstract class ClassyNodeComposite extends ClassyNode{

    private List<ClassyNode> children;

    public ClassyNodeComposite(List<ClassyNode> children) {
        this.children = children;
    }

    ///Metode
    public abstract void addChild(ClassyNode child);
    public abstract void removeChild(ClassyNode child);

    public List<ClassyNode> getChildren() {
        return children;
    }

    public void setChildren(List<ClassyNode> children) {///Mislim da ovo ni ne treba
        this.children = children;
    }
}
