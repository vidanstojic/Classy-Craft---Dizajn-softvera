package raf.classycraft.app.model.compositeAbstract;

public abstract class ClassyNode {

    private String name;
    private ClassyNode parent;

    public ClassyNode() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ClassyNode getParent() {
        return parent;
    }

    public void setParent(ClassyNode parent) {
        this.parent = parent;
    }


}
