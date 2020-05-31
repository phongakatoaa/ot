package parser;

import model.uml.*;
import model.uml.abstracts.UMLRelationship;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;
import painter.uml.UMLPainterFactory;

import java.io.File;
import java.util.List;

public class UMLParser extends XMLParser {
    private final UMLPainterFactory painterFactory;

    public UMLParser() {
        painterFactory = new UMLPainterFactory();
    }

    @Override
    public UMLDiagram parse(File file) throws MyXMLParserException {
        try {
            UMLPainterFactory painterFactory = new UMLPainterFactory();
            SAXBuilder saxBuilder = new SAXBuilder();
            Document doc = saxBuilder.build(file);

            Element rootElement = doc.getRootElement();
            UMLDiagram diagram = new UMLDiagram(rootElement.getAttributeValue("name"));

            List<Element> classElements = rootElement.getChild("classes").getChildren("class");
            for (Element c : classElements) {
                diagram.addUMLClass(this.parseClass(c));
            }

            List<Element> relationshipElements = rootElement.getChild("relationships").getChildren("relationship");
            for (Element r : relationshipElements) {
                diagram.addRelationship(this.parseRelationship(r, diagram));
            }

            return diagram;
        } catch (Exception e) {
            throw new MyXMLParserException(e.getMessage());
        }
    }

    private UMLClass parseClass(Element element) {
        UMLClass umlClass = new UMLClass(element.getAttributeValue("name"));
        umlClass.setId(element.getAttributeValue("id"));
        int x = Integer.parseInt(element.getAttributeValue("x"));
        int y = Integer.parseInt(element.getAttributeValue("y"));
        umlClass.setPosition(x, y);

        List<Element> attributeElements = element.getChild("attributes").getChildren("attribute");
        attributeElements.forEach(el -> umlClass.addAttribute(new UMLAttribute(el.getValue())));

        List<Element> operationElements = element.getChild("operations").getChildren("operation");
        operationElements.forEach(el -> umlClass.addOperation(new UMLOperation(el.getValue())));

        painterFactory.createPainter(umlClass);
        return umlClass;
    }

    private UMLRelationship parseRelationship(Element element, UMLDiagram diagram) throws MyXMLParserException {
        UMLRelationship relationship;
        String srcId = element.getChild("source").getValue();
        String descId = element.getChild("destination").getValue();
        UMLClass src = null;
        UMLClass desc = null;
        for (UMLClass c : diagram.getUmlClasses()) {
            if (c.getId().equals(srcId)) {
                src = c;
            } else if (c.getId().equals(descId)) {
                desc = c;
            }
        }
        if (src == null || desc == null) {
            throw new MyXMLParserException("src or desc class not found");
        }
        switch (element.getAttributeValue("type")) {
            case "generalization":
                relationship = new UMLGeneralization(src, desc);
                break;
            case "realization":
                relationship = new UMLRealization(src, desc);
                break;
            case "association":
                relationship = new UMLAssociation(src, desc);
                break;
            default:
                throw new MyXMLParserException("relationship type not found");
        }

        painterFactory.createPainter(relationship);
        return relationship;
    }
}
