package model.uml;

import model.Element;

import java.util.ArrayList;

public class UMLClass extends Element {
    private String name;
    private ArrayList<String> attributes;
    private ArrayList<String> operations;
    private int x;
    private int y;

    public UMLClass(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<String> getAttributes() {
        return attributes;
    }

    public void setAttributes(ArrayList<String> attributes) {
        this.attributes = attributes;
    }

    public ArrayList<String> getOperations() {
        return operations;
    }

    public void setOperations(ArrayList<String> operations) {
        this.operations = operations;
    }
}
