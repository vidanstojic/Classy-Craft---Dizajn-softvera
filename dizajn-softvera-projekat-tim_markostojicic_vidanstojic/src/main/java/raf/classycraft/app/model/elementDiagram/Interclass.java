package raf.classycraft.app.model.elementDiagram;

import raf.classycraft.app.model.elementDiagram.DiagramElement;
import raf.classycraft.app.model.elementDiagram.classContent.Attribute;
import raf.classycraft.app.model.elementDiagram.classContent.ClassContent;
import raf.classycraft.app.model.elementDiagram.classContent.Method;
import raf.classycraft.app.observer.IPublisher;
import raf.classycraft.app.observer.ISubscriber;
import raf.classycraft.app.observer.NotificationDiagramView;
import raf.classycraft.app.observer.TypeDiagramView;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public abstract class Interclass extends DiagramElement implements IPublisher {
    private String name;
    private String visibility;
    private Rectangle rectangle;
    private Point point;
    private Point specialPoint;
    private List<Attribute> attributes = new ArrayList<>();
    private List<Method> methods = new ArrayList<>();

    private List<ClassContent> classContents = new ArrayList<>();
    private List<Point> connectionDots = new ArrayList<>();

    private List<ISubscriber> listOfSubscribers = new ArrayList<>();


    public Interclass(Point point,Color color, int stroke, String name, String visibility) {
        super(color, stroke);
        this.name = name;
        this.visibility = visibility;
        this.point = point;
        this.specialPoint = point;
    }

    public Point getPoint() {
        return point;
    }

    public void setPoint(Point point) {
        this.point = point;
        NotificationDiagramView notificationDiagramView = new NotificationDiagramView(TypeDiagramView.MODIFY_LOCATION);
        notifySub(notificationDiagramView);
    }
    public Point getSpecialPoint(){
        return this.specialPoint;
    }
    public Point setSpecialPoint(Point point){
        return this.specialPoint = point;
    }
    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    public String getVisibility() {
        return visibility;
    }

    public void setVisibility(String visibility) {
        this.visibility = visibility;
    }

    public List<Attribute> getAttributes() {
        return attributes;
    }

    public void setAttributes(List<Attribute> attributes) {
        this.attributes = attributes;
    }

    public List<Method> getMethods() {
        return methods;
    }

    public void setMethods(List<Method> methods) {
        this.methods = methods;
    }

    public List<ClassContent> getClassContents() {
        return classContents;
    }

    public Rectangle getRectangle() {
        return rectangle;
    }

    public void setRectangle(Rectangle rectangle) {
        this.rectangle = rectangle;
    }

    public List<Point> getConnectionDots() {
        return connectionDots;
    }

    public void setClassContents(List<ClassContent> classContents) {
        this.classContents = classContents;
    }

    public Interclass(Color color, int stroke) {
        super(color, stroke);
    }

    @Override
    public void addSubscriber(ISubscriber iSubscriber) {
        this.listOfSubscribers.add(iSubscriber);
    }

    @Override
    public void removeSubscriber(ISubscriber iSubscriber) {
        this.listOfSubscribers.remove(iSubscriber);
    }

    @Override
    public void notifySub(Object notify) {
        for(ISubscriber subscriber : this.listOfSubscribers){
            subscriber.update(notify);
        }
    }

    public List<ISubscriber> getListOfSubscribers() {
        return listOfSubscribers;
    }
}
