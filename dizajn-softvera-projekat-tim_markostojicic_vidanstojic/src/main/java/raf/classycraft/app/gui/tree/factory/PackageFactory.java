package raf.classycraft.app.gui.tree.factory;

import raf.classycraft.app.model.compositeAbstract.ClassyNode;
import raf.classycraft.app.model.compositeAbstract.ClassyNodeComposite;
import raf.classycraft.app.model.compositeImplement.MyPackage;

import java.util.Random;

public class PackageFactory extends FactoryChild {
    @Override
    public ClassyNode order(ClassyNodeComposite parent) {
        ClassyNode newNode = make(parent);
        return newNode;
    }

    @Override
    public ClassyNode make(ClassyNodeComposite parent) {
        return new MyPackage("MyPackage"+new Random().nextInt(100), parent);
    }
}
