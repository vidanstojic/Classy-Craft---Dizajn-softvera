package raf.classycraft.app.gui.controller;

import raf.classycraft.app.core.ApplicationFramework;
import raf.classycraft.app.gui.view.MainFrame;
import raf.classycraft.app.model.compositeImplement.Project;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.File;

public class SaveAsAction extends AbstractClassyAction{
    public SaveAsAction(){
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(
                KeyEvent.VK_S, ActionEvent.SHIFT_MASK));
        putValue(SMALL_ICON, loadIcon("/images/save.png"));
        putValue(NAME, "Save");
        putValue(SHORT_DESCRIPTION, "Save as");
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        JFileChooser jfc = new JFileChooser();

        if (!(MainFrame.getInstance().getClassyTree().getSelectedNode().getClassyNode() instanceof Project)) return;

        Project project = (Project) MainFrame.getInstance().getClassyTree().getSelectedNode().getClassyNode();
        File projectFile = null;

        if (!project.isChanged()) {
            return;
        }

        if (project.getFilepath() == null || project.getFilepath().isEmpty()) {
            if (jfc.showSaveDialog(MainFrame.getInstance()) == JFileChooser.APPROVE_OPTION) {
                projectFile = jfc.getSelectedFile();
                project.setFilepath(projectFile.getPath());
            }

        }
        else{
            projectFile = new File(project.getFilepath());
            project.setFilepath(projectFile.getPath());
        }


        ApplicationFramework.getInstance().getSerializer().saveProject(project);

        project.setChanged(false);
    }
}
