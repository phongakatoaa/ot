package com.uet.ot.operation;

import com.uet.ot.helper.ElementFinder;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.Parent;

public class Delete extends Operation {
    private final String nodeId;

    public Delete(String userId, String timestamp, String nodeId) {
        super(userId, timestamp, "delete");
        this.nodeId = nodeId;
    }

    public String getNodeId() {
        return nodeId;
    }

    @Override
    public String toString() {
        return "Delete{" +
                "nodeId='" + nodeId + '\'' +
                '}';
    }

    @Override
    public void apply(Document document, ElementFinder elementFinder) {
        Element element = elementFinder.find(nodeId, document);
        Parent parent = element.getParent();
        parent.removeContent(element);
    }
}
