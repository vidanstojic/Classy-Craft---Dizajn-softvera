package raf.classycraft.app.state.concrete;

import raf.classycraft.app.gui.tree.ClassyTreeImplementation;
import raf.classycraft.app.gui.tree.model.ClassyTreeItem;
import raf.classycraft.app.gui.view.DiagramView;
import raf.classycraft.app.gui.view.MainFrame;
import raf.classycraft.app.gui.view.paint.*;
import raf.classycraft.app.model.elementDiagram.DiagramElement;
import raf.classycraft.app.model.elementDiagram.Interclass;
import raf.classycraft.app.state.State;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class RemoveState implements State {

    private Rectangle rectangle;
    private ClassyTreeImplementation classyTreeImplementation = (ClassyTreeImplementation) MainFrame.getInstance().getClassyTree();

    @Override
    public void stateMousePressed(MouseEvent e, DiagramView tempTab) {
        rectangle = tempTab.getRectangle();
        rectangle.setRect((int) (e.getX() / tempTab.getAffineTransform().getScaleX()), (int) (e.getY() / tempTab.getAffineTransform().getScaleY()), 3, 3);
        tempTab.setRectangle(rectangle);
        tempTab.repaint();
        List<ElementPainter> elementPaintersToRemove = new ArrayList<>();
        List<DiagramElement> elementModelsToRemove = new ArrayList<>();
        for(ElementPainter elementPainter : tempTab.getListOfSelectedPainters()){
            elementPaintersToRemove.add(elementPainter);
            if (elementPainter.getRectangle() != null)
                elementModelsToRemove.add(((InterClassPainter) elementPainter).getInterclass());
            else if (elementPainter.getLine2D() != null) {
                elementModelsToRemove.add(((ConnectionPainter) elementPainter).getConnection());
            }
        }
        Point point = new Point(e.getX(), e.getY());

        for(ElementPainter elementPainter : tempTab.getListOfPainters()){
            if (elementPainter instanceof InterClassPainter) {
                if (rectangle.intersects(elementPainter.getRectangle())) {
                    if (elementPainter instanceof ClassPainter || elementPainter instanceof EnumPainter || elementPainter instanceof InterfacePainter) {
                        elementPaintersToRemove.add(elementPainter);
                        for(ElementPainter connectionElement : tempTab.getListOfPainters()){
                            // ovde se samo proverava da li se klasa/enum/ interfejs koji brisemo nalazi u nekoj vezi kao interClassTo ili from
                            // ukoliko se nalazi onda dodajemo tu vezu u listu elemenata za brisanje
                            if(connectionElement instanceof ConnectionPainter && ( ((ConnectionPainter) connectionElement).getConnection().getClassFrom() == (Interclass) ((InterClassPainter) elementPainter).getInterclass() || ((ConnectionPainter) connectionElement).getConnection().getClassTo() == (Interclass) ((InterClassPainter) elementPainter).getInterclass())){
                                elementPaintersToRemove.add(connectionElement);
                                elementModelsToRemove.add(((ConnectionPainter) connectionElement).getConnection());
                            }
                        }
                        elementModelsToRemove.add(((InterClassPainter) elementPainter).getInterclass());
                    }
                }
            }
            else if (elementPainter instanceof ConnectionPainter){
                if (rectangle.intersectsLine(elementPainter.getLine2D())){
                    if (elementPainter instanceof AggregationPainter || elementPainter instanceof CompositionPainter || elementPainter instanceof DependancyPainter || elementPainter instanceof GeneralizationPainter) {
                        elementPaintersToRemove.add(elementPainter);
                        elementModelsToRemove.add(((ConnectionPainter) elementPainter).getConnection());
                    }
                }
            }
        }


        for(ElementPainter elementPainter : elementPaintersToRemove){
            tempTab.getListOfPainters().remove(elementPainter);
        }
        for(DiagramElement elementToRemove : elementModelsToRemove){
            ClassyTreeItem itemToRemove = this.classyTreeImplementation.findTreeItem((ClassyTreeItem) classyTreeImplementation.getTreeModel().getRoot() ,elementToRemove);
            this.classyTreeImplementation.removeChild(itemToRemove);
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
