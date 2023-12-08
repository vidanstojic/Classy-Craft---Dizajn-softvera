package raf.classycraft.app.state.concrete;

import raf.classycraft.app.gui.view.DiagramView;
import raf.classycraft.app.gui.view.paint.ClassPainter;
import raf.classycraft.app.gui.view.paint.ElementPainter;
import raf.classycraft.app.gui.view.paint.EnumPainter;
import raf.classycraft.app.gui.view.paint.InterfacePainter;
import raf.classycraft.app.model.elementDiagram.Interclass;
import raf.classycraft.app.model.elementDiagram.concreteInterclass.ClassInterClass;
import raf.classycraft.app.model.elementDiagram.concreteInterclass.EnumInterclass;
import raf.classycraft.app.model.elementDiagram.concreteInterclass.InterfaceInterclass;
import raf.classycraft.app.state.State;

import java.awt.*;
import java.awt.event.MouseEvent;

public class MoveState implements State {


    private Interclass interclass;
    private Point oldPoint;
    private Point newPoint;
    @Override
    public void stateMousePressed(MouseEvent e, DiagramView tempTab) {

        Point point = new Point(e.getX(), e.getY());
            for (ElementPainter elementPainter : tempTab.getListOfPainters()) {
                if (elementPainter.elementAt(point) == true) {
                    if (elementPainter instanceof ClassPainter) {
                        ClassPainter classPainter = (ClassPainter) elementPainter;
                        interclass = classPainter.getClassInterClass();
                        oldPoint = new Point(interclass.getPoint());
                        ClassInterClass classInterClass = (ClassInterClass) interclass;
                        classInterClass.setColor(Color.BLUE);
                        tempTab.repaint();
                        break;
                    } else if (elementPainter instanceof EnumPainter) {
                        EnumPainter enumPainter = (EnumPainter) elementPainter;
                        interclass = enumPainter.getEnumInterclass();
                        oldPoint = new Point(interclass.getPoint());
                        EnumInterclass enumInterclass = (EnumInterclass) interclass;
                        enumInterclass.setColor(Color.BLUE);
                        tempTab.repaint();
                        break;
                    } else if (elementPainter instanceof InterfacePainter) {
                        InterfacePainter interfacePainter = (InterfacePainter) elementPainter;
                        interclass = interfacePainter.getInterfaceInterclass();
                        oldPoint = new Point(interclass.getPoint());
                        InterfaceInterclass interfaceInterclass = (InterfaceInterclass) interclass;
                        interfaceInterclass.setColor(Color.BLUE);
                        tempTab.repaint();
                        break;
                    }
                }
            }
    }

    @Override
    public void stateMouseReleased(MouseEvent e, DiagramView tempTab) {
        if (interclass != null) {
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
        if(interclass != null) {
            newPoint = new Point(e.getX(), e.getY());
            interclass.setPoint(newPoint);
            tempTab.repaint();
        }
    }
}
