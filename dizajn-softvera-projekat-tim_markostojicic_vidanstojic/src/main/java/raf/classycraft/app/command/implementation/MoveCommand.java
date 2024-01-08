package raf.classycraft.app.command.implementation;

import raf.classycraft.app.command.AbstractCommand;
import raf.classycraft.app.gui.view.DiagramView;
import raf.classycraft.app.model.elementDiagram.Interclass;

import java.awt.*;
import java.util.List;
import java.util.ArrayList;

public class MoveCommand extends AbstractCommand {
    private DiagramView diagramView;
    private Interclass interclass;
    private List<Interclass> interclassList = new ArrayList<>();
    private List<Point> interclassPoint = new ArrayList<>();
    private List<Point> oldPointList = new ArrayList<>();
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
    public MoveCommand(DiagramView diagramView, List<Interclass> interclassList, List<Point> pointList, List<Point> oldPointList){
        this.diagramView = diagramView;
        this.interclassList.addAll(interclassList);
        this.interclassPoint.addAll(pointList);
        this.oldPointList.addAll(oldPointList);
    }
    @Override
    public void doCommand() {
        if((interclass == null && interclassList.isEmpty()) || diagramView == null) return;
        if(!(interclassList.isEmpty())){
            int cnt = 0;
            for (Interclass interclass1 : interclassList) {
                    interclass1.setPoint(interclassPoint.get(cnt));
                    interclass1.setSpecialPoint(interclassPoint.get(cnt++));
            }
            diagramView.repaint();
        }
        else {
            interclass.setPoint(newPoint);
            interclass.setSpecialPoint(newPoint);
            diagramView.repaint();
        }
    }

    @Override
    public void undoCommand() {
        if((interclass == null && oldPointList.isEmpty()) || diagramView == null) return;
        if (!(oldPointList.isEmpty())){
            int cnt = 0;
            for (Interclass interclass1 : interclassList){
                    interclass1.setSpecialPoint(oldPointList.get(cnt));
                    interclass1.setPoint(oldPointList.get(cnt++));
            }
            diagramView.repaint();
        }
        else {
            interclass.setPoint(oldPoint);
            interclass.setSpecialPoint(oldPoint);
            diagramView.repaint();
        }
    }


}
