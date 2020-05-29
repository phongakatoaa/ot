package model.uml.abstracts;

import model.uml.UMLClass;

public abstract class UMLDirectedRelationship extends UMLRelationship {
    public UMLDirectedRelationship(UMLClass first, UMLClass second) {
        super(first, second);
    }
}
