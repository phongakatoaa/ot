package model.uml;

import model.uml.abstracts.UMLRelationship;

public class UMLAssociation extends UMLRelationship {
    public UMLAssociation(UMLClass first, UMLClass second) {
        super(first, second);
    }
}
