package raf.classycraft.app.state.concrete;

import raf.classycraft.app.gui.view.DiagramView;
import raf.classycraft.app.gui.view.paint.ClassPainter;
import raf.classycraft.app.gui.view.paint.ConnectionPainter;
import raf.classycraft.app.gui.view.paint.ElementPainter;
import raf.classycraft.app.gui.view.paint.GeneralizationPainter;
import raf.classycraft.app.model.elementDiagram.Connection;
import raf.classycraft.app.model.elementDiagram.Interclass;
import raf.classycraft.app.model.elementDiagram.concreteConnections.Generalization;
import raf.classycraft.app.model.elementDiagram.concreteInterclass.ClassInterClass;
import raf.classycraft.app.state.State;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;

public class AddConnectionState implements State {

    private boolean flagForSelection = false;
    private Object selection;

    private Point startPoint;
    private Point endPoint;

    private Connection connection;

    private Interclass classFrom;
    private Interclass classTo;

    private Double minDistance = Double.MAX_VALUE;

    private Point closestConnectionDot;
    @Override
    public void stateMousePressed(MouseEvent e, DiagramView tempTab) {
        if(flagForSelection == false && selection == null) {
            Object[] selectionValues = {"Dependency", "Composition", "Aggregation", "Generalisation"};
            String initialSelection = "Generalisation";
            selection = JOptionPane.showInputDialog(null, "What type of connection do you want to add?",
                    "Add new connection", JOptionPane.QUESTION_MESSAGE, null, selectionValues, initialSelection);
            flagForSelection = true;
        }
        if (selection == null) {
            return;
        }
        else if (selection.equals("Generalisation")) {
            System.out.println("Dodavanje generalizacije");
            Point point = new Point(e.getX(), e.getY());
            boolean flagForAdd = false;
            for(ElementPainter elementPainter : tempTab.getListOfPainters()){
                if(elementPainter.elementAt(point) == true){
                    if(elementPainter instanceof  ClassPainter){
                        startPoint = ((ClassPainter) elementPainter).getClassInterClass().getConnectionDots().get(0);
                        classFrom = ((ClassPainter) elementPainter).getClassInterClass();
                        endPoint = startPoint;
                        tempTab.lineRefresh(startPoint, endPoint);
                        connection = new Generalization(Color.BLACK, 2, tempTab.getLine2D());
                        connection.setFrom(classFrom);
                        flagForAdd = true;
                    }
                }
            }
            if (flagForAdd == false) return;
            GeneralizationPainter generalizationPainter = new GeneralizationPainter((Generalization) connection);
            tempTab.getListOfPainters().add(generalizationPainter);
            tempTab.getDiagram().addChild(connection);

        }
        else if (selection.equals("Dependency")) {
            System.out.println("Dodavanje dependency");
        }
        else if (selection.equals("Composition")) {
            System.out.println("Dodavanje kompozicije");
        }
        else if (selection.equals("Aggregation")){
            System.out.println("Dodavanje agregacije");
        }


    }

    @Override
    public void stateMouseReleased(MouseEvent e, DiagramView tempTab) {
        flagForSelection = false;
        selection = null;
        boolean flag = false;
        minDistance = Double.MAX_VALUE;
        for(ElementPainter elementPainter : tempTab.getListOfPainters()){
            if(elementPainter.elementAt(endPoint) == true){
                if(elementPainter instanceof  ClassPainter){
                    flag = true;
                    classTo = ((ClassPainter) elementPainter).getClassInterClass();
                    connection.setTo(classTo);
                    for(Point connectionDot : classTo.getConnectionDots()){
                        double distance = Math.sqrt(Math.pow(startPoint.x - connectionDot.x,2) + Math.pow(startPoint.y - connectionDot.y, 2));
                        if(distance < minDistance){
                            minDistance = distance;
                            closestConnectionDot = connectionDot;
                        }
                    }
                    endPoint = closestConnectionDot;


                    tempTab.lineRefresh(startPoint,endPoint);
                    connection.setLine2D(tempTab.getLine2D());
                    tempTab.repaint();
                }
            }
        }
        if(flag == false){
            tempTab.setLine2D(null);
            connection.setLine2D(tempTab.getLine2D());
            tempTab.repaint();
        }

    }

    @Override
    public void stateMouseDragged(MouseEvent e, DiagramView tempTab) {
        endPoint = e.getPoint();
        for(Point connectionDot : classFrom.getConnectionDots()){
            double distance = Math.sqrt(Math.pow(connectionDot.x - endPoint.x, 2) + Math.pow(connectionDot.y - endPoint.y, 2));
            if(distance < minDistance){
                minDistance = distance;
                closestConnectionDot = connectionDot;
            }
        }
        startPoint = closestConnectionDot;
        tempTab.lineRefresh(startPoint,endPoint);
        connection.setLine2D(tempTab.getLine2D());
        tempTab.repaint();
    }




}
