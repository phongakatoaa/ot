package model.uml;

import model.MyElement;

import java.util.ArrayList;

public class UMLClass extends MyElement {
    private String id;
    private final UMLClassName name;
    private final ArrayList<UMLAttribute> attributes;
    private final ArrayList<UMLOperation> operations;
    private int x;
    private int y;

    public UMLClass(String name) {
        this.name = new UMLClassName(name);
        this.attributes = new ArrayList<>();
        this.operations = new ArrayList<>();
        this.x = 0;
        this.y = 0;
    }

    public UMLClassName getName() {
        return name;
    }

    public void setName(String name) {
        this.name.setValue(name);
    }

    public ArrayList<UMLAttribute> getAttributes() {
        return attributes;
    }

    public ArrayList<UMLOperation> getOperations() {
        return operations;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void addAttribute(UMLAttribute attribute) {
        this.attributes.add(attribute);
    }

    public void removeAttribute(UMLAttribute attribute) {
        this.attributes.remove(attribute);
    }

    public void addOperation(UMLOperation operation) {
        this.operations.add(operation);
    }

    public void removeOperation(UMLOperation operation) {
        this.operations.remove(operation);
    }
}
