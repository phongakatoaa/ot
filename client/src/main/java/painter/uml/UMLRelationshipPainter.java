package painter.uml;

import model.uml.abstracts.UMLRelationship;
import painter.Painter;

import java.awt.*;

public abstract class UMLRelationshipPainter extends Painter {
    protected UMLRelationship umlRelationship;
    protected int x1;
    protected int y1;
    protected int x2;
    protected int y2;
    protected int rotateDegree;

    public UMLRelationshipPainter(UMLRelationship umlRelationship) {
        this.umlRelationship = umlRelationship;
        rotateDegree = 0;
    }

    @Override
    public boolean intersectMouse(int x, int y) {
        try {
            return (x1 - x2) / (x1 - x) == (y1 - y2) / (y1 - y) && !(x > x1 && x > x2) &&
                    !(x < x1 && x < x2) && !(y > y1 && y > y2) && !(y < y1 && y < y2);
        } catch (ArithmeticException e) {
            return false;
        }
    }

    @Override
    public void paint(Graphics2D graphics2D) {
        calculatePosition();
        graphics2D.drawLine(this.x1, this.y1, this.x2, this.y2);
    }

    private void calculatePosition() {
        final Rectangle srcRect = ((UMLClassPainter) umlRelationship.getSrc().getPainter()).getClassRectangle();
        final Rectangle descRect = ((UMLClassPainter) umlRelationship.getDesc().getPainter()).getClassRectangle();
        double dX = srcRect.getCenterX() - descRect.getCenterX();
        double dY = srcRect.getCenterY() - descRect.getCenterY();
        double a = Math.abs(dY);
        double b = Math.sqrt(Math.pow(dX, 2) + Math.pow(dY, 2));
        double degree = Math.asin(a / b) * 90;
        double dx1 = 0;
        double dx2 = 0;
        double dy1 = 0;
        double dy2 = 0;
        if (dX == 0) {
            dx1 = srcRect.getX() + srcRect.getWidth() / 2;
            dx2 = descRect.getX() + descRect.getWidth() / 2;
            if (dY >= 0) {
                dy1 = srcRect.getY();
                dy2 = descRect.getY() + descRect.getHeight();
            } else {
                dy1 = srcRect.getY() + srcRect.getHeight();
                dy2 = descRect.getY();
                rotateDegree = 180;
            }
        } else if (dY == 0) {
            dy1 = srcRect.getY() + srcRect.getHeight() / 2;
            dy2 = descRect.getY() + descRect.getHeight() / 2;
            if (dX >= 0) {
                dx1 = srcRect.getX();
                dx2 = descRect.getX() + descRect.getWidth();
                rotateDegree = 90;
            } else {
                dx1 = srcRect.getX() + srcRect.getWidth();
                dx2 = descRect.getX();
                rotateDegree = -90;
            }
        } else {
            if (dX > 0 && dY > 0) {
                if (degree <= 30) {
                    dx1 = srcRect.getX();
                    dy1 = srcRect.getY() + srcRect.getHeight() / 2;
                    dx2 = descRect.getX() + descRect.getWidth();
                    dy2 = descRect.getY() + descRect.getHeight() / 2;
                    rotateDegree = 90;
                } else if (degree > 30 && degree < 60) {
                    dx1 = srcRect.getX();
                    dy1 = srcRect.getY();
                    dx2 = descRect.getX() + descRect.getWidth();
                    dy2 = descRect.getY() + descRect.getHeight();
                    rotateDegree = 135;
                } else {
                    dx1 = srcRect.getX() + srcRect.getWidth() / 2;
                    dy1 = srcRect.getY();
                    dx2 = descRect.getX() + descRect.getWidth() / 2;
                    dy2 = descRect.getY() + descRect.getHeight();
                    rotateDegree = 180;
                }
            } else if (dX < 0 && dY > 0) {
                if (degree <= 30) {
                    dx1 = srcRect.getX() + srcRect.getWidth();
                    dy1 = srcRect.getY() + srcRect.getHeight() / 2;
                    dx2 = descRect.getX();
                    dy2 = descRect.getY() + descRect.getHeight() / 2;
                    rotateDegree = -90;
                } else if (degree > 30 && degree < 60) {
                    dx1 = srcRect.getX() + srcRect.getWidth();
                    dy1 = srcRect.getY();
                    dx2 = descRect.getX();
                    dy2 = descRect.getY() + descRect.getHeight();
                    rotateDegree = -135;
                } else {
                    dx1 = srcRect.getX() + srcRect.getWidth() / 2;
                    dy1 = srcRect.getY();
                    dx2 = descRect.getX() + descRect.getWidth() / 2;
                    dy2 = descRect.getY() + descRect.getHeight();
                    rotateDegree = -180;
                }
            } else if (dX < 0 && dY < 0) {
                if (degree <= 30) {
                    dx1 = srcRect.getX() + srcRect.getWidth();
                    dy1 = srcRect.getY() + srcRect.getHeight() / 2;
                    dx2 = descRect.getX();
                    dy2 = descRect.getY() + descRect.getHeight() / 2;
                    rotateDegree = -90;
                } else if (degree > 30 && degree < 60) {
                    dx1 = srcRect.getX() + srcRect.getWidth();
                    dy1 = srcRect.getY() + srcRect.getHeight();
                    dx2 = descRect.getX();
                    dy2 = descRect.getY();
                    rotateDegree = -45;
                } else {
                    dx1 = srcRect.getX() + srcRect.getWidth() / 2;
                    dy1 = srcRect.getY() + srcRect.getHeight();
                    dx2 = descRect.getX() + descRect.getWidth() / 2;
                    dy2 = descRect.getY();
                    rotateDegree = 0;
                }
            } else if (dX > 0 && dY < 0) {
                if (degree <= 30) {
                    dx1 = srcRect.getX();
                    dy1 = srcRect.getY() + srcRect.getHeight() / 2;
                    dx2 = descRect.getX() + descRect.getWidth();
                    dy2 = descRect.getY() + descRect.getHeight() / 2;
                    rotateDegree = 90;
                } else if (degree > 30 && degree < 60) {
                    dx1 = srcRect.getX();
                    dy1 = srcRect.getY() + srcRect.getHeight();
                    dx2 = descRect.getX() + descRect.getWidth();
                    dy2 = descRect.getY();
                    rotateDegree = 45;
                } else {
                    dx1 = srcRect.getX() + srcRect.getWidth() / 2;
                    dy1 = srcRect.getY() + srcRect.getHeight();
                    dx2 = descRect.getX() + descRect.getWidth() / 2;
                    dy2 = descRect.getY();
                    rotateDegree = 0;
                }
            }
        }
        //System.out.println("dx: " + dX + ", dy: " + dY + ", degree: " + degree + ", rotate: " + rotateDegree);
        this.x1 = (int) dx1;
        this.x2 = (int) dx2;
        this.y1 = (int) dy1;
        this.y2 = (int) dy2;
    }

    public UMLRelationship getUmlRelationship() {
        return umlRelationship;
    }

    public int getX1() {
        return x1;
    }

    public void setX1(int x1) {
        this.x1 = x1;
    }

    public int getY1() {
        return y1;
    }

    public void setY1(int y1) {
        this.y1 = y1;
    }

    public int getX2() {
        return x2;
    }

    public void setX2(int x2) {
        this.x2 = x2;
    }

    public int getY2() {
        return y2;
    }

    public void setY2(int y2) {
        this.y2 = y2;
    }

    protected double getLength() {
        return Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
    }
}
