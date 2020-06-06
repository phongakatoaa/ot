package com.uet.ui;

import com.uet.model.MyEditableElement;
import com.uet.model.uml.UMLClass;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class DiagramTextField extends JTextField {

    private final MyEditableElement element;
    private final DiagramTextFieldPopupMenu popupMenu;

    public DiagramTextField(MyEditableElement element, UMLClass umlClass) {
        super();
        this.element = element;
        this.popupMenu = new DiagramTextFieldPopupMenu(element, umlClass);

        this.setText(element.getValue());
        bind();
    }

    private void bind() {
        this.setFont(Constants.DEFAULT_FONT);
        this.setOpaque(false);
        //this.setEditable(false);
        this.setBorder(null);
        this.setBackground(null);

        this.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                updateLayout(false);
            }

            @Override
            public void focusGained(FocusEvent e) {
                updateLayout(true);
            }
        });

        this.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER || e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    updateLayout(false);
                } else if (e.getKeyCode() == KeyEvent.VK_TAB) {
                    transferFocus();
                }
            }
        });

        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                setCursor(new Cursor(Cursor.TEXT_CURSOR));
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                if (SwingUtilities.isRightMouseButton(e)) {
                    popupMenu.show(e.getComponent(), e.getX(), e.getY());
                } else {
                    updateLayout(true);
                    requestFocus();
                }
            }
        });
    }

    private void updateLayout(boolean focus) {
        this.setEditable(focus);
        this.getCaret().setVisible(focus);
        if (focus) {
            this.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Constants.DEFAULT_BORDER_COLOR));
            this.selectAll();
        } else {
            this.setBorder(null);
            //this.setBackground(Constants.DEFAULT_BACKGROUND_COLOR);
            this.select(0, 0);
            String newValue = this.getText();
            if (!newValue.equals(this.element.getValue())) {
                //System.out.println("new value: " + newValue);
                this.element.setValue(newValue);
            }
        }
    }

    public MyEditableElement getElement() {
        return element;
    }

    public DiagramTextFieldPopupMenu getPopupMenu() {
        return popupMenu;
    }
}
