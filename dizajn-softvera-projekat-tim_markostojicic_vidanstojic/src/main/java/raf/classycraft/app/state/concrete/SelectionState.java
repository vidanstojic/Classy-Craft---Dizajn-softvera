package raf.classycraft.app.state.concrete;

import raf.classycraft.app.gui.view.DiagramView;
import raf.classycraft.app.state.State;

import java.awt.*;
import java.awt.event.MouseEvent;

import static java.lang.Math.abs;

public class SelectionState implements State {

    private Rectangle rectangle;
    private Point point;
    @Override
    public void stateMousePressed(MouseEvent e, DiagramView tempTab) {
            rectangle = tempTab.getRectangle();
            point = new Point(e.getX(), e.getY());
    }

    @Override
    public void stateMouseReleased(MouseEvent e, DiagramView tempTab) {

    }

    @Override
    public void stateMouseDragged(MouseEvent e, DiagramView tempTab) {
            rectangle.setRect(point.x, point.y, abs(point.x - e.getX()), abs(point.y - e.getY()));
            tempTab.setRectangle(rectangle);
            tempTab.repaint();
    }
}
