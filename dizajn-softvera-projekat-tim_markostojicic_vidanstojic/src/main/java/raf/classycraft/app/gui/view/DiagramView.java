
package raf.classycraft.app.gui.view;
import raf.classycraft.app.gui.controller.drawingToolbarActions.MyMouseListener;
import raf.classycraft.app.gui.view.paint.ElementPainter;
import raf.classycraft.app.model.compositeImplement.Diagram;
import raf.classycraft.app.model.elementDiagram.DiagramElement;
import raf.classycraft.app.observer.ISubscriber;
import raf.classycraft.app.observer.NotificationDiagramView;
import raf.classycraft.app.observer.TypeDiagramView;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Line2D;
import java.util.ArrayList;
import java.util.List;


public class DiagramView extends JPanel implements ISubscriber {

    private Diagram diagram;
    private MyMouseListener myMouseListener;
    private List<ElementPainter> listOfPainters = new ArrayList<>();
    private List<ElementPainter>listOfSelectedPainters = new ArrayList<>();
    private Rectangle rectangle;
    private DiagramElement diagramElement;
    private Line2D line2D;

    public DiagramView(Diagram diagram){
        this.diagram = diagram;
        diagram.addSubscriber(this);/// proveriti da li je ovo dozvoljeno zbog MVC-A
        this.myMouseListener = new MyMouseListener(this);
        addMouseListener(myMouseListener);
        addMouseMotionListener(myMouseListener);
        this.rectangle = new Rectangle();
        this.setPreferredSize(new Dimension(MainFrame.getInstance().getWidth() * 2, MainFrame.getInstance().getHeight() * 2));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D graphics2D = (Graphics2D) g;
        for(ElementPainter painter : listOfPainters){
            painter.paint(graphics2D, this.diagramElement);
        }
        if(rectangle != null){
            graphics2D.setColor(new Color(200, 240, 255, 100));
            graphics2D.setBackground(new Color(200, 240, 255, 100));
            graphics2D.fill(rectangle);
            graphics2D.draw(rectangle);

        }
        revalidate();
    }

    public DiagramView(){

    }

    public void lineRefresh(Point start, Point end){
       line2D = new Line2D.Double(start.x, start.y, end.x, end.y);
    }

    @Override
    public void update(Object notify) {
        if(notify instanceof  NotificationDiagramView){
            NotificationDiagramView notificationDiagramView = (NotificationDiagramView) notify;
            if(notificationDiagramView.getTypeDiagramView() == TypeDiagramView.ADD_DIAGRAM_ELEMENT){
                this.diagramElement = notificationDiagramView.getDiagramElement();
            }
        }
        repaint();

    }
    public void removeListOfSelectedPainters(List<ElementPainter> listOfSelectedPainters){
        List<ElementPainter> elementPainters = new ArrayList<>();
        elementPainters.addAll(listOfSelectedPainters);
        for (ElementPainter elementPainter : elementPainters){
            listOfSelectedPainters.remove(elementPainter);
        }
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

    public Line2D getLine2D() {
        return line2D;
    }

    public void setLine2D(Line2D line2D) {
        this.line2D = line2D;
    }

    public Rectangle getRectangle() {
        return rectangle;
    }

    public void setRectangle(Rectangle rectangle) {
        this.rectangle = rectangle;
    }

    public List<ElementPainter> getListOfSelectedPainters() {
        return listOfSelectedPainters;
    }

    public void setListOfSelectedPainters(List<ElementPainter> listOfSelectedPainters) {
        this.listOfSelectedPainters = listOfSelectedPainters;
    }

  
}