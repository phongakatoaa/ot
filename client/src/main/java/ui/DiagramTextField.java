package ui;

import model.EditableElement;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class DiagramTextField extends JTextField {

    private final EditableElement element;

    public DiagramTextField(EditableElement element) {
        super();
        this.element = element;
        this.setText(element.getValue());
        bind();
    }

    private void bind() {
        this.setEditable(false);
        this.setBorder(null);
        this.setBackground(Constants.DEFAULT_BACKGROUND_COLOR);
        this.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                DiagramTextField textField = (DiagramTextField) e.getComponent();
                textField.setEditable(true);
                textField.getCaret().setVisible(true);
                textField.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Constants.DEFAULT_BORDER_COLOR));
                textField.selectAll();
            }

            @Override
            public void focusLost(FocusEvent e) {
                DiagramTextField textField = (DiagramTextField) e.getComponent();
                textField.setEditable(false);
                textField.setBorder(null);
                textField.setBackground(Constants.DEFAULT_BACKGROUND_COLOR);
                textField.onValueChange(textField.getText());
            }
        });

        this.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER || e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    DiagramTextField textField = (DiagramTextField) e.getComponent();
                    textField.setEditable(false);
                    textField.transferFocus();
                    textField.onValueChange(textField.getText());
                }
            }
        });

        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                setCursor(new Cursor(Cursor.TEXT_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
            }
        });
    }

    private void onValueChange(String newValue) {
        if (!newValue.equals(this.element.getValue())) {
            this.element.setValue(newValue);
        }
    }
}
