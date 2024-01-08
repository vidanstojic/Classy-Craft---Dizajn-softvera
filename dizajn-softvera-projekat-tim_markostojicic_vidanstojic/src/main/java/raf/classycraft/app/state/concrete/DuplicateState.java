package raf.classycraft.app.state.concrete;

import raf.classycraft.app.command.implementation.AddClassCommand;
import raf.classycraft.app.command.implementation.DuplicateCommand;
import raf.classycraft.app.core.ApplicationFramework;
import raf.classycraft.app.gui.tree.ClassyTreeImplementation;
import raf.classycraft.app.gui.tree.model.ClassyTreeItem;
import raf.classycraft.app.gui.view.DiagramView;
import raf.classycraft.app.gui.view.MainFrame;
import raf.classycraft.app.gui.view.paint.*;
import raf.classycraft.app.model.elementDiagram.Connection;
import raf.classycraft.app.model.elementDiagram.Interclass;
import raf.classycraft.app.model.elementDiagram.classContent.Attribute;
import raf.classycraft.app.model.elementDiagram.classContent.ClassContent;
import raf.classycraft.app.model.elementDiagram.classContent.Method;
import raf.classycraft.app.model.elementDiagram.concreteInterclass.ClassInterClass;
import raf.classycraft.app.model.elementDiagram.concreteInterclass.EnumInterclass;
import raf.classycraft.app.model.elementDiagram.concreteInterclass.InterfaceInterclass;
import raf.classycraft.app.model.messageGenerator.EventTypes;
import raf.classycraft.app.model.messageGenerator.Type;
import raf.classycraft.app.state.State;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class DuplicateState implements State {

    ClassyTreeImplementation classyTreeImplementation = (ClassyTreeImplementation) MainFrame.getInstance().getClassyTree();
    @Override
    public void stateMousePressed(MouseEvent e, DiagramView tempTab) {
        deselect(tempTab);
        Point tempPoint = new Point((int) (e.getX() / tempTab.getAffineTransform().getScaleX()), (int) (e.getY() / tempTab.getAffineTransform().getScaleY()));
        Interclass classToCopy = null;
        for(ElementPainter elementPainter : tempTab.getListOfPainters()){
            if(elementPainter instanceof InterClassPainter && elementPainter.elementAt(tempPoint)){
                classToCopy = ((InterClassPainter) elementPainter).getInterclass();
            }
        }
        if(classToCopy == null){
            ApplicationFramework.getInstance().getMessageGenerator().generateMessage(EventTypes.CANT_COPY_CLASS, Type.ERROR);
            return;
        }
        else {
            EnumInterclass enumInterclass = null;
            if (classToCopy instanceof ClassInterClass) {
                ClassInterClass classInterClass = (ClassInterClass) classToCopy;
                ClassInterClass copiedClass = new ClassInterClass(new Point(tempPoint.x + 20, tempPoint.y + 20), classInterClass.getColor(), classInterClass.getStroke(), classInterClass.getName(), classInterClass.getVisibility(), classInterClass.getAbstractClass());
                copiedClass.getListOfSubscribers().add(tempTab);
                List<Method> methods = new ArrayList<>();
                methods.addAll(classInterClass.getMethods());
                List<Attribute> attributes = new ArrayList<>();
                attributes.addAll(classInterClass.getAttributes());
                List<ClassContent> classContents = new ArrayList<>();
                classContents.addAll(classInterClass.getClassContents());

                copiedClass.setAttributes(attributes);
                copiedClass.setMethods(methods);
                copiedClass.setClassContents(classContents);

                ClassPainter classPainter = new ClassPainter(new Point(tempPoint.x + 20, tempPoint.y + 20), copiedClass);

                ClassyTreeItem parentItem = this.classyTreeImplementation.findTreeItem((ClassyTreeItem) classyTreeImplementation.getTreeModel().getRoot(), tempTab.getDiagram());
                ClassyTreeItem childItem = new ClassyTreeItem(copiedClass);

                DuplicateCommand duplicateCommand = new DuplicateCommand(tempTab, copiedClass, classPainter, parentItem, childItem, classyTreeImplementation);
                tempTab.getCommandManager().addCommand(duplicateCommand);
            } else if (classToCopy instanceof EnumInterclass) {
                enumInterclass = (EnumInterclass) classToCopy;
                EnumInterclass copiedEnum = new EnumInterclass(new Point(tempPoint.x + 20, tempPoint.y + 20), enumInterclass.getColor(), enumInterclass.getStroke(), enumInterclass.getName(), enumInterclass.getVisibility());
                copiedEnum.getListOfSubscribers().add(tempTab);
                List<Attribute> attributes = new ArrayList<>();
                attributes.addAll(enumInterclass.getAttributes());
                List<ClassContent> classContents = new ArrayList<>();
                classContents.addAll(enumInterclass.getClassContents());

                copiedEnum.setAttributes(attributes);
                copiedEnum.setClassContents(classContents);

                EnumPainter enumPainter = new EnumPainter(new Point(tempPoint.x + 20, tempPoint.y + 20), copiedEnum);

                ClassyTreeItem parentItem = this.classyTreeImplementation.findTreeItem((ClassyTreeItem) classyTreeImplementation.getTreeModel().getRoot(), tempTab.getDiagram());
                ClassyTreeItem childItem = new ClassyTreeItem(copiedEnum);

                DuplicateCommand duplicateCommand = new DuplicateCommand(tempTab, copiedEnum, enumPainter, parentItem, childItem, classyTreeImplementation);
                tempTab.getCommandManager().addCommand(duplicateCommand);
            } else if (classToCopy instanceof InterfaceInterclass) {
                InterfaceInterclass interfaceInterclass = (InterfaceInterclass) classToCopy;
                InterfaceInterclass copiedInterface = new InterfaceInterclass(new Point(tempPoint.x + 20, tempPoint.y + 20), interfaceInterclass.getColor(), interfaceInterclass.getStroke(), interfaceInterclass.getName(), interfaceInterclass.getVisibility());
                copiedInterface.getListOfSubscribers().add(tempTab);
                List<Method> methods = new ArrayList<>();
                methods.addAll(interfaceInterclass.getMethods());
                List<ClassContent> classContents = new ArrayList<>();
                classContents.addAll(interfaceInterclass.getClassContents());

                copiedInterface.setMethods(methods);
                copiedInterface.setClassContents(classContents);

                InterfacePainter interfacePainter = new InterfacePainter(new Point(tempPoint.x + 20, tempPoint.y + 20), copiedInterface);

                ClassyTreeItem parentItem = this.classyTreeImplementation.findTreeItem((ClassyTreeItem) classyTreeImplementation.getTreeModel().getRoot(), tempTab.getDiagram());
                ClassyTreeItem childItem = new ClassyTreeItem(copiedInterface);

                DuplicateCommand duplicateCommand = new DuplicateCommand(tempTab, copiedInterface, interfacePainter, parentItem, childItem, classyTreeImplementation);
                tempTab.getCommandManager().addCommand(duplicateCommand);
            }
        }
    }

    @Override
    public void stateMouseReleased(MouseEvent e, DiagramView tempTab) {

    }

    @Override
    public void stateMouseDragged(MouseEvent e, DiagramView tempTab) {

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
