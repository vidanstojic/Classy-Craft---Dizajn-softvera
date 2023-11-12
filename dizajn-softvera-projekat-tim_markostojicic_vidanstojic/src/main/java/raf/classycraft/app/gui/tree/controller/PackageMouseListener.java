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
                Package parentPackage = (Package) classyTreeImplementation.getSelectedNode().getClassyNode();
                PackageView packageView = MainFrame.getInstance().getPackageView();//Mislim da moramo da imamo vise PackageView, ovim imamo samo jedan
                for(ClassyNode child : parentPackage.getChildren()){
                    if(child instanceof Diagrams){
                        if(parentPackage.findProject() != null && parentPackage.findProject() instanceof  Project) {
                            Project project = parentPackage.findProject();
                            //packageView.setNameA(project.getAuthor());
                            //packageView.setNameP(project.getName());
                            //PackageView packageView = new PackageView(project.getName(),project.getAuthor());
                            //MainFrame.getInstance().setPackageView(packageView);
                            packageView.addTab(child.getName(), new DiagramView(project.getName(), project.getAuthor()));
                            packageView.getAuthor().setText(project.getAuthor());
                            packageView.getNameOfProject().setText(project.getName());
                            packageView.setFlag(true);
                        }
                    }
                }
            }

        }
    }
}
