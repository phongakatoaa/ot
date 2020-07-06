package com.uet.model.uml;

import com.uet.model.MyElement;
import com.uet.ot.UMLDocumentControl;
import com.uet.ot.helper.CanvasOperationMapper;
import com.uet.ot.operation.Operation;

import java.util.ArrayList;

public class UMLClass extends MyElement {
    private final UMLClassName name;
    private final ArrayList<UMLAttribute> attributes;
    private final ArrayList<UMLOperation> operations;
    private int x;
    private int y;

    public UMLClass(String name) {
        this.name = new UMLClassName(name, this);
        this.attributes = new ArrayList<>();
        this.operations = new ArrayList<>();
        this.x = 0;
        this.y = 0;
    }

//    public UMLClass(Element element) {
//        this.name = new UMLClassName(element.getAttributeValue("name"));
//        this.id = element.getAttributeValue("id");
//        this.x = Integer.parseInt(element.getAttributeValue("x"));
//        this.y = Integer.parseInt(element.getAttributeValue("y"));
//        this.attributes = new ArrayList<>();
//        this.operations = new ArrayList<>();
//
//        List<Element> attributeElements = element.getChildren("attribute");
//        attributeElements.forEach(el -> attributes.add(new UMLAttribute(el.getAttributeValue("id"), el.getValue())));
//
//        List<Element> operationElements = element.getChildren("operation");
//        operationElements.forEach(el -> operations.add(new UMLOperation(el.getAttributeValue("id"), el.getValue())));
//    }

    public UMLClassName getName() {
        return name;
    }

    public void setName(String name) {
        this.name.setValue(name);

        if (this.id != null) {
            Operation operation = CanvasOperationMapper.getInstance().updateClassEventOperation(this);
            UMLDocumentControl.getInstance().applyLocal(operation);
        }
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

    public int getY() {
        return y;
    }

    public void setPosition(int x, int y, boolean logOperation) {
        //boolean shifted = (x != this.x || y != this.y);
        this.x = x;
        this.y = y;

        if (this.id != null && logOperation) {
            Operation operation = CanvasOperationMapper.getInstance().updateClassEventOperation(this);
            UMLDocumentControl.getInstance().applyLocal(operation);
        }
    }

    public void addAttribute(UMLAttribute attribute) {
        this.attributes.add(attribute);
        if (attribute.getId() == null) {
            Operation operation = CanvasOperationMapper.getInstance().addAttributeEventOperation(attribute, this);
            UMLDocumentControl.getInstance().applyLocal(operation);
        }
    }

    public void removeAttribute(UMLAttribute attribute) {
        this.attributes.remove(attribute);

        Operation operation = CanvasOperationMapper.getInstance().deleteElementEventOperation(attribute);
        UMLDocumentControl.getInstance().applyLocal(operation);
    }

    public void addOperation(UMLOperation umlOperation) {
        this.operations.add(umlOperation);
        if (umlOperation.getId() == null) {
            Operation operation = CanvasOperationMapper.getInstance().addOperationEventOperation(umlOperation, this);
            UMLDocumentControl.getInstance().applyLocal(operation);
        }
    }

    public void removeOperation(UMLOperation umlOperation) {
        this.operations.remove(umlOperation);

        Operation operation = CanvasOperationMapper.getInstance().deleteElementEventOperation(umlOperation);
        UMLDocumentControl.getInstance().applyLocal(operation);
    }
}
