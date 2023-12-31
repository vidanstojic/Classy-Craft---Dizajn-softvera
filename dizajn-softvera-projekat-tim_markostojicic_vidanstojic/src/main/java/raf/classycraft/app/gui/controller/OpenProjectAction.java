package raf.classycraft.app.gui.controller;

import raf.classycraft.app.core.ApplicationFramework;
import raf.classycraft.app.gui.view.MainFrame;
import raf.classycraft.app.model.compositeImplement.Diagram;
import raf.classycraft.app.model.compositeImplement.MyPackage;
import raf.classycraft.app.model.compositeImplement.Project;
import raf.classycraft.app.model.compositeImplement.ProjectExplorer;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.File;

public class OpenProjectAction extends AbstractClassyAction{
    public OpenProjectAction(){
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(
                KeyEvent.VK_O, ActionEvent.SHIFT_MASK));
        putValue(SMALL_ICON, loadIcon("/images/open project.png"));
        putValue(NAME, "Open");
        putValue(SHORT_DESCRIPTION, "Open project");
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        JFileChooser jfc = new JFileChooser();
        if ((MainFrame.getInstance().getClassyTree().getSelectedNode().getClassyNode() instanceof ProjectExplorer)){
        if (jfc.showOpenDialog(MainFrame.getInstance()) == JFileChooser.APPROVE_OPTION) {
            try {
                File file = jfc.getSelectedFile();
                Project p = (Project) ApplicationFramework.getInstance().getSerializer().loadProject(file);
                MainFrame.getInstance().getClassyTree().loadProject(p, ApplicationFramework.getInstance().getClassyRepository().getRoot());

            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }
        } else if (MainFrame.getInstance().getClassyTree().getSelectedNode().getClassyNode() instanceof MyPackage) {
            if (jfc.showOpenDialog(MainFrame.getInstance()) == JFileChooser.APPROVE_OPTION) {
                jfc.setCurrentDirectory(new File(System.getProperty("user.home") + File.separator + "Desktop" + "Dizajn" + "dizajn-softvera-projekat-tim_markostojicic_vidanstojic/dizajn-softvera-projekat-tim_markostojicic_vidanstojic/src/main/resources/template"));
                File file = jfc.getSelectedFile();
                Diagram diagram = (Diagram) ApplicationFramework.getInstance().getSerializer().loadDiagram(file);
                MainFrame.getInstance().getClassyTree().loadProject(diagram, ApplicationFramework.getInstance().getClassyRepository().getRoot());
            }
        }else if (MainFrame.getInstance().getClassyTree().getSelectedNode().getClassyNode() instanceof Diagram) {
                //jfc.setCurrentDirectory(new File(System.getProperty("user.home") + File.separator + "Desktop" + "Dizajn" + "dizajn-softvera-projekat-tim_markostojicic_vidanstojic/dizajn-softvera-projekat-tim_markostojicic_vidanstojic/src/main/resources/template"));
                File file = jfc.getSelectedFile();
                //Diagram diagram = (Diagram) ApplicationFramework.getInstance().getSerializer().loadDiagram(file);//OVDE BACA NULL VRV JER NEMAMO LOAD DIAGRAMELEMENT
                //MainFrame.getInstance().getClassyTree().loadProject(diagram, ApplicationFramework.getInstance().getClassyRepository().getRoot());

        }
    }
}
