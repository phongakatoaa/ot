package ui;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class MyFrame extends JFrame {
    private static final String APP_TITLE = "UML Collaborative Editor";

    public MyFrame() throws IOException {
        super(APP_TITLE);
        this.setExtendedState(MAXIMIZED_BOTH);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());
        //this.setUndecorated(true);
        JMenuBar menubar = new MyMenuBar();
        this.setJMenuBar(menubar);

        JToolBar toolBar = new MyToolbar();
        this.add(toolBar, BorderLayout.PAGE_START);

        XMLViewer xmlViewer = new XMLViewer();
        xmlViewer.read("examples/book.xml");
        this.add(xmlViewer, BorderLayout.LINE_START);

        Canvas canvas = new Canvas();
        this.add(canvas, BorderLayout.CENTER);

        Chat chat = new Chat();
        this.add(chat, BorderLayout.LINE_END);
    }
}
