package raf.classycraft.app.model.compositeImplement;

import raf.classycraft.app.model.compositeAbstract.ClassyNode;
import raf.classycraft.app.model.compositeAbstract.ClassyNodeComposite;

import java.net.URL;

public class Project extends ClassyNodeComposite {

    private String author;
    private URL filepath;

    public Project(String name, ClassyNode parent){
        super.setName(name);
        super.setParent(parent);
    }

    public Project(String author) {
        this.author = author;
    }

    @Override
    public void addChild(ClassyNode child) {
        if (child != null &&  child instanceof Package){
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
