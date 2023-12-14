package raf.classycraft.app.gui.tree.view;

import raf.classycraft.app.gui.tree.model.ClassyTreeItem;
import raf.classycraft.app.model.compositeImplement.Diagram;
import raf.classycraft.app.model.compositeImplement.Package;
import raf.classycraft.app.model.compositeImplement.Project;
import raf.classycraft.app.model.compositeImplement.ProjectExplorer;
import raf.classycraft.app.model.elementDiagram.DiagramElement;

import javax.swing.*;
import javax.swing.tree.DefaultTreeCellRenderer;
import java.awt.*;
import java.net.URL;

public class ClassyTreeCellRenderer extends DefaultTreeCellRenderer {
    @Override
    public Component getTreeCellRendererComponent(JTree tree, Object value, boolean sel, boolean expanded, boolean leaf, int row, boolean hasFocus) {
        super.getTreeCellRendererComponent(tree, value, sel, expanded, leaf, row, hasFocus);

        URL imageURL = null;


        if (((ClassyTreeItem)value).getClassyNode() instanceof ProjectExplorer) {
            imageURL = getClass().getResource("/images/projectExplorer.png");
        }
        else if (((ClassyTreeItem)value).getClassyNode() instanceof Project) {
            imageURL = getClass().getResource("/images/project.png");
        }
        else if (((ClassyTreeItem)value).getClassyNode() instanceof Package) {
            imageURL = getClass().getResource("/images/package.png");
        }
        else if (((ClassyTreeItem)value).getClassyNode() instanceof Diagram) {
            imageURL = getClass().getResource("/images/diagram.png");
        }
        else if(((ClassyTreeItem)value).getClassyNode() instanceof DiagramElement){
            imageURL = getClass().getResource("/images/element.png");
        }

        Icon icon = null;
        if (imageURL != null)
            icon = new ImageIcon(imageURL);
        setIcon(icon);

        return this;

    }
}
