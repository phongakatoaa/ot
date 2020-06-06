package com.uet.ui;

import org.jdom.Document;
import org.jdom.output.XMLOutputter;
import xmleditorkit.XMLDocument;
import xmleditorkit.XMLEditorKit;

import javax.swing.*;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringReader;

public class XMLViewer extends JEditorPane {

    public XMLViewer() {
        super();
        this.setEditorKit(new XMLEditorKit());
    }

    public void parseXML(File file) throws IOException {
        this.read(new FileReader(file), new XMLDocument());
    }

    public void parseXML(Document document) {
        String output = new XMLOutputter().outputString(document);
        try {
            this.read(new StringReader(output), new XMLDocument());
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error parsing xml document", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
