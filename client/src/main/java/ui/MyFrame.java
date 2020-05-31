package ui;

import javax.swing.*;
import java.awt.*;

public class MyFrame extends JFrame {
    private static final String APP_TITLE = "UML Collaborative Editor";
    private final MyMenuBar menuBar;
    private final MyToolbar toolbar;
    private final XMLViewer xmlViewer;
    private final JScrollPane xmlViewerScrollPane;
    private final MyCanvas myCanvas;
    private final Chat chat;

    public MyFrame() {
        super(APP_TITLE);
        this.setExtendedState(MAXIMIZED_BOTH);
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        this.setLayout(new BorderLayout());

        menuBar = new MyMenuBar();
        xmlViewer = new XMLViewer();
        myCanvas = new MyCanvas();
        toolbar = new MyToolbar(myCanvas);
        chat = new Chat();
        xmlViewerScrollPane = new JScrollPane(xmlViewer);

        JSplitPane splitPane = new JSplitPane();
        JSplitPane subSplitPane = new JSplitPane();

        menuBar.setFrame(this);
        menuBar.setXmlViewer(xmlViewer);
        splitPane.setLeftComponent(xmlViewerScrollPane);
        splitPane.setRightComponent(myCanvas);
        splitPane.setResizeWeight(0.15);

        this.setJMenuBar(menuBar);
        this.add(toolbar, BorderLayout.PAGE_START);
        this.add(splitPane, BorderLayout.CENTER);
        this.add(chat, BorderLayout.LINE_END);
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

    public Chat getChat() {
        return chat;
    }

    public JScrollPane getXmlViewerScrollPane() {
        return xmlViewerScrollPane;
    }
}
