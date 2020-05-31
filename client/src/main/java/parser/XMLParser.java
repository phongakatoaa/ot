package parser;

import model.uml.UMLDiagram;

import java.io.File;

public abstract class XMLParser {
    public abstract UMLDiagram parse(File file) throws MyXMLParserException;
}
