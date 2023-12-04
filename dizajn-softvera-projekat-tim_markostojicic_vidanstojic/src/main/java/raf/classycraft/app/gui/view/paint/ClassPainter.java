package raf.classycraft.app.gui.view.paint;

import raf.classycraft.app.model.elementDiagram.DiagramElement;
import raf.classycraft.app.model.elementDiagram.concreteInterclass.ClassInterClass;

import java.awt.*;

public class ClassPainter extends InterClassPainter{
    public ClassInterClass classInterClass; // recept za pravljenje paintera

    public ClassPainter(ClassInterClass classInterClass){
        this.classInterClass = classInterClass;
    }

    @Override
    public void paint(Graphics g, DiagramElement diagramElement) {
        Graphics2D graphics2D = (Graphics2D) g;
        
    }

    @Override
    public void elementAt() {

    }
}
