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
import java.nio.file.Paths;

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
                File file = jfc.getSelectedFile();
                Diagram diagram = (Diagram) ApplicationFramework.getInstance().getSerializer().loadDiagram(file);
                MainFrame.getInstance().getClassyTree().loadProject(diagram, ApplicationFramework.getInstance().getClassyRepository().getRoot());
            }
        } else if (MainFrame.getInstance().getClassyTree().getSelectedNode().getClassyNode() instanceof Diagram) {
            jfc.setCurrentDirectory(new File(String.valueOf(Paths.get(System.getProperty("user.dir")).toAbsolutePath())));
            if (jfc.showOpenDialog(MainFrame.getInstance()) == JFileChooser.APPROVE_OPTION) {
                Diagram diagram = (Diagram) MainFrame.getInstance().getClassyTree().getSelectedNode().getClassyNode();
                if (diagram.getChildren().isEmpty()) {
                    File file = jfc.getSelectedFile();
                    Diagram loadDiagram = (Diagram) ApplicationFramework.getInstance().getSerializer().loadDiagram(file);
                    diagram.setName(loadDiagram.getName());
                    diagram.setChildren(loadDiagram.getChildren());
                    MainFrame.getInstance().getClassyTree().loadDiagramElement(diagram);
                }
            }
        }
    }
}
