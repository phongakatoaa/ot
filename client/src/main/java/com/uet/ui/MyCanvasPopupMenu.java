package com.uet.ui;

import com.uet.model.uml.UMLAttribute;
import com.uet.model.uml.UMLClass;
import com.uet.model.uml.UMLOperation;
import com.uet.model.uml.abstracts.UMLRelationship;
import com.uet.painter.uml.UMLClassPainter;
import com.uet.painter.uml.UMLPainterFactory;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class MyCanvasPopupMenu extends JPopupMenu {
    private final JMenuItem menuItemAddClass;
    private final JMenuItem menuItemAddAttribute;
    private final JMenuItem menuItemAddOperation;
    private final JMenuItem menuItemDeleteClass;
    private final JMenuItem menuItemDeleteRelationship;
    private final ArrayList<JMenuItem> menuItems;

    private final MyCanvas canvas;

    public MyCanvasPopupMenu(MyCanvas canvas) {
        this.canvas = canvas;

        this.menuItemAddClass = new JMenuItem("Add class");
        this.menuItemAddAttribute = new JMenuItem("Add attribute");
        this.menuItemAddOperation = new JMenuItem("Add operation");
        this.menuItemDeleteClass = new JMenuItem("Delete class");
        this.menuItemDeleteRelationship = new JMenuItem("Delete relationship");

        this.add(menuItemAddClass);
        this.add(menuItemAddAttribute);
        this.add(menuItemAddOperation);
        this.add(menuItemDeleteClass);
        this.add(menuItemDeleteRelationship);

        this.menuItems = new ArrayList<>();
        menuItems.add(menuItemAddClass);
        menuItems.add(menuItemAddAttribute);
        menuItems.add(menuItemAddOperation);
        menuItems.add(menuItemDeleteClass);
        menuItems.add(menuItemDeleteRelationship);
    }

    private void unbind() {
        this.menuItems.forEach(m -> {
            ActionListener[] listeners = m.getActionListeners();
            for (ActionListener l : listeners) {
                m.removeActionListener(l);
            }
        });
    }

    public void bind(int mouseX, int mouseY) {
        unbind();
        this.menuItemAddClass.setVisible(true);
        this.menuItemAddAttribute.setVisible(false);
        this.menuItemAddOperation.setVisible(false);
        this.menuItemDeleteClass.setVisible(false);
        this.menuItemDeleteRelationship.setVisible(false);

        this.menuItemAddClass.addActionListener(e -> onClickAddClass(mouseX, mouseY));
    }

    public void bind(UMLClass umlClass) {
        unbind();
        this.menuItemAddClass.setVisible(false);
        this.menuItemAddAttribute.setVisible(true);
        this.menuItemAddOperation.setVisible(true);
        this.menuItemDeleteClass.setVisible(true);
        this.menuItemDeleteRelationship.setVisible(false);

        this.menuItemAddAttribute.addActionListener(e -> onClickAddAttribute(umlClass));
        this.menuItemAddOperation.addActionListener(e -> onClickAddOperation(umlClass));
        this.menuItemDeleteClass.addActionListener(e -> onClickDeleteClass(umlClass));
    }

    public void bind(UMLRelationship umlRelationship) {
        unbind();
        this.menuItemAddClass.setVisible(false);
        this.menuItemAddAttribute.setVisible(false);
        this.menuItemAddOperation.setVisible(false);
        this.menuItemDeleteClass.setVisible(false);
        this.menuItemDeleteRelationship.setVisible(true);

        this.menuItemDeleteRelationship.addActionListener(e -> onClickDeleteRelationship(umlRelationship));
    }

    public void onClickAddClass(int x, int y) {
        String errorMessage = null;
        if (canvas.getDiagram() == null) {
            errorMessage = "Please create/open a diagram first";
        } else {
            String value = JOptionPane.showInputDialog(null, "Enter new class name");
            if (value != null) {
                value = value.trim();
                if (value.length() == 0) {
                    errorMessage = "Class name cannot be empty";
                } else {
                    UMLClass umlClass = new UMLClass(value);
                    umlClass.setPosition(x, y, false);
                    UMLPainterFactory painterFactory = new UMLPainterFactory();
                    UMLClassPainter painter = (UMLClassPainter) painterFactory.createPainter(umlClass);
                    canvas.getDiagram().addClass(umlClass);
                    canvas.bindDiagram();
                    canvas.repaint();
                }
            }
        }
        if (errorMessage != null) {
            JOptionPane.showMessageDialog(null, errorMessage, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void onClickDeleteClass(UMLClass umlClass) {
        int confirm = JOptionPane.showConfirmDialog(null, "Are you sure?");
        if (confirm == JOptionPane.YES_OPTION) {
            canvas.getDiagram().removeClass(umlClass);
            canvas.bindDiagram();
            canvas.repaint();
        }
    }

    public void onClickDeleteRelationship(UMLRelationship relationship) {
        int confirm = JOptionPane.showConfirmDialog(null, "Are you sure?");
        if (confirm == JOptionPane.YES_OPTION) {
            canvas.getDiagram().removeRelationship(relationship);
            canvas.bindDiagram();
            canvas.repaint();
        }
    }

    public void onClickAddAttribute(UMLClass umlClass) {
        UMLAttribute attribute = new UMLAttribute("new attribute");
        umlClass.addAttribute(attribute);
        ((UMLClassPainter) umlClass.getPainter()).revalidateProperties();
        canvas.bindDiagram();
        canvas.repaint();
    }

    public void onClickAddOperation(UMLClass umlClass) {
        UMLOperation umlOperation = new UMLOperation("new operation");
        umlClass.addOperation(umlOperation);
        ((UMLClassPainter) umlClass.getPainter()).revalidateProperties();
        canvas.bindDiagram();
        canvas.repaint();
    }
}
