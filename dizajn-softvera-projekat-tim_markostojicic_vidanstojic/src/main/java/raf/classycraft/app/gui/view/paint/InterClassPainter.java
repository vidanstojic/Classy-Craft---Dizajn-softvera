package raf.classycraft.app.gui.view.paint;

import raf.classycraft.app.model.elementDiagram.Interclass;

import java.awt.*;

public abstract class InterClassPainter extends ElementPainter {
    private Point point;
    private Interclass interclass;

    public InterClassPainter(Point point, Interclass interclass) {
        this.point = point;
        this.interclass = interclass;
    }

    public Interclass getInterclass() {
        return interclass;
    }
}
