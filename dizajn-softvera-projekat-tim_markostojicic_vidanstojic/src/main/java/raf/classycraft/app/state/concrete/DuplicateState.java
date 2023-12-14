package raf.classycraft.app.state.concrete;

import raf.classycraft.app.core.ApplicationFramework;
import raf.classycraft.app.gui.tree.ClassyTreeImplementation;
import raf.classycraft.app.gui.tree.model.ClassyTreeItem;
import raf.classycraft.app.gui.view.DiagramView;
import raf.classycraft.app.gui.view.MainFrame;
import raf.classycraft.app.gui.view.paint.*;
import raf.classycraft.app.model.elementDiagram.Connection;
import raf.classycraft.app.model.elementDiagram.Interclass;
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
        else{
            if(classToCopy instanceof ClassInterClass){
                ClassInterClass classInterClass = (ClassInterClass) classToCopy;
                ClassInterClass copiedClass = new ClassInterClass(new Point(tempPoint.x+20, tempPoint.y+20), classInterClass.getColor(), classInterClass.getStroke(), classInterClass.getName(),classInterClass.getVisibility() ,classInterClass.getAbstractClass());
                copiedClass.getListOfSubscribers().add(tempTab);
                copiedClass.setAttributes(classInterClass.getAttributes());
                copiedClass.setMethods(classInterClass.getMethods());


                ClassPainter classPainter = new ClassPainter(new Point(tempPoint.x+20, tempPoint.y+20),copiedClass);

                //dodavanje u tree
                ClassyTreeItem parentItem = this.classyTreeImplementation.findTreeItem((ClassyTreeItem) classyTreeImplementation.getTreeModel().getRoot(),tempTab.getDiagram());
                this.classyTreeImplementation.addChild(parentItem);
                ClassyTreeItem childItem = new ClassyTreeItem(copiedClass);
                this.classyTreeImplementation.addDiagramElement(parentItem, childItem);


                tempTab.getListOfPainters().add(classPainter);
                tempTab.getDiagram().addChild(copiedClass);
            }
            else if(classToCopy instanceof EnumInterclass){
                EnumInterclass enumInterclass = (EnumInterclass) classToCopy;
                EnumInterclass copiedEnum = new EnumInterclass(new Point(tempPoint.x+20, tempPoint.y+20), enumInterclass.getColor(), enumInterclass.getStroke(), enumInterclass.getName(),enumInterclass.getVisibility());
                copiedEnum.getListOfSubscribers().add(tempTab);
                copiedEnum.setAttributes(enumInterclass.getAttributes());

                EnumPainter enumPainter = new EnumPainter(new Point(tempPoint.x+20, tempPoint.y+20), copiedEnum);

                //dodavanje u tree
                ClassyTreeItem parentItem = this.classyTreeImplementation.findTreeItem((ClassyTreeItem) classyTreeImplementation.getTreeModel().getRoot(),tempTab.getDiagram());
                this.classyTreeImplementation.addChild(parentItem);
                ClassyTreeItem childItem = new ClassyTreeItem(copiedEnum);
                this.classyTreeImplementation.addDiagramElement(parentItem, childItem);


                tempTab.getListOfPainters().add(enumPainter);
                tempTab.getDiagram().addChild(copiedEnum);
            }
            else if(classToCopy instanceof InterfaceInterclass){
                InterfaceInterclass interfaceInterclass = (InterfaceInterclass) classToCopy;
                InterfaceInterclass copiedInterface = new InterfaceInterclass(new Point(tempPoint.x+20, tempPoint.y+20), interfaceInterclass.getColor(), interfaceInterclass.getStroke(), interfaceInterclass.getName(),interfaceInterclass.getVisibility());
                copiedInterface.getListOfSubscribers().add(tempTab);
                copiedInterface.setMethods(interfaceInterclass.getMethods());

                InterfacePainter interfacePainter = new InterfacePainter(new Point(tempPoint.x+20, tempPoint.y+20), copiedInterface);
                //dodavanje u tree
                ClassyTreeItem parentItem = this.classyTreeImplementation.findTreeItem((ClassyTreeItem) classyTreeImplementation.getTreeModel().getRoot(),tempTab.getDiagram());
                this.classyTreeImplementation.addChild(parentItem);
                ClassyTreeItem childItem = new ClassyTreeItem(copiedInterface);
                this.classyTreeImplementation.addDiagramElement(parentItem, childItem);


                tempTab.getListOfPainters().add(interfacePainter);
                tempTab.getDiagram().addChild(copiedInterface);
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
            }
            for (ElementPainter elementPainter : helpList) {
                tempTab.getListOfSelectedPainters().remove(elementPainter);
            }
            tempTab.repaint();
        }
    }
}
