package raf.classycraft.app.gui.tree.factory;

import raf.classycraft.app.model.compositeAbstract.ClassyNode;
import raf.classycraft.app.model.compositeAbstract.ClassyNodeComposite;

public abstract class FactoryChild {
    abstract public ClassyNode order(ClassyNodeComposite parent);
    abstract public ClassyNode make(ClassyNodeComposite parent);
}
