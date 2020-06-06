package model.uml;

import model.uml.abstracts.UMLRelationship;
import ot.UMLDocumentControl;
import ot.helper.CanvasEventToOperationMapper;
import ot.operation.Operation;

import java.util.ArrayList;

public class UMLDiagram {
    private final ArrayList<UMLClass> umlClasses;
    private final ArrayList<UMLRelationship> umlRelationships;
    private final CanvasEventToOperationMapper operationMapper;
    private final UMLDocumentControl control;
    private String name;

    public UMLDiagram(String name) {
        this.name = name;
        this.umlClasses = new ArrayList<>();
        this.umlRelationships = new ArrayList<>();
        this.control = UMLDocumentControl.getInstance();
        this.operationMapper = CanvasEventToOperationMapper.getInstance();
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
            control.applyOperation(operation);
        }
    }

    public void removeClass(UMLClass umlClass) {
        if (this.umlRelationships.size() > 0) {
            this.umlRelationships.forEach(r -> {
                if (r.getSrc().equals(umlClass) || r.getDesc().equals(umlClass)) {
                    Operation operation = operationMapper.deleteElementEventOperation(r);
                    control.applyOperation(operation);
                }
            });
        }
        this.umlRelationships.removeIf(r -> r.getSrc().equals(umlClass) || r.getDesc().equals(umlClass));
        this.umlClasses.remove(umlClass);

        Operation operation = operationMapper.deleteElementEventOperation(umlClass);
        control.applyOperation(operation);
    }

    public void addRelationship(UMLRelationship umlRelationship) {
        this.umlRelationships.add(umlRelationship);
        if (umlRelationship.getId() == null) {
            Operation operation = operationMapper.addRelationshipEventOperation(umlRelationship);
            control.applyOperation(operation);
        }
    }

    public void removeRelationship(UMLRelationship umlRelationship) {
        this.umlRelationships.remove(umlRelationship);

        Operation operation = operationMapper.deleteElementEventOperation(umlRelationship);
        control.applyOperation(operation);
    }

    public boolean relationshipExists(UMLClass src, UMLClass desc) {
        return this.umlRelationships.stream().anyMatch(r -> r.getSrc().equals(src) && r.getDesc().equals(desc));
    }
}
