import model.uml.UMLDiagram;
import org.jdom.Document;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import ot.UMLDocumentControl;
import parser.MyXMLParserException;
import parser.UMLParser;
import ui.MyFrame;

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
