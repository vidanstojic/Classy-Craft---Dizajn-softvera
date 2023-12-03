
package raf.classycraft.app.gui.view;
import raf.classycraft.app.gui.controller.MyMouseListener;
import raf.classycraft.app.observer.ISubscriber;
import raf.classycraft.app.observer.TreeNotificationType;

import javax.swing.*;
import java.awt.*;


public class DiagramView extends JPanel implements ISubscriber {

    private MyMouseListener myMouseListener;
 
    public DiagramView(String name,String author){
        this.myMouseListener = new MyMouseListener(this);
        addMouseListener(myMouseListener);
        addMouseMotionListener(myMouseListener);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
    }

    public DiagramView(){

    }


    @Override
    public void update(Object notify) {
        // ovo ce se tek popunjavati kasnije u toku projekta, za sada neka stoji ovako
    }
}