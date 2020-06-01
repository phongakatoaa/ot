package model.uml.abstracts;

import model.uml.UMLClass;

public abstract class UMLBidirectionalRelationship extends UMLRelationship {
    public UMLBidirectionalRelationship(UMLClass first, UMLClass second) {
        super(first, second);
    }
}
