package com.uet.parser;

import com.uet.model.uml.UMLDiagram;
import org.jdom.Document;

public abstract class XMLParser {
    public abstract UMLDiagram parse(Document document) throws MyXMLParserException;
}
