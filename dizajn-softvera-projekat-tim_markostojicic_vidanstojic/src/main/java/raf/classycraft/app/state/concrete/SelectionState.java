package raf.classycraft.app.state.concrete;

import raf.classycraft.app.core.ApplicationFramework;
import raf.classycraft.app.gui.view.DiagramView;
import raf.classycraft.app.gui.view.paint.ClassPainter;
import raf.classycraft.app.gui.view.paint.ElementPainter;
import raf.classycraft.app.gui.view.paint.EnumPainter;
import raf.classycraft.app.gui.view.paint.InterfacePainter;
import raf.classycraft.app.model.compositeImplement.Diagram;
import raf.classycraft.app.model.elementDiagram.DiagramElement;
import raf.classycraft.app.model.elementDiagram.Interclass;
import raf.classycraft.app.model.elementDiagram.concreteInterclass.ClassInterClass;
import raf.classycraft.app.model.elementDiagram.concreteInterclass.EnumInterclass;
import raf.classycraft.app.model.elementDiagram.concreteInterclass.InterfaceInterclass;
import raf.classycraft.app.model.messageGenerator.EventTypes;
import raf.classycraft.app.model.messageGenerator.Type;
import raf.classycraft.app.observer.NotificationDiagramView;
import raf.classycraft.app.observer.TypeDiagramView;
import raf.classycraft.app.state.State;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class SelectionState implements State {


    private Interclass interclass;
    @Override
    public void stateMousePressed(MouseEvent e, DiagramView tempTab) {

        Point point = new Point(e.getX(), e.getY());
            for (ElementPainter elementPainter : tempTab.getListOfPainters()) {
                if (elementPainter.elementAt(point) == true) {
                    if (elementPainter instanceof ClassPainter) {
                        ClassPainter classPainter = (ClassPainter) elementPainter;
                        interclass = classPainter.getClassInterClass();
                        ClassInterClass classInterClass = (ClassInterClass) interclass;
                        classInterClass.setColor(Color.BLUE);
                        tempTab.repaint();
                        break;
                    } else if (elementPainter instanceof EnumPainter) {
                        EnumPainter enumPainter = (EnumPainter) elementPainter;
                        interclass = enumPainter.getEnumInterclass();
                        EnumInterclass enumInterclass = (EnumInterclass) interclass;
                        enumInterclass.setColor(Color.BLUE);
                        tempTab.repaint();
                        break;
                    } else if (elementPainter instanceof InterfacePainter) {
                        InterfacePainter interfacePainter = (InterfacePainter) elementPainter;
                        interclass = interfacePainter.getInterfaceInterclass();
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
            interclass.setColor(Color.BLACK);
            tempTab.repaint();
            interclass = null;
        }
    }

    @Override
    public void stateMouseDragged(MouseEvent e, DiagramView tempTab) {
        if(interclass != null) {
            Point point = new Point(e.getX(), e.getY());
            interclass.setPoint(point);
            tempTab.repaint();
        }
    }
}
