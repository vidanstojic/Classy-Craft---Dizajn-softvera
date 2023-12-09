package raf.classycraft.app.state.concrete;

import raf.classycraft.app.gui.view.DiagramView;
import raf.classycraft.app.gui.view.paint.*;
import raf.classycraft.app.state.State;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class RemoveState implements State {

    private Rectangle rectangle;
    @Override
    public void stateMousePressed(MouseEvent e, DiagramView tempTab) {
        rectangle = tempTab.getRectangle();
        rectangle.setRect(e.getX(), e.getY(), 3, 3);
        tempTab.setRectangle(rectangle);
        tempTab.repaint();
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
            if (elementPainter instanceof InterClassPainter) {
                if (rectangle.intersects(elementPainter.getRectangle())) {
                    if (elementPainter instanceof ClassPainter || elementPainter instanceof EnumPainter || elementPainter instanceof InterfacePainter) {
                        elementPaintersToRemove.add(elementPainter);
                        tempTab.getDiagram().removeChild(((InterClassPainter) elementPainter).getInterclass());
                    }
                }
            }else if (elementPainter instanceof ConnectionPainter){
                if (rectangle.intersectsLine(elementPainter.getLine2D())){
                    if (elementPainter instanceof AggregationPainter || elementPainter instanceof CompositionPainter || elementPainter instanceof DependancyPainter || elementPainter instanceof GeneralizationPainter) {
                        elementPaintersToRemove.add(elementPainter);
                        tempTab.getDiagram().removeChild(((ConnectionPainter) elementPainter).getConnection());
                    }
                }
            }
        }
        for(ElementPainter elementPainter : elementPaintersToRemove){
            tempTab.getListOfPainters().remove(elementPainter);
        }
    }

    @Override
    public void stateMouseReleased(MouseEvent e, DiagramView tempTab) {
        rectangle.setSize(0,0);
        tempTab.setRectangle(rectangle);
        tempTab.repaint();
    }

    @Override
    public void stateMouseDragged(MouseEvent e, DiagramView tempTab) {

    }
}
