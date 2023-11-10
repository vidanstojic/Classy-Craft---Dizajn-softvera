package raf.classycraft.app.model.compositeImplement;

import raf.classycraft.app.model.compositeAbstract.ClassyNode;

public class Diagrams extends ClassyNode {
    public Diagrams(String name, ClassyNode parent){
        super.setName(name);
        super.setParent(parent);
    }


    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public String getName() {
        return super.getName();
    }

    @Override
    public void setName(String name) {
        super.setName(name);
    }

    @Override
    public ClassyNode getParent() {
        return super.getParent();
    }

    @Override
    public void setParent(ClassyNode parent) {
        super.setParent(parent);
    }
}
