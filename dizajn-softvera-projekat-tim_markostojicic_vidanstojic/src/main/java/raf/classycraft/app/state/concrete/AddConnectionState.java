package raf.classycraft.app.state.concrete;

import raf.classycraft.app.gui.view.DiagramView;
import raf.classycraft.app.gui.view.paint.*;
import raf.classycraft.app.model.elementDiagram.Connection;
import raf.classycraft.app.model.elementDiagram.ConnectionMode;
import raf.classycraft.app.model.elementDiagram.Interclass;
import raf.classycraft.app.model.elementDiagram.classContent.Visibility;
import raf.classycraft.app.model.elementDiagram.concreteConnections.*;
import raf.classycraft.app.state.State;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

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

    private Rectangle rectangle;

    private boolean flagForConnectionInfo = false;

    private ConnectionMode connectionMode = ConnectionMode.NONE;
    @Override
    public void stateMousePressed(MouseEvent e, DiagramView tempTab) {
        connection = null;
        painter  = null;
        rectangle = tempTab.getRectangle();
        rectangle.setRect(e.getX(), e.getY(), 3, 3);
        tempTab.setRectangle(rectangle);
        deselect(tempTab);
        if(connectionMode == ConnectionMode.NONE) {
            boolean askUser = false;
            Point point = new Point(e.getX(), e.getY());
            for (ElementPainter elementPainter : tempTab.getListOfPainters()) {
                if((elementPainter.elementAt(point) == true) && elementPainter instanceof InterClassPainter){
                    System.out.println("Marko car");
                    askUser = true;
                }

            }
            if(askUser == false) return;
            Object[] selectionValues = {"Dependency", "Composition", "Aggregation", "Generalisation"};
            String initialSelection = "Generalisation";
            selection = JOptionPane.showInputDialog(null, "What type of connection do you want to add?",
                    "Add new connection", JOptionPane.QUESTION_MESSAGE, null, selectionValues, initialSelection);
            if (selection == null) {
                return;
            }

            flagForConnectionInfo = false;
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
                            ConnectionInfo connectionInfo = new ConnectionInfo("Generalisation connection");
                            connection.setConnectionInfo(connectionInfo);
                            connection.setClassFrom(classFrom);
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
                Point point = new Point(e.getX(), e.getY());
                boolean flagForAdd = false;
                int brojac = 0;
                for (ElementPainter elementPainter : tempTab.getListOfPainters()) {
                    if (elementPainter.elementAt(point) == true) {
                        if (elementPainter instanceof ClassPainter || elementPainter instanceof EnumPainter || elementPainter instanceof InterfacePainter) {
                            startPoint = ((InterClassPainter) elementPainter).getInterclass().getConnectionDots().get(0);
                            classFrom = ((InterClassPainter) elementPainter).getInterclass();
                            connection = new Dependency(Color.BLACK, 2, tempTab.getLine2D());

                            if(flagForConnectionInfo == false){
                                String nameOfAttribute = JOptionPane.showInputDialog("Name of the attribute:");
                                if(nameOfAttribute == null || nameOfAttribute.length() == 0) return;
                                Object[] selectionValuesVisibility = {"Public", "Private", "Protected", "Default"};
                                String initialSelectionVisibility = "Public";
                                Object visibility = JOptionPane.showInputDialog(null, "What is your attribute visibility?",
                                        "Visibility", JOptionPane.QUESTION_MESSAGE, null, selectionValuesVisibility, initialSelectionVisibility);
                                String cardinality = JOptionPane.showInputDialog("Cardinality:");
                                if(visibility == null) return;
                                Visibility visibilityEnum;
                                if (visibility == "Public") {
                                    visibilityEnum = Visibility.PUBLIC;
                                } else if (visibility == "Private") {
                                    visibilityEnum = Visibility.PRIVATE;
                                } else if (visibility == "Protected") {
                                    visibilityEnum = Visibility.PROTECTED;
                                } else {
                                    visibilityEnum = Visibility.DEFAULT;
                                }
                                if(cardinality == null || cardinality.length() == 0) return;


                                ConnectionInfo connectionInfo = new ConnectionInfo("Composition", nameOfAttribute, cardinality, visibilityEnum);
                                connection.setConnectionInfo(connectionInfo);


                                flagForConnectionInfo = true;

                            }


                            connection.setClassFrom(classFrom);
                            flagForAdd = true;
                        }
                    }
                }
                if (flagForAdd == false) return;

                painter = new DependancyPainter((Dependency) connection);
                tempTab.getListOfPainters().add(painter);
                tempTab.getDiagram().addChild(connection);
                tempTab.repaint();
            } else if (selection.equals("Composition")) {
                System.out.println("Dodavanje kompozicije");
               Point point = new Point(e.getX(), e.getY());
                boolean flagForAdd = false;
                for (ElementPainter elementPainter : tempTab.getListOfPainters()) {
                    if (elementPainter.elementAt(point) == true) {
                        if (elementPainter instanceof ClassPainter || elementPainter instanceof EnumPainter || elementPainter instanceof InterfacePainter) {
                            startPoint = ((InterClassPainter) elementPainter).getInterclass().getConnectionDots().get(0);
                            classFrom = ((InterClassPainter) elementPainter).getInterclass();
                            connection = new Composition(Color.BLACK, 2, tempTab.getLine2D());
                            connection.setClassFrom(classFrom);
                            flagForAdd = true;
                        }
                    }
                }
                if (flagForAdd == false) return;
                painter = new CompositionPainter((Composition) connection);
                tempTab.getListOfPainters().add(painter);
                tempTab.getDiagram().addChild(connection);



                tempTab.repaint();
            } else if (selection.equals("Aggregation")) {
                System.out.println("Dodavanje agregacije");
                Point point = new Point(e.getX(), e.getY());
                boolean flagForAdd = false;
                for (ElementPainter elementPainter : tempTab.getListOfPainters()) {
                    if (elementPainter.elementAt(point) == true) {
                        if (elementPainter instanceof ClassPainter || elementPainter instanceof EnumPainter || elementPainter instanceof InterfacePainter) {
                            startPoint = ((InterClassPainter) elementPainter).getInterclass().getConnectionDots().get(0);
                            classFrom = ((InterClassPainter) elementPainter).getInterclass();
                            connection = new Aggregation(Color.BLACK, 2, tempTab.getLine2D());
                            connection.setClassFrom(classFrom);
                            flagForAdd = true;
                        }
                    }
                }
                if (flagForAdd == false) return;
                painter = new AggregationPainter((Aggregation) connection);
                tempTab.getListOfPainters().add(painter);
                tempTab.getDiagram().addChild(connection);
                tempTab.repaint();

            }

        }
    }

    @Override
    public void stateMouseReleased(MouseEvent e, DiagramView tempTab) {
        if (connection == null){
            tempTab.repaint();
            return;
        }
        if (connectionMode == ConnectionMode.DRAW_CONNECTION) {
            boolean flag = false;
            rectangle.setSize(0,0);
            tempTab.setRectangle(rectangle);
            minDistance = Double.MAX_VALUE;
            for (ElementPainter elementPainter : tempTab.getListOfPainters()) {
                if (elementPainter.elementAt(endPoint) == true) {
                    if (elementPainter instanceof ClassPainter || elementPainter instanceof EnumPainter || elementPainter instanceof InterfacePainter) {
                        flag = true;
                        classTo = ((InterClassPainter) elementPainter).getInterclass();
                        connection.setClassTo(classTo);

                        endPoint = e.getPoint();
                        for(Point connectionDot : classFrom.getConnectionDots()){
                            double distance = Math.sqrt(Math.pow(connectionDot.x - endPoint.x, 2) + Math.pow(connectionDot.y - endPoint.y, 2));
                            if(distance < minDistance){
                                minDistance = distance;
                                closestConnectionDot = connectionDot;
                            }
                        }
                        startPoint = closestConnectionDot;
                        minDistance = Double.MAX_VALUE;

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

            if (flag == false && classTo == null) {
                tempTab.setLine2D(null);  // Resetovanje Line2D
                connection.setLine2D(tempTab.getLine2D());
                tempTab.getDiagram().removeChild(connection);
                tempTab.getListOfPainters().remove(painter);


                tempTab.repaint();
                flagForConnectionInfo = false;
            }
        }
    }

    @Override
    public void stateMouseDragged(MouseEvent e, DiagramView tempTab) {
        if (classFrom == null){
            tempTab.repaint();
            return;
        }
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
    private void deselect(DiagramView tempTab){
        if(!tempTab.getListOfSelectedPainters().isEmpty()) {
            List<ElementPainter> helpList = new ArrayList<>();
            Interclass interclass;
            Connection connection;
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
            tempTab.repaint();
        }
    }

}
