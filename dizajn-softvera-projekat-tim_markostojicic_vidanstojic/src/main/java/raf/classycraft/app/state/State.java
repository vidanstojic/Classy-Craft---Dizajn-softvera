package raf.classycraft.app.state;

import raf.classycraft.app.gui.view.DiagramView;

import java.awt.event.MouseEvent;

public interface State {
    void stateMousePressed(MouseEvent e, DiagramView tempTab);
    void stateMouseReleased(MouseEvent e, DiagramView tempTab);
    void stateMouseDragged(MouseEvent e, DiagramView tempTab);
}
