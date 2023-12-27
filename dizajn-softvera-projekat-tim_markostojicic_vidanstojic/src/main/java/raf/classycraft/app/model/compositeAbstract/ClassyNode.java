package raf.classycraft.app.model.compositeAbstract;

import com.fasterxml.jackson.annotation.JsonIgnore;

public abstract class ClassyNode {

    private String name;
    @JsonIgnore
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
