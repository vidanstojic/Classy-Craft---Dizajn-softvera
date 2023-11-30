package raf.classycraft.app.gui.tree.factory;

import raf.classycraft.app.model.compositeAbstract.ClassyNode;
import raf.classycraft.app.model.compositeAbstract.ClassyNodeComposite;
import raf.classycraft.app.model.compositeImplement.Package;
import raf.classycraft.app.model.compositeImplement.Project;
import raf.classycraft.app.model.compositeImplement.ProjectExplorer;

import javax.swing.*;

public class FactoryUtils {

    public static ClassyNode initNode(ClassyNodeComposite parent) {
        if (parent instanceof ProjectExplorer) {
            FactoryChild factoryChild = new ProjectFactory();
            return factoryChild.order(parent);
        }
        else if (parent instanceof Project) {
            FactoryChild factoryChild = new PackageFactory();
            return factoryChild.order(parent);
        }
        else if (parent instanceof Package) {
            Object[] selectionValues = {"Package", "Diagram"};
            String initialSelection = "Diagram";
            Object selection = JOptionPane.showInputDialog(null, "Do you want a new subpackage, or would you like a new diagram?",
                    "Add new item", JOptionPane.QUESTION_MESSAGE, null, selectionValues, initialSelection);
            if (selection == null) {
                return null;
            }
            else if (selection.equals("Diagram")) {
                FactoryChild factoryChild = new DiagramFactory();
                return factoryChild.order(parent);
            }
            else if (selection.equals("Package")) {
                FactoryChild factoryChild = new PackageFactory();
                return factoryChild.order(parent);
            }
        }
        return null;

    }
}