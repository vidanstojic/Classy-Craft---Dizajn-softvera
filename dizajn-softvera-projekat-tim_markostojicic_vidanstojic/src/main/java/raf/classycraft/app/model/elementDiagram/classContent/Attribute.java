package raf.classycraft.app.model.elementDiagram.classContent;

import com.fasterxml.jackson.annotation.JsonTypeName;
import raf.classycraft.app.model.elementDiagram.classContent.ClassContent;

@JsonTypeName("Attribute")
public class Attribute extends ClassContent {
    private boolean listOrNot;
    public Attribute(){}
    public Attribute(String name, Visibility visibility, String returnType) {
        super(name, visibility, returnType);
    }

    public Attribute(String name, Visibility visibility, String returnType, String abstractContentOrNot, String staticContentOrNot) {
        super(name, visibility, returnType, abstractContentOrNot, staticContentOrNot);
    }
    public Attribute(String name){super(name);}
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
        StringBuilder stringBuilder = new StringBuilder();
        String staticAttribute = this.getStaticContentOrNot().replace("{","").replace("}","");
        String abstractAttribute = this.getAbstractContentOrNot().replace("{","").replace("}","");
        if(getVisibility() != null && getReturnType() != null){
            stringBuilder.append(this.getVisibility().toString().toLowerCase() +" "+ abstractAttribute+" " + staticAttribute+" "+this.getReturnType()+" "+this.getName()+";");
        }
        else{
            stringBuilder.append(this.getName()+";");
        }
        return stringBuilder.toString();
    }

    public boolean isListOrNot() {
        return listOrNot;
    }

    public void setListOrNot(boolean listOrNot) {
        this.listOrNot = listOrNot;
    }
}
