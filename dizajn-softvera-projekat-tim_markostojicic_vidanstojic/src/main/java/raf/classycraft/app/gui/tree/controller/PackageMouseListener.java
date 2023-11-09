package raf.classycraft.app.gui.tree.controller;

import raf.classycraft.app.gui.tree.ClassyTreeImplementation;
import raf.classycraft.app.gui.tree.model.ClassyTreeItem;
import raf.classycraft.app.model.compositeImplement.Package;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.security.interfaces.RSAPrivateCrtKey;

public class PackageMouseListener extends MouseAdapter {

    ClassyTreeImplementation classyTreeImplementation;
    public PackageMouseListener(ClassyTreeImplementation classyTreeImplementation){
        this.classyTreeImplementation = classyTreeImplementation;

    }

    @Override
    public void mouseClicked(MouseEvent e) {

        if(e.getClickCount() == 2) {
            if(classyTreeImplementation.getSelectedNode().getClassyNode() instanceof Package){
                System.out.println("Marko");
            }

        }
    }
}
