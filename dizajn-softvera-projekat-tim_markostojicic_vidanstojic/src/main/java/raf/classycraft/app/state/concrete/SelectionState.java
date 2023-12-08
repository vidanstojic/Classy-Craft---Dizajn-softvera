package raf.classycraft.app.state.concrete;

import raf.classycraft.app.gui.view.DiagramView;
import raf.classycraft.app.gui.view.paint.ElementPainter;
import raf.classycraft.app.gui.view.paint.InterClassPainter;
import raf.classycraft.app.model.elementDiagram.Interclass;
import raf.classycraft.app.state.State;

import java.awt.*;
import java.awt.event.MouseEvent;

import static java.lang.Math.abs;

public class SelectionState implements State {

    private Rectangle rectangle;
    private Point point;
    private Interclass interclass;
    @Override
    public void stateMousePressed(MouseEvent e, DiagramView tempTab) {
        for(ElementPainter elementPainter : tempTab.getListOfSelectedPainters()){
            interclass = ((InterClassPainter) elementPainter).getInterclass();
            interclass.setColor(Color.BLACK);
        }///IZBACITI IZ LISTE SELEKTOVANIH
            rectangle = tempTab.getRectangle();
            point = new Point(e.getX(), e.getY());
            tempTab.repaint();
    }

    @Override
    public void stateMouseReleased(MouseEvent e, DiagramView tempTab) {

        rectangle.setSize(0,0);
        tempTab.repaint();
    }

    @Override
    public void stateMouseDragged(MouseEvent e, DiagramView tempTab) {
        rectangle.setRect(point.x, point.y, abs(point.x - e.getX()), abs(point.y - e.getY()));
        tempTab.setRectangle(rectangle);
        tempTab.repaint();
        for(ElementPainter elementPainter : tempTab.getListOfPainters()){
            if (elementPainter.getRectangle() == null)
                continue;
            if (tempTab.getRectangle().intersects(elementPainter.getRectangle())){
                interclass = ((InterClassPainter) elementPainter).getInterclass();
                interclass.setColor(Color.BLUE);
                tempTab.getListOfSelectedPainters().add(elementPainter);
                tempTab.repaint();
            }
        }



    }
}
