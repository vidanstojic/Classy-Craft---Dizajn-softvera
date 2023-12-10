package raf.classycraft.app.state.concrete;

import raf.classycraft.app.gui.view.DiagramView;
import raf.classycraft.app.gui.view.paint.*;
import raf.classycraft.app.model.elementDiagram.Interclass;
import raf.classycraft.app.model.elementDiagram.concreteInterclass.ClassInterClass;
import raf.classycraft.app.model.elementDiagram.concreteInterclass.EnumInterclass;
import raf.classycraft.app.model.elementDiagram.concreteInterclass.InterfaceInterclass;
import raf.classycraft.app.state.State;

import java.awt.*;
import java.awt.event.MouseEvent;

public class DuplicateState implements State {
    @Override
    public void stateMousePressed(MouseEvent e, DiagramView tempTab) {
        Point tempPoint = new Point(e.getX(), e.getY());
        Interclass classToCopy = null;
        for(ElementPainter elementPainter : tempTab.getListOfPainters()){
            if(elementPainter instanceof InterClassPainter && elementPainter.elementAt(tempPoint)){
                classToCopy = ((InterClassPainter) elementPainter).getInterclass();
            }
        }
        if(classToCopy == null){
            System.out.println("Niste na klasi");
            return;
        }
        else{
            if(classToCopy instanceof ClassInterClass){
                ClassInterClass classInterClass = (ClassInterClass) classToCopy;
                ClassInterClass copiedClass = new ClassInterClass(tempPoint, classInterClass.getColor(), classInterClass.getStroke(), classInterClass.getName(),classInterClass.getVisibility() ,classInterClass.getAbstractClass());
                copiedClass.setAttributes(classInterClass.getAttributes());
                copiedClass.setMethods(classInterClass.getMethods());

                ClassPainter classPainter = new ClassPainter(tempPoint,copiedClass);
                tempTab.getListOfPainters().add(classPainter);
                tempTab.getDiagram().addChild(copiedClass);
            }
            else if(classToCopy instanceof EnumInterclass){
                EnumInterclass enumInterclass = (EnumInterclass) classToCopy;
                EnumInterclass copiedEnum = new EnumInterclass(tempPoint, enumInterclass.getColor(), enumInterclass.getStroke(), enumInterclass.getName(),enumInterclass.getVisibility());
                copiedEnum.setAttributes(enumInterclass.getAttributes());

                EnumPainter enumPainter = new EnumPainter(tempPoint, copiedEnum);
                tempTab.getListOfPainters().add(enumPainter);
                tempTab.getDiagram().addChild(copiedEnum);
            }
            else if(classToCopy instanceof InterfaceInterclass){
                InterfaceInterclass interfaceInterclass = (InterfaceInterclass) classToCopy;
                InterfaceInterclass copiedInterface = new InterfaceInterclass(tempPoint, interfaceInterclass.getColor(), interfaceInterclass.getStroke(), interfaceInterclass.getName(),interfaceInterclass.getVisibility());
                copiedInterface.setMethods(interfaceInterclass.getMethods());

                InterfacePainter interfacePainter = new InterfacePainter(tempPoint, copiedInterface);
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
}
