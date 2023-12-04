
package raf.classycraft.app.gui.view;
import raf.classycraft.app.gui.controller.drawingToolbarActions.MyMouseListener;
import raf.classycraft.app.gui.view.paint.ElementPainter;
import raf.classycraft.app.model.compositeImplement.Diagram;
import raf.classycraft.app.observer.ISubscriber;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;


public class DiagramView extends JPanel implements ISubscriber {

    private Diagram diagram;
    private MyMouseListener myMouseListener;
    private List<ElementPainter> listOfPainters = new ArrayList<>();

    public DiagramView(Diagram diagram){
        this.diagram = diagram;
        diagram.addSubscriber(this);/// proveriti da li je ovo dozvoljeno zbog MVC-A
        this.myMouseListener = new MyMouseListener(this);
        addMouseListener(myMouseListener);
        addMouseMotionListener(myMouseListener);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for(ElementPainter painter : listOfPainters){
            painter.paint(g);
        }

    }

    public DiagramView(){

    }


    @Override
    public void update(Object notify) {
        repaint();
    }

    public List<ElementPainter> getListOfPainters() {
        return listOfPainters;
    }

    public void setListOfPainters(List<ElementPainter> listOfPainters) {
        this.listOfPainters = listOfPainters;
    }

    public Diagram getDiagram() {
        return diagram;
    }
}