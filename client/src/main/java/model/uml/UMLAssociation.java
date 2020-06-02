package model.uml;

import model.uml.abstracts.UMLRelationship;

public class UMLAssociation extends UMLRelationship {
    public UMLAssociation(UMLClass src, UMLClass desc) {
        super(src, desc, UMLRelationshipType.ASSOCIATION);
    }
}
