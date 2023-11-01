package raf.classycraft.app.model.compositeAbstract;

import java.util.ArrayList;
import java.util.List;

public abstract class ClassyNodeComposite extends ClassyNode{

    protected List<ClassyNode> children = new ArrayList<>();

    public ClassyNodeComposite() {

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
