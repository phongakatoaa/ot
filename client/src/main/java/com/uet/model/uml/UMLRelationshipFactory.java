package com.uet.model.uml;

import com.uet.model.uml.abstracts.UMLRelationship;

public class UMLRelationshipFactory {
    public UMLRelationship createRelationship(UMLRelationshipType type, UMLClass src, UMLClass desc) {
        switch (type) {
            case ASSOCIATION:
                return new UMLAssociation(src, desc);
            case REALIZATION:
                return new UMLRealization(src, desc);
            case GENERALIZATION:
                return new UMLGeneralization(src, desc);
            case DEPENDENCY:
                return new UMLDependency(src, desc);
            default:
                return null;
        }
    }
}
