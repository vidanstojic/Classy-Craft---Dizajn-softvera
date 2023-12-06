package raf.classycraft.app.state.concrete;

import raf.classycraft.app.gui.view.DiagramView;
import raf.classycraft.app.gui.view.paint.ClassPainter;
import raf.classycraft.app.gui.view.paint.ElementPainter;
import raf.classycraft.app.gui.view.paint.EnumPainter;
import raf.classycraft.app.gui.view.paint.InterfacePainter;
import raf.classycraft.app.state.State;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class RemoveState implements State {

    @Override
    public void stateMousePressed(MouseEvent e, DiagramView tempTab) {
        Point point = new Point(e.getX(), e.getY());
        List<ElementPainter> elementPaintersToRemove = new ArrayList<>();
        for(ElementPainter elementPainter : tempTab.getListOfPainters()){
            if(elementPainter.elementAt(point) == true){
               if(elementPainter instanceof ClassPainter){
                   elementPaintersToRemove.add(elementPainter);
                   tempTab.getDiagram().removeChild(((ClassPainter) elementPainter).getClassInterClass());
               }
               else if(elementPainter instanceof EnumPainter){
                   elementPaintersToRemove.add(elementPainter);
                   tempTab.getDiagram().removeChild(((EnumPainter) elementPainter).getEnumInterclass());
               }
               else if(elementPainter instanceof InterfacePainter){
                   elementPaintersToRemove.add(elementPainter);
                   tempTab.getDiagram().removeChild(((InterfacePainter) elementPainter).getInterfaceInterclass());
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
