package raf.classycraft.app.model.elementDiagram;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import raf.classycraft.app.model.elementDiagram.concreteConnections.*;
import raf.classycraft.app.model.elementDiagram.concreteInterclass.ClassInterClass;
import raf.classycraft.app.model.elementDiagram.concreteInterclass.EnumInterclass;
import raf.classycraft.app.model.elementDiagram.concreteInterclass.InterfaceInterclass;

import java.awt.*;
import java.awt.geom.Line2D;
@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type"
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = Aggregation.class, name = "Aggregation"),
        @JsonSubTypes.Type(value = Composition.class, name = "Composition"),
        @JsonSubTypes.Type(value = Dependency.class, name = "Dependency"),
        @JsonSubTypes.Type(value = Generalization.class, name = "Generalization"),
        @JsonSubTypes.Type(value = ConnectionInfo.class, name = "ConnectionInfo"),
})
public abstract class Connection extends DiagramElement {
    private Interclass classFrom;
    private Interclass classTo;
    private ConnectionInfo connectionInfo;
    @JsonIgnore
    private Line2D line2D;
    public Connection(Color color, int stroke, Interclass from, Interclass to, Line2D line2D, ConnectionInfo connectionInfo) {
        super(color, stroke);
        this.classFrom = from;
        this.classTo = to;
        this.line2D = line2D;
        this.connectionInfo = connectionInfo;
    }
    public Connection(){}
    public Connection(Color color, int stroke,Line2D line2D){
        super(color, stroke);
        this.line2D = line2D;
    }
    public void lineUpdate(){
        if(this.classFrom != null && this.classTo != null){
            Point startPoint = classFrom.getPoint();
            Point endPoint = classTo.getPoint();
            Point closestConnectionDot = null;
            Double minDistance = Double.MAX_VALUE;
            for(Point connectionDot : classFrom.getConnectionDots()){
                double distance = Math.sqrt(Math.pow(connectionDot.x - endPoint.x, 2) + Math.pow(connectionDot.y - endPoint.y, 2));
                if(distance < minDistance){
                    minDistance = distance;
                    closestConnectionDot = connectionDot;
                }
            }
            startPoint = closestConnectionDot;
            minDistance = Double.MAX_VALUE;

            for (Point connectionDot : classTo.getConnectionDots()) {
                double distance = Math.sqrt(Math.pow(startPoint.x - connectionDot.x, 2) + Math.pow(startPoint.y - connectionDot.y, 2));
                if (distance < minDistance) {
                    minDistance = distance;
                    closestConnectionDot = connectionDot;
                }
            }
            endPoint = closestConnectionDot;
            this.setLine2D(new Line2D.Double(startPoint, endPoint));
        }
    }

    public Interclass getClassFrom() {
        return classFrom;
    }

    public void setClassFrom(Interclass classFrom) {
        this.classFrom = classFrom;
    }

    public Interclass getClassTo() {
        return classTo;
    }

    public void setClassTo(Interclass classTo) {
        this.classTo = classTo;
    }

    public Line2D getLine2D() {
        return line2D;
    }

    public void setLine2D(Line2D line2D) {
        this.line2D = line2D;
    }

    public ConnectionInfo getConnectionInfo() {
        return connectionInfo;
    }

    public void setConnectionInfo(ConnectionInfo connectionInfo) {
        this.connectionInfo = connectionInfo;
    }
}
