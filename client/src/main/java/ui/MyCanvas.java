package ui;

import model.uml.UMLClass;
import model.uml.UMLDiagram;
import model.uml.UMLRelationshipType;
import model.uml.abstracts.UMLRelationship;
import painter.Painter;
import painter.uml.UMLClassPainter;
import painter.uml.UMLPainterFactory;
import painter.uml.UMLRelationshipPainter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class MyCanvas extends JPanel implements MouseMotionListener, MouseListener {
    private final ArrayList<Painter> painters;
    private final TranslateControl translateControl;
    private final ArrayList<Shape> customShapes;
    private int mouseX, mouseY;

    private UMLDiagram diagram;
    private Painter focusedPainter;

    public MyCanvas() {
        super();
        this.setLayout(null);
        this.painters = new ArrayList<>();
        this.customShapes = new ArrayList<>();
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
            JPopupMenu popupMenu = new JPopupMenu();
            JMenuItem menuItem = new JMenuItem();
            boolean intersect = false;
            for (Painter painter : this.painters) {
                if (painter.intersectMouse(e.getX(), e.getY())) {
                    menuItem.setText("Delete");
                    menuItem.addActionListener(e1 -> onClickDeleteElement(painter));
                    intersect = true;
                    break;
                }
            }
            if (!intersect) {
                menuItem.setText("New Class");
                menuItem.addActionListener(e1 -> onClickAddClass(e.getX(), e.getY()));
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

    public void onClickAddClass(int x, int y) {
        String errorMessage = null;
        if (this.diagram == null) {
            errorMessage = "Please create/open a diagram first";
        } else {
            String value = JOptionPane.showInputDialog(this, "Enter new class name").trim();
            if (value.length() == 0) {
                errorMessage = "Class name cannot be empty";
            } else {
                UMLClass umlClass = new UMLClass(value);
                umlClass.setPosition(x, y);
                UMLPainterFactory painterFactory = new UMLPainterFactory();
                UMLClassPainter painter = (UMLClassPainter) painterFactory.createPainter(umlClass);
                this.diagram.addClass(umlClass);
                this.bindDiagram();
                this.repaint();
            }
        }
        if (errorMessage != null) {
            JOptionPane.showMessageDialog(this, errorMessage, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void onClickDeleteElement(Painter painter) {
        String confirmMessage = null;
        int confirm = JOptionPane.showConfirmDialog(null, "Are you sure?");
        if (confirm == JOptionPane.YES_OPTION) {
            if (painter instanceof UMLClassPainter) {
                UMLClass umlClass = ((UMLClassPainter) painter).getUmlClass();
                this.diagram.removeClass(umlClass);
            } else if (painter instanceof UMLRelationshipPainter) {
                UMLRelationship umlRelationship = ((UMLRelationshipPainter) painter).getUmlRelationship();
                this.diagram.removeRelationship(umlRelationship);
            }
            this.bindDiagram();
            this.repaint();
        }
    }

    public void onClickCreateRelationship(UMLRelationshipType type) {
        this.removeMouseListener(this);
        this.removeMouseMotionListener(this);
        CreateRelationshipMouseListener l = new CreateRelationshipMouseListener(this, type);
        this.addMouseListener(l);
        this.addMouseMotionListener(l);
    }

    public void resetMouseListener() {
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
    }
}
