package raf.classycraft.app.state.concrete;

import raf.classycraft.app.gui.view.DiagramView;
import raf.classycraft.app.state.State;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.geom.AffineTransform;

public class ZoomOutState implements State {
    @Override
    public void stateMousePressed(MouseEvent e, DiagramView tempTab) {
        AffineTransform affineTransform = tempTab.getAffineTransform();
        if (affineTransform.getScaleX() < 0.35 && affineTransform.getScaleY() < 0.35)return;
        affineTransform.scale(1.0 / 1.1, 1.0 / 1.1);
        tempTab.setPreferredSize(new Dimension((int) (tempTab.getWidth() * (1.0 / 1.1)), (int) (tempTab.getHeight() * (1.0 / 1.1))));
        tempTab.repaint();
    }

    @Override
    public void stateMouseReleased(MouseEvent e, DiagramView tempTab) {

    }

    @Override
    public void stateMouseDragged(MouseEvent e, DiagramView tempTab) {

    }
}
