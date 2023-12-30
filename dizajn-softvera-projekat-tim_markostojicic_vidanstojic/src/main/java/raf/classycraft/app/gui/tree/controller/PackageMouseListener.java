package raf.classycraft.app.gui.tree.controller;

import raf.classycraft.app.gui.tree.ClassyTreeImplementation;
import raf.classycraft.app.gui.view.DiagramView;
import raf.classycraft.app.gui.view.MainFrame;
import raf.classycraft.app.gui.view.PackageView;
import raf.classycraft.app.model.compositeAbstract.ClassyNode;
import raf.classycraft.app.model.compositeImplement.Diagram;
import raf.classycraft.app.model.compositeImplement.MyPackage;
import raf.classycraft.app.model.compositeImplement.Project;
import raf.classycraft.app.model.elementDiagram.Interclass;

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
            if(classyTreeImplementation.getSelectedNode().getClassyNode() instanceof MyPackage){
                MyPackage parentMyPackage = (MyPackage) classyTreeImplementation.getSelectedNode().getClassyNode();
                PackageView packageView = MainFrame.getInstance().getPackageView();
                for(ClassyNode child : parentMyPackage.getChildren()){
                    if(child instanceof Diagram){
                        if(parentMyPackage.findProject() != null && parentMyPackage.findProject() instanceof  Project) {
                            packageView.addTab(child.getName(), new DiagramView((Diagram) child));
                            packageView.setFlag(true);
                        }
                        Diagram diagram = (Diagram) child;
                        diagram.getSubscribers().add(MainFrame.getInstance().getCurrentDiagramView());
                    }
                    else if(child instanceof Interclass){
                        Interclass interclass = (Interclass) child;
                        interclass.getListOfSubscribers().add(MainFrame.getInstance().getCurrentDiagramView());
                    }
                }

            }

        }
    }
}
