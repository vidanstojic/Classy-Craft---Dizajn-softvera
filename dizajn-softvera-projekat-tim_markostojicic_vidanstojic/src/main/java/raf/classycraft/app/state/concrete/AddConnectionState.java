package raf.classycraft.app.state.concrete;

import raf.classycraft.app.gui.view.DiagramView;
import raf.classycraft.app.gui.view.paint.ClassPainter;
import raf.classycraft.app.gui.view.paint.ElementPainter;
import raf.classycraft.app.model.elementDiagram.concreteConnections.Association;
import raf.classycraft.app.model.elementDiagram.concreteInterclass.ClassInterClass;
import raf.classycraft.app.state.State;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;

public class AddConnectionState implements State {

    @Override
    public void stateMousePressed(MouseEvent e, DiagramView tempTab) {
        Object[] selectionValues = {"Association", "Dependency", "Composition", "Aggregation", "Generalisation"};
        String initialSelection = "Association";
        Object selection = JOptionPane.showInputDialog(null, "What type of connection do you want to add?",
                "Add new connection", JOptionPane.QUESTION_MESSAGE, null, selectionValues, initialSelection);
        if (selection == null) {
            return;
        }
        else if (selection.equals("Association")) {
            System.out.println("Dodavanje asocijacije");
            Point point = new Point(e.getX(), e.getY());
        }
        else if (selection.equals("Dependency")) {
            System.out.println("Dodavanje dependency");
        }
        else if (selection.equals("Composition")) {
            System.out.println("Dodavanje kompozita");
        }
        else if (selection.equals("Aggregation")){
            System.out.println("Dodavanje agregacije");
        }
        else if (selection.equals("Generalisation")) {
            System.out.println("Dodavanje generalizacije");
        }
    }

    @Override
    public void stateMouseReleased(MouseEvent e, DiagramView tempTab) {

    }

    @Override
    public void stateMouseDragged(MouseEvent e, DiagramView tempTab) {

    }
}
