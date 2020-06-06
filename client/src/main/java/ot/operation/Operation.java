package ot.operation;

import org.jdom.Document;
import ot.helper.ElementFinder;

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
