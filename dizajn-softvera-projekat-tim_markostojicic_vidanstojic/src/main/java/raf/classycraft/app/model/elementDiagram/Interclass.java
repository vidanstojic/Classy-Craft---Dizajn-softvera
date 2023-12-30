package raf.classycraft.app.model.elementDiagram;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import raf.classycraft.app.model.compositeImplement.Diagram;
import raf.classycraft.app.model.compositeImplement.MyPackage;
import raf.classycraft.app.model.compositeImplement.Project;
import raf.classycraft.app.model.compositeImplement.ProjectExplorer;
import raf.classycraft.app.model.elementDiagram.DiagramElement;
import raf.classycraft.app.model.elementDiagram.classContent.Attribute;
import raf.classycraft.app.model.elementDiagram.classContent.ClassContent;
import raf.classycraft.app.model.elementDiagram.classContent.Method;
import raf.classycraft.app.model.elementDiagram.concreteInterclass.ClassInterClass;
import raf.classycraft.app.model.elementDiagram.concreteInterclass.EnumInterclass;
import raf.classycraft.app.model.elementDiagram.concreteInterclass.InterfaceInterclass;
import raf.classycraft.app.observer.IPublisher;
import raf.classycraft.app.observer.ISubscriber;
import raf.classycraft.app.observer.NotificationDiagramView;
import raf.classycraft.app.observer.TypeDiagramView;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type"
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = ClassInterClass.class, name = "Class"),
        @JsonSubTypes.Type(value = InterfaceInterclass.class, name = "Interface"),
        @JsonSubTypes.Type(value = EnumInterclass.class, name = "Enum"),
})
public abstract class Interclass extends DiagramElement implements IPublisher {
    private String name;
    private String visibility;
    @JsonIgnore
    private Rectangle rectangle;
    private Point point;
    private Point specialPoint;
    private List<Attribute> attributes = new ArrayList<>();
    private List<Method> methods = new ArrayList<>();

    private List<ClassContent> classContents = new ArrayList<>();
    private List<Point> connectionDots = new ArrayList<>();
    @JsonIgnore
    private List<ISubscriber> listOfSubscribers = new ArrayList<>();


    public Interclass(Point point,Color color, int stroke, String name, String visibility) {
        super(color, stroke);
        this.name = name;
        this.visibility = visibility;
        this.point = point;
        this.specialPoint = point;
    }
    public Interclass(){
        super();
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
