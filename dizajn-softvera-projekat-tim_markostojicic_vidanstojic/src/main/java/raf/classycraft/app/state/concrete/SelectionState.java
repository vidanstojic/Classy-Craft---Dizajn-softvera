package raf.classycraft.app.state.concrete;

import raf.classycraft.app.gui.view.DiagramView;
import raf.classycraft.app.gui.view.paint.ConnectionPainter;
import raf.classycraft.app.gui.view.paint.ElementPainter;
import raf.classycraft.app.gui.view.paint.InterClassPainter;
import raf.classycraft.app.model.elementDiagram.Connection;
import raf.classycraft.app.model.elementDiagram.Interclass;
import raf.classycraft.app.state.State;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.abs;

public class SelectionState implements State {

    private Rectangle rectangle;
    private Point point;
    private Interclass interclass;
    private Connection connection;
    private List<ElementPainter> helpList = new ArrayList<>();
    @Override
    public void stateMousePressed(MouseEvent e, DiagramView tempTab) {
        if(!tempTab.getListOfSelectedPainters().isEmpty()) {
            for (ElementPainter elementPainter : tempTab.getListOfSelectedPainters()) {
                if (elementPainter.getRectangle() != null) {
                    interclass = ((InterClassPainter) elementPainter).getInterclass();
                    interclass.setColor(Color.BLACK);
                } else if (elementPainter.getLine2D() != null) {
                    connection = ((ConnectionPainter) elementPainter).getConnection();
                    connection.setColor(Color.BLACK);
                }
            }
            for (ElementPainter elementPainter : helpList) {
                tempTab.getListOfSelectedPainters().remove(elementPainter);
            }
        }
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
        int x = Math.min(point.x,e.getX());
        int y = Math.min(point.y, e.getY());
        int width = Math.abs(e.getX() - point.x);
        int height = Math.abs(e.getY() - point.y);///ovim sam napravio da se selekcija vrsi u svakom pravcu
        rectangle.setRect(x, y, width, height);
        tempTab.setRectangle(rectangle);
        tempTab.repaint();
        for(ElementPainter elementPainter : tempTab.getListOfPainters()){
            if (elementPainter.getRectangle() == null) continue;
            if (tempTab.getRectangle().intersects(elementPainter.getRectangle())){
                interclass = ((InterClassPainter) elementPainter).getInterclass();
                interclass.setColor(Color.BLUE);
                if (tempTab.getListOfSelectedPainters().contains(elementPainter)) continue;
                tempTab.getListOfSelectedPainters().add(elementPainter);
                helpList.add(elementPainter);
                tempTab.repaint();
            }
        }for (ElementPainter elementPainter : tempTab.getListOfPainters()){
            if (elementPainter.getLine2D() == null) continue;
            if (tempTab.getRectangle().intersectsLine(elementPainter.getLine2D())){
                connection = ((ConnectionPainter) elementPainter).getConnection();
                connection.setColor(Color.BLUE);
                if (tempTab.getListOfSelectedPainters().contains(elementPainter)) continue;
                tempTab.getListOfSelectedPainters().add(elementPainter);
                helpList.add(elementPainter);
                tempTab.repaint();
            }
        }
    }
}
