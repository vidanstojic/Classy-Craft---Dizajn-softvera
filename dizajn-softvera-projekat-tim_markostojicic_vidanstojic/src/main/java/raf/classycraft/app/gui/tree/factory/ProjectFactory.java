package raf.classycraft.app.gui.tree.factory;

import raf.classycraft.app.model.compositeAbstract.ClassyNode;
import raf.classycraft.app.model.compositeAbstract.ClassyNodeComposite;
import raf.classycraft.app.model.compositeImplement.Project;

import java.util.Random;

public class ProjectFactory extends FactoryChild{


    public ProjectFactory() {

    }

    @Override
    public ClassyNode order(ClassyNodeComposite parent) {
        ClassyNode newNode = make(parent);
        return newNode;
    }

    @Override
    public ClassyNode make(ClassyNodeComposite parent) {
        return new Project("Project" +new Random().nextInt(100),parent);
    }
}
