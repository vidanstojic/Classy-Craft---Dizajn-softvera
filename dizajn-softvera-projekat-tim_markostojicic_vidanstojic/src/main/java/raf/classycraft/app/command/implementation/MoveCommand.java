package raf.classycraft.app.command.implementation;

import raf.classycraft.app.command.AbstractCommand;
import raf.classycraft.app.gui.tree.ClassyTreeImplementation;
import raf.classycraft.app.gui.tree.model.ClassyTreeItem;
import raf.classycraft.app.gui.view.DiagramView;
import raf.classycraft.app.gui.view.paint.InterClassPainter;
import raf.classycraft.app.model.elementDiagram.Interclass;

import java.awt.*;

public class MoveCommand extends AbstractCommand {
    private DiagramView diagramView;
    private Interclass interclass;
    private InterClassPainter interClassPainter;
    private Point oldPoint;
    private Point newPoint;
    private Point specialPoint;
    public MoveCommand(DiagramView diagramView, Interclass interclass, Point oldPoint, Point specialPoint, Point newPoint){
        this.diagramView = diagramView;
        this.interclass = interclass;
        this.oldPoint = oldPoint;
        this.newPoint = newPoint;
        this.specialPoint = specialPoint;
    }
    public MoveCommand(DiagramView diagramView, Interclass interclass, Point oldPoint, Point newPoint){
        this.diagramView = diagramView;
        this.interclass = interclass;
        this.oldPoint = oldPoint;
        this.newPoint = newPoint;
    }
    @Override
    public void doCommand() {
        if(interclass == null || diagramView == null) return;

        interclass.setPoint(newPoint);
        interclass.setSpecialPoint(newPoint);
        diagramView.repaint();
    }

    @Override
    public void undoCommand() {
        if(interclass == null || diagramView == null) return;

        interclass.setPoint(oldPoint);
        interclass.setSpecialPoint(oldPoint);
        diagramView.repaint();
    }

    public void setOldPoint(Point oldPoint) {
        this.oldPoint = oldPoint;
    }

    public Point getOldPoint() {
        return oldPoint;
    }

    public void setNewPoint(Point newPoint) {
        this.newPoint = newPoint;
    }
}
