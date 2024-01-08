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

    private String abstractClass;

    private List<Attribute> associationAttribute = new ArrayList<>();

    private List<Method> overrideMethods = new ArrayList<>();

    private List<ClassInterClass> extendsClassList = new ArrayList<>();

    private List<InterfaceInterclass> implementsInterfaceList = new ArrayList<>();

    public ClassInterClass(Point point, Color color, int stroke, String name, String visibility, String abstractClass) {
        super(point,color, stroke, name, visibility);
        this.abstractClass = abstractClass;
    }
    public ClassInterClass(){
        super();
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

    public List<ClassInterClass> getExtendsClassList() {
        return extendsClassList;
    }

    public List<InterfaceInterclass> getImplementsInterfaceList() {
        return implementsInterfaceList;
    }

    @Override
    public String getName() {
        return super.getName();
    }

    @Override
    public void setName(String name) {
        super.setName(name);
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        if(this.getExtendsClassList().isEmpty() == true && this.getImplementsInterfaceList().isEmpty() == true){
            if(this.abstractClass.equals("No")){
                stringBuilder.append("public class " + this.getName() + "{ " + "\n");
            }
            else{
                stringBuilder.append("public abstract class " + this.getName() + "{ " + "\n");
            }
        }
        else if(this.getExtendsClassList().isEmpty() == false && this.getImplementsInterfaceList().isEmpty() == false){
            if(this.abstractClass.equals("No"))  stringBuilder.append("public class " + this.getName() + " extends ");
            else stringBuilder.append("public abstract class " + this.getName() + " extends ");


            int sizeExtendClassList = extendsClassList.size();
            for (int i = 0; i < sizeExtendClassList; i++) {
                ClassInterClass classInterClass = extendsClassList.get(i);
                stringBuilder.append(classInterClass.getName());


                if (i != sizeExtendClassList - 1) {
                    stringBuilder.append(", ");
                }
            }
            stringBuilder.append(" implements ");
            int sizeImplementsInterface = implementsInterfaceList.size();
            for (int i = 0; i < sizeImplementsInterface; i++) {
                InterfaceInterclass interfaceInterclass = implementsInterfaceList.get(i);
                stringBuilder.append(interfaceInterclass.getName());


                if (i != sizeImplementsInterface - 1) {
                    stringBuilder.append(", ");
                }
            }
            stringBuilder.append("{\n");
        }
        else if(this.getExtendsClassList().isEmpty() == false && this.getImplementsInterfaceList().isEmpty() == true){
            if(this.abstractClass.equals("No"))  stringBuilder.append("public class " + this.getName() + " extends ");
            else stringBuilder.append("public abstract class " + this.getName() + " extends ");

            int sizeExtendClassList = extendsClassList.size();
            for (int i = 0; i < sizeExtendClassList; i++) {
                ClassInterClass classInterClass = extendsClassList.get(i);
                stringBuilder.append(classInterClass.getName());


                if (i != sizeExtendClassList - 1) {
                    stringBuilder.append(", ");
                }
            }
            stringBuilder.append("{\n");
        }
        else if(this.getExtendsClassList().isEmpty() == true && this.getImplementsInterfaceList().isEmpty() == false){
            if(this.abstractClass.equals("No"))  stringBuilder.append("public class " + this.getName() + " implements ");
            else stringBuilder.append("public abstract class " + this.getName() + " implements ");

            int sizeImplementsInterface = implementsInterfaceList.size();
            for (int i = 0; i < sizeImplementsInterface; i++) {
                InterfaceInterclass interfaceInterclass = implementsInterfaceList.get(i);
                stringBuilder.append(interfaceInterclass.getName());

                if (i != sizeImplementsInterface - 1) {
                    stringBuilder.append(", ");
                }
            }
            stringBuilder.append("{\n");
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

        if(overrideMethods.isEmpty() == false){
            for(Method method : this.getOverrideMethods()){
                    stringBuilder.append("@Override");
                    stringBuilder.append("\n");
                    stringBuilder.append(method.toString().replaceAll("abstract", ""));
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

