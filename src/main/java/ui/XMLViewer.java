package ui;

import xmleditorkit.XMLDocument;
import xmleditorkit.XMLEditorKit;

import javax.swing.*;
import java.awt.*;
import java.io.FileInputStream;
import java.io.IOException;

public class XMLViewer extends JPanel {
    private JEditorPane editorPane;

    public XMLViewer() {
        super();
        editorPane = new JEditorPane();
        editorPane.setEditorKit(new XMLEditorKit());

        JScrollPane scrollPane = new JScrollPane(editorPane, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        this.add(scrollPane);
        this.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, Color.black));
    }

    public void read(String filePath) throws IOException {
        editorPane.read(new FileInputStream(filePath), new XMLDocument());
    }
}
