
package raf.classycraft.app.gui.view;
import raf.classycraft.app.command.CommandManager;
import raf.classycraft.app.gui.controller.drawingToolbarActions.MyMouseListener;
import raf.classycraft.app.gui.tree.ClassyTreeImplementation;
import raf.classycraft.app.gui.tree.model.ClassyTreeItem;
import raf.classycraft.app.gui.view.paint.*;
import raf.classycraft.app.model.compositeAbstract.ClassyNode;
import raf.classycraft.app.model.compositeImplement.Diagram;
import raf.classycraft.app.model.elementDiagram.Connection;
import raf.classycraft.app.model.elementDiagram.DiagramElement;
import raf.classycraft.app.model.elementDiagram.concreteConnections.Aggregation;
import raf.classycraft.app.model.elementDiagram.concreteConnections.Composition;
import raf.classycraft.app.model.elementDiagram.concreteConnections.Dependency;
import raf.classycraft.app.model.elementDiagram.concreteConnections.Generalization;
import raf.classycraft.app.model.elementDiagram.concreteInterclass.ClassInterClass;
import raf.classycraft.app.model.elementDiagram.concreteInterclass.EnumInterclass;
import raf.classycraft.app.model.elementDiagram.concreteInterclass.InterfaceInterclass;
import raf.classycraft.app.observer.ISubscriber;
import raf.classycraft.app.observer.NotificationDiagramView;
import raf.classycraft.app.observer.TypeDiagramView;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
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
    private Line2D createLine2D;
    private AffineTransform affineTransform;
    private ClassyTreeImplementation classyTreeImplementation = (ClassyTreeImplementation) MainFrame.getInstance().getClassyTree();
    private CommandManager commandManager;
    public DiagramView(Diagram diagram){
        this.diagram = diagram;
        diagram.addSubscriber(this);
        this.myMouseListener = new MyMouseListener(this);
        addMouseListener(myMouseListener);
        addMouseMotionListener(myMouseListener);
        this.rectangle = new Rectangle();
        this.setPreferredSize(new Dimension(MainFrame.getInstance().getWidth() * 2, MainFrame.getInstance().getHeight() * 2));
        affineTransform = new AffineTransform();
        commandManager = new CommandManager();
        loadPainters(diagram);
    }
    public void loadPainters(Diagram diagram){
        for (ClassyNode classyNode : diagram.getChildren()){
            DiagramElement diagramElement1 = (DiagramElement) classyNode;
            if (diagramElement1 instanceof ClassInterClass){
                ClassPainter classPainter = new ClassPainter(((ClassInterClass) diagramElement1).getPoint(), (ClassInterClass) diagramElement1);
                this.getListOfPainters().add(classPainter);
            } else if (diagramElement1 instanceof InterfaceInterclass) {
                InterfacePainter interfacePainter = new InterfacePainter(((InterfaceInterclass) diagramElement1).getPoint(), (InterfaceInterclass) diagramElement1);
                this.getListOfPainters().add(interfacePainter);
            } else if (diagramElement1 instanceof EnumInterclass) {
                EnumPainter enumPainter = new EnumPainter(((EnumInterclass) diagramElement1).getPoint(), (EnumInterclass) diagramElement1);
                this.getListOfPainters().add(enumPainter);
            } else if (diagramElement1 instanceof Aggregation) {
                AggregationPainter aggregationPainter = new AggregationPainter(((Connection) diagramElement1), createLine(((Aggregation) diagramElement1).getClassFrom().getPoint(),((Aggregation) diagramElement1).getClassTo().getPoint()));
                this.getListOfPainters().add(aggregationPainter);
            } else if (diagramElement1 instanceof Composition) {
                CompositionPainter compositionPainter = new CompositionPainter(((Connection) diagramElement1), createLine(((Composition) diagramElement1).getClassFrom().getPoint(),((Composition) diagramElement1).getClassTo().getPoint()));
                this.getListOfPainters().add(compositionPainter);
            } else if (diagramElement1 instanceof Dependency) {
                DependancyPainter dependancyPainter = new DependancyPainter(((Dependency) diagramElement1), createLine(((Dependency) diagramElement1).getClassFrom().getPoint(),((Dependency) diagramElement1).getClassTo().getPoint()));
                this.getListOfPainters().add(dependancyPainter);
            } else if (diagramElement1 instanceof Generalization) {
                GeneralizationPainter generalizationPainter = new GeneralizationPainter(((Generalization) diagramElement1), createLine(((Generalization) diagramElement1).getClassFrom().getPoint(),((Generalization) diagramElement1).getClassTo().getPoint()));
                this.getListOfPainters().add(generalizationPainter);
            }
        }
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D graphics2D = (Graphics2D) g;
        graphics2D.scale(affineTransform.getScaleX(), affineTransform.getScaleY());
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
    public Line2D createLine(Point start, Point end){
        return createLine2D = new Line2D.Double(start.x, start.y, end.x, end.y);
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
            if(notificationDiagramView.getTypeDiagramView() == TypeDiagramView.REMOVE_DIAGRAM_ELEMENT){
                List<ElementPainter> paintersToRemove = new ArrayList<>();
                for(ElementPainter elementPainter : this.listOfPainters){
                    if(elementPainter instanceof InterClassPainter && ((InterClassPainter) elementPainter).getInterclass().equals(notificationDiagramView.getDiagramElement())){
                        paintersToRemove.add(elementPainter);
                    }
                    else if(elementPainter instanceof ConnectionPainter && ((ConnectionPainter) elementPainter).getConnection().equals(notificationDiagramView.getDiagramElement())){
                        paintersToRemove.add(elementPainter);
                        ClassyTreeItem itemToRemove = this.classyTreeImplementation.findTreeItem((ClassyTreeItem) classyTreeImplementation.getTreeModel().getRoot(), ((ConnectionPainter) elementPainter).getConnection());
                        if(itemToRemove != null){
                            this.classyTreeImplementation.removeChild(itemToRemove);
                        }
                    }
                }

                for(ElementPainter painterToRemove : paintersToRemove){
                    this.getListOfPainters().remove(painterToRemove);
                }
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

    public CommandManager getCommandManager() {
        return commandManager;
    }

    public AffineTransform getAffineTransform() {
        return affineTransform;
    }
}