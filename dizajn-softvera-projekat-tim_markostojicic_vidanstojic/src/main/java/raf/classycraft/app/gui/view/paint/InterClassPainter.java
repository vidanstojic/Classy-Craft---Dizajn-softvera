package raf.classycraft.app.gui.view.paint;

import java.awt.*;

public abstract class InterClassPainter extends ElementPainter {
    Point point;

    public InterClassPainter(Point point) {
        this.point = point;
    }
}
