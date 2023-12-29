package raf.classycraft.app.model.elementDiagram.concreteConnections;

import com.fasterxml.jackson.annotation.JsonTypeName;
import raf.classycraft.app.model.elementDiagram.classContent.Visibility;
@JsonTypeName("ConnectionInfo")
public class ConnectionInfo {
    private String nameOfConnection;
    private String nameofAttribute;
    private String cardinaliy;
    private Visibility visibility;

    // konstruktor za agregaciju i kompoziciju
    public ConnectionInfo(String nameOfConnection, String nameofAttribute, String cardinaliy, Visibility visibility) {
        this.nameOfConnection = nameOfConnection;
        this.nameofAttribute = nameofAttribute;
        this.cardinaliy = cardinaliy;
        this.visibility = visibility;
    }
    public ConnectionInfo(){}
    // konstruktor za dependancy i generalizaciju


    public ConnectionInfo(String nameOfConnection) {
        this.nameOfConnection = nameOfConnection;
    }

    public String getNameOfConnection() {
        return nameOfConnection;
    }

    public void setNameOfConnection(String nameOfConnection) {
        this.nameOfConnection = nameOfConnection;
    }

    public String getNameofAttribute() {
        return nameofAttribute;
    }

    public void setNameofAttribute(String nameofAttribute) {
        this.nameofAttribute = nameofAttribute;
    }

    public String getCardinaliy() {
        return cardinaliy;
    }

    public void setCardinaliy(String cardinaliy) {
        this.cardinaliy = cardinaliy;
    }

    public Visibility getVisibility() {
        return visibility;
    }

    public void setVisibility(Visibility visibility) {
        this.visibility = visibility;
    }
}
