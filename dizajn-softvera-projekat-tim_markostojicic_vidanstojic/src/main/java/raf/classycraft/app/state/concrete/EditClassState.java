package raf.classycraft.app.state.concrete;

import raf.classycraft.app.gui.view.DiagramView;
import raf.classycraft.app.gui.view.paint.*;
import raf.classycraft.app.model.elementDiagram.Interclass;
import raf.classycraft.app.model.elementDiagram.classContent.Attribute;
import raf.classycraft.app.model.elementDiagram.classContent.ClassContent;
import raf.classycraft.app.model.elementDiagram.classContent.Method;
import raf.classycraft.app.model.elementDiagram.classContent.Visibility;
import raf.classycraft.app.state.State;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class EditClassState implements State {

    private Interclass interclass;
    private List<ClassContent>listForRemoveContent = new ArrayList<>();
    @Override
    public void stateMousePressed(MouseEvent e, DiagramView tempTab) {
        ElementPainter classToEdit;
        Point point = new Point(e.getX(), e.getY());
        for(ElementPainter elementPainter : tempTab.getListOfPainters()){
            if (elementPainter.elementAt(point))
                interclass = ((InterClassPainter) elementPainter).getInterclass();
            Object[] selectionValuesMode = {"Add elements", "Rename", "Remove"};
            String initialSelectionMode = "Add attributes";
            Object selectionMode = JOptionPane.showInputDialog(null, "Select edit mode",
                    "Edit mode", JOptionPane.QUESTION_MESSAGE, null, selectionValuesMode, initialSelectionMode);
            if (selectionMode == "Rename"){
                renameMethod(interclass, tempTab);
            } else if (selectionMode == "Remove") {
                removeMethod(interclass, tempTab);
            } else if (selectionMode == "Add elements") {
                if (elementPainter.elementAt(point) && elementPainter instanceof ClassPainter) {
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
                } else if (elementPainter.elementAt(point) && elementPainter instanceof EnumPainter) {
                    String name = JOptionPane.showInputDialog("What is name of your enum?");
                    if (name == null) return;
                    String returnType = JOptionPane.showInputDialog("What is type of your enum?");
                    if (returnType == null) return;
                    Attribute attribute = new Attribute(name, returnType);
                    Interclass interClass = ((InterClassPainter) elementPainter).getInterclass();
                    interClass.getAttributes().add(attribute);
                    interClass.getClassContents().add(attribute);
                    tempTab.repaint();
                } else if (elementPainter.elementAt(point) && elementPainter instanceof InterfacePainter) {
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
    }

    @Override
    public void stateMouseReleased(MouseEvent e, DiagramView tempTab) {

    }

    @Override
    public void stateMouseDragged(MouseEvent e, DiagramView tempTab) {

    }
    private void removeMethod(Interclass interclass, DiagramView tempTab){
        if (interclass.getClassContents().isEmpty()){
            return;
        } else {
            String inputText = JOptionPane.showInputDialog("Enter full name of attribute that exist");
            if (inputText == null)return;
            for (ClassContent classContent : interclass.getClassContents()){
                if (classContent.getName().equals(inputText)){
                    listForRemoveContent.add(classContent);
                }
            }for (ClassContent classContent : listForRemoveContent){
                if (classContent instanceof Attribute)
                    interclass.getAttributes().remove(classContent);
                else if (classContent instanceof Method) {
                    interclass.getMethods().remove(classContent);
                }
                interclass.getClassContents().remove(classContent);
                tempTab.repaint();
            }
        }
    }
    private void renameMethod(Interclass interclass, DiagramView tempTab){

        Object[] selectionValuesRename = {"Rename class name", "Rename attributes/methods"};
        String initialSelectionRename = "Rename class name";
        Object selectionRename = JOptionPane.showInputDialog(null, "Select edit mode",
                "Edit mode", JOptionPane.QUESTION_MESSAGE, null, selectionValuesRename, initialSelectionRename);
        if (selectionRename == "Rename class name") {
            String inputText = JOptionPane.showInputDialog("Enter new name");
            if (inputText.length() == 0)return;
            interclass.setName(inputText);
            tempTab.repaint();
        }else if (!interclass.getClassContents().isEmpty()){

            String inputText = JOptionPane.showInputDialog("Enter full name of attribute that exist");
            if (inputText == null) return;
            elementsEdit(interclass, tempTab, inputText);
        }
    }
    private void elementsEdit(Interclass interclass, DiagramView tempTab, String input){
        Object[] selectionValuesEdit = {"Rename element name", "Change element type", "Change element visibility", "Nothing"};
        String initialSelectionEdit = "Rename element name";
        Object selectionEdit = JOptionPane.showInputDialog(null, "Select edit mode",
                "Edit mode", JOptionPane.QUESTION_MESSAGE, null, selectionValuesEdit, initialSelectionEdit);
        if(selectionEdit == "Nothing"){
            return;
        }else if (selectionEdit == "Rename element name"){
            for (ClassContent classContent : interclass.getClassContents()){
                if (classContent.getName().equals(input)){
                    String inputName = JOptionPane.showInputDialog("Enter new name");
                    classContent.setName(inputName);
                    input = inputName;
                    tempTab.repaint();
                    elementsEdit(interclass, tempTab, input);
                }
            }
        } else if (selectionEdit == "Change element type") {
            for (ClassContent classContent : interclass.getClassContents()){
                if (classContent.getName().equals(input)){
                    String inputType = JOptionPane.showInputDialog("Enter new name");
                    classContent.setReturnType(inputType);
                    tempTab.repaint();
                    elementsEdit(interclass, tempTab, input);
                }
            }
        } else if (selectionEdit == "Change element visibility") {
            for (ClassContent classContent : interclass.getClassContents()){
                if (classContent.getName().equals(input)){
                    Object[] selectionValuesVisibility = {"Public", "Private", "Protected", "Default"};
                    String initialSelectionVisibility = "Public";
                    Object visibility = JOptionPane.showInputDialog(null, "What is your method visibility?",
                            "Visibility", JOptionPane.QUESTION_MESSAGE, null, selectionValuesVisibility, initialSelectionVisibility);
                    if (visibility == null) {
                        elementsEdit(interclass, tempTab, input);
                    }
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
                        classContent.setVisibility(visibilityEnum);
                    }
                    tempTab.repaint();
                    elementsEdit(interclass, tempTab, input);
                }
            }
        }
    }
}
