package raf.classycraft.app.gui.tree.factory;

import raf.classycraft.app.model.compositeAbstract.ClassyNode;
import raf.classycraft.app.model.compositeAbstract.ClassyNodeComposite;
import raf.classycraft.app.model.compositeImplement.Diagrams;

import java.util.Random;

public class DiagramFactory extends FactoryChild{
    @Override
    public ClassyNode order(ClassyNodeComposite parent) {
        ClassyNode newNode = make(parent);
        return newNode;
    }

    @Override
    public ClassyNode make(ClassyNodeComposite parent) {
        return new Diagrams("Diagram"+new Random().nextInt(100), parent);
    }
}
