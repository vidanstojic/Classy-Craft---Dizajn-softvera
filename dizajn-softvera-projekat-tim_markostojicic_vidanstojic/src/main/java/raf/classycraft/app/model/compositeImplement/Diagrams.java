package raf.classycraft.app.model.compositeImplement;

import raf.classycraft.app.model.compositeAbstract.ClassyNode;

public class Diagrams extends ClassyNode {
    public Diagrams(String name, ClassyNode parent){
        super.setName(name);
        super.setParent(parent);
    }
}
