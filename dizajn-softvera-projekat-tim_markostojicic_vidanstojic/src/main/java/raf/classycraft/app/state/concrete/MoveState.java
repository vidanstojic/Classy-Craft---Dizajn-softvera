package raf.classycraft.app.state.concrete;

import raf.classycraft.app.command.implementation.MoveCommand;
import raf.classycraft.app.gui.view.DiagramView;
import raf.classycraft.app.gui.view.paint.*;
import raf.classycraft.app.model.elementDiagram.Interclass;
import raf.classycraft.app.state.State;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.abs;

public class MoveState implements State {


    private Interclass interclass;
    private Point oldPoint;
    private Point newPoint;
    private Point startPointInInterClass;
    private List<Point> pointList = new ArrayList<>();
    @Override
    public void stateMousePressed(MouseEvent e, DiagramView tempTab) {
        Point point = new Point((int) (e.getX() / tempTab.getAffineTransform().getScaleX()), (int) (e.getY() / tempTab.getAffineTransform().getScaleX()));
        startPointInInterClass = point;
        if (tempTab.getListOfSelectedPainters().isEmpty()) {
            for (ElementPainter elementPainter : tempTab.getListOfPainters()) {
                if (!tempTab.getListOfSelectedPainters().isEmpty()) {
                    break;
                }
                if (elementPainter.elementAt(point) == true) {
                    if (elementPainter instanceof ClassPainter || elementPainter instanceof EnumPainter || elementPainter instanceof InterfacePainter) {
                        interclass = ((InterClassPainter) elementPainter).getInterclass();
                        oldPoint = new Point(interclass.getPoint());
                        //pointList.add(oldPoint);
                        interclass.setColor(Color.BLUE);
                        tempTab.repaint();
                        break;
                    }
                }
            }
        } else if (!tempTab.getListOfSelectedPainters().isEmpty()) {
            for (ElementPainter elementPainter : tempTab.getListOfSelectedPainters()){
                if (elementPainter instanceof ClassPainter || elementPainter instanceof EnumPainter || elementPainter instanceof InterfacePainter) {
                    interclass = ((InterClassPainter) elementPainter).getInterclass();
                    oldPoint = new Point(interclass.getPoint());
                    interclass.setColor(Color.BLUE);
                    pointList.add(oldPoint);
                    tempTab.repaint();
                }
            }
        }
    }

    @Override
    public void stateMouseReleased(MouseEvent e, DiagramView tempTab) {
        int counter = 0;
        int flag = 0;
        if (!tempTab.getListOfSelectedPainters().isEmpty()){
            for (ElementPainter elementPainter1 : tempTab.getListOfSelectedPainters()) {
                if (elementPainter1 instanceof InterClassPainter) {
                    for (ElementPainter elementPainter2 : tempTab.getListOfPainters()) {
                        if (elementPainter2 instanceof InterClassPainter) {
                            if (elementPainter1.getRectangle() == null || elementPainter1.getRectangle().equals(elementPainter2.getRectangle()))
                                continue;
                            interclass = ((InterClassPainter) elementPainter1).getInterclass();
                            if (interclass.getRectangle().intersects(elementPainter2.getRectangle())) {
                                interclass.setPoint(interclass.getSpecialPoint());
                                interclass.setColor(Color.BLACK);
                                interclass.setSpecialPoint(interclass.getPoint());
                                for (int i = 0; i < pointList.size(); i++) {
                                    if (interclass.getPoint().equals(pointList.get(i))) {
                                        flag++;
                                        pointList.remove(pointList.get(i));
                                        break;
                                    }
                                }
                            }///problem je sto ovim se ni ne dodje do klase koja se intersektovala
                            //interclass.setColor(Color.BLACK);
                            //interclass.setSpecialPoint(interclass.getPoint());

                            /*if (flag == 0) {
                                MoveCommand moveCommand = new MoveCommand(tempTab, interclass, pointList.get(counter++), interclass.getPoint());////ovde baca gresku
                                tempTab.getCommandManager().addCommand(moveCommand);
                            }
                            flag = 0;*/
                            //if (tempTab.getListOfSelectedPainters().contains(interclass))continue;

                        }
                    }
                    interclass.setColor(Color.BLACK);
                    interclass.setSpecialPoint(interclass.getPoint());
                    if (flag == 0) {
                        MoveCommand moveCommand = new MoveCommand(tempTab, interclass, pointList.get(counter++), interclass.getPoint());////ovde baca gresku
                        tempTab.getCommandManager().addCommand(moveCommand);
                    }
                    flag = 0;
                }
            }

            tempTab.removeListOfSelectedPainters(tempTab.getListOfSelectedPainters());
            interclass = null;
        }
        if (interclass != null && tempTab.getListOfSelectedPainters().isEmpty()) {
            if(tempTab.getListOfPainters().size() > 1) {
                for (ElementPainter elementPainter : tempTab.getListOfPainters()) {
                    if (elementPainter.getRectangle() == null)
                        continue;
                    if(elementPainter.getRectangle().equals(interclass.getRectangle()))
                        continue;
                    if (elementPainter.getRectangle().intersects(interclass.getRectangle())) {
                        interclass.setPoint(oldPoint);
                        interclass.setSpecialPoint(interclass.getPoint());
                        interclass.setColor(Color.BLACK);
                        interclass = null;
                        pointList.removeAll(pointList);
                        tempTab.repaint();
                        return;
                    }
                }
            }
            interclass.setSpecialPoint(interclass.getPoint());
            interclass.setColor(Color.BLACK);
            MoveCommand moveCommand = new MoveCommand(tempTab, interclass, oldPoint, interclass.getSpecialPoint(), interclass.getPoint());
            tempTab.getCommandManager().addCommand(moveCommand);
            interclass = null;
            tempTab.repaint();
        }
        pointList.removeAll(pointList);
    }

    @Override
    public void stateMouseDragged(MouseEvent e, DiagramView tempTab) {
        newPoint = new Point((int) (e.getX() / tempTab.getAffineTransform().getScaleX()), (int) (e.getY() / tempTab.getAffineTransform().getScaleY()));
        int weight = newPoint.x - startPointInInterClass.x;
        int height = newPoint.y - startPointInInterClass.y;
        if (!tempTab.getListOfSelectedPainters().isEmpty()){
            for (ElementPainter elementPainter : tempTab.getListOfSelectedPainters()){
                if(elementPainter instanceof InterClassPainter) {
                    interclass = ((InterClassPainter) elementPainter).getInterclass();
                    Point point2 = new Point(interclass.getSpecialPoint());
                    Point point = new Point(point2.x + weight, point2.y + height);
                    interclass.setPoint(point);
                }
            }

        }

        if(interclass != null && tempTab.getListOfSelectedPainters().isEmpty()) {
            interclass.setPoint(newPoint);
            tempTab.repaint();
        }
        if (newPoint.x > tempTab.getSize().width){
            tempTab.setPreferredSize(new Dimension(tempTab.getWidth() + 50, tempTab.getHeight()));
            tempTab.repaint();
        }if (newPoint.y > tempTab.getSize().height){
            tempTab.setPreferredSize(new Dimension(tempTab.getWidth(), tempTab.getHeight() + 50));
            tempTab.repaint();
        }
    }
}
