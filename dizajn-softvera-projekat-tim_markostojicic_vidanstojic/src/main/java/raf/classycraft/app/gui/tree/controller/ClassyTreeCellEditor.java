package raf.classycraft.app.gui.tree.controller;

import raf.classycraft.app.gui.tree.model.ClassyTreeItem;
import raf.classycraft.app.model.compositeAbstract.ClassyNode;
import raf.classycraft.app.model.compositeImplement.Diagrams;
import raf.classycraft.app.model.compositeImplement.Package;
import raf.classycraft.app.model.compositeImplement.ProjectExplorer;

import javax.swing.*;
import javax.swing.tree.DefaultTreeCellEditor;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.TreeCellEditor;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.util.EventObject;

public class ClassyTreeCellEditor extends DefaultTreeCellEditor implements ActionListener {

    private Object clickedOn = null;
    private JTextField edit = null;

    public ClassyTreeCellEditor(JTree tree, DefaultTreeCellRenderer renderer) {
        super(tree, renderer);
    }

    @Override
    public Component getTreeCellEditorComponent(JTree tree, Object value, boolean isSelected, boolean expanded, boolean leaf, int row) {
        clickedOn = value;
        edit = new JTextField(value.toString());
        edit.addActionListener(this);
        return edit;
    }

    public boolean isCellEditable(EventObject obj){
        if(obj instanceof MouseEvent){
            ClassyTreeItem clicked = (ClassyTreeItem) clickedOn;
            if(((MouseEvent)obj).getClickCount() == 3){
                return true;
            }
        }
        return false;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(!(clickedOn instanceof ClassyTreeItem)){
            return;
        }
        ClassyTreeItem clicked = (ClassyTreeItem) clickedOn;
        if(clicked.getClassyNode() instanceof ProjectExplorer){
            return;
        }
        String odlName = clicked.getClassyNode().getName();
        clicked.setName(e.getActionCommand());
        if(clicked.getClassyNode() instanceof Diagrams){
            Package packageParent = (Package) clicked.getClassyNode().getParent();
            packageParent.nameChangedDiagram(clicked.getClassyNode(), odlName);
        }
    }


}
