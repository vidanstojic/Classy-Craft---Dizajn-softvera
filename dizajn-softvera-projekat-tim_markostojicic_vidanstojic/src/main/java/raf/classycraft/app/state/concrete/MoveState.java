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
            for (ElementPainter elementPainter : tempTab.getListOfPainters()){
                if (elementPainter.getRectangle() == null)
                    continue;
                if(elementPainter.getRectangle().equals(interclass.getRectangle()))
                    continue;
                if (elementPainter.getRectangle().intersects(interclass.getRectangle())) {
                    Point point = new Point(interclass.getPoint().x + abs(pointProba.x - newPoint.x), interclass.getPoint().y + abs(pointProba.y - newPoint.y));
                    interclass.setPoint(point);
                }
            }
            interclass.setColor(Color.BLACK);
            tempTab.repaint();
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
        if (!tempTab.getListOfSelectedPainters().isEmpty()){
            for (ElementPainter elementPainter : tempTab.getListOfSelectedPainters()){
                interclass = ((InterClassPainter) elementPainter).getInterclass();
                /*System.out.println(interclass.getPoint());
                System.out.println(newPoint);
                System.out.println(pointProba);
                System.out.println(abs(pointProba.x - newPoint.x));
                //interclass.setPoint(new Point(interclass.getPoint().x + (pointProba.x - newPoint.x), interclass.getPoint().y + (pointProba.y - newPoint.y)));
                System.out.println(interclass.getPoint().x +  " " + interclass.getPoint().y);
                //interclass.getPoint().x + abs(pointProba.x - newPoint.x), interclass.getPoint().y + abs(pointProba.y - newPoint.y));
                //interclass.setPoint(new Point(interclass.getPoint().x + e.getX() , interclass.getPoint().y + e.getY()));
                */
                interclass.setPoint(newPoint);
                tempTab.repaint();
            }

        }

        if(interclass != null && tempTab.getListOfSelectedPainters().isEmpty()) {
            interclass.setPoint(newPoint);
            tempTab.repaint();
        }
    }
}
