package raf.classycraft.app.state.concrete;

import raf.classycraft.app.gui.view.DiagramView;
import raf.classycraft.app.state.State;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.geom.AffineTransform;

public class ZoomInState implements State {

    private double scaleFactor = 1.0;
    private Point point;
    @Override
    public void stateMousePressed(MouseEvent e, DiagramView tempTab) {
        point = e.getPoint();
        AffineTransform affineTransform = tempTab.getAffineTransform();
        if (affineTransform.getScaleX() > 2.0 && affineTransform.getScaleY() > 2.0)return;
        affineTransform.scale(1.1, 1.1);
        double zoom = affineTransform.getScaleX();
        double razlika = zoom - scaleFactor;
        //
        // scaleFactor = zoom;
        double deltaX = point.x * razlika;
        double deltaY = point.y * razlika;
        affineTransform.translate(deltaX,deltaY);
        tempTab.setPreferredSize(new Dimension((int) (tempTab.getWidth() * 1.1), (int) (tempTab.getHeight() * 1.1)));
        tempTab.repaint();
    }

    @Override
    public void stateMouseReleased(MouseEvent e, DiagramView tempTab) {

    }

    @Override
    public void stateMouseDragged(MouseEvent e, DiagramView tempTab) {

    }
}
