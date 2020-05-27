package ui;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class MyFrame extends JFrame {
    private static final String APP_TITLE = "UML Collaborative Editor";

    public MyFrame() {
        super(APP_TITLE);
        this.setExtendedState(MAXIMIZED_BOTH);
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        this.setLayout(new BorderLayout());

        MyMenuBar menubar = new MyMenuBar();
        MyToolbar toolBar = new MyToolbar();
        XMLViewer xmlViewer = new XMLViewer();
        Canvas canvas = new Canvas();
        JSplitPane splitPane = new JSplitPane();
        Chat chat = new Chat();

        menubar.setFrame(this);
        menubar.setXmlViewer(xmlViewer);
        splitPane.setLeftComponent(new JScrollPane(xmlViewer));
        splitPane.setRightComponent(canvas);

        this.setJMenuBar(menubar);
        this.add(toolBar, BorderLayout.PAGE_START);
        this.add(splitPane, BorderLayout.CENTER);
        this.add(chat, BorderLayout.LINE_END);
    }
}
