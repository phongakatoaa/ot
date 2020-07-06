package com.uet.ui;

import com.uet.model.uml.UMLClass;
import com.uet.model.uml.UMLDiagram;
import com.uet.painter.Painter;
import com.uet.painter.uml.UMLClassPainter;
import com.uet.painter.uml.UMLRelationshipPainter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

public class MyCanvas extends JPanel implements MouseMotionListener, MouseListener {
    private final ArrayList<Painter> painters;
    private final TranslateControl translateControl;
    private final ArrayList<Shape> customShapes;
    private final MyCanvasPopupMenu canvasPopupMenu;
    private int mouseX, mouseY;

    private UMLDiagram diagram;
    private Painter focusedPainter;

    public MyCanvas() {
        super();
        this.setLayout(null);
        this.painters = new ArrayList<>();
        this.customShapes = new ArrayList<>();
        this.translateControl = new TranslateControl();
        this.canvasPopupMenu = new MyCanvasPopupMenu(this);
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
    }

    public ArrayList<Painter> getPainters() {
        return this.painters;
    }

    public UMLDiagram getDiagram() {
        return this.diagram;
    }

    public void setDiagram(UMLDiagram diagram) {
        this.diagram = diagram;
        this.bindDiagram();
    }

    public void bindDiagram() {
        this.removeAll();
        this.painters.clear();
        this.diagram.getUmlClasses().forEach(c -> {
            ((UMLClassPainter) c.getPainter()).bindTextFields(this);
            this.painters.add(c.getPainter());
        });
        this.diagram.getUmlRelationships().forEach(r -> this.painters.add(r.getPainter()));
    }

    private void drawDiagram(Graphics g) {
        Graphics2D graphics2D = (Graphics2D) g;
        this.painters.forEach(p -> p.paint(graphics2D));
    }

    public void addCustomShape(Shape shape) {
        this.customShapes.add(shape);
    }

    public void removeCustomShape(Shape shape) {
        this.customShapes.remove(shape);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (this.diagram != null) {
            this.drawDiagram(g);
        }
        this.customShapes.forEach(((Graphics2D) g)::draw);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (SwingUtilities.isRightMouseButton(e)) {
            boolean intersect = false;
            for (Painter painter : this.painters) {
                if (painter.intersectMouse(e.getX(), e.getY())) {
                    intersect = true;
                    if (painter instanceof UMLClassPainter) {
                        this.canvasPopupMenu.bind(((UMLClassPainter) painter).getUmlClass());
                    } else if (painter instanceof UMLRelationshipPainter) {
                        this.canvasPopupMenu.bind(((UMLRelationshipPainter) painter).getUmlRelationship());
                    }
                    break;
                }
            }
            if (!intersect) {
                this.canvasPopupMenu.bind(e.getX(), e.getY());
            }
            this.canvasPopupMenu.show(this, e.getX(), e.getY());
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (SwingUtilities.isRightMouseButton(e)) {
            return;
        }
        this.mouseX = e.getX();
        this.mouseY = e.getY();
        for (Painter painter : this.painters) {
            if (painter.intersectMouse(e.getX(), e.getY())) {
                painter.setFocused(true);
                this.focusedPainter = painter;
                repaint();
                return;
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (this.focusedPainter != null && this.focusedPainter instanceof UMLClassPainter) {
            UMLClass umlClass = ((UMLClassPainter) focusedPainter).getUmlClass();
            umlClass.setPosition(umlClass.getX() + (e.getX() - mouseX), umlClass.getY() + (e.getY() - mouseY), true);
            this.mouseX = e.getX();
            this.mouseY = e.getY();
            this.repaint();

            focusedPainter.setFocused(false);
            focusedPainter = null;
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if (this.focusedPainter != null && this.focusedPainter instanceof UMLClassPainter) {
            UMLClass umlClass = ((UMLClassPainter) focusedPainter).getUmlClass();
            umlClass.setPosition(umlClass.getX() + (e.getX() - mouseX), umlClass.getY() + (e.getY() - mouseY), false);
            this.mouseX = e.getX();
            this.mouseY = e.getY();
            this.repaint();
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        for (Painter painter : this.painters) {
            painter.setHovered(painter.intersectMouse(e.getX(), e.getY()));
        }
        this.repaint();
    }

    public void unbindMouseListeners() {
        this.removeMouseListener(this);
        this.removeMouseMotionListener(this);
    }

    public void resetMouseListeners() {
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
    }
}
