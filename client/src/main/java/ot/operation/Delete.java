package ot.operation;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.Parent;

public class Delete extends Operation {
    private final String nodeId;

    public Delete(String nodeId) {
        this.nodeId = nodeId;
    }

    @Override
    public void apply(Document document) {
        Element element = elementFinder.find(nodeId, document);
        Parent parent = element.getParent();
        parent.removeContent(element);
    }
}
