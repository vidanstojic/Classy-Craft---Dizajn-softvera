package raf.classycraft.app.state.concrete;

import raf.classycraft.app.gui.view.DiagramView;
import raf.classycraft.app.model.elementDiagram.concreteInterclass.ClassInterClass;
import raf.classycraft.app.state.State;

import java.awt.event.MouseEvent;

public class AddClassState implements State {

    @Override
    public void stateMousePressed(MouseEvent e, DiagramView tempTab) {
        ClassInterClass classInterClass = new ClassInterClass();
    }

    @Override
    public void stateMouseReleased(MouseEvent e, DiagramView tempTab) {

    }

    @Override
    public void stateMouseDragged(MouseEvent e, DiagramView tempTab) {

    }
}
