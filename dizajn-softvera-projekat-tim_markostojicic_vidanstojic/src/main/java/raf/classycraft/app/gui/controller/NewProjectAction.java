package raf.classycraft.app.gui.controller;

import raf.classycraft.app.core.ApplicationFramework;
import raf.classycraft.app.gui.tree.model.ClassyTreeItem;
import raf.classycraft.app.gui.view.MainFrame;
import raf.classycraft.app.model.messageGenerator.EventTypes;
import raf.classycraft.app.model.messageGenerator.Type;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class NewProjectAction extends AbstractClassyAction{


    public NewProjectAction(){
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(
                KeyEvent.VK_N, ActionEvent.CTRL_MASK));
        putValue(SMALL_ICON, loadIcon("/images/plus.png"));
        putValue(NAME, "Add Item");
        putValue(SHORT_DESCRIPTION, "Add Item");
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        ClassyTreeItem selected = (ClassyTreeItem) MainFrame.getInstance().getClassyTree().getSelectedNode();
        if(selected == null) {
            ApplicationFramework.getInstance().getMessageGenerator().generateMessage(EventTypes.NODE_MUST_BE_SELECTED, Type.WARNING);
            return;
        }
        MainFrame.getInstance().getClassyTree().addChild(selected);
    }
}
