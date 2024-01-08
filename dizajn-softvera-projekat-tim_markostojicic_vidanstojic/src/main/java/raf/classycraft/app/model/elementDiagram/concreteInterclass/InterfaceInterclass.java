package raf.classycraft.app.model.elementDiagram.concreteInterclass;

import com.fasterxml.jackson.annotation.JsonTypeName;
import raf.classycraft.app.model.elementDiagram.Interclass;
import raf.classycraft.app.model.elementDiagram.classContent.Attribute;
import raf.classycraft.app.model.elementDiagram.classContent.ClassContent;
import raf.classycraft.app.model.elementDiagram.classContent.Method;
import raf.classycraft.app.observer.ISubscriber;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
@JsonTypeName("Interface")
public class InterfaceInterclass extends Interclass {
    private List<InterfaceInterclass> extendsInterfaceList = new ArrayList<>();
    private List<Method> overrideMethods = new ArrayList<>();

    public InterfaceInterclass(Point point,Color color, int stroke, String name, String visibility) {
        super(point,color, stroke, name, visibility);
    }
    public InterfaceInterclass(){super();}

    @Override
    public String getName() {
        return super.getName();
    }

    @Override
    public void setName(String name) {
        super.setName(name);
    }

    @Override
    public Point getPoint() {
        return super.getPoint();
    }

    @Override
    public void setPoint(Point point) {
        super.setPoint(point);
    }

    @Override
    public String getVisibility() {
        return super.getVisibility();
    }

    @Override
    public void setVisibility(String visibility) {
        super.setVisibility(visibility);
    }

    @Override
    public java.util.List<Attribute> getAttributes() {
        return super.getAttributes();
    }

    @Override
    public void setAttributes(java.util.List<Attribute> attributes) {
        super.setAttributes(attributes);
    }

    @Override
    public java.util.List<Method> getMethods() {
        return super.getMethods();
    }

    @Override
    public void setMethods(List<Method> methods) {
        super.setMethods(methods);
    }
    @Override
    public List<ClassContent> getClassContents() {
        return super.getClassContents();
    }

    @Override
    public List<Point> getConnectionDots() {
        return super.getConnectionDots();
    }

    @Override
    public void addSubscriber(ISubscriber iSubscriber) {
        super.addSubscriber(iSubscriber);
    }

    @Override
    public void removeSubscriber(ISubscriber iSubscriber) {
        super.removeSubscriber(iSubscriber);
    }

    @Override
    public void notifySub(Object notify) {
        super.notifySub(notify);
    }

    @Override
    public List<ISubscriber> getListOfSubscribers() {
        return super.getListOfSubscribers();
    }

    public List<InterfaceInterclass> getExtendsInterfaceList() {
        return extendsInterfaceList;
    }

    public List<Method> getOverrideMethods() {
        return overrideMethods;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        if(this.extendsInterfaceList.isEmpty() == true){
            stringBuilder.append("public interface "+ this.getName()+"{ "+"\n");
        }
        else{
            stringBuilder.append("public interface " + this.getName() + " extends ");

            int sizeExtendsInterface= extendsInterfaceList.size();
            for (int i = 0; i < sizeExtendsInterface; i++) {
                InterfaceInterclass interfaceInterclass = extendsInterfaceList.get(i);
                stringBuilder.append(interfaceInterclass.getName());

                if (i != sizeExtendsInterface - 1) {
                    stringBuilder.append(", ");
                }
            }
            stringBuilder.append("{\n");
        }


        for(ClassContent classContent : this.getClassContents()){
            stringBuilder.append(classContent.toString().replace("{","").replace("}",";"));
            stringBuilder.append("\n");
        }
        stringBuilder.append("}");
        return stringBuilder.toString();
    }
}
