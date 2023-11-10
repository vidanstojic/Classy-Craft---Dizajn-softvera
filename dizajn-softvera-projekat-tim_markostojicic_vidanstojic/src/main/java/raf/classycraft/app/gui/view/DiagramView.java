
package raf.classycraft.app.gui.view;
import raf.classycraft.app.observer.ISubscriberView;
import raf.classycraft.app.observer.TreeNotification;

import javax.swing.*;
import java.awt.*;


public class DiagramView extends JPanel implements ISubscriberView {


    private Label authorL;
    private Label nameL;
    public DiagramView(String name,String author){

        this.authorL = new Label("Author: "+author);
        this.nameL = new Label("Project:"+name);
        add(nameL);
        add(authorL);
    }



    public DiagramView(){

    }


    @Override
    public void update(Object notify, TreeNotification typeNotify) {

    }
}