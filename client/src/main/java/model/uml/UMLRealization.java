package model.uml;

import model.uml.abstracts.UMLRelationship;

public class UMLRealization extends UMLRelationship {
    public UMLRealization(UMLClass src, UMLClass desc) {
        super(src, desc, UMLRelationshipType.REALIZATION);
    }
}
