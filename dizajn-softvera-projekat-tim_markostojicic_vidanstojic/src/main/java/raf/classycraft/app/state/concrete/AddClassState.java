package raf.classycraft.app.state.concrete;

import raf.classycraft.app.core.ApplicationFramework;
import raf.classycraft.app.gui.tree.factory.DiagramFactory;
import raf.classycraft.app.gui.tree.factory.FactoryChild;
import raf.classycraft.app.gui.view.DiagramView;
import raf.classycraft.app.gui.view.paint.ClassPainter;
import raf.classycraft.app.gui.view.paint.EnumPainter;
import raf.classycraft.app.gui.view.paint.InterfacePainter;
import raf.classycraft.app.model.elementDiagram.concreteInterclass.ClassInterClass;
import raf.classycraft.app.model.elementDiagram.concreteInterclass.EnumInterclass;
import raf.classycraft.app.model.elementDiagram.concreteInterclass.InterfaceInterclass;
import raf.classycraft.app.model.messageGenerator.EventTypes;
import raf.classycraft.app.model.messageGenerator.Type;
import raf.classycraft.app.state.State;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;

public class AddClassState implements State {

    @Override
    public void stateMousePressed(MouseEvent e, DiagramView tempTab) {
        Object[] selectionValues = {"Class", "Interface", "Enum"};
        String initialSelection = "Class";
        Object selection = JOptionPane.showInputDialog(null, "What element do you want to add?",
                "Add new element", JOptionPane.QUESTION_MESSAGE, null, selectionValues, initialSelection);
        if (selection == null) {
            return;
        }
        else if (selection.equals("Class")) {
            System.out.println("Dodavanje klase");
            String name = JOptionPane.showInputDialog("Name of the class");
            if (name == null){
                name = message(name);
            }
            ClassInterClass classInterClass = new ClassInterClass(Color.BLACK, 2, name, "public");
            Point point = new Point(e.getX(), e.getY());
            ClassPainter classPainter = new ClassPainter(point,classInterClass);
            tempTab.getListOfPainters().add(classPainter);
            tempTab.getDiagram().addChild(classInterClass);

        }
        else if (selection.equals("Interface")) {
            System.out.println("Dodavanje interfejsa");
            String name = JOptionPane.showInputDialog("Name of the interface");
            InterfaceInterclass interfaceInterclass = new InterfaceInterclass(Color.BLACK, 2, name, "public");
            Point point = new Point(e.getX(), e.getY());
            InterfacePainter interfacePainter = new InterfacePainter(point, interfaceInterclass);
            tempTab.getListOfPainters().add(interfacePainter);
            tempTab.getDiagram().addChild(interfaceInterclass);
        }
        else if (selection.equals("Enum")) {
            System.out.println("Dodavanje enuma");
            String name = JOptionPane.showInputDialog("Name of the enum");
            EnumInterclass enumInterclass = new EnumInterclass(Color.BLACK, 2, "asd", "public");
            Point point = new Point(e.getX(), e.getY());
            EnumPainter enumPainter = new EnumPainter(point, enumInterclass);
            tempTab.getListOfPainters().add(enumPainter);
            tempTab.getDiagram().addChild(enumInterclass);
        }
    }

    @Override
    public void stateMouseReleased(MouseEvent e, DiagramView tempTab) {

    }

    @Override
    public void stateMouseDragged(MouseEvent e, DiagramView tempTab) {
    }
    private String message(String name){
        if (name == null){
            ApplicationFramework.getInstance().getMessageGenerator().generateMessage(EventTypes.NAME_CANNOT_BE_EMPTY, Type.ERROR);
            name = JOptionPane.showInputDialog("Name of the class");
            name = message(name);
        }
        return name;
    }
}
