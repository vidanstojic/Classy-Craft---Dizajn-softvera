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

    public Project(String author, URL filepath) {
        this.author = author;
        this.filepath = filepath;
    }

    @Override
    public void addChild(ClassyNode child) {
        children.add(child);
    }

    @Override
    public void removeChild(ClassyNode child) {
        children.remove(child);
    }
}
