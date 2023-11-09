package raf.classycraft.app.model.compositeImplement;

import raf.classycraft.app.model.compositeAbstract.ClassyNode;
import raf.classycraft.app.model.compositeAbstract.ClassyNodeComposite;

public class Package extends ClassyNodeComposite {

    public Package(String name, ClassyNode parent){
        super.setName(name);
        super.setParent(parent);
    }

    @Override
    public void addChild(ClassyNode child) {
        if (child != null &&  child instanceof Diagrams){
            Diagrams diagrams = (Diagrams) child;
            if (!this.getChildren().contains(diagrams)){
                this.getChildren().add(diagrams);
            }
        }
        else if (child != null &&  child instanceof Package){
            Package packageChild = (Package) child;
            if (!this.getChildren().contains(packageChild)){
                this.getChildren().add(packageChild);
            }
        }
    }

    @Override
    public void removeChild(ClassyNode child) {
        if(this.getChildren().contains(child)){
            this.getChildren().remove(child);
        }
    }

}
