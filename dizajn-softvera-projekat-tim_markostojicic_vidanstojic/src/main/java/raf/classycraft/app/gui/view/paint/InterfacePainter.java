package raf.classycraft.app.gui.view.paint;

import raf.classycraft.app.model.elementDiagram.DiagramElement;
import raf.classycraft.app.model.elementDiagram.Interclass;
import raf.classycraft.app.model.elementDiagram.classContent.Method;
import raf.classycraft.app.model.elementDiagram.classContent.Visibility;
import raf.classycraft.app.model.elementDiagram.concreteInterclass.InterfaceInterclass;

import java.awt.*;

public class InterfacePainter extends InterClassPainter{

    private Rectangle rectangle;
    public InterfacePainter(Point point, InterfaceInterclass interfaceInterclass) {
        super(point, interfaceInterclass);
    }


    private void calculateConnectionDots() {
        InterfaceInterclass interfaceInterclass = (InterfaceInterclass) super.getInterclass();

        interfaceInterclass.getConnectionDots().clear();


        int centerX = (int) rectangle.getCenterX();
        int centerY = (int) rectangle.getCenterY();

        // Gornja tacka
        interfaceInterclass.getConnectionDots().add(new Point(centerX, (int) rectangle.getY()));
        // Donja tacka
        interfaceInterclass.getConnectionDots().add(new Point(centerX, (int) rectangle.getMaxY()));
        // Leva tacka
        interfaceInterclass.getConnectionDots().add(new Point((int) rectangle.getX(), centerY));
        // Desna tacka
        interfaceInterclass.getConnectionDots().add(new Point((int) rectangle.getMaxX(), centerY));
    }
    @Override
    public void paint(Graphics2D graphics2D, DiagramElement diagramElement)  {
        InterfaceInterclass interfaceInterclass = (InterfaceInterclass) super.getInterclass();

        Point mainPoint = new Point();
        Point heightPoint = new Point();
        heightPoint.y = 35;
        Stroke stroke = new BasicStroke(interfaceInterclass.getStroke());
        String nameOfClass = interfaceInterclass.getName();
        graphics2D.setColor(interfaceInterclass.getColor());
        graphics2D.setStroke(stroke);
        mainPoint.y = interfaceInterclass.getPoint().y + 35;
        int length = graphics2D.getFontMetrics().stringWidth(nameOfClass);
        int heightOfString = graphics2D.getFontMetrics().getMaxAscent();
        mainPoint.y = mainPoint.y + 15;
        heightPoint.y += 15;
        for (Method method : interfaceInterclass.getMethods()) {
            String visibilityChar;
            if (method.getVisibility() == Visibility.PUBLIC) {
                visibilityChar = "+ ";
            } else if (method.getVisibility() == Visibility.PRIVATE) {
                visibilityChar = "- ";
            } else if (method.getVisibility() == Visibility.PROTECTED) {
                visibilityChar = "# ";
            } else {
                visibilityChar = " ";
            }
            String methodeFullName = visibilityChar + method.getName()+method.getStaticContentOrNot()+" " + ":" + method.getReturnType();
            if (length < graphics2D.getFontMetrics().stringWidth(methodeFullName)) {
                length = graphics2D.getFontMetrics().stringWidth(methodeFullName);
            }
            graphics2D.drawString(methodeFullName, interfaceInterclass.getPoint().x + 5, mainPoint.y - 10);
            mainPoint.y = mainPoint.y + 15;
            heightPoint.y += 15;
        }
        int heightOfRectangle = ((interfaceInterclass.getClassContents().size()) == 0) ? (heightPoint.y) : (interfaceInterclass.getClassContents().size() * heightOfString + 33);
        this.rectangle = new Rectangle(interfaceInterclass.getPoint().x, interfaceInterclass.getPoint().y, (int) (15 + length + 15), heightOfRectangle);
        graphics2D.drawRect((int) rectangle.getX(), (int) rectangle.getY(), (int) rectangle.getWidth(), (int) rectangle.getHeight());
        graphics2D.drawString("I", interfaceInterclass.getPoint().x + 5, interfaceInterclass.getPoint().y + 15);
        graphics2D.drawString(interfaceInterclass.getName(), interfaceInterclass.getPoint().x + 20, interfaceInterclass.getPoint().y + 15);
        graphics2D.drawLine(interfaceInterclass.getPoint().x, interfaceInterclass.getPoint().y + 20, interfaceInterclass.getPoint().x + (int) (15 + length + 15), interfaceInterclass.getPoint().y + 20);
        this.setRectangle(rectangle);
        interfaceInterclass.setRectangle(rectangle);
        this.calculateConnectionDots();
    }

    @Override
    public boolean elementAt(Point pos) {
        return (rectangle != null && rectangle.contains(pos));
    }


    @Override
    public Rectangle getRectangle() {
        return rectangle;
    }
    @Override
    public void setRectangle(Rectangle rectangle) {
        this.rectangle = rectangle;
    }

    @Override
    public Interclass getInterclass() {
        return super.getInterclass();
    }
}
