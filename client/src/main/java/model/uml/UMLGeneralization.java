package model.uml;

import model.uml.abstracts.UMLRelationship;

public class UMLGeneralization extends UMLRelationship {
    public UMLGeneralization(UMLClass src, UMLClass desc) {
        super(src, desc, UMLRelationshipType.GENERALIZATION);
    }
}
