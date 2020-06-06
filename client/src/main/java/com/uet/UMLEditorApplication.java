package com.uet;

import com.uet.model.uml.UMLDiagram;
import org.jdom2.Document;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import com.uet.ot.UMLDocumentControl;
import com.uet.parser.MyXMLParserException;
import com.uet.parser.UMLParser;
import com.uet.ui.MyFrame;

import java.io.File;
import java.io.IOException;

public class UMLEditorApplication {
    public static void main(String[] args) throws IOException, JDOMException, MyXMLParserException {
        MyFrame frame = new MyFrame();

        File file = new File("examples/example_db.xml");
        SAXBuilder saxBuilder = new SAXBuilder();
        Document document = saxBuilder.build(file);

        UMLDocumentControl.getInstance().setDocument(document);
        UMLDocumentControl.getInstance().setXmlViewer(frame.getXmlViewer());

        UMLParser parser = new UMLParser();
        UMLDiagram diagram = parser.parse(document);

        frame.getMyCanvas().setDiagram(diagram);
        frame.setVisible(true);

        frame.getXmlViewer().parseXML(file);
    }
}
