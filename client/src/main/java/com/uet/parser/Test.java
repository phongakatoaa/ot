package com.uet.parser;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

import java.io.IOException;

public class Test {
    public static void main(String[] args) throws IOException {
        Document document = new Document();
        Element element = new Element("student");
        element.setAttribute("name", "Student 1");
        Element element1 = new Element("student");
        element1.setAttribute("name", "Student 2");
        Element element2 = new Element("student");
        element2.setAttribute("name", "Student 3");
        Element element3 = new Element("student");
        element3.setAttribute("name", "Ha Tuan Phong");

        document.setRootElement(new Element("students"));
        document.getRootElement().addContent(element);
        document.getRootElement().addContent(element1);
        document.getRootElement().addContent(element2);
        document.getRootElement().addContent(element3);

        XMLOutputter xmlOutput = new XMLOutputter();
        xmlOutput.setFormat(Format.getPrettyFormat());
        xmlOutput.output(document, System.out);
    }
}
