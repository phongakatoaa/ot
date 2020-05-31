import model.uml.UMLDiagram;
import parser.MyXMLParserException;
import parser.UMLParser;
import ui.MyFrame;

import java.io.File;
import java.io.IOException;

public class UMLEditorApplication {
    public static void main(String[] args) throws IOException, MyXMLParserException {
        MyFrame frame = new MyFrame();

//        UMLDiagram diagram = new UMLDiagram("example");
//        UMLClass umlClass = new UMLClass("Person");
//        umlClass.setPosition(100, 100);
//        umlClass.addAttribute(new UMLAttribute("name"));
//        umlClass.addAttribute(new UMLAttribute("age"));
//        umlClass.addOperation(new UMLOperation("setName()"));
//        umlClass.addOperation(new UMLOperation("setAge()"));
//        umlClass.addOperation(new UMLOperation("getName()"));
//        umlClass.addOperation(new UMLOperation("getAge()"));
//        umlClass.addOperation(new UMLOperation("toString()"));
//
//        UMLClass umlClass1 = new UMLClass("Car");
//        umlClass1.setPosition(500, 500);
//        umlClass1.addAttribute(new UMLAttribute("brand"));
//        umlClass1.addAttribute(new UMLAttribute("model"));
//        umlClass1.addAttribute(new UMLAttribute("numberOfSeats"));
//        umlClass1.addOperation(new UMLOperation("toString()"));
//
//        diagram.addUMLClass(umlClass);
//        diagram.addUMLClass(umlClass1);
//
//        UMLClassPainter painter = new UMLClassPainter(umlClass);
//        umlClass.setPainter(painter);
//
//        UMLClassPainter painter1 = new UMLClassPainter(umlClass1);
//        umlClass1.setPainter(painter1);

//        UMLAssociation umlAssociation = new UMLAssociation(umlClass, umlClass1);
//        UMLAssociationPainter painter2 = new UMLAssociationPainter(umlAssociation);
//        umlAssociation.setPainter(painter2);
//        diagram.addRelationship(umlAssociation);
//
//        UMLRealization umlRealization = new UMLRealization(umlClass, umlClass1);
//        UMLRealizationPainter painter3 = new UMLRealizationPainter(umlRealization);
//        umlRealization.setPainter(painter3);
//        diagram.addRelationship(umlRealization);
        File file = new File("examples/example_db.xml");
        UMLParser parser = new UMLParser();
        UMLDiagram diagram = parser.parse(file);
        frame.getMyCanvas().setDiagram(diagram);
        frame.setVisible(true);

        frame.getXmlViewer().parseXML(file);
    }
}
