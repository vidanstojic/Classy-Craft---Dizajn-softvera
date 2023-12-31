package raf.classycraft.app.gui.controller;

import raf.classycraft.app.core.ApplicationFramework;
import raf.classycraft.app.gui.view.MainFrame;
import raf.classycraft.app.model.compositeImplement.Diagram;
import raf.classycraft.app.model.compositeImplement.Project;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

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

        //if (!(MainFrame.getInstance().getClassyTree().getSelectedNode().getClassyNode() instanceof Project)) return;
        if (MainFrame.getInstance().getClassyTree().getSelectedNode().getClassyNode() instanceof Project) {
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

            } else {
                projectFile = new File(project.getFilepath());
                project.setFilepath(projectFile.getPath());
            }


            ApplicationFramework.getInstance().getSerializer().saveProject(project);

            project.setChanged(false);
        }else if (MainFrame.getInstance().getClassyTree().getSelectedNode().getClassyNode() instanceof Diagram){
            Diagram diagram = (Diagram) MainFrame.getInstance().getClassyTree().getSelectedNode().getClassyNode();
            File projectFile = null;
            /*if (!project.isChanged()) {
                return;
            }*/

            if (diagram.findProject().getFilepath() == null || diagram.findProject().getFilepath().isEmpty()) {
                if (jfc.showSaveDialog(MainFrame.getInstance()) == JFileChooser.APPROVE_OPTION) {
                    projectFile = jfc.getSelectedFile();
                    diagram.findProject().setFilepath(projectFile.getPath());
                    /*try {
                        FileWriter fileWriter = new FileWriter("dizajn-softvera-projekat-tim_markostojicic_vidanstojic/dizajn-softvera-projekat-tim_markostojicic_vidanstojic/src/main/resources/template");
                        fileWriter.write();
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
*/
                }

            } else {
                projectFile = new File(diagram.findProject().getFilepath());
                diagram.findProject().setFilepath(projectFile.getPath());
            }


            ApplicationFramework.getInstance().getSerializer().saveProject(diagram);

            //project.setChanged(false);
        }
    }
}
