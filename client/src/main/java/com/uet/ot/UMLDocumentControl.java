package com.uet.ot;

import org.jdom2.Document;
import com.uet.ot.helper.UMLElementFinder;
import com.uet.ot.operation.Operation;
import com.uet.ui.XMLViewer;

public class UMLDocumentControl {
    private static final UMLDocumentControl instance = new UMLDocumentControl();

    private XMLViewer xmlViewer;
    private Document document;
    private final UMLElementFinder finder;

    private UMLDocumentControl() {
        finder = new UMLElementFinder();
    }

    public static UMLDocumentControl getInstance() {
        return instance;
    }

    public Document getDocument() {
        return document;
    }

    public XMLViewer getXmlViewer() {
        return xmlViewer;
    }

    public void setXmlViewer(XMLViewer xmlViewer) {
        this.xmlViewer = xmlViewer;
    }

    public void setDocument(Document document) {
        this.document = document;
    }

    public void applyOperation(Operation operation) {
        operation.setElementFinder(finder);
        operation.apply(document);
        xmlViewer.parseXML(document);
    }
}

