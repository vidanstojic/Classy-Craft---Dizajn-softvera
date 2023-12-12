package raf.classycraft.app.state.concrete;

import raf.classycraft.app.core.ApplicationFramework;
import raf.classycraft.app.gui.view.DiagramView;
import raf.classycraft.app.gui.view.paint.*;
import raf.classycraft.app.model.elementDiagram.Connection;
import raf.classycraft.app.model.elementDiagram.Interclass;
import raf.classycraft.app.model.elementDiagram.concreteInterclass.ClassInterClass;
import raf.classycraft.app.model.elementDiagram.concreteInterclass.EnumInterclass;
import raf.classycraft.app.model.elementDiagram.concreteInterclass.InterfaceInterclass;
import raf.classycraft.app.model.messageGenerator.EventTypes;
import raf.classycraft.app.model.messageGenerator.Type;
import raf.classycraft.app.state.State;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class AddClassState implements State {

    @Override
    public void stateMousePressed(MouseEvent e, DiagramView tempTab) {
        deselect(tempTab);
        Object[] selectionValues = {"Class", "Interface", "Enum"};
        String initialSelection = "Class";
        Object selection = JOptionPane.showInputDialog(null, "What element do you want to add?",
                "Add new element", JOptionPane.QUESTION_MESSAGE, null, selectionValues, initialSelection);
        if (selection == null) {
            return;
        }
        else if (selection.equals("Class")) {
            String name = JOptionPane.showInputDialog("Name of the class");

            if(name == null) return;
            if (name.length() == 0){
                name = message(name);
            }
            if(name == null) return;
            Object[] selectionClass = {"No" , "Yes"};
            String initialSelectionClass = "No";
            Object selection2 = JOptionPane.showInputDialog(null, "Is your class abstract?",
                    "Abstract class", JOptionPane.QUESTION_MESSAGE, null, selectionClass, initialSelectionClass);
            String string = (String)selection2;
            Point point = new Point(e.getX(), e.getY());
            ClassInterClass classInterClass = new ClassInterClass(point, Color.BLACK, 2, name, "public", string);
            classInterClass.getListOfSubscribers().add(tempTab);
            ClassPainter classPainter = new ClassPainter(point,classInterClass);
            for(ElementPainter elementPainter : tempTab.getListOfPainters()){
                if (elementPainter.elementAt(point) == true) {
                    ApplicationFramework.getInstance().getMessageGenerator().generateMessage(EventTypes.WRONG_POSITION, Type.ERROR);
                    return;
                }
            }
            tempTab.getListOfPainters().add(classPainter);
            tempTab.getDiagram().addChild(classInterClass);
        }
        else if (selection.equals("Interface")) {
            String name = JOptionPane.showInputDialog("Name of the interface");
            if (name == null || name.length() == 0){
                name = message(name);
            }
            Point point = new Point(e.getX(), e.getY());
            InterfaceInterclass interfaceInterclass = new InterfaceInterclass(point,Color.BLACK, 2, name, "public");
            interfaceInterclass.getListOfSubscribers().add(tempTab);
            InterfacePainter interfacePainter = new InterfacePainter(point, interfaceInterclass);
            for(ElementPainter elementPainter : tempTab.getListOfPainters()){
                if (elementPainter.elementAt(point) == true) {
                    ApplicationFramework.getInstance().getMessageGenerator().generateMessage(EventTypes.WRONG_POSITION, Type.ERROR);
                    return;
                }
            }
            tempTab.getListOfPainters().add(interfacePainter);
            tempTab.getDiagram().addChild(interfaceInterclass);
            tempTab.repaint();
        }
        else if (selection.equals("Enum")) {
            String name = JOptionPane.showInputDialog("Name of the enum");
            if (name == null || name.length() == 0){
                name = message(name);
            }
            Point point = new Point(e.getX(), e.getY());
            EnumInterclass enumInterclass = new EnumInterclass(point,Color.BLACK, 2, name, "public");
            enumInterclass.getListOfSubscribers().add(tempTab);
            EnumPainter enumPainter = new EnumPainter(point, enumInterclass);
            for(ElementPainter elementPainter : tempTab.getListOfPainters()){
                if (elementPainter.elementAt(point) == true) {
                    ApplicationFramework.getInstance().getMessageGenerator().generateMessage(EventTypes.WRONG_POSITION, Type.ERROR);
                    return;
                }
            }
            tempTab.getListOfPainters().add(enumPainter);
            tempTab.getDiagram().addChild(enumInterclass);
            tempTab.repaint();
        }
        tempTab.repaint();
    }

    @Override
    public void stateMouseReleased(MouseEvent e, DiagramView tempTab) {

    }

    @Override
    public void stateMouseDragged(MouseEvent e, DiagramView tempTab) {
    }
    private String message(String name){
        if(name == null)
            return null;
        if (name.length() == 0){
            ApplicationFramework.getInstance().getMessageGenerator().generateMessage(EventTypes.NAME_CANNOT_BE_EMPTY, Type.ERROR);
            name = JOptionPane.showInputDialog("Name of the class");
            name = message(name);
        }
        return name;
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
