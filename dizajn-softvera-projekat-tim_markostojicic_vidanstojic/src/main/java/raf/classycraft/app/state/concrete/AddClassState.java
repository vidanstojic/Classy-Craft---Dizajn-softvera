package raf.classycraft.app.state.concrete;

import raf.classycraft.app.gui.tree.factory.DiagramFactory;
import raf.classycraft.app.gui.tree.factory.FactoryChild;
import raf.classycraft.app.gui.view.DiagramView;
import raf.classycraft.app.gui.view.paint.ClassPainter;
import raf.classycraft.app.model.elementDiagram.concreteInterclass.ClassInterClass;
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
            ClassInterClass classInterClass = new ClassInterClass(Color.BLACK, 10, "Klasa1", "public");
            ClassPainter classPainter = new ClassPainter(classInterClass);
            tempTab.getListOfPainters().add(classPainter);
            tempTab.getDiagram().addChild(classInterClass);
        }
        else if (selection.equals("Interface")) {
            System.out.println("Dodavanje interfejsa");
        }
        else if (selection.equals("Enum")) {
            System.out.println("Dodavanje enuma");
        }
    }

    @Override
    public void stateMouseReleased(MouseEvent e, DiagramView tempTab) {

    }

    @Override
    public void stateMouseDragged(MouseEvent e, DiagramView tempTab) {

    }
}
