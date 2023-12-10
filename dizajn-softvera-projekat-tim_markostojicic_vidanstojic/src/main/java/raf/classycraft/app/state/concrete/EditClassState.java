package raf.classycraft.app.state.concrete;

import raf.classycraft.app.gui.view.DiagramView;
import raf.classycraft.app.gui.view.paint.*;
import raf.classycraft.app.model.elementDiagram.Interclass;
import raf.classycraft.app.model.elementDiagram.classContent.Attribute;
import raf.classycraft.app.model.elementDiagram.classContent.Method;
import raf.classycraft.app.model.elementDiagram.classContent.Visibility;
import raf.classycraft.app.model.elementDiagram.concreteInterclass.ClassInterClass;
import raf.classycraft.app.model.elementDiagram.concreteInterclass.EnumInterclass;
import raf.classycraft.app.model.elementDiagram.concreteInterclass.InterfaceInterclass;
import raf.classycraft.app.state.State;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;

public class EditClassState implements State {

    @Override
    public void stateMousePressed(MouseEvent e, DiagramView tempTab) {
        ElementPainter classToEdit;
        Point point = new Point(e.getX(), e.getY());
        for(ElementPainter elementPainter : tempTab.getListOfPainters()){
            if(elementPainter.elementAt(point) && elementPainter instanceof ClassPainter) {
                Object[] selectionValues = {"Attribute", "Method"};
                String initialSelection = "Attribute";
                Object selection = JOptionPane.showInputDialog(null, "Do you want to add method or attribute?",
                        "Method/Attribute", JOptionPane.QUESTION_MESSAGE, null, selectionValues, initialSelection);
                if (selection == null) return;
                if (selection == "Method") {
                    Object[] selectionValuesVisibility = {"Public", "Private", "Protected", "Default"};
                    String initialSelectionVisibility = "Public";
                    Object visibility = JOptionPane.showInputDialog(null, "What is your method visibility?",
                            "Visibility", JOptionPane.QUESTION_MESSAGE, null, selectionValuesVisibility, initialSelectionVisibility);
                    if (visibility == null) return;
                    else {
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
                        String name = JOptionPane.showInputDialog("What is name of your method?");
                        if (name == null) return;
                        String returnType = JOptionPane.showInputDialog("What is return type of your method?");
                        if (returnType == null) return;

                        Method method = new Method(name, visibilityEnum, returnType);
                        Interclass interClass = ((InterClassPainter) elementPainter).getInterclass();
                        interClass.getMethods().add(method);
                        interClass.getClassContents().add(method);
                        tempTab.repaint();

                    }
                } else if (selection == "Attribute") {

                    Object[] selectionValuesVisibility = {"Public", "Private", "Protected", "Default"};
                    String initialSelectionVisibility = "Public";
                    Object visibility = JOptionPane.showInputDialog(null, "What is your attribute visibility?",
                            "Visibility", JOptionPane.QUESTION_MESSAGE, null, selectionValuesVisibility, initialSelectionVisibility);
                    if (visibility == null) return;
                    else {
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
                        String name = JOptionPane.showInputDialog("What is name of your attribute?");
                        if (name == null) return;
                        String returnType = JOptionPane.showInputDialog("What is type of your attribute?");
                        if (returnType == null) return;

                        Attribute attribute = new Attribute(name, visibilityEnum, returnType);
                        Interclass interClass = ((InterClassPainter) elementPainter).getInterclass();
                        interClass.getAttributes().add(attribute);
                        interClass.getClassContents().add(attribute);
                        tempTab.repaint();
                    }
                }
            }else if (elementPainter.elementAt(point) && elementPainter instanceof EnumPainter) {
                    String name = JOptionPane.showInputDialog("What is name of your element?");
                    if (name == null) return;
                    Attribute attribute = new Attribute(name, null);
                    Interclass interClass = ((InterClassPainter) elementPainter).getInterclass();
                    interClass.getAttributes().add(attribute);
                    interClass.getClassContents().add(attribute);
                    tempTab.repaint();
            }else if (elementPainter.elementAt(point) && elementPainter instanceof InterfacePainter){
                Object[] selectionValuesVisibility = {"Public", "Private", "Protected", "Default"};
                String initialSelectionVisibility = "Public";
                Object visibility = JOptionPane.showInputDialog(null, "What is your method visibility?",
                        "Visibility", JOptionPane.QUESTION_MESSAGE, null, selectionValuesVisibility, initialSelectionVisibility);
                if (visibility == null) return;
                else {
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
                    String name = JOptionPane.showInputDialog("What is name of your method?");
                    if (name == null) return;
                    String returnType = JOptionPane.showInputDialog("What is return type of your method?");
                    if (returnType == null) return;

                    Method method = new Method(name, visibilityEnum, returnType);
                    Interclass interClass = ((InterClassPainter) elementPainter).getInterclass();
                    interClass.getMethods().add(method);
                    interClass.getClassContents().add(method);
                    tempTab.repaint();
                }
            }
        }
    }

    @Override
    public void stateMouseReleased(MouseEvent e, DiagramView tempTab) {

    }

    @Override
    public void stateMouseDragged(MouseEvent e, DiagramView tempTab) {

    }
}
