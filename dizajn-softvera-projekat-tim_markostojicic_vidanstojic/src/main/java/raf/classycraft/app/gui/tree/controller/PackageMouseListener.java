package raf.classycraft.app.gui.tree.controller;

import raf.classycraft.app.gui.tree.model.ClassyTreeItem;
import raf.classycraft.app.model.compositeImplement.Package;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.security.interfaces.RSAPrivateCrtKey;

public class PackageMouseListener extends MouseAdapter {

    ClassyTreeItem classyTreeItem;
    public PackageMouseListener(Object lastComponentObject){
        if(lastComponentObject instanceof DefaultMutableTreeNode){
            DefaultMutableTreeNode lastCompNode = (DefaultMutableTreeNode)lastComponentObject;
            this.classyTreeItem = (ClassyTreeItem) lastComponentObject;
        }

    }

    @Override
    public void mouseClicked(MouseEvent e) {

        if(e.getClickCount() == 2 && classyTreeItem != null) {
            if(classyTreeItem.getClassyNode() instanceof Package){
                System.out.println("Marko");
            }
        }
    }
}
