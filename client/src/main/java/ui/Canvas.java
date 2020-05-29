package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Canvas extends JPanel {
    public Canvas() {
        super();
        this.setLayout(null);
        JTextField textField = new JTextField();
        textField.setEditable(false);
        textField.setText("abc");
        textField.setBounds(100 + (160 - 120) / 2, 122, 120, 16);
        textField.setBorder(null);
        //textField.setBackground(null);
        textField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                textField.setEditable(true);
                textField.getCaret().setVisible(true);
                textField.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK));
                textField.selectAll();
            }

            @Override
            public void focusLost(FocusEvent e) {
                textField.setEditable(false);
                textField.setBorder(null);
            }
        });

        textField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_ENTER) {
                    textField.setEditable(false);
                    textField.transferFocus();
                }
            }
        });

        this.add(textField);
    }

    private void drawDiagram(Graphics g) {
        Graphics2D graphics2D = (Graphics2D) g;
        g.drawRect(100, 100, 160, 120);
        g.drawLine(100, 140, 260, 140);
        //textField.paint(g);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        this.drawDiagram(g);
    }
}
