package raf.classycraft.app.state.concrete;

import raf.classycraft.app.core.ApplicationFramework;
import raf.classycraft.app.gui.tree.ClassyTreeImplementation;
import raf.classycraft.app.gui.tree.model.ClassyTreeItem;
import raf.classycraft.app.gui.view.DiagramView;
import raf.classycraft.app.gui.view.MainFrame;
import raf.classycraft.app.gui.view.paint.*;
import raf.classycraft.app.model.elementDiagram.Connection;
import raf.classycraft.app.model.elementDiagram.ConnectionMode;
import raf.classycraft.app.model.elementDiagram.Interclass;
import raf.classycraft.app.model.elementDiagram.classContent.Visibility;
import raf.classycraft.app.model.elementDiagram.concreteConnections.*;
import raf.classycraft.app.model.messageGenerator.EventTypes;
import raf.classycraft.app.model.messageGenerator.Type;
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

    ClassyTreeImplementation classyTreeImplementation = (ClassyTreeImplementation) MainFrame.getInstance().getClassyTree();

    private ConnectionMode connectionMode = ConnectionMode.NONE;
    @Override
    public void stateMousePressed(MouseEvent e, DiagramView tempTab) {
        connection = null;
        painter  = null;
        rectangle = tempTab.getRectangle();
        rectangle.setRect((int) (e.getX() / tempTab.getAffineTransform().getScaleX()), (int) (e.getY() / tempTab.getAffineTransform().getScaleY()), 5, 5);
        tempTab.setRectangle(rectangle);
        deselect(tempTab);
        // provera da li je kliknuto na vezu
        for(ElementPainter elementPainter : tempTab.getListOfPainters()){
            if(  elementPainter instanceof ConnectionPainter && elementPainter.getLine2D() != null && rectangle.intersectsLine(elementPainter.getLine2D())){
                Connection tempConnection = ((ConnectionPainter) elementPainter).getConnection();
                if(tempConnection instanceof Composition || tempConnection instanceof Aggregation){
                    if(tempConnection.getConnectionInfo() == null){
                        ApplicationFramework.getInstance().getMessageGenerator().generateMessage(EventTypes.UNENTERED_DATA, Type.ERROR);
                        return;
                    }
                    Object[] selectionValues = {"Check your connection", "Change info"};
                    String initialSelection = "Check your connection";
                    Object selectConnection = JOptionPane.showInputDialog(null, "Do you want to check info about your connection or you want to change information about connection?",
                            "Connection info", JOptionPane.QUESTION_MESSAGE, null, selectionValues, initialSelection);
                    if (selectConnection == null) {
                        return;
                    }
                    else if(selectConnection == "Check your connection"){
                        String nameOfConnection = tempConnection.getConnectionInfo().getNameOfConnection();
                        String nameofAttribute = tempConnection.getConnectionInfo().getNameofAttribute();
                        String visibilityOfConnection;
                        String cardinality = tempConnection.getConnectionInfo().getCardinaliy();
                        if(tempConnection.getConnectionInfo().getVisibility() == Visibility.PUBLIC){
                            visibilityOfConnection = "+ ";
                        }
                        else if(tempConnection.getConnectionInfo().getVisibility() == Visibility.PRIVATE){
                            visibilityOfConnection = "- ";
                        }
                        else if(tempConnection.getConnectionInfo().getVisibility() == Visibility.PROTECTED){
                            visibilityOfConnection = "# ";
                        }
                        else{
                            visibilityOfConnection = " ";
                        }
                        JOptionPane.showMessageDialog(null, nameOfConnection+ "\n" + visibilityOfConnection + nameofAttribute + "\n"+ cardinality,"Information about connection",JOptionPane.INFORMATION_MESSAGE,null);
                    }
                    else if(selectConnection == "Change info"){
                        Object[] select = {"Cardinality", "Name of attribute", "Visibility"};
                        String initial = "Check your connection";
                        Object selectInfo = JOptionPane.showInputDialog(null, "Select which information you want to change:",
                                "Select information", JOptionPane.QUESTION_MESSAGE, null, select, initial);
                        if (selectInfo == null) {
                            return;
                        }
                        else if(selectInfo == "Cardinality"){
                            String inputText = JOptionPane.showInputDialog("Enter new cardinality:");
                            if (inputText == null || inputText.isEmpty())return;
                            tempConnection.getConnectionInfo().setCardinaliy(inputText);
                            tempTab.repaint();
                        }
                        else if(selectInfo == "Name of attribute"){
                            String inputText = JOptionPane.showInputDialog("Enter new name of attribute:");
                            if (inputText == null || inputText.isEmpty())return;
                            tempConnection.getConnectionInfo().setNameofAttribute(inputText);
                            tempTab.repaint();
                        }
                        else if (selectInfo == "Visibility"){
                            Object[] selectionValuesVisibility = {"Public", "Private", "Protected", "Default"};
                            String initialSelectionVisibility = "Public";
                            Object visibility = JOptionPane.showInputDialog(null, "Select new visibility",
                                    "Visibility", JOptionPane.QUESTION_MESSAGE, null, selectionValuesVisibility, initialSelectionVisibility);
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
                            tempConnection.getConnectionInfo().setVisibility(visibilityEnum);
                            tempTab.repaint();
                        }
                    }
                }
                else if(tempConnection instanceof Dependency || tempConnection instanceof Generalization){
                    JOptionPane.showMessageDialog(null, tempConnection.getConnectionInfo().getNameOfConnection(),"Information about connection",JOptionPane.INFORMATION_MESSAGE,null);
                }
            }
        }



        if(connectionMode == ConnectionMode.NONE) {
            boolean askUser = false;
            Point point = new Point((int) (e.getX() / tempTab.getAffineTransform().getScaleX() ), (int) (e.getY() / tempTab.getAffineTransform().getScaleY()));
            for (ElementPainter elementPainter : tempTab.getListOfPainters()) {
                if((elementPainter.elementAt(point) == true) && elementPainter instanceof InterClassPainter){
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

            connectionMode = ConnectionMode.START_CONNECTION;
            return;
        }
        if(connectionMode == ConnectionMode.START_CONNECTION) {
            if (selection == null) {
                return;
            } else if (selection.equals("Generalisation")) {
                System.out.println("Dodavanje generalizacije");
                Point point = new Point((int) (e.getX() / tempTab.getAffineTransform().getScaleX() ), (int) (e.getY() / tempTab.getAffineTransform().getScaleY()));
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
                Point point = new Point((int) (e.getX() / tempTab.getAffineTransform().getScaleX() ), (int) (e.getY() / tempTab.getAffineTransform().getScaleY()));
                boolean flagForAdd = false;
                for (ElementPainter elementPainter : tempTab.getListOfPainters()) {
                    if (elementPainter.elementAt(point) == true) {
                        if (elementPainter instanceof ClassPainter || elementPainter instanceof EnumPainter || elementPainter instanceof InterfacePainter) {
                            startPoint = ((InterClassPainter) elementPainter).getInterclass().getConnectionDots().get(0);
                            classFrom = ((InterClassPainter) elementPainter).getInterclass();
                            connection = new Dependency(Color.BLACK, 2, tempTab.getLine2D());
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
               Point point = new Point((int) (e.getX() / tempTab.getAffineTransform().getScaleX() ), (int) (e.getY() / tempTab.getAffineTransform().getScaleY()));
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
                Point point = new Point((int) (e.getX() / tempTab.getAffineTransform().getScaleX() ), (int) (e.getY() / tempTab.getAffineTransform().getScaleY()));
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
        rectangle.setSize(0,0);
    }

    @Override
    public void stateMouseReleased(MouseEvent e, DiagramView tempTab) {
        if (connection == null){
            connectionMode = ConnectionMode.NONE;
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

                        endPoint = new Point((int) (e.getX() / tempTab.getAffineTransform().getScaleX()), (int) (e.getY() / tempTab.getAffineTransform().getScaleY()));
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
                        if(connection instanceof Composition){
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
                        }
                        else if(connection instanceof Aggregation){
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


                            ConnectionInfo connectionInfo = new ConnectionInfo("Aggregation", nameOfAttribute, cardinality, visibilityEnum);
                            connection.setConnectionInfo(connectionInfo);
                        }
                        else if(connection instanceof Generalization){
                            ConnectionInfo connectionInfo = new ConnectionInfo("Generalization");
                            connection.setConnectionInfo(connectionInfo);
                        }
                        else if(connection instanceof Dependency){
                            ConnectionInfo connectionInfo = new ConnectionInfo("Dependancy");
                            connection.setConnectionInfo(connectionInfo);
                        }


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
            else{
                ClassyTreeItem parentItem = this.classyTreeImplementation.findTreeItem((ClassyTreeItem) classyTreeImplementation.getTreeModel().getRoot(),tempTab.getDiagram());
                ClassyTreeItem childItem = new ClassyTreeItem(connection);
                this.classyTreeImplementation.addDiagramElement(parentItem, childItem);
            }

        }
    }

    @Override
    public void stateMouseDragged(MouseEvent e, DiagramView tempTab) {
        rectangle.setSize(5,5);
        if (classFrom == null ){
            tempTab.repaint();
            return;
        }
        if(connection == null) return;
        connectionMode = ConnectionMode.DRAW_CONNECTION;
        endPoint = new Point((int) (e.getX() / tempTab.getAffineTransform().getScaleX()), (int) (e.getY() / tempTab.getAffineTransform().getScaleY()));
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
                helpList.add(elementPainter);
            }
            for (ElementPainter elementPainter : helpList) {
                tempTab.getListOfSelectedPainters().remove(elementPainter);
            }
            tempTab.repaint();
        }
    }

}
