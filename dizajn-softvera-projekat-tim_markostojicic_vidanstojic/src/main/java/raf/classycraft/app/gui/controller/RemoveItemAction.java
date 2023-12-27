package raf.classycraft.app.gui.controller;

import raf.classycraft.app.core.ApplicationFramework;
import raf.classycraft.app.gui.tree.model.ClassyTreeItem;
import raf.classycraft.app.gui.view.MainFrame;
import raf.classycraft.app.model.compositeImplement.MyPackage;
import raf.classycraft.app.model.compositeImplement.Project;
import raf.classycraft.app.model.compositeImplement.ProjectExplorer;
import raf.classycraft.app.model.messageGenerator.EventTypes;
import raf.classycraft.app.model.messageGenerator.Type;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class RemoveItemAction extends AbstractClassyAction{

    public RemoveItemAction(){
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(
                KeyEvent.VK_N, ActionEvent.SHIFT_MASK));
        putValue(SMALL_ICON, loadIcon("/images/delete.png"));
        putValue(NAME, "Remove Item");
        putValue(SHORT_DESCRIPTION, "Remove Item");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        ClassyTreeItem selected = (ClassyTreeItem) MainFrame.getInstance().getClassyTree().getSelectedNode();
        if(selected == null){
            ApplicationFramework.getInstance().getMessageGenerator().generateMessage(EventTypes.NODE_MUST_BE_SELECTED, Type.WARNING);
            return;
        }
        else if(selected.getClassyNode() instanceof ProjectExplorer){
            ApplicationFramework.getInstance().getMessageGenerator().generateMessage(EventTypes.NODE_CANNOT_BE_DELETED, Type.ERROR);
            return;
        }

        if(selected.getClassyNode() instanceof MyPackage){
            MyPackage selectedMyPackage = (MyPackage)selected.getClassyNode();
            selectedMyPackage.packageDeleted(selectedMyPackage);
        }
        else if(selected.getClassyNode() instanceof Project){
            Project project = (Project) selected.getClassyNode();
            project.projectDeleted(project);
            MainFrame.getInstance().getPackageView().getAuthor().setText("");
            MainFrame.getInstance().getPackageView().getNameOfProject().setText("");
        }
        MainFrame.getInstance().getClassyTree().removeChild(selected);
    }
}
