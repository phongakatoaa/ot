package com.uet.ui;

import com.uet.config.UserConfig;
import com.uet.ot.operation.Operation;

import javax.swing.*;
import java.awt.*;

public class OperationLogger extends JPanel {
    private final JEditorPane editorPane;
    private final JScrollPane scrollPane;
    private final StringBuilder opLog;

    public OperationLogger() {
        super();
        this.setLayout(new BorderLayout());

        this.editorPane = new JEditorPane();
        this.editorPane.setContentType("text/html");
        this.editorPane.putClientProperty(JEditorPane.HONOR_DISPLAY_PROPERTIES, true);
        this.editorPane.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 14));
        this.editorPane.setEditable(false);

        this.scrollPane = new JScrollPane(this.editorPane);
        this.scrollPane.setPreferredSize(new Dimension(300, 600));
        this.add(this.scrollPane, BorderLayout.CENTER);

        this.opLog = new StringBuilder();
        this.add(this.scrollPane, BorderLayout.CENTER);
    }

    public void appendOperation(Operation operation) {
        String color = "blue";
        if (!operation.getUserId().equals(UserConfig.getInstance().getUserId())) {
            color = "red";
        }

        this.opLog.append("<span style=\"color: ").append(color).append(";\">")
                .append(operation.getUserId())
                .append("</span> <span style=\"color: gray; font-style: italic;\">(")
                .append(operation.getTimestamp().substring(operation.getTimestamp().lastIndexOf("T") + 1))
                .append(")</span>: ")
                .append(operation.getClass().getSimpleName())
                .append("<br>");
        this.editorPane.setText(this.opLog.toString());
        JScrollBar vertical = this.scrollPane.getVerticalScrollBar();
        vertical.setValue(vertical.getMaximum());
    }
}
