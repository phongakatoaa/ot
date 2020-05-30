package ui;

import model.uml.UMLAttribute;
import model.uml.UMLClass;
import model.uml.UMLDiagram;
import model.uml.UMLOperation;
import painter.Painter;
import painter.uml.UMLClassPainter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;

public class MyCanvas extends JPanel {
    private final ArrayList<Painter> painters;
    private UMLDiagram diagram;
    private Painter focusedPainter;
    private final TranslateControl translateControl;
    private int mouseX;
    private int mouseY;

    public MyCanvas() {
        super();
        this.setLayout(null);
        this.painters = new ArrayList<>();
        this.translateControl = new TranslateControl();
        bind();
    }

    private void drawDiagram(Graphics g) {
        Graphics2D graphics2D = (Graphics2D) g;
        this.painters.forEach(p -> p.paint(graphics2D));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        this.drawDiagram(g);
    }

    public UMLDiagram getDiagram() {
        return diagram;
    }

    public void setDiagram(UMLDiagram diagram) {
        this.diagram = diagram;

        UMLClass umlClass = new UMLClass("Person");
        umlClass.setPosition(100, 100);
        umlClass.addAttribute(new UMLAttribute("name"));
        umlClass.addAttribute(new UMLAttribute("age"));
        umlClass.addOperation(new UMLOperation("setName()"));
        umlClass.addOperation(new UMLOperation("setAge()"));
        umlClass.addOperation(new UMLOperation("getName()"));
        umlClass.addOperation(new UMLOperation("getAge()"));
        umlClass.addOperation(new UMLOperation("toString()"));

        this.diagram.addUMLClass(umlClass);

        UMLClassPainter painter = new UMLClassPainter(umlClass);
        painter.bindTextFields(this);

        umlClass.setPainter(painter);
        this.painters.add(painter);
    }

    private void bind() {
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if (SwingUtilities.isRightMouseButton(e)) {
                    System.out.println("right mouse clicked");
                    JPopupMenu popupMenu = new JPopupMenu();
                    JMenuItem menuItem = new JMenuItem();
                    boolean intersect = false;
                    for(Painter painter: painters) {
                        if(painter.intersectMouse(e.getX(), e.getY())) {
                            menuItem.setText("Delete");
                            intersect = true;
                            break;
                        }
                    }
                    if(!intersect) {
                        menuItem.setText("New Class");
                    }
                    popupMenu.add(menuItem);
                    popupMenu.show(e.getComponent(), e.getX(), e.getY());
                }
            }


            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                mouseX = e.getX();
                mouseY = e.getY();
                for (Painter painter : painters) {
                    if (painter.intersectMouse(e.getX(), e.getY())) {
                        painter.setFocused(true);
                        focusedPainter = painter;
                        repaint();
                        return;
                    }
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
                if (focusedPainter != null) {
                    focusedPainter.setFocused(false);
                    focusedPainter = null;
                }
            }
        });

        this.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                super.mouseDragged(e);
                if (focusedPainter != null) {
                    translateControl.translate(mouseX, mouseY, e.getX(), e.getY(), focusedPainter);
                    mouseX = e.getX();
                    mouseY = e.getY();
                    repaint();
                }
            }

            @Override
            public void mouseMoved(MouseEvent e) {
                super.mouseMoved(e);
                for (Painter painter: painters) {
                    painter.setHovered(painter.intersectMouse(e.getX(), e.getY()));
                }
                repaint();
            }
        });
    }

    public ArrayList<Painter> getPainters() {
        return painters;
    }
}
