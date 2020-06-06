package ot.helper;

import org.jdom.Document;
import org.jdom.Element;

public abstract class ElementFinder {
    public abstract Element find(String id, Document document);
}
