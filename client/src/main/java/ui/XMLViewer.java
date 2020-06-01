package ui;

import xmleditorkit.XMLDocument;
import xmleditorkit.XMLEditorKit;

import javax.swing.*;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class XMLViewer extends JEditorPane {
    public XMLViewer() {
        super();
        this.setEditorKit(new XMLEditorKit());
    }

    public void parseXML(File file) throws IOException {
        this.read(new FileReader(file), new XMLDocument());
    }
}
