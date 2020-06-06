package com.uet.model.uml;

import com.uet.model.uml.abstracts.UMLRelationship;

public class UMLGeneralization extends UMLRelationship {
    public UMLGeneralization(UMLClass src, UMLClass desc) {
        super(src, desc, UMLRelationshipType.GENERALIZATION);
    }
}
