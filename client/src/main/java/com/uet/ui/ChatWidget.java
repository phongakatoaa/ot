package com.uet.ui;

import com.uet.config.UserConfig;
import com.uet.websocket.ChatMessage;
import com.uet.websocket.MySocketClient;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class ChatWidget extends JPanel {
    private MySocketClient socketClient;
    private final JEditorPane editorPane;
    private final JScrollPane scrollPane;
    private StringBuilder chatLog;

    public ChatWidget() {
        super();
        this.chatLog = new StringBuilder();
        this.setLayout(new BorderLayout());

        this.editorPane = new JEditorPane();
        this.editorPane.setContentType("text/html");
        this.editorPane.putClientProperty(JEditorPane.HONOR_DISPLAY_PROPERTIES, true);
        this.editorPane.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 14));
        this.editorPane.setEditable(false);

        this.scrollPane = new JScrollPane(this.editorPane);
        this.scrollPane.setPreferredSize(new Dimension(300, 600));
        this.add(this.scrollPane, BorderLayout.CENTER);

        JPanel panel = new JPanel();
        JTextField textField = new JTextField();
        textField.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 16));
        textField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    String message = textField.getText();
                    if (!message.isEmpty()) {
                        sendMessage(message);
                    }
                    textField.setText("");
                }
            }
        });
        JButton button = new JButton("Send");
        button.addActionListener(e -> {
            String message = textField.getText();
            if (!message.isEmpty()) {
                sendMessage(message);
            }
            textField.setText("");
        });
        button.setPreferredSize(new Dimension(80, 40));

        panel.setLayout(new BorderLayout());
        panel.add(textField, BorderLayout.CENTER);
        panel.add(button, BorderLayout.EAST);
        this.add(panel, BorderLayout.SOUTH);
    }

    public MySocketClient getSocketClient() {
        return socketClient;
    }

    public void setSocketClient(MySocketClient socketClient) {
        this.socketClient = socketClient;
    }

    public void appendMessage(ChatMessage chatMessage) {
        String color = "red";
        if (chatMessage.getUserId().equals(UserConfig.getInstance().getUserId())) {
            color = "blue";
        }
        this.chatLog.append("<span style=\"color: ").append(color).append(";\">")
                .append(chatMessage.getUserId())
                .append("</span> <span style=\"color: gray; font-style: italic;\">(")
                .append(chatMessage.getTimestamp().substring(chatMessage.getTimestamp().lastIndexOf("T") + 1))
                .append(")</span>: ")
                .append(chatMessage.getMessage())
                .append("<br>");
        this.editorPane.setText(this.chatLog.toString());
        JScrollBar vertical = this.scrollPane.getVerticalScrollBar();
        vertical.setValue(vertical.getMaximum());
    }

    public void sendMessage(String message) {
        socketClient.sendMessage(message);
    }
}
