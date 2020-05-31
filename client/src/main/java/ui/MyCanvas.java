package ui;

import model.uml.UMLDiagram;
import painter.Painter;
import painter.uml.UMLClassPainter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class MyCanvas extends JPanel implements MouseMotionListener, MouseListener {
    private final ArrayList<Painter> painters;
    private final TranslateControl translateControl;
    private int mouseX, mouseY;

    private UMLDiagram diagram;
    private Painter focusedPainter;

    public MyCanvas() {
        super();
        this.setLayout(null);
        this.painters = new ArrayList<>();
        this.translateControl = new TranslateControl();
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

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (this.diagram != null) {
            this.drawDiagram(g);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (SwingUtilities.isRightMouseButton(e)) {
            JPopupMenu popupMenu = new JPopupMenu();
            JMenuItem menuItem = new JMenuItem();
            boolean intersect = false;
            for (Painter painter : this.painters) {
                if (painter.intersectMouse(e.getX(), e.getY())) {
                    menuItem.setText("Delete");
                    intersect = true;
                    break;
                }
            }
            if (!intersect) {
                menuItem.setText("New Class");
            }
            popupMenu.add(menuItem);
            popupMenu.show(e.getComponent(), e.getX(), e.getY());
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
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
        if (this.focusedPainter != null) {
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
        if (this.focusedPainter != null) {
            translateControl.translate(mouseX, mouseY, e.getX(), e.getY(), focusedPainter);
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
}
