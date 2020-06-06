package com.uet.ot.operation;

import org.jdom2.Document;
import com.uet.ot.helper.ElementFinder;

public abstract class Operation {
    protected ElementFinder elementFinder;

    public abstract void apply(Document document);

    public ElementFinder getElementFinder() {
        return elementFinder;
    }

    public void setElementFinder(ElementFinder elementFinder) {
        this.elementFinder = elementFinder;
    }
}
