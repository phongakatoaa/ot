package com.uet.ot.operation;

import com.uet.ot.helper.ElementFinder;
import org.jdom2.Document;
import org.jdom2.Element;

import java.util.HashMap;

public class Update extends Operation {
    private final String nodeId;
    private final String text;
    private final HashMap<String, String> keyValuePairs;

    public Update(String userId, String timestamp, String nodeId, String text, HashMap<String, String> keyValuePairs) {
        super(userId, timestamp, "update");
        this.nodeId = nodeId;
        this.text = text;
        this.keyValuePairs = keyValuePairs;
    }

    public String getNodeId() {
        return nodeId;
    }

    public String getText() {
        return text;
    }

    public HashMap<String, String> getKeyValuePairs() {
        return keyValuePairs;
    }

    @Override
    public String toString() {
        return "Update{" +
                "nodeId='" + nodeId + '\'' +
                ", text='" + text + '\'' +
                ", keyValuePairs=" + keyValuePairs +
                '}';
    }

    @Override
    public void apply(Document document, ElementFinder elementFinder) {
        Element element = elementFinder.find(nodeId, document);
        if (text != null) {
            element.setText(text);
        }
        if (keyValuePairs != null) {
            keyValuePairs.keySet().forEach(k -> element.setAttribute(k, keyValuePairs.get(k)));
        }
    }
}
