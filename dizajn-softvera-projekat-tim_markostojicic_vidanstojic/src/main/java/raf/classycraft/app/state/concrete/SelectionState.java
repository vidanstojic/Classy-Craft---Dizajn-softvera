package raf.classycraft.app.state.concrete;

import raf.classycraft.app.core.ApplicationFramework;
import raf.classycraft.app.gui.view.DiagramView;
import raf.classycraft.app.gui.view.paint.ClassPainter;
import raf.classycraft.app.gui.view.paint.ElementPainter;
import raf.classycraft.app.model.compositeImplement.Diagram;
import raf.classycraft.app.model.elementDiagram.DiagramElement;
import raf.classycraft.app.model.elementDiagram.concreteInterclass.ClassInterClass;
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

    private Rectangle rectangle;
    private DiagramElement diagramElement;
    private ClassInterClass classInterClass;
    @Override
    public void stateMousePressed(MouseEvent e, DiagramView tempTab) {

        Point point = new Point(e.getX(), e.getY());
        List<ElementPainter> elementRemoveList = new ArrayList<>();
        for(ElementPainter elementPainter : tempTab.getListOfPainters()) {
            if (elementPainter.elementAt(point) == true) {
                if (elementPainter instanceof ClassPainter){
                    ClassPainter classPainter = (ClassPainter) elementPainter;
                    rectangle = classPainter.getRectangle();
                    classInterClass = classPainter.getClassInterClass();
                    classInterClass.setColor(Color.BLUE);
                    elementRemoveList.add(elementPainter);
                    tempTab.repaint();
                    diagramElement = (DiagramElement) classInterClass;
                }
            }
        }

    }

    @Override
    public void stateMouseReleased(MouseEvent e, DiagramView tempTab) {

        Diagram diagram = (Diagram) tempTab.getDiagram();
        NotificationDiagramView notificationDiagramView = new NotificationDiagramView(TypeDiagramView.ADD_DIAGRAM_ELEMENT, diagramElement);
        diagram.notifySub(notificationDiagramView);
        classInterClass.setColor(Color.BLACK);
        tempTab.repaint();
    }

    @Override
    public void stateMouseDragged(MouseEvent e, DiagramView tempTab) {
        Point point = new Point(e.getX(), e.getY());
        rectangle.setLocation(point);
        classInterClass.setPoint(point);
        tempTab.repaint();
    }
}
