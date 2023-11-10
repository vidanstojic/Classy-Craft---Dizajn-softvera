
package raf.classycraft.app.gui.view;
import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class DiagramView extends JPanel {

    private String author;
    private String name;
    private Label autor;
    private Label nameL;
    public DiagramView(String name,String author){
        this.name = name;
        this.author = author;
        this.autor = new Label("Author: "+author);
        this.nameL = new Label("Project:"+name);
        add(nameL);
        add(autor);
    }



    public DiagramView(){

    }



}