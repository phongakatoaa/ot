package com.uet.ot.operation;

import com.uet.ot.helper.ElementFinder;
import org.jdom2.Document;
import org.jdom2.Element;

import java.util.HashMap;

public class Insert extends Operation {
    private final int position;
    private final String parentId;

    private final String name;
    private final String text;
    private final HashMap<String, String> keyValuePairs;

    public Insert(String userId, String timestamp, String name, String text, HashMap<String, String> keyValuePairs, int position, String parentId) {
        super(userId, timestamp, "insert");
        this.position = position;
        this.parentId = parentId;

        this.name = name;
        this.text = text;
        this.keyValuePairs = keyValuePairs;
    }

    public int getPosition() {
        return position;
    }

    public String getParentId() {
        return parentId;
    }

    public String getName() {
        return name;
    }

    public String getText() {
        return text;
    }

    public HashMap<String, String> getKeyValuePairs() {
        return keyValuePairs;
    }

    @Override
    public String toString() {
        return "Insert{" +
                "position=" + position +
                ", parentId='" + parentId + '\'' +
                ", name='" + name + '\'' +
                ", text='" + text + '\'' +
                ", keyValuePairs=" + keyValuePairs +
                '}';
    }

    @Override
    public void apply(Document document, ElementFinder elementFinder) {
        Element parent;
        if (parentId == null) {
            parent = document.getRootElement();
        } else {
            parent = elementFinder.find(parentId, document);
        }

        Element newElement = new Element(name);
        newElement.setText(text);
        keyValuePairs.keySet().forEach(k -> newElement.setAttribute(k, keyValuePairs.get(k)));

        if (position > -1) {
            parent.addContent(position, newElement);
        } else {
            parent.addContent(newElement);
        }
    }
}
