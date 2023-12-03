package raf.classycraft.app.gui.controller;

import raf.classycraft.app.gui.view.DiagramView;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class MyMouseListener implements MouseListener, MouseMotionListener {

    private DiagramView diagramView;

    public MyMouseListener(DiagramView diagramView){
        this.diagramView = diagramView;
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        // potrebno
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // potrebno
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {
        // potrebno
    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }
}
