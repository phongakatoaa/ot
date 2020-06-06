package com.uet.ot.operation;

import org.jdom2.Document;
import org.jdom2.Element;

import java.util.HashMap;

public class Update extends Operation {
    private final String nodeId;
    private final String text;
    private final HashMap<String, String> keyValuePairs;

    public Update(String nodeId, String text, HashMap<String, String> keyValuePairs) {
        this.nodeId = nodeId;
        this.text = text;
        this.keyValuePairs = keyValuePairs;
    }

    @Override
    public void apply(Document document) {
        Element element = elementFinder.find(nodeId, document);
        if (text != null) {
            element.setText(text);
        }
        if (keyValuePairs != null) {
            keyValuePairs.keySet().forEach(k -> {
//                System.out.println(k);
//                System.out.println(keyValuePairs.get(k));
                element.setAttribute(k, keyValuePairs.get(k));
            });
        }
    }
}
