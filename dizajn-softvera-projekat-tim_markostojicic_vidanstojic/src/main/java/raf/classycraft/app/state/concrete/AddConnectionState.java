package raf.classycraft.app.state.concrete;

import raf.classycraft.app.gui.view.DiagramView;
import raf.classycraft.app.gui.view.paint.*;
import raf.classycraft.app.model.compositeImplement.Package;
import raf.classycraft.app.model.elementDiagram.Connection;
import raf.classycraft.app.model.elementDiagram.ConnectionMode;
import raf.classycraft.app.model.elementDiagram.Interclass;
import raf.classycraft.app.model.elementDiagram.concreteConnections.Generalization;
import raf.classycraft.app.model.elementDiagram.concreteInterclass.ClassInterClass;
import raf.classycraft.app.state.State;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;

public class AddConnectionState implements State {

    private Object selection;

    private Point startPoint;
    private Point endPoint;

    private Connection connection;

    private Interclass classFrom;
    private Interclass classTo;

    private Double minDistance = Double.MAX_VALUE;

    private Point closestConnectionDot;

    private ConnectionPainter painter;

    private ConnectionMode connectionMode = ConnectionMode.NONE;
    @Override
    public void stateMousePressed(MouseEvent e, DiagramView tempTab) {
        if(connectionMode == ConnectionMode.NONE) {
            Object[] selectionValues = {"Dependency", "Composition", "Aggregation", "Generalisation"};
            String initialSelection = "Generalisation";
            selection = JOptionPane.showInputDialog(null, "What type of connection do you want to add?",
                    "Add new connection", JOptionPane.QUESTION_MESSAGE, null, selectionValues, initialSelection);
            connectionMode = ConnectionMode.START_CONNECTION;
            return;
        }
        if(connectionMode == ConnectionMode.START_CONNECTION) {
            if (selection == null) {
                return;
            } else if (selection.equals("Generalisation")) {
                System.out.println("Dodavanje generalizacije");
                Point point = new Point(e.getX(), e.getY());
                boolean flagForAdd = false;
                for (ElementPainter elementPainter : tempTab.getListOfPainters()) {
                    if (elementPainter.elementAt(point) == true) {
                        if (elementPainter instanceof ClassPainter || elementPainter instanceof EnumPainter || elementPainter instanceof InterfacePainter) {
                            startPoint = ((InterClassPainter) elementPainter).getInterclass().getConnectionDots().get(0);
                            classFrom = ((InterClassPainter) elementPainter).getInterclass();
                            connection = new Generalization(Color.BLACK, 2, tempTab.getLine2D());
                            connection.setFrom(classFrom);
                            flagForAdd = true;
                        }
                    }
                }
                if (flagForAdd == false) return;
                painter = new GeneralizationPainter((Generalization) connection);
                tempTab.getListOfPainters().add(painter);
                tempTab.getDiagram().addChild(connection);
                tempTab.repaint();

            } else if (selection.equals("Dependency")) {
                System.out.println("Dodavanje dependency");
            } else if (selection.equals("Composition")) {
                System.out.println("Dodavanje kompozicije");
            } else if (selection.equals("Aggregation")) {
                System.out.println("Dodavanje agregacije");
            }

        }

    }

    @Override
    public void stateMouseReleased(MouseEvent e, DiagramView tempTab) {
        if (connectionMode == ConnectionMode.DRAW_CONNECTION) {
            boolean flag = false;
            minDistance = Double.MAX_VALUE;
            for (ElementPainter elementPainter : tempTab.getListOfPainters()) {
                if (elementPainter.elementAt(endPoint) == true) {
                    if (elementPainter instanceof ClassPainter || elementPainter instanceof EnumPainter || elementPainter instanceof InterfacePainter) {
                        flag = true;
                        classTo = ((InterClassPainter) elementPainter).getInterclass();
                        connection.setTo(classTo);
                        for (Point connectionDot : classTo.getConnectionDots()) {
                            double distance = Math.sqrt(Math.pow(startPoint.x - connectionDot.x, 2) + Math.pow(startPoint.y - connectionDot.y, 2));
                            if (distance < minDistance) {
                                minDistance = distance;
                                closestConnectionDot = connectionDot;
                            }
                        }
                        endPoint = closestConnectionDot;


                        tempTab.lineRefresh(startPoint, endPoint);
                        connection.setLine2D(tempTab.getLine2D());
                        connectionMode = ConnectionMode.NONE;
                        tempTab.repaint();
                    }
                }
            }
            if (flag == false) {
                tempTab.setLine2D(null);  // Resetovanje Line2D
                connection.setLine2D(tempTab.getLine2D());
                tempTab.getDiagram().removeChild(connection);
                tempTab.getListOfPainters().remove(painter);
                tempTab.repaint();
            }
        }
    }

    @Override
    public void stateMouseDragged(MouseEvent e, DiagramView tempTab) {
        connectionMode = ConnectionMode.DRAW_CONNECTION;
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
