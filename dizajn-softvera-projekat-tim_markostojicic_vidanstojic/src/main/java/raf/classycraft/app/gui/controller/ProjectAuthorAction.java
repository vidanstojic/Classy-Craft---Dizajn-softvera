package raf.classycraft.app.gui.controller;

import raf.classycraft.app.gui.tree.model.ClassyTreeItem;
import raf.classycraft.app.gui.view.MainFrame;
import raf.classycraft.app.model.compositeImplement.Project;
import raf.classycraft.app.model.compositeImplement.ProjectExplorer;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class ProjectAuthorAction extends AbstractClassyAction {

    public ProjectAuthorAction() {
        putValue(SMALL_ICON, loadIcon("/images/author.png"));
        putValue(NAME, "Project Author");
        putValue(SHORT_DESCRIPTION, "Set the project author");
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        ClassyTreeItem selected = (ClassyTreeItem) MainFrame.getInstance().getClassyTree().getSelectedNode();
        if (selected.getClassyNode() instanceof Project) {
            Project project = (Project) selected.getClassyNode();
            String userInput = JOptionPane.showInputDialog("Input name of the author:");
            if (!(project.getAuthor() != null)) {
                if (userInput == null) {
                    project.setAuthor(null);
                } else {
                    project.setAuthor(userInput);
                }
            }
        }
    }
}