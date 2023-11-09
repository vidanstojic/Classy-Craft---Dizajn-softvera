package raf.classycraft.app.gui.tree.view;

import raf.classycraft.app.gui.tree.ClassyTreeImplementation;
import raf.classycraft.app.gui.tree.controller.ClassyTreeCellEditor;
import raf.classycraft.app.gui.tree.controller.ClassyTreeSelectionListener;
import raf.classycraft.app.gui.tree.controller.PackageMouseListener;
import raf.classycraft.app.gui.view.MainFrame;

import javax.swing.*;
import javax.swing.tree.DefaultTreeModel;


public class ClassyTreeView extends JTree {

    public ClassyTreeView(DefaultTreeModel defaultTreeModel){
        setModel(defaultTreeModel);
        ClassyTreeCellRenderer treeCellRenderer = new ClassyTreeCellRenderer();
        addTreeSelectionListener(new ClassyTreeSelectionListener());
        addMouseListener(new PackageMouseListener((ClassyTreeImplementation) MainFrame.getInstance().getClassyTree()));
        setCellEditor(new ClassyTreeCellEditor(this, treeCellRenderer));
        setCellRenderer(treeCellRenderer);
        setEditable(true);
    }
}
