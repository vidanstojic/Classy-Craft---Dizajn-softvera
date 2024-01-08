package raf.classycraft.app.model.elementDiagram.classContent;

import com.fasterxml.jackson.annotation.JsonTypeName;
import raf.classycraft.app.model.elementDiagram.classContent.ClassContent;

@JsonTypeName("Method")
public class Method extends ClassContent {
    public Method(){}
    public Method(String name, Visibility visibility, String returnType) {
        super(name, visibility, returnType);
    }

    public Method(String name, Visibility visibility, String returnType, String abstractContentOrNot, String staticContentOrNot) {
        super(name, visibility, returnType, abstractContentOrNot, staticContentOrNot);
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
    public Visibility getVisibility() {
        return super.getVisibility();
    }

    @Override
    public void setVisibility(Visibility visibility) {
        super.setVisibility(visibility);
    }

    @Override
    public String getReturnType() {
        return super.getReturnType();
    }

    @Override
    public void setReturnType(String returnType) {
        super.setReturnType(returnType);
    }

    @Override
    public String getAbstractContentOrNot() {
        return super.getAbstractContentOrNot();
    }

    @Override
    public void setAbstractContentOrNot(String abstractContentOrNot) {
        super.setAbstractContentOrNot(abstractContentOrNot);
    }

    @Override
    public String getStaticContentOrNot() {
        return super.getStaticContentOrNot();
    }

    @Override
    public void setStaticContentOrNot(String staticContentOrNot) {
        super.setStaticContentOrNot(staticContentOrNot);
    }

    @Override
    public String toString() {
        // proveriti da li izlazi u ispisu metode da je static/abstract
        StringBuilder stringBuilder = new StringBuilder();
        String staticMethod = this.getStaticContentOrNot().replace("{","").replace("}","");
        String abstractMethod = this.getAbstractContentOrNot().replace("{","").replace("}","");
        stringBuilder.append(this.getVisibility().toString().toLowerCase()+" " + abstractMethod+" " + staticMethod+" "+this.getReturnType()+" "+this.getName()+"()"+"{}");
        return stringBuilder.toString();
    }
}
