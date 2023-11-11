
package raf.classycraft.app.gui.view;
import raf.classycraft.app.observer.ISubscriber;
import raf.classycraft.app.observer.TreeNotificationType;

import javax.swing.*;
import java.awt.*;


public class DiagramView extends JPanel implements ISubscriber {


    private Label authorL;
    private Label nameL;
    public DiagramView(String name,String author){
        if(author != null){
            this.authorL = new Label("Author: "+author);
        }
        else{
            this.authorL = new Label("Author: empty (you need to set the project author)");
        }
        this.nameL = new Label("Project:"+name);
        add(nameL);
        add(authorL);
    }



    public DiagramView(){

    }


    @Override
    public void update(Object notify) {
        // ovo ce se tek popunjavati kasnije u toku projekta, za sada neka stoji ovako
    }
}