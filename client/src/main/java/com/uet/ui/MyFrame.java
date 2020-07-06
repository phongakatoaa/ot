package com.uet.ui;

import com.uet.ot.helper.CanvasOperationMapper;

import javax.swing.*;
import java.awt.*;

public class MyFrame extends JFrame {
    private static final String APP_TITLE = "UML Collaborative Editor";

    private final MyMenuBar menuBar;
    private final MyToolbar toolbar;
    private final XMLViewer xmlViewer;
    private final MyCanvas myCanvas;
    private final ChatWidget chatWidget;
    private final OperationLogger operationLogger;

    public MyFrame() {
        super(APP_TITLE);
        this.setExtendedState(MAXIMIZED_BOTH);
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        this.setLayout(new BorderLayout());

        menuBar = new MyMenuBar();
        xmlViewer = new XMLViewer();
        myCanvas = new MyCanvas();
        toolbar = new MyToolbar(myCanvas);
        chatWidget = new ChatWidget();
        operationLogger = new OperationLogger();

        JSplitPane splitPane = new JSplitPane();
        JSplitPane splitPane1 = new JSplitPane(JSplitPane.VERTICAL_SPLIT);

        menuBar.setFrame(this);
        menuBar.setXmlViewer(xmlViewer);
        splitPane.setLeftComponent(new JScrollPane(xmlViewer));
        splitPane.setRightComponent(new JScrollPane(myCanvas));
        splitPane.setResizeWeight(0.15);

        splitPane1.setTopComponent(chatWidget);
        splitPane1.setBottomComponent(operationLogger);
        splitPane1.setResizeWeight(0.15);

        this.setJMenuBar(menuBar);
        this.add(toolbar, BorderLayout.NORTH);
        this.add(splitPane, BorderLayout.CENTER);
        this.add(splitPane1, BorderLayout.EAST);
    }

    public MyToolbar getToolbar() {
        return toolbar;
    }

    public XMLViewer getXmlViewer() {
        return xmlViewer;
    }

    public MyCanvas getMyCanvas() {
        return myCanvas;
    }

    public ChatWidget getChatWidget() {
        return chatWidget;
    }

    public OperationLogger getOperationLogger() {
        return operationLogger;
    }
}
