package raf.classycraft.app.state.concrete;

import raf.classycraft.app.gui.view.DiagramView;
import raf.classycraft.app.gui.view.paint.*;
import raf.classycraft.app.model.elementDiagram.Interclass;
import raf.classycraft.app.model.elementDiagram.concreteInterclass.ClassInterClass;
import raf.classycraft.app.model.elementDiagram.concreteInterclass.EnumInterclass;
import raf.classycraft.app.model.elementDiagram.concreteInterclass.InterfaceInterclass;
import raf.classycraft.app.state.State;

import java.awt.*;
import java.awt.event.MouseEvent;

import static java.lang.Math.abs;

public class MoveState implements State {


    private Interclass interclass;
    private Point oldPoint;
    private Point newPoint;
    private Point polazna;
    private Point pointProba;
    @Override
    public void stateMousePressed(MouseEvent e, DiagramView tempTab) {

        Point point = new Point(e.getX(), e.getY());
        pointProba = point;
            for (ElementPainter elementPainter : tempTab.getListOfPainters()) {
                if (!tempTab.getListOfSelectedPainters().isEmpty()){
                    //oldPoint = new Point(interclass.getPoint());
                    break;
                }
                if (elementPainter.elementAt(point) == true) {
                    if (elementPainter instanceof ClassPainter || elementPainter instanceof EnumPainter || elementPainter instanceof InterfacePainter) {
                        interclass = ((InterClassPainter) elementPainter).getInterclass();
                        oldPoint = new Point(interclass.getPoint());
                        interclass.setColor(Color.BLUE);
                        tempTab.repaint();
                        break;
                    }
                }
            }
    }

    @Override
    public void stateMouseReleased(MouseEvent e, DiagramView tempTab) {
        if (!tempTab.getListOfSelectedPainters().isEmpty()){
            for (ElementPainter elementPainter1 : tempTab.getListOfSelectedPainters()){
                for (ElementPainter elementPainter2 : tempTab.getListOfPainters()) {
                    if (elementPainter1.getRectangle() == null || elementPainter1.getRectangle().equals(elementPainter2.getRectangle()))
                        continue;
                    interclass = ((InterClassPainter) elementPainter1).getInterclass();
                    if (interclass.getRectangle().intersects(elementPainter2.getRectangle())) {
                       interclass.setPoint(interclass.getSpecialPoint());
                    }
                }
                interclass.setColor(Color.BLACK);
                interclass.setSpecialPoint(interclass.getPoint());
                tempTab.repaint();
            }

            tempTab.removeListOfSelectedPainters(tempTab.getListOfSelectedPainters());
            interclass = null;
        }
        if (interclass != null && tempTab.getListOfSelectedPainters().isEmpty()) {
            if(tempTab.getListOfPainters().size() > 1) {
                for (ElementPainter elementPainter : tempTab.getListOfPainters()) {
                    if (elementPainter.getRectangle() == null)
                        continue;
                    if(elementPainter.getRectangle().equals(interclass.getRectangle()))
                        continue;
                    if (elementPainter.getRectangle().intersects(interclass.getRectangle())) {
                        interclass.setPoint(oldPoint);
                        break;
                    }
                }
            }

            interclass.setColor(Color.BLACK);
            tempTab.repaint();
            interclass = null;
        }
    }

    @Override
    public void stateMouseDragged(MouseEvent e, DiagramView tempTab) {
        newPoint = new Point(e.getX(), e.getY());
        int weight = newPoint.x - pointProba.x;
        int height = newPoint.y - pointProba.y;
        if (!tempTab.getListOfSelectedPainters().isEmpty()){
            for (ElementPainter elementPainter : tempTab.getListOfSelectedPainters()){
                interclass = ((InterClassPainter) elementPainter).getInterclass();
                Point point2 = new Point(interclass.getSpecialPoint());
                Point point = new Point(point2.x + weight, point2.y + height);
                interclass.setPoint(point);
                tempTab.repaint();
            }

        }

        if(interclass != null && tempTab.getListOfSelectedPainters().isEmpty()) {
            interclass.setPoint(newPoint);
            tempTab.repaint();
        }
    }
}
