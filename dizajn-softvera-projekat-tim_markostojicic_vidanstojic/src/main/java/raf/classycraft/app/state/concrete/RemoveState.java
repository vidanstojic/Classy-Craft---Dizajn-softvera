package raf.classycraft.app.state.concrete;

import raf.classycraft.app.gui.view.DiagramView;
import raf.classycraft.app.gui.view.paint.*;
import raf.classycraft.app.state.State;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class RemoveState implements State {

    @Override
    public void stateMousePressed(MouseEvent e, DiagramView tempTab) {
        List<ElementPainter> elementPaintersToRemove = new ArrayList<>();
        for(ElementPainter elementPainter : tempTab.getListOfSelectedPainters()){
            elementPaintersToRemove.add(elementPainter);
            if (elementPainter.getRectangle() != null)
            tempTab.getDiagram().removeChild(((InterClassPainter) elementPainter).getInterclass());
            else if (elementPainter.getLine2D() != null) {
                tempTab.getDiagram().removeChild(((ConnectionPainter) elementPainter).getConnection());
            }
        }
        Point point = new Point(e.getX(), e.getY());

        for(ElementPainter elementPainter : tempTab.getListOfPainters()){
            if(elementPainter.elementAt(point) == true){
               if(elementPainter instanceof ClassPainter || elementPainter instanceof EnumPainter || elementPainter instanceof InterfacePainter){
                   elementPaintersToRemove.add(elementPainter);
                   tempTab.getDiagram().removeChild(((InterClassPainter) elementPainter).getInterclass());
               }
            }
        }
        for(ElementPainter elementPainter : elementPaintersToRemove){
            tempTab.getListOfPainters().remove(elementPainter);
        }
    }

    @Override
    public void stateMouseReleased(MouseEvent e, DiagramView tempTab) {

    }

    @Override
    public void stateMouseDragged(MouseEvent e, DiagramView tempTab) {

    }
}
