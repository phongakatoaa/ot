package com.uet.model.uml;

import com.uet.model.uml.abstracts.UMLRelationship;
import com.uet.ot.UMLDocumentControl;
import com.uet.ot.helper.CanvasOperationMapper;
import com.uet.ot.operation.Operation;

import java.util.ArrayList;

public class UMLDiagram {
    private final ArrayList<UMLClass> umlClasses;
    private final ArrayList<UMLRelationship> umlRelationships;
    private final CanvasOperationMapper operationMapper;
    private final UMLDocumentControl control;
    private String name;

    public UMLDiagram(String name) {
        this.name = name;
        this.umlClasses = new ArrayList<>();
        this.umlRelationships = new ArrayList<>();
        this.control = UMLDocumentControl.getInstance();
        this.operationMapper = CanvasOperationMapper.getInstance();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<UMLClass> getUmlClasses() {
        return umlClasses;
    }

    public ArrayList<UMLRelationship> getUmlRelationships() {
        return umlRelationships;
    }

    public void addClass(UMLClass umlClass) {
        this.umlClasses.add(umlClass);
        if (umlClass.getId() == null) {
            Operation operation = operationMapper.addClassEventOperation(umlClass);
            control.applyLocal(operation);
        }
    }

    public void removeClass(UMLClass umlClass) {
        if (this.umlRelationships.size() > 0) {
            this.umlRelationships.forEach(r -> {
                if (r.getSrc().equals(umlClass) || r.getDesc().equals(umlClass)) {
                    Operation operation = operationMapper.deleteElementEventOperation(r);
                    control.applyLocal(operation);
                }
            });
        }
        this.umlRelationships.removeIf(r -> r.getSrc().equals(umlClass) || r.getDesc().equals(umlClass));
        this.umlClasses.remove(umlClass);

        Operation operation = operationMapper.deleteElementEventOperation(umlClass);
        control.applyLocal(operation);
    }

    public void addRelationship(UMLRelationship umlRelationship) {
        this.umlRelationships.add(umlRelationship);
        if (umlRelationship.getId() == null) {
            Operation operation = operationMapper.addRelationshipEventOperation(umlRelationship);
            control.applyLocal(operation);
        }
    }

    public void removeRelationship(UMLRelationship umlRelationship) {
        this.umlRelationships.remove(umlRelationship);

        Operation operation = operationMapper.deleteElementEventOperation(umlRelationship);
        control.applyLocal(operation);
    }

    public boolean relationshipExists(UMLClass src, UMLClass desc) {
        return this.umlRelationships.stream().anyMatch(r -> r.getSrc().equals(src) && r.getDesc().equals(desc));
    }
}
