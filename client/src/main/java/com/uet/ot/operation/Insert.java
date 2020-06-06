package com.uet.ot.operation;

import org.jdom.Document;
import org.jdom.Element;

public class Insert extends Operation {
    private final Element element;
    public final String parentId;

    public Insert(Element element, String parentId) {
        this.element = element;
        this.parentId = parentId;
    }

    @Override
    public void apply(Document document) {
        Element parent;
        if(parentId == null) {
            parent = document.getRootElement();
        } else {
            parent = elementFinder.find(parentId, document);
        }
        parent.addContent(element);
    }
}
