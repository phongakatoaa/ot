package ui;

import javax.swing.*;
import java.awt.*;

public class Canvas extends JPanel {
    public Canvas() {
        super();
        this.setLayout(new BorderLayout());
        JLabel label = new JLabel("Canvas");
        this.add(label, BorderLayout.CENTER);
    }
}
