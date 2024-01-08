package raf.classycraft.app.model.elementDiagram.concreteInterclass;

import com.fasterxml.jackson.annotation.JsonTypeName;
import raf.classycraft.app.model.elementDiagram.Interclass;
import raf.classycraft.app.model.elementDiagram.classContent.Attribute;
import raf.classycraft.app.model.elementDiagram.classContent.ClassContent;
import raf.classycraft.app.model.elementDiagram.classContent.Method;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
@JsonTypeName("Class")
public class ClassInterClass extends Interclass {

    private String name;
    private String abstractClass;

    private List<Attribute> associationAttribute = new ArrayList<>();

    private List<Method> overrideMethods = new ArrayList<>();

    private Boolean implementsInterface = false;
    private Boolean extendsClass = false;

    private String nameOfExtendClass;

    public ClassInterClass(Point point, Color color, int stroke, String name, String visibility, String abstractClass) {
        super(point,color, stroke, name, visibility);
        this.name = name;
        this.abstractClass = abstractClass;
    }
    public ClassInterClass(){
        super();
    }
    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
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
    public List<Attribute> getAttributes() {
        return super.getAttributes();
    }

    @Override
    public void setAttributes(List<Attribute> attributes) {
        super.setAttributes(attributes);
    }

    @Override
    public List<Method> getMethods() {
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

    public String getAbstractClass() {
        return abstractClass;
    }

    public void setAbstractClass(String abstractClass) {
        this.abstractClass = abstractClass;
    }

    public List<Attribute> getAssociationAttribute() {
        return associationAttribute;
    }

    public void setAssociationAttribute(List<Attribute> associationAttribute) {
        this.associationAttribute = associationAttribute;
    }

    public List<Method> getOverrideMethods() {
        return overrideMethods;
    }

    public void setOverrideMethods(List<Method> overrideMethods) {
        this.overrideMethods = overrideMethods;
    }

    public Boolean getImplementsInterface() {
        return implementsInterface;
    }

    public void setImplementsInterface(Boolean implementsInterface) {
        this.implementsInterface = implementsInterface;
    }

    public Boolean getExtendsClass() {
        return extendsClass;
    }

    public void setExtendsClass(Boolean extendsClass) {
        this.extendsClass = extendsClass;
    }

    public String getNameOfExtendClass() {
        return nameOfExtendClass;
    }

    public void setNameOfExtendClass(String nameOfExtendClass) {
        this.nameOfExtendClass = nameOfExtendClass;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        if(this.abstractClass.equals("No")){
            if(this.extendsClass == false && this.implementsInterface == false){
                stringBuilder.append("public class " + this.getName() + "{ " + "\n");
            }
            else if(this.extendsClass == true){
                stringBuilder.append("public class " + this.getName() + " extends "+this.nameOfExtendClass+"{" + "\n");
            }
            else if(this.implementsInterface == true){
                stringBuilder.append("public class " + this.getName() + " implements "+this.nameOfExtendClass+"{" + "\n");
            }
        }
        else{
            stringBuilder.append("public abstract class " + this.getName() + "{ " + "\n");
        }
        for (Attribute attribute : this.getAssociationAttribute()) {
            if(attribute.isListOrNot() == true){
                stringBuilder.append(attribute.getVisibility().toString().toLowerCase() + " List<" + attribute.getReturnType() + "> " + attribute.getName() + ";");
            }
            else{
                stringBuilder.append(attribute.getVisibility().toString().toLowerCase() + " " + attribute.getReturnType() + " " + attribute.getName() + ";");
            }
            stringBuilder.append("\n");
        }



        for(Attribute attribute : this.getAttributes()){
            stringBuilder.append(attribute.toString());
            stringBuilder.append("\n");
        }

        if(this.extendsClass == true || this.implementsInterface == true){
            for(Method method : this.getOverrideMethods()){
                stringBuilder.append("@Override");
                stringBuilder.append("\n");
                stringBuilder.append(method.toString());
                stringBuilder.append("\n");
            }
        }
        for(Method method : this.getMethods()){
            stringBuilder.append(method.toString());
            stringBuilder.append("\n");
        }

        stringBuilder.append("}");
        return stringBuilder.toString();
    }


}

