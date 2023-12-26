package raf.classycraft.app.gui.controller;

import raf.classycraft.app.gui.view.DiagramView;
import raf.classycraft.app.gui.view.MainFrame;
import raf.classycraft.app.gui.view.paint.ConnectionPainter;
import raf.classycraft.app.gui.view.paint.ElementPainter;
import raf.classycraft.app.gui.view.paint.InterClassPainter;
import raf.classycraft.app.model.elementDiagram.Connection;
import raf.classycraft.app.model.elementDiagram.Interclass;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

public class RedoAction extends AbstractClassyAction{
    public RedoAction(){
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(
                KeyEvent.VK_R, ActionEvent.SHIFT_MASK));
        putValue(SMALL_ICON, loadIcon("/images/redo.png"));
        putValue(NAME, "Redo");
        putValue(SHORT_DESCRIPTION, "Redo");
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (MainFrame.getInstance().getCurrentDiagramView() == null)return;
        deselect(MainFrame.getInstance().getCurrentDiagramView());
        MainFrame.getInstance().getCurrentDiagramView().getCommandManager().doCommand();
    }
    private void deselect(DiagramView tempTab){
        if(!tempTab.getListOfSelectedPainters().isEmpty()) {
            List<ElementPainter> helpList = new ArrayList<>();
            Interclass interclass;
            Connection connection;
            for (ElementPainter elementPainter : tempTab.getListOfSelectedPainters()) {
                if (elementPainter.getRectangle() != null) {
                    interclass = ((InterClassPainter) elementPainter).getInterclass();
                    interclass.setColor(Color.BLACK);
                } else if (elementPainter.getLine2D() != null) {
                    connection = ((ConnectionPainter) elementPainter).getConnection();
                    connection.setColor(Color.BLACK);
                }
                helpList.add(elementPainter);
            }
            for (ElementPainter elementPainter : helpList) {
                tempTab.getListOfSelectedPainters().remove(elementPainter);
            }
            tempTab.repaint();
        }
    }
}
