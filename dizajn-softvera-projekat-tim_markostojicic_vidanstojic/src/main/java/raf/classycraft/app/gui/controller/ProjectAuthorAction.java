package raf.classycraft.app.gui.controller;

import raf.classycraft.app.core.ApplicationFramework;
import raf.classycraft.app.gui.tree.model.ClassyTreeItem;
import raf.classycraft.app.gui.view.MainFrame;
import raf.classycraft.app.model.compositeImplement.Project;
import raf.classycraft.app.model.messageGenerator.EventTypes;
import raf.classycraft.app.model.messageGenerator.Type;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class ProjectAuthorAction extends AbstractClassyAction {

    public ProjectAuthorAction() {
        putValue(SMALL_ICON, loadIcon("/images/author.png"));
        putValue(NAME, "Project Author");
        putValue(SHORT_DESCRIPTION, "Set the project author");
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        ClassyTreeItem selected = (ClassyTreeItem) MainFrame.getInstance().getClassyTree().getSelectedNode();
        if (selected == null) {
            ApplicationFramework.getInstance().getMessageGenerator().generateMessage(EventTypes.NODE_MUST_BE_SELECTED, Type.WARNING);
            return;
        }
        ClassyTreeItem parent = (ClassyTreeItem) selected.getParent();
        if(parent == null){
            ApplicationFramework.getInstance().getMessageGenerator().generateMessage(EventTypes.NODE_MUST_BE_SELECTED, Type.WARNING);
            return;
        }
        if (selected.getClassyNode() instanceof Project) {
            Project project = (Project) selected.getClassyNode();
            String userInput = JOptionPane.showInputDialog("Input name of the author:");

                if (userInput == null) {
                    project.setAuthor(null);
                } else {
                    project.setAuthor(userInput);
                }

        } else {
            ApplicationFramework.getInstance().getMessageGenerator().generateMessage(EventTypes.ONLY_PROJECT_HAS_AUTHOR, Type.WARNING);
        }
    }
}