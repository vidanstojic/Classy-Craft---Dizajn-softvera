package raf.classycraft.app.model.elementDiagram.concreteConnections;

import raf.classycraft.app.model.elementDiagram.classContent.Visibility;

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

    // konstruktor za dependancy i generalizaciju


    public ConnectionInfo(String nameOfConnection) {
        this.nameOfConnection = nameOfConnection;
    }
}
