package com.uet.ot.helper;

import org.jdom2.Document;
import org.jdom2.Element;

public abstract class ElementFinder {
    public abstract Element find(String id, Document document);
}
