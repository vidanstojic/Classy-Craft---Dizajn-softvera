package raf.classycraft.app.gui.controller;

import raf.classycraft.app.gui.view.MainFrame;
import raf.classycraft.app.model.compositeAbstract.ClassyNode;
import raf.classycraft.app.model.compositeAbstract.ClassyNodeComposite;
import raf.classycraft.app.model.compositeImplement.Diagram;

import raf.classycraft.app.model.compositeImplement.MyPackage;
import raf.classycraft.app.model.compositeImplement.Project;
import raf.classycraft.app.model.elementDiagram.Interclass;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class ExportCodeAction extends AbstractClassyAction {

    public ExportCodeAction() {
        putValue(SMALL_ICON, loadIcon("/images/exportCode.png"));
        putValue(NAME, "Export code");
        putValue(SHORT_DESCRIPTION, "Export your Code");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JFileChooser jfc = new JFileChooser();

        if (!(MainFrame.getInstance().getClassyTree().getSelectedNode().getClassyNode() instanceof Project)) return;

        Project project = (Project) MainFrame.getInstance().getClassyTree().getSelectedNode().getClassyNode();
        File projectFile = null;

        if (jfc.showSaveDialog(MainFrame.getInstance()) == JFileChooser.APPROVE_OPTION) {
            projectFile = jfc.getSelectedFile();

            if (!projectFile.exists()) {
                boolean success = projectFile.mkdirs();
                if (success) {
                    System.out.println("Folder successfully created at: " + projectFile.getAbsolutePath());
                } else {
                        System.err.println("Error creating folder.");
                    return;
                }
            } else {
                System.out.println("Folder already exists at: " + projectFile.getAbsolutePath());
            }

            for (ClassyNode child : project.getChildren()) {
                createSubFolder(projectFile, child);
            }
        }
    }



    private void createSubFolder(File parentFolder, ClassyNode child) {
        File subFolder = new File(parentFolder, child.getName());
        ClassyNodeComposite packageOrDiagram = (ClassyNodeComposite) child;
        for(ClassyNode classyNode : packageOrDiagram.getChildren()){
            if (!subFolder.exists()) {
                boolean success = subFolder.mkdirs();
                if (success) {
                    System.out.println("Subfolder successfully created at:" + subFolder.getAbsolutePath());
                    if(child instanceof MyPackage){
                        createSubFolder(subFolder, classyNode);
                    }
                    else if(child instanceof Diagram){
                        Diagram diagram = (Diagram) child;
                        for(ClassyNode diagramElement : diagram.getChildren()){
                            createTxtFile(subFolder, diagramElement);
                        }
                    }
                } else {
                    System.err.println("Error creating subfolder.");
                }
            }

        }
    }

    private void createTxtFile(File parentFolder, ClassyNode child) {
        if(!(child instanceof Interclass)) return;
        File txtFile = new File(parentFolder, child.getName() + ".txt");
        Interclass interclass = (Interclass) child;

        try {
            if (txtFile.createNewFile()) {
                System.out.println("Txt file successfully created at: " + txtFile.getAbsolutePath());
                writeContentToFile(txtFile, interclass);
            } else {
                System.err.println("Error creating txt file.");
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private void writeContentToFile(File txtFile, Interclass interclass) {
        try (FileWriter fileWriter = new FileWriter(txtFile);
             BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)) {

            bufferedWriter.write(interclass.toString());

            System.out.println("Content successfully written to txt file.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
