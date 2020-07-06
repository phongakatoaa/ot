package com.uet.parser;

import com.uet.model.uml.*;
import com.uet.model.uml.abstracts.UMLRelationship;
import org.jdom2.Document;
import org.jdom2.Element;
import com.uet.painter.uml.UMLPainterFactory;

import java.util.List;

public class UMLParser extends XMLParser {
    private final UMLPainterFactory painterFactory;

    public UMLParser() {
        painterFactory = new UMLPainterFactory();
    }

    @Override
    public UMLDiagram parse(Document doc) throws MyXMLParserException {
        try {
            Element rootElement = doc.getRootElement();
            UMLDiagram diagram = new UMLDiagram(rootElement.getAttributeValue("name"));

            List<Element> classElements = rootElement.getChildren("class");
            for (Element c : classElements) {
                diagram.addClass(this.parseClass(c));
            }

            List<Element> relationshipElements = rootElement.getChildren("relationship");
            for (Element r : relationshipElements) {
                diagram.addRelationship(this.parseRelationship(r, diagram));
            }

            return diagram;
        } catch (Exception e) {
            e.printStackTrace();
            throw new MyXMLParserException(e.getMessage());
        }
    }

    private UMLClass parseClass(Element element) {
        UMLClass umlClass = new UMLClass(element.getAttributeValue("name"));
        umlClass.setId(element.getAttributeValue("id"));
        int x = Integer.parseInt(element.getAttributeValue("x"));
        int y = Integer.parseInt(element.getAttributeValue("y"));
        umlClass.setPosition(x, y, false);

        List<Element> attributeElements = element.getChildren("attribute");
        attributeElements.forEach(el -> umlClass.addAttribute(new UMLAttribute(el.getAttributeValue("id"), el.getValue())));

        List<Element> operationElements = element.getChildren("operation");
        operationElements.forEach(el -> umlClass.addOperation(new UMLOperation(el.getAttributeValue("id"), el.getValue())));

        painterFactory.createPainter(umlClass);
        return umlClass;
    }

    private UMLRelationship parseRelationship(Element element, UMLDiagram diagram) throws MyXMLParserException {
        UMLRelationship relationship;
        String srcId = element.getAttributeValue("source");
        String descId = element.getAttributeValue("destination");
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
            case "dependency":
                relationship = new UMLDependency(src, desc);
                break;
            default:
                throw new MyXMLParserException("relationship type not found");
        }
        relationship.setId(element.getAttributeValue("id"));
        painterFactory.createPainter(relationship);
        return relationship;
    }
}
