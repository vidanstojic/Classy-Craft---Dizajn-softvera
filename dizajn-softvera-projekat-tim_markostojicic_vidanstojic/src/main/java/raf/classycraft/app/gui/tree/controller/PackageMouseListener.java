package raf.classycraft.app.gui.tree.controller;

import raf.classycraft.app.gui.tree.ClassyTreeImplementation;
import raf.classycraft.app.gui.view.DiagramView;
import raf.classycraft.app.gui.view.MainFrame;
import raf.classycraft.app.gui.view.PackageView;
import raf.classycraft.app.model.compositeAbstract.ClassyNode;
import raf.classycraft.app.model.compositeAbstract.ClassyNodeComposite;
import raf.classycraft.app.model.compositeImplement.Diagrams;
import raf.classycraft.app.model.compositeImplement.Package;
import raf.classycraft.app.model.compositeImplement.Project;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class PackageMouseListener extends MouseAdapter {

    ClassyTreeImplementation classyTreeImplementation;
    public PackageMouseListener(ClassyTreeImplementation classyTreeImplementation){
        this.classyTreeImplementation = classyTreeImplementation;

    }

    @Override
    public void mouseClicked(MouseEvent e) {

        if(e.getClickCount() == 2) {
            if(classyTreeImplementation.getSelectedNode().getClassyNode() instanceof Package){
                ClassyNodeComposite parentPackage = (ClassyNodeComposite) classyTreeImplementation.getSelectedNode().getClassyNode();
                PackageView packageView = MainFrame.getInstance().getPackageView();
                for(ClassyNode child : parentPackage.getChildren()){
                    if(child instanceof Diagrams){
                        Project project = (Project) child.getParent().getParent();
                        packageView.addTab(child.getName(), new DiagramView(project.getName(), project.getAuthor()));
                    }
                }
            }

        }
    }
}
