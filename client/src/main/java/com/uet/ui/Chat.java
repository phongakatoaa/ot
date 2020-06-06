package com.uet.ui;

import javax.swing.*;
import java.awt.*;

public class Chat extends JPanel {
    public Chat() {
        super();
        this.setLayout(new BorderLayout());
        this.add(new JLabel("Chat"), BorderLayout.CENTER);
    }
}
