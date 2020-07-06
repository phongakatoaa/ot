package com.uet.ui;

import javax.swing.*;
import java.awt.*;

public class UserConfigDialog extends JPanel {
    private JTextField tfUserId;
    private JTextField tfChannelId;
    private JTextField tfHost;

    public UserConfigDialog() {
        super();
        this.setLayout(new GridLayout(3, 3));

        JLabel labelUserId = new JLabel("User ID");
        JLabel labelChannelId = new JLabel("Channel ID");
        JLabel labelHost = new JLabel("Host");
        tfUserId = new JTextField();
        tfChannelId = new JTextField();
        tfHost = new JTextField();

        this.add(labelUserId);
        this.add(tfUserId);
        this.add(labelChannelId);
        this.add(tfChannelId);
        this.add(labelHost);
        this.add(tfHost);
    }

    public String getUserId() {
        return tfUserId.getText();
    }

    public String getChannelId() {
        return tfChannelId.getText();
    }

    public String getHost() {
        return tfHost.getText();
    }
}
