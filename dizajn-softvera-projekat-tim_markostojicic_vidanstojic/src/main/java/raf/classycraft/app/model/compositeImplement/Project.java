package raf.classycraft.app.model.compositeImplement;

import raf.classycraft.app.model.compositeAbstract.ClassyNode;
import raf.classycraft.app.model.compositeAbstract.ClassyNodeComposite;

import java.net.URL;

public class Project extends ClassyNodeComposite {

    private String author;
    private URL filepath;

    public Project(){

    }

    public Project(String autor, URL filepath) {
        this.author = autor;
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
