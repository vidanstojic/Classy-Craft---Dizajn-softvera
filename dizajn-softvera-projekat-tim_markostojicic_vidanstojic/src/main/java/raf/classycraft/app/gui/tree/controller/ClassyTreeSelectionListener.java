package raf.classycraft.app.gui.tree.controller;

import raf.classycraft.app.gui.tree.model.ClassyTreeItem;
import raf.classycraft.app.gui.view.MainFrame;
import raf.classycraft.app.gui.view.PackageView;
import raf.classycraft.app.model.compositeImplement.Package;
import raf.classycraft.app.model.compositeImplement.Project;

import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.TreePath;

public class ClassyTreeSelectionListener implements TreeSelectionListener {

    @Override
    public void valueChanged(TreeSelectionEvent e) {
        TreePath path = e.getPath();
        ClassyTreeItem treeItemSelected = (ClassyTreeItem)path.getLastPathComponent();
        if(treeItemSelected.getClassyNode() instanceof Project){
            Project project = (Project) treeItemSelected.getClassyNode();
            MainFrame.getInstance().getPackageView().getAuthor().setText(project.getAuthor());
            MainFrame.getInstance().getPackageView().getNameOfProject().setText(project.getName());
        }
        if(treeItemSelected.getClassyNode() instanceof Package){
            PackageView.flag = false;
        }
        System.out.println("Selektovan cvor:"+ treeItemSelected.getClassyNode().getName());
        System.out.println("getPath: "+e.getPath());
    }
}
