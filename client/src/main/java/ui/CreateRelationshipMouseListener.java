package ui;

import model.uml.UMLClass;
import model.uml.UMLRelationshipFactory;
import model.uml.UMLRelationshipType;
import model.uml.abstracts.UMLRelationship;
import painter.Painter;
import painter.uml.UMLClassPainter;
import painter.uml.UMLPainterFactory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Line2D;

public class CreateRelationshipMouseListener implements MouseListener, MouseMotionListener {
    private boolean onSetSrc;
    private boolean onSetDesc;
    private final MyCanvas canvas;
    private final UMLRelationshipType relationshipType;
    private final Line2D onSetDescLine;

    private int srcX;
    private int srcY;
    private UMLClass classSrc;

    public CreateRelationshipMouseListener(MyCanvas canvas, UMLRelationshipType relationshipType) {
        onSetSrc = true;
        onSetDesc = false;
        this.canvas = canvas;
        this.relationshipType = relationshipType;
        this.onSetDescLine = new Line2D.Double();
        classSrc = null;

        this.canvas.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        boolean unbind = false;
        if (SwingUtilities.isRightMouseButton(e)) {
            unbind = true;
        } else if (SwingUtilities.isLeftMouseButton(e)) {
            if (onSetSrc) {
                for (Painter painter : canvas.getPainters()) {
                    if (painter.intersectMouse(e.getX(), e.getY()) && painter instanceof UMLClassPainter) {
                        classSrc = ((UMLClassPainter) painter).getUmlClass();
                        onSetSrc = false;
                        onSetDesc = true;
                        canvas.addCustomShape(onSetDescLine);
                        break;
                    }
                }
                if (onSetDesc) {
                    srcX = e.getX();
                    srcY = e.getY();
                } else {
                    unbind = true;
                }
            } else if (onSetDesc) {
                for (Painter painter : canvas.getPainters()) {
                    if (painter.intersectMouse(e.getX(), e.getY()) && painter instanceof UMLClassPainter) {
                        UMLClass classDesc = ((UMLClassPainter) painter).getUmlClass();
                        if(canvas.getDiagram().relationshipExists(classSrc, classDesc)) {
                            JOptionPane.showMessageDialog(null,
                                    "A relationship between two class is already created", "Error", JOptionPane.ERROR_MESSAGE);
                        } else {
                            UMLRelationshipFactory relationshipFactory = new UMLRelationshipFactory();
                            UMLRelationship relationship = relationshipFactory.createRelationship(relationshipType, classSrc, classDesc);
                            UMLPainterFactory painterFactory = new UMLPainterFactory();
                            painterFactory.createPainter(relationship);
                            canvas.getDiagram().addRelationship(relationship);
                            canvas.bindDiagram();
                        }
                        break;
                    }
                }
                unbind = true;
            }
        }
        if (unbind) {
            canvas.removeCustomShape(onSetDescLine);
            canvas.removeMouseListener(this);
            canvas.removeMouseMotionListener(this);
            canvas.resetMouseListeners();
            canvas.setCursor(Cursor.getDefaultCursor());
            canvas.repaint();
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {
        if (onSetDesc) {
            onSetDescLine.setLine(srcX, srcY, e.getX(), e.getY());
            canvas.repaint();
        }
    }
}
