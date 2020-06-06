package com.uet.ot.helper;

import org.jdom.Document;
import org.jdom.Element;

import java.util.List;

public class UMLElementFinder extends ElementFinder {
    private static final String UNDERSCORE = "_";

    @Override
    public Element find(String id, Document document) {
        String prefix = id.substring(0, id.lastIndexOf(UNDERSCORE));
        switch (prefix) {
            case "c":
                return findClass(id, document);
            case "a":
                return findAttribute(id, document);
            case "o":
                return findOperation(id, document);
            case "r":
                return findRelationship(id, document);
            default:
                return null;
        }
    }

    private Element findClass(String id, Document document) {
        List<Element> classes = document.getRootElement().getChildren("class");
        for (Element c : classes) {
            if (c.getAttributeValue("id").equals(id)) {
                return c;
            }
        }
        return null;
    }

    private Element findAttribute(String id, Document document) {
        List<Element> classes = document.getRootElement().getChildren("class");
        for (Element c : classes) {
            List<Element> attributes = c.getChildren("attribute");
            for (Element a : attributes) {
                if (a.getAttributeValue("id").equals(id)) {
                    return a;
                }
            }
        }
        return null;
    }

    private Element findOperation(String id, Document document) {
        List<Element> classes = document.getRootElement().getChildren("class");
        for (Element c : classes) {
            List<Element> operations = c.getChildren("operation");
            for (Element o : operations) {
                if (o.getAttributeValue("id").equals(id)) {
                    return o;
                }
            }
        }
        return null;
    }

    private Element findRelationship(String id, Document document) {
        List<Element> relationships = document.getRootElement().getChildren("relationship");
        for (Element r : relationships) {
            if (r.getAttributeValue("id").equals(id)) {
                return r;
            }
        }
        return null;
    }
}
