package com.uet.ui;

import javax.swing.*;
import java.awt.*;

public class UserConfigDialog extends JPanel {
    private JTextField tfUserId;
    private JTextField tfChannelId;
    private JTextField tfHost;
    private JTextField tfDelay;

    public UserConfigDialog() {
        super();
        this.setLayout(new GridLayout(3, 3));

        JLabel labelUserId = new JLabel("User ID");
        JLabel labelChannelId = new JLabel("Channel ID");
        JLabel labelHost = new JLabel("Host");
        JLabel labelDelay = new JLabel("Delay milliseconds");
        tfUserId = new JTextField();
        tfChannelId = new JTextField();
        tfChannelId.setText("1");
        tfHost = new JTextField();
        tfHost.setText("localhost");
        tfDelay = new JTextField();
        tfDelay.setText("0");

        this.add(labelUserId);
        this.add(tfUserId);
        this.add(labelChannelId);
        this.add(tfChannelId);
        this.add(labelHost);
        this.add(tfHost);
        this.add(labelDelay);
        this.add(tfDelay);
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

    public int getDelay() {
        return Integer.parseInt(tfDelay.getText());
    }
}
